package Actions;

import Game.World;

public class Light 
{
	public Light() {}
	
	public void execute(Character p) 
	{
		Coordonnees c = p.getPosition();
		c.z -= 1; //Car le personnage est une case au dessus
		World.WORLD.changeColor(c);
	}
}
