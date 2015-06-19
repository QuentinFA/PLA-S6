package Prog;

import org.jsfml.graphics.Sprite;

import Entities.Character;

public abstract class Action extends Prog
{
	private Sprite sprite; //Sprite associe
	public Sprite getSprite() {return sprite;}
	public void setSprite(Sprite spr) {sprite = spr;}
	
	public Action(Color c)
	{
		super(c);
	}
	
	/**
	 * Application d'une action pour un personnage
	 * @param p Le personnage dont il est question
	 */
	public abstract boolean execute(Character p);
}
