package Game;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import Actions.Coordonnees;
import Entities.Character;
import UI.Graphic;

public abstract class Block
{
	protected Sprite sprite = new Sprite(); //Sprite du block
	protected Coordonnees position;
	
	
	public Coordonnees getCoord() {return position;}
	
	public Vector2f getPosSprite()
	{
		return this.sprite.getPosition();
	}
	
	public void setPosSprite(Vector2f pos)
	{
		this.sprite.setPosition(pos);
	}

	public FloatRect getGlobalBounds() {return sprite.getGlobalBounds();}
	
	/**
	 * Afficher un block
	 */
	public void afficher()
	{
		Graphic.SFML.draw(sprite);
	}
	
	public abstract void perform(Character p);
}
