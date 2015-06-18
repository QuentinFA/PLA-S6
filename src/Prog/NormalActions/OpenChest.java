package Prog.NormalActions;

import Entities.Block;
import Entities.Character;
import Entities.Chest;
import Entities.Entities;
import Game.World;
import Prog.Action;
import Prog.Color;
import Prog.Coordonnees;
import Prog.Orientation;

public class OpenChest extends Action
{
	public OpenChest(Color c) {couleur = c;}
	
	public boolean execute(Character p)
	{
		Coordonnees coord = p.getCoord();
		Coordonnees check;

		switch (p.getOrientation())
		{
			case Orientation.NORTH:
				check = new Coordonnees(coord.getX(), coord.getY()+1, coord.getZ()); break;
			case Orientation.EAST:
				check = new Coordonnees(coord.getX()+1, coord.getY(), coord.getZ()); break;
			case Orientation.SOUTH:
				check = new Coordonnees(coord.getX(), coord.getY()-1, coord.getZ()); break;
			case Orientation.WEST:
			default:
				check = new Coordonnees(coord.getX()-1, coord.getY(), coord.getZ()); break;
		}

		Entities e = World.WORLD.getEntitiesAt(check);
			if (e != null && e instanceof Chest)
			{
				((Chest)e).perform(p);
				System.out.println(e);
				World.WORLD.deleteBlock(((Block)e));
			}

		
		return true;
	}
}
