package Prog;
import Entities.Character;
import Game.World;

public class Forward extends Action
{
	public Forward(Color c) {couleur = c;}
	
	public void execute(Character p)
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
		
		if (World.WORLD.isValidPosition(check))
		{
			p.setCoord(check);
			p.setPosSprite(World.WORLD.placeMe(p.getCoord()));
		}
	}
}
