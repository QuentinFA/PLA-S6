package Game;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;

import Actions.Coordonnees;
import UI.Graphic;

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
		{
			sprite.setTexture(Ressources.RESSOURCES.getTexture1());
			sprite.setTextureRect(new IntRect(1, 1, 81, 81));
		}
		else
			System.out.println("Problème de level...");
		
		position = pos;
		
		sprite.setPosition(World.WORLD.placeMe(pos));
	}
	
	public Coordonnees getCoord() {return position;}
	
	public FloatRect getGlobalBounds() {return sprite.getGlobalBounds();}
	
	/**
	 * Afficher un block
	 */
	public void afficher()
	{
		Graphic.SFML.draw(sprite);
	}
}
