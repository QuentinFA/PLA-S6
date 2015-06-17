package Prog;
import Entities.Character;
import Prog.Color;

public abstract class Action extends Prog
{
	/**
	 * Application d'une action pour un personnage
	 * @param p Le personnage dont il est question
	 */
	public abstract boolean execute(Character p);
}
