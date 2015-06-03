package Game;
import java.util.ArrayList;
import java.util.List;

public class World 
{
	public static final World WORLD = new World();
	private List<Block> blockList = new ArrayList<Block>(); //Liste des blocks

	//TODO matrice
	//private matrice = ;
	
	private World() 
	{
		
	}
	
	/**
	 * Ajoute un block au monde
	 * @param b
	 */
	public void addBlock(Block b)
	{
		blockList.add(b);
	}
	
	/**
	 * Afficher tous les blocks
	 */
	public void afficherBlocks()
	{
		for (int i=0; i < blockList.size(); i++)
			blockList.get(i).afficher();
	}
}
