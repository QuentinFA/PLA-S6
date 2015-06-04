package Actions;
import Game.World;

public class Forward extends Action
{
	public Forward() {}
	
	public void execute(Character p) 
	{
		Coordonnees c = p.getPosition();
		int orientation = p.getOrientation();
		
		if (orientation == 0) //Nord
			c.y += 1;
		else if (orientation == 1) //Est
			c.x += 1;
		else if (orientation == 2) //Sud
			c.y -= 1;
		else //Ouest
			c.x -= 1;
		
		if (World.WORLD.checkNewPosition(p, c))  //personnage et nouvelle cordonnées
				p.setPosition(c);
		//else {//le personnage nebouge pas) }
	}
}
