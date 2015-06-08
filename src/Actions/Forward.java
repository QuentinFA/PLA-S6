package Actions;
import Entities.Character;

public class Forward extends Action
{
	public Forward(Color.COLOR c) {couleur = c;}
	
	public void execute(Character p)
	{
		if (p.getColor() == couleur)
		{
			Coordonnees coord = p.getCoord();
			switch (p.getOrientation())
			{
				case Direction.NORTH:
					p.setCoord(new Coordonnees(coord.getX(), coord.getY()+1, coord.getZ())); break;
				case Direction.EAST:
					p.setCoord(new Coordonnees(coord.getX()+1, coord.getY(), coord.getZ())); break;
				case Direction.SOUTH:
					p.setCoord(new Coordonnees(coord.getX(), coord.getY()-1, coord.getZ())); break;
				case Direction.WEST:
					p.setCoord(new Coordonnees(coord.getX()-1, coord.getY(), coord.getZ())); break;
			}
		}
	}
}
