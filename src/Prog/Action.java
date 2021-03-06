package Prog;

import org.jsfml.graphics.Sprite;

import Entities.Character;

/**
 * Classe qui permet d'utiliser les Actions.
 *
 */
public abstract class Action extends Prog
{
	public Action(Color color)
	{
		super(color);
	}

	private Sprite sprite; //Sprite associe
	public Sprite getSprite() {return sprite;}
	public void setSprite(Sprite spr) {sprite = spr;}
	
	/**
	 * Application d'une action pour un personnage
	 * @param p Le personnage dont il est question
	 */
	public abstract boolean execute(Character p);
}
