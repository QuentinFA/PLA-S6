package Game;
import org.jsfml.graphics.Sprite;

import Actions.Coordonnees;
import UI.Graphic;

public abstract class Block
{
	protected Sprite sprite = new Sprite(); //Sprite du block
	protected Coordonnees position;
	
	public Coordonnees getCoord() {return position;}
	
	/**
	 * Afficher un block
	 */
	public void afficher()
	{
		Graphic.SFML.draw(sprite);
	}
}
