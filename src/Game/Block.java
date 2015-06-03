package Game;
import java.util.Comparator;

import org.jsfml.graphics.*;

import Actions.Coordonnees;

public class Block
{
	private Sprite sprite = new Sprite(); //Sprite du block
	private Coordonnees position;
	
	/**
	 * Créer un block
	 * @param i: type du block (0: block standard)
	 */
	public Block(int i, Coordonnees pos)
	{
		if (i == 1)
			sprite.setTexture(Ressources.texture_block1);
		else
			System.out.println("Problème de level...");
		
		position = pos;
		
		sprite.setPosition(World.WORLD.placeMe(pos));
	}
	
	public Coordonnees getCoord() {return position;}
	
	/**
	 * Afficher un block
	 */
	public void afficher()
	{
		SFML.fenetre.draw(sprite);
	}
}
