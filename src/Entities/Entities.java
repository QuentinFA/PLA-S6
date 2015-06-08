package Entities;

import org.jsfml.graphics.Sprite;

import UI.Graphic;
import Actions.Coordonnees;

public abstract class Entities {
	
	protected Sprite sprite = new Sprite(); //Sprite du block ou personnage
	protected Coordonnees coord;
	
	public Coordonnees getCoord() {return coord;}
	
	/**
	 * Afficher un block
	 */
	public void afficher() {Graphic.SFML.draw(sprite);}
}
