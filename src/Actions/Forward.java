package Actions;
import Entities.Character;

public class Forward extends Action
{
	public Forward(int color) {c = color;}

	@Override
	public Coordonnees execute(Coordonnees p, int orientation)
	{
		switch(orientation)
		{
			case Direction.NORTH :
				return new Coordonnees(p.getX(), p.getY() + 1, p.getZ());
			case Direction.WEST :
				return new Coordonnees(p.getX() - 1, p.getY(), p.getZ());
			case Direction.SOUTH :
				return new Coordonnees(p.getX(), p.getY() - 1, p.getZ());
			case Direction.EST :
			default :
				return new Coordonnees(p.getX() + 1, p.getY(), p.getZ());
		}
	}
	
	//Inutile
	public void execute(Character p) {}
}
