package Game;
import org.jsfml.graphics.*;

public class Block 
{
	private Sprite sprite = new Sprite(); //Sprite du block
	
	/**
	 * Créer un block
	 * @param i: type du block (0: block standard)
	 */
	public Block(int i)
	{
		if (i == 0)
			sprite.setTexture(Ressources.texture_block0);
	}
	
	/**
	 * Afficher un block
	 */
	public void afficher()
	{
		SFML.fenetre.draw(sprite);
	}
}
