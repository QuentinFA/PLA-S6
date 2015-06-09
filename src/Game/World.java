package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsfml.system.Vector2f;

import Actions.Coordonnees;
import Entities.Block;
import Entities.Character;
import Entities.EntitieComparator;
import Entities.Entities;
import UI.Graphic;

public class World
{
	public static World WORLD = null;
	
	private List<Block> blockList = new ArrayList<Block>(); //Liste des blocks
	private List<Character> characterList = new ArrayList<Character>(); //Liste des personnages
	
	private List<Entities> allList = new ArrayList<Entities>(); //Listes de tous les objets
	
	private Coordonnees coordStart; //Coordonnees du d�part
	private int orientStart; //Orientation du d�part
	private Vector2f centerWorld = null;
	
	private String name;
	
	/**
	 * Constructeur de niveau
	 * @param n Le nom du niveau
	 * @param lb La liste des blocks du niveau
	 * @param cStart Les coordonnees de depart du personnage
	 * @param oStart L'orientation de depart du personnage
	 */
	public World(String n, List<Block> lb, Coordonnees cStart, int oStart) 
	{
		name = n;
		coordStart = cStart;
		orientStart = oStart;
		
		for (Block b : lb)
			addBlock(b);

		addCharacter(new Character(coordStart, orientStart));
		
		for (Block b : blockList)
			b.setPosSprite(placeMe(b.getCoord()));
		
		for (Character c : characterList)
			c.setPosSprite(placeMe(c.getCoord()));
		
		WORLD = this;
		
		Graphic.SFML.setCenterCamera(getCenterWorld());
	}
	
	/**
	 * Gerer le monde (animation du monde)
	 */
	public void gerer()
	{
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

		pos_x = 40 * c.getX() - 40 * c.getY();
		pos_y = - 23 * c.getX() - 23 * c.getY() - 26 * c.getZ();
		
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
					(int)(40*(max_x+min_x)/2 - 40*(max_y+min_y)/2 + 81/2.f), 
					(int)(- 23*(max_x+min_x)/2 - 23*(max_y+min_y)/2 + 81/2.f)
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
	 * Afficher tous les objets du monde (blocks, personnages, ...)
	 */
	public void afficher()
	{
		Collections.sort(allList, new EntitieComparator());
		for (Entities obj : allList)
			obj.afficher();
	}
	
	/**
	 * Verification de la validite d'une coordonne</br>
	 * Si quelque chose se trouve�a cette coordonne, la coordonne n'est pas valide
	 * @param c La coordonne a�verifier
	 * @return True si valide, false sinon
	 */
	public boolean isValidPosition(Coordonnees c)
	{
		for(Entities b : allList)
			if(b.getCoord().equals(c))
				return false;
		
		return true;
	}
	
	/**
	 * Recupere la liste des blocks d'un type</br>
	 * Exemple d'utilisation : getBlocksT(Block.class)
	 * @param blockType Le type de blocs a recuperer
	 * @return La liste des blocks du type blockType
	 */
	public List<Block> getBlocksByType(Class<? extends Block> blockType)
	{
		List<Block> l = new ArrayList<Block>();
		
		for(Block b : blockList)
			if(b.getClass().equals(blockType))
				l.add(b);
		
		return l;
	}

	/**
	 * Retourne le block en dessous de la coordonnee passee en parametre
	 * @param coord La coordonnee a tester
	 * @return Le block sous la coordonnee, null si rien
	 */
	public Block getUnderBlock(Coordonnees coord)
	{
		for (Block b : blockList)
			if (b.getCoord().equals(new Coordonnees(coord.getX(), coord.getY(), coord.getZ() - 1)))
				return b;

		return null;
	}

}
