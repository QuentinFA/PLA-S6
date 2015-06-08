package Actions;
import Entities.Character;

public class Jump extends Action
{
	public Jump(Color.COLOR c) {couleur = c;}
	
	public void execute(Character p)
	{
		if (p.getColor() == couleur)
		{
			Coordonnees coord = p.getCoord();
			switch (p.getOrientation())
			{
				case Direction.NORTH:
					p.setCoord(new Coordonnees(coord.getX(), coord.getY()+1, coord.getZ()+1)); break;
				case Direction.EAST:
					p.setCoord(new Coordonnees(coord.getX()+1, coord.getY(), coord.getZ()+1)); break;
				case Direction.SOUTH:
					p.setCoord(new Coordonnees(coord.getX(), coord.getY()-1, coord.getZ()+1)); break;
				case Direction.WEST:
					p.setCoord(new Coordonnees(coord.getX()-1, coord.getY(), coord.getZ()+1)); break;
			}
		}
	}
}
