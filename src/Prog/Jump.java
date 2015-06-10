package Prog;
import Entities.Character;
import Game.World;

public class Jump extends Action
{
	public Jump(Color c) {couleur = c;}
	
	public void execute(Character p)
	{
		if ( (p.getColor() == Color.DEFAUT) || (p.getColor() == couleur) )
		{
			Coordonnees coord = p.getCoord();
			Coordonnees check;
			switch (p.getOrientation())
			{
				case Orientation.NORTH:
					check = new Coordonnees(coord.getX(), coord.getY()+1, coord.getZ()+1); break;
				case Orientation.EAST:
					check = new Coordonnees(coord.getX()+1, coord.getY(), coord.getZ()+1); break;
				case Orientation.SOUTH:
					check = new Coordonnees(coord.getX(), coord.getY()-1, coord.getZ()+1); break;
				case Orientation.WEST:
				default:
					check = new Coordonnees(coord.getX()-1, coord.getY(), coord.getZ()+1); break;
			}
			
			if(World.WORLD.isValidPosition(check))	
				p.setCoord(check);
		}
	}
}
