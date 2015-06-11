package Prog;
import Entities.Character;

public abstract class Action extends Prog implements Cloneable
{
	/**
	 * Application d'une action pour un personnage
	 * @param p Le personnage dont il est question
	 */
	public abstract boolean execute(Character p);
	
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	} 
}
