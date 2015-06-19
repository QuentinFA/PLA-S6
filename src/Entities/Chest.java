package Entities;

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
	
	public Action getAction() {return action;}
	public void setAction(Action a) {action = a;}
	
	public abstract void setTextureChest();
	
	/**
	 * Utiliser un Chest equivaut a le ranger dans le personnage.
	 */
	public void perform(Character c) 
	{
		c.setChest(this);
	}
}
