package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsfml.system.Vector2f;

import Actions.Coordonnees;
import Entities.Block;
import Entities.Character;
import Entities.Entities;
import UI.Graphic;

public class World
{
	public static World WORLD = null;
	
	private List<Block> blockList = new ArrayList<Block>(); //Liste des blocks
	private List<Character> characterList = new ArrayList<Character>();
	
	private List<Entities> allList = new ArrayList<Entities>();
	
	// TODO Utile ?
	private int width;
	private int height;
	
	private Coordonnees begining;
	private int starting_direction;
	
	private String name;
	
	public World(int w, int h) 
	{
		width = w;
		height = h;
		name = "Testing World !";
		
		WORLD = this;
	}
	
	public World(int w, int h, String n, List<Block> lb, Coordonnees begining, int direction) 
	{
		width = w;
		height = h;
		name = n;
		this.begining = begining;
		this.starting_direction = direction;
		
		for (Block b : lb)
			addBlock(b);

		addCharacter(new Character(begining, direction));
		
		Graphic.SFML.setCenterCamera(getCenterWorld());
		
		for (Block b : blockList)
			b.setPosSprite(placeMe(b.getCoord()));
		
		for (Character c : characterList)
			c.setPosSprite(placeMe(c.getCoord()));
		
		
		
		WORLD = this;
	}
	
	// TODO Doc
	public boolean gerer()
	{
		return false;
	}
	
	public String getName()
	{
		return this.name;

	}
	
	/**
	 * Place un block en fonction de ses coordonnees matricielles
	 * @param pos: la position matricielle
	 * @return: la position a l'ecran
	 */
	public Vector2f placeMe(Coordonnees pos)
	{
		float pos_x, pos_y;
		Vector2f center = Graphic.SFML.getCenterCamera();
		
		pos_x = center.x + 40 * pos.getX() - 40 * pos.getY();
		pos_y = center.y - 23 * pos.getX() - 23 * pos.getY() - 26 * pos.getZ();
		
		return new Vector2f(pos_x, pos_y);
	}
	
	public Vector2f getCenterWorld()
	{
		float min_x = Float.MAX_VALUE;
		float min_y = Float.MAX_VALUE;
		float max_x = Float.MIN_VALUE;
		float max_y = Float.MIN_VALUE;
		
		for (int i=0; i < blockList.size(); i++)
		{
			if (blockList.get(i).getGlobalBounds().left < min_x)
				min_x = blockList.get(i).getGlobalBounds().left;
			if (blockList.get(i).getGlobalBounds().top < min_y)
				min_y = blockList.get(i).getGlobalBounds().top;
			
			if (blockList.get(i).getGlobalBounds().left + blockList.get(i).getGlobalBounds().width > max_x)
				max_x = blockList.get(i).getGlobalBounds().left + blockList.get(i).getGlobalBounds().width;
			if (blockList.get(i).getGlobalBounds().top + blockList.get(i).getGlobalBounds().height > max_y)
				max_y = blockList.get(i).getGlobalBounds().top + blockList.get(i).getGlobalBounds().height;	
		}
		return new Vector2f((max_x + min_x)/2.f, (max_y + min_y)/2.f);
	}
	
	/**
	 * Ajoute un block au monde
	 * @param b
	 */
	public void addBlock(Block b) 
	{
		blockList.add(b);
		allList.add(b);
	}
	
	public void addCharacter(Character c) 
	{
		characterList.add(c);
		allList.add(c);
	}
	
	public List<Block> getBlockList() {return blockList;}
	
	public List<Character> getCharacterList() {return characterList;}
	
	/**
	 * Afficher tous les blocks
	 */
	public void afficherBlocks()
	{
		Collections.sort(allList, new BlockComparator());
		for (Entities obj : allList)
			obj.afficher();
	}
	
	/**
	 * Vérification de la validité d'une position</br>
	 * Si un bloc se trouve
	 *  à cette position ou qu'il n'y en a pas en dessous, la position n'est pas valide
	 * @param p : La position à vérifier
	 * @return Selon si la position est valide
	 * @throws OutOfMapException : Si la position est en dehors de la map
	 */
	public boolean isValidPosition(Coordonnees p)
	{
		/*if (p.getX() >= width || p.getY() >= height || p.getX() < 0 || p.getY() < 0 || p.getZ() < 0)
			throw new OutOfMapException();*/
		//return !blockList.contains(p) && blockList.contains(new Coordonnees(p.getX(), p.getY(), p.getZ()));
		return true;
	}
	
	/**
	 * Tri des blocs de la maps selon un type</br>
	 * Exemple d'utilisation : getBlocksT(Block.class)
	 * @param blockType : Le type de blocs à récupérer
	 * @return Liste de bloc de type blockType
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
	 * @param coord
	 * @return 
	 */
	public Block getUnderBlock(Coordonnees coord)
	{
		for (Block b : blockList)
			if (b.getCoord().equals(new Coordonnees(coord.getX(), coord.getY(), coord.getZ() - 1)))
				return b;

		return null;
	}

}
