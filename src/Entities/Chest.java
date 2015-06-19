package Entities;

import org.jsfml.graphics.Sprite;

import Prog.Action;
import Entities.Character;

/**
 * Classe permettant la representation du concept d'objet (voir doc). Elle herite de block car elle a les mêmes proprietes de base.
 * @author edwin
 *
 */
public abstract class Chest extends Block 
{
	protected Action action;
	protected Sprite sprite_action = new Sprite();

	public Sprite getBulle() {return sprite_action;}
	
	public Action getAction() {return action;}
	public void setAction(Action a) {action = a;}
	
	public abstract void setTextureChest();
	public void setAlpha(int a) {sprite.setColor(new org.jsfml.graphics.Color(255, 255, 255, a));}
	
	public void initialiser() {sprite.setColor(org.jsfml.graphics.Color.WHITE);}
	
	/**
	 * Utiliser un Chest equivaut a le ranger dans le personnage.
	 */
	public void perform(Character p) {p.setChest(this);}
}
