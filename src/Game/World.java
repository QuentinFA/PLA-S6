package Game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsfml.system.Vector2f;

import Actions.Coordonnees;
import Actions.OutOfMapException;
import UI.Graphic;

public class World 
{
	public static World WORLD = null;
	//public static Menu MENU = null;
	
	private List<Block> blockList = new ArrayList<Block>(); //Liste des blocks
	
	private int width;
	private int height;

	//TODO matrice
	//private matrice = ;
	
	public World(int w, int h) 
	{
		width = w;
		height = h;
		
		WORLD = this;
	}
	
	/**
	 * Place un block en fonction de ses coordonn�es matricielles
	 * @param pos: la position matricielle
	 * @return: la position � l'�cran
	 */
	public Vector2f placeMe(Coordonnees pos)
	{
		float pos_x, pos_y;
		Vector2f center = new Vector2f(Graphic.SFML.getSizeFenetre().x/2, Graphic.SFML.getSizeFenetre().y/2);
		
		pos_x = center.x + 37 * pos.getX() - 37 * pos.getY();
		pos_y = center.y - 21 * pos.getX() - 21 * pos.getY() - 29 * pos.getZ();
		
		return new Vector2f(pos_x, pos_y);
	}
	
	/**
	 * Ajoute un block au monde
	 * @param b
	 */
	public void addBlock(Block b) {blockList.add(b);}
	
	/**
	 * Afficher tous les blocks
	 */
	public void afficherBlocks()
	{
		Collections.sort(blockList, new BlockComparator());
		for (int i=0; i < blockList.size(); i++)
			blockList.get(i).afficher();
	}
	
	/**
	 * Vérification de la validité d'une position</br>
	 * Si un bloc se trouve à cette position ou qu'il n'y en a pas en dessous, la position n'est pas valide
	 * @param p : La position à vérifier
	 * @return : Selon si la position est valide
	 * @throws OutOfMapException : Si la position est en dehors de la map
	 */
	public boolean isValidPosition(Coordonnees p) throws OutOfMapException
	{
		if(p.getX() > width || p.getY() > height || p.getX() < 0 || p.getY() < 0 || p.getZ() < 0)
			throw new OutOfMapException();
		return !blockList.contains(p) && blockList.contains(new Coordonnees(p.getX(), p.getY(), p.getZ()-1));
	}
}
