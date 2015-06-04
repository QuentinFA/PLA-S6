package Actions;

import Game.World;

public class Jump 
{
	public Jump() {}
	
	/**
	 * Calcule la nouvelle coordonnée. Puis demande au monde si elle peut déplacer le character à cet emplacement.
	 * @param p Character à déplacer. 
	 */
	public void execute(Character p) 
	{
		Coordonnees c = p.getPosition();
		Coordonnees c2 = p.getPosition();
		
		int orientation = p.getOrientation();
		
		if (orientation == 0) //Nord
		{
			c.y += 1;
			c.z += 1;
			c2.y += 1;
			c2.z -= 1;
		}
		else if (orientation == 1) //Est
		{
			c.x += 1;
			c.z += 1;
			c2.x += 1;
			c2.z -= 1;
		}
		else if (orientation == 2) //Sud
		{
			c.y-=1;
			c.z+=1;
			c2.y-=1;
			c2.z-=1;
		}
		else //Ouest
		{
			c.x-=1;
			c.z+=1;
			c2.x-=1;
			c2.z-=1;
		}
		
		if (World.WORLD.checkNewPosition(p, c)) //personnage et nouvelle cordonnées. Saut en montant
				p.setPosition(c);
		else if (World.WORLD.checkNewPosition(p, c2)) //Saut en descendant
			p.setPosition(c2);
		//else {//le personnage nebouge pas) }
	}
}
