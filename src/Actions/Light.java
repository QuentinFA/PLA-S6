package Actions;

import Game.World;

public class Light 
{
	public Light() {}
	
	/**
	 * Change la couleur de la case se trouver à un niveau en dessous de la case du personnage si c'est possible
	 * @param p Character.
	 */
	public void execute(Character p) 
	{
		Coordonnees c = p.getPosition();
		c.z -= 1; //Car le personnage est une case au dessus
		World.WORLD.changeColor(c);
	}
}
