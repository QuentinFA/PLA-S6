package Prog;

import Entities.Character;
import Entities.Chest;
import Entities.Entities;
import Game.World;

public class OpenChest extends Action
{
	public OpenChest(Color c) {couleur = c;}
	
	public void execute(Character p)
	{
		Coordonnees coord = p.getCoord();
		Coordonnees check;
		int o = p.getOrientation();
		switch (o)
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
		
		if (World.WORLD.isValidPosition(check))
		{
			Entities e = World.WORLD.getEntitiesAt(check);
			if (e != null && e instanceof Chest && (!((Chest)e).isOpen()))
			{
				switch (o)
				{
					case Orientation.NORTH:
						if (((Chest)e).getOrientation() == Orientation.SOUTH) 
							p.setChest(((Chest)e).openChest());
						break;
					case Orientation.EAST:
						if (((Chest)e).getOrientation() == Orientation.WEST) 
							p.setChest(((Chest)e).openChest());
						break;
					case Orientation.SOUTH:
						if (((Chest)e).getOrientation() == Orientation.NORTH) 
							p.setChest(((Chest)e).openChest());
						break;
					case Orientation.WEST:
					default:
						if (((Chest)e).getOrientation() == Orientation.EAST) 
							p.setChest(((Chest)e).openChest());
						break;
				}
			}
		}
	}
}
