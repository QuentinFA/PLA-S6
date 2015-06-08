package Entities;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import UI.Graphic;
import Actions.Coordonnees;

public abstract class Entities {
	
	protected Sprite sprite = new Sprite(); //Sprite du block ou personnage
	protected Coordonnees coord;
	
	public Coordonnees getCoord() {return coord;}
	
	public Vector2f getPosSprite() {return sprite.getPosition();}
	public void setPosSprite(Vector2f pos) {sprite.setPosition(pos);}
	public FloatRect getGlobalBounds() {return sprite.getGlobalBounds();}
	
	/**
	 * Afficher un block
	 */
	public void afficher() {Graphic.SFML.draw(sprite);}
}
