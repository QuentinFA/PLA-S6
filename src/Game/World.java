package Game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsfml.system.Vector2f;

import Actions.Coordonnees;

public class World 
{
	public static World WORLD;
	
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
	 * Place un block en fonction de ses coordonnées matricielles
	 * @param pos: la position matricielle
	 * @return: la position à l'écran
	 */
	public Vector2f placeMe(Coordonnees pos)
	{
		float pos_x, pos_y;
		Vector2f center = new Vector2f(SFML.width/2, SFML.height/2);
		
		pos_x = center.x + 37 * pos.x - 37 * pos.y;
		pos_y = center.y - 21 * pos.x - 21 * pos.y - 29 * pos.z;
		
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
}
