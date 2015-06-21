package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import Entities.Block;
import Entities.Character;
import Entities.DepthComparator;
import Entities.EntitieComparator;
import Entities.Entities;
import Entities.Blocks.LightBlock;
import Prog.Action;
import Prog.Coordation;
import Prog.Coordonnees;
import Prog.Procedure;
import UI.Graphic;

/**
 * TODO
 *
 */
public class World
{
	public static World WORLD = null;
	
	private List<Sprite> frontList = new ArrayList<Sprite>(); //Sprite � dessiner en dernier (les bulles des chests)
	private List<Block> blockList = new ArrayList<Block>(); //Liste des blocks
	private List<Character> characterList = new ArrayList<Character>(); //Liste des personnages
	private List<Coordation> cloneList = new ArrayList<Coordation>(); //Liste des coordonnes des clones
	private List<Entities> allList = new ArrayList<Entities>(); //Listes de tous les objets
	
	private List<Block> blockListStart; // Liste de block qui sera fournie par le reader
	private Coordonnees coordStart; //Coordonnees du depart
	private int orientStart; //Orientation du depart
	private List<Procedure> listSoluce;
	
	private Vector2f centerWorld = null;
	
	private boolean playing = false;
	
	private String name; // Nom du niveau
	private int minStar, maxStar; // Rang pour le scoring
	
	private List<Action> actionList; // Liste d'actions utilisables
	
	/**
	 * Constructeur de niveau
	 * @param n Le nom du niveau
	 * @param lb La liste des blocks du niveau
	 * @param cStart Les coordonnees de depart du personnage
	 * @param oStart L'orientation de depart du personnage
	 */
	public World(String n, List<Block> lb, Coordonnees cStart, int oStart, 
			List<Action> allowedActions, int minStar, int maxStar, List<Procedure> ls) 
	{
		name = n;
		coordStart = cStart;
		orientStart = oStart;
		actionList = allowedActions;
		blockListStart = lb;
		this.minStar = minStar;
		this.maxStar = maxStar;
		listSoluce = ls;
		
		WORLD = this;
	}
	
	public void initialiser()
	{
		Interpreter.INTERPRETER = new Interpreter();
		Controler.CONTROLER = new Controler(); 
		
		frontList.clear();
		allList.clear();
		blockList.clear();
		characterList.clear();
		cloneList.clear();
		
		for (Block b : blockListStart)
			try 
			{
				Block bl = (Block) b.clone();
				bl.initialiser();
				
				addBlock(bl);
			}
			catch (CloneNotSupportedException e) {e.printStackTrace();}

		addCharacter(new Character(new Coordonnees(coordStart), orientStart));
		
		for (Block b : blockList)
			b.setPosSprite(placeMe(b.getCoord()));
		characterList.get(0).setPosSprite(placeMe(coordStart));
		
		Graphic.SFML.setCenterCamera(getCenterWorld());
	}
	
	/**
	 * Gerer le monde (animation du monde)
	 */
	public void gerer()
	{
		for (Character p : characterList)
			p.gerer();
	}
	
	/**
	 * Gere la creation des clones
	 */
	public void popClone()
	{
		for (int i=0; i < cloneList.size(); i++)
			if (isValidPosition(cloneList.get(i).getCoord()))
			{
				Character p = new Character(new Coordonnees(cloneList.get(i).getCoord()),
						cloneList.get(i).getOrientation());
				p.setPosSprite(placeMe(p.getCoord()));
					
				addCharacter(p);
				p.setMain();
				
				cloneList.remove(i);
				i--;
			}
	}
	
	/**
	 * Recuperer le nom du niveau actuel
	 * @return Le nom du niveau actuel
	 */
	public String getName() {return name;}
	
	/**
	 * Place un objet en fonction de ses coordonnees matricielles
	 * @param c La position matricielle
	 * @return La position a l'ecran
	 */
	public Vector2f placeMe(Coordonnees c)
	{
		float pos_x, pos_y;
		
		pos_x = -40 * c.getX() + 40 * c.getY();
		pos_y = 23 * c.getX() + 23 * c.getY() - 26 * c.getZ();
		
		return new Vector2f(pos_x, pos_y);
	}
	
	/**
	 * Calcule (une seule fois) et renvoie la centre du monde (pour centrer la camera)
	 * @return Le centre du monde
	 */
	public Vector2f getCenterWorld()
	{
		if (centerWorld == null)
		{
			float min_x = Float.MAX_VALUE;
			float min_y = Float.MAX_VALUE;
			
			float max_x = Float.MIN_VALUE;
			float max_y = Float.MIN_VALUE;
			
			for (int i=0; i < blockList.size(); i++)
			{
				if (blockList.get(i).getCoord().getX() < min_x)
					min_x = blockList.get(i).getCoord().getX();
				if (blockList.get(i).getCoord().getY() < min_y)
					min_y = blockList.get(i).getCoord().getY();
				
				if (blockList.get(i).getCoord().getX() > max_x)
					max_x = blockList.get(i).getCoord().getX();
				if (blockList.get(i).getCoord().getY() > max_y)
					max_y = blockList.get(i).getCoord().getY();
			}
			centerWorld = new Vector2f(
					(int)(-40*(max_x+min_x)/2 + 40*(max_y+min_y)/2 + 81/2.f), 
					(int)(23*(max_x+min_x)/2 + 23*(max_y+min_y)/2 + 81/2.f)
					);
		}
		return centerWorld;
	}
	
	/**
	 * Ajoute un block au monde
	 * @param b Le block a ajouter
	 */
	public void addBlock(Block b) 
	{
		blockList.add(b);
		allList.add(b);
	}
	
	/**
	 * Ajoute un personnage au monde
	 * @param c Le personnage a ajouter
	 */
	public void addCharacter(Character c) 
	{
		characterList.add(c);
		allList.add(c);
	}
	
	/**
	 * Recuperer la liste des blocks
	 * @return La liste des blocks
	 */
	public List<Block> getBlockList() {return blockList;}
	
	/**
	 * Recuperer la liste des personnages
	 * @return La liste des personnages
	 */
	public List<Character> getCharacterList() {return characterList;}
	
	/**
	 * Recuperer la liste solution du puzzle
	 * @return La liste des différentes procédures de listes d'action
	 */
	public List<Procedure> getListSoluce() {return listSoluce;}
	/**
	 * Afficher tous les objets du monde (blocks, personnages, ...)
	 */
	public void afficher()
	{
		Collections.sort(allList, new EntitieComparator());
		for (Entities obj : allList)
			obj.afficher();
		Collections.sort(frontList, new DepthComparator());
		for (Sprite spr : frontList)
			Graphic.SFML.draw(spr);
	}
	
	public void addFront(Sprite spr)
	{
		frontList.add(spr);
	}
	
	/**
	 * Verification de la validite d'une coordonne (blocks)</br>
	 * Si quelque chose se trouve a cette coordonne, la coordonne n'est pas valide
	 * @param c La coordonne a verifier
	 * @return True si valide, false sinon
	 */
	public boolean isValidPosition(Coordonnees c)
	{
		for (Entities e : allList)
			if (e.getCollision() && e.getCoord().int_equals(c))
				return false;
		
		return true;
	}
	
	/**
	 * Verification de la validite d'une coordonne (entities)</br>
	 * Si quelque chose se trouve a cette coordonne, la coordonne n'est pas valide
	 * @param c La coordonne a verifier
	 * @return True si valide, false sinon
	 */
	public boolean isFreePosition(Coordonnees c)
	{
		for (Entities b : allList)
			if (b.getCoord().int_equals(c))
				return false;
		
		return true;
	}
	
	/**
	 * Renvoie l'entite qui se trouve a la coordonnee donnee
	 * @param c La coordonnee ou l'on cherche l'entite
	 * @return renvoie l'entite si elle existe, sinon null
	 */
	public Entities getEntitiesAt(Coordonnees c) 
	{
		for (Entities b : allList)
			if (b.getCoord().int_equals(c))
				return b;
		
		return null;
	}
	
	/**
	 * Recupere la liste des blocks d'un type</br>
	 * Exemple d'utilisation : getBlocksT(Block.class)
	 * @param <T>
	 * @param blockType Le type de blocs a recuperer
	 * @return La liste des blocks du type blockType
	 */
	@SuppressWarnings("unchecked") // TypeSafe : Check c'est un block de type T puis cast en T
	public <T> List<T> getBlocksByType(Class<? extends Block> blockType)
	{
		List<T> l = new ArrayList<T>();
		
		for(Block b : blockList)
			if(b.getClass().equals(blockType))
				l.add((T) b);
		
		return l;
	}
	
	/**
	 * Recupere la liste des entities d'un type</br>
	 * Exemple d'utilisation : getEntitiesByType(Entities.class)
	 * @param EntitesType Le type de l'entite a recuperer
	 * @return La liste des entities du type entitiesType
	 */
	public List<Entities> getEntitiesByType(Class<? extends Entities> EntitiesType)
	{
		List<Entities> l = new ArrayList<Entities>();
		
		for(Entities e : allList)
			if(e.getClass().equals(EntitiesType))
				l.add(e);
		
		return l;
	}
	
	public int getStartingOrientation() {return orientStart;}
	
	public Coordonnees getStartingCoord() {return coordStart;}
	
	public List<Action> getActionList() {return actionList;}
	
	/**	
	 * Verifie si un monde est gagne (tous les blocs lumiere allumes)
	 */
	public boolean isComplete()
	{
		List<LightBlock> llb = this.getBlocksByType(LightBlock.class);
		boolean win = true;
		
		for(LightBlock lb : llb)
			win = win && lb.getLight();
		
		return win;
	}
	
	public void deleteBlock(Block a) {
		allList.remove(a);
		blockList.remove(a);
		
	}
	
	public int getMinStar() {return minStar;}
	
	public int getMaxStar() {return maxStar;}
	
	/**
	 * Remplit la liste des coordonnee des clones
	 */
	public void setClone(Coordonnees xyz, int o) 
	{cloneList.add(new Coordation(xyz, o));}
	

	public void setPlaying(boolean p) {playing = p;}
	public boolean isPlaying() {return playing;}
}
