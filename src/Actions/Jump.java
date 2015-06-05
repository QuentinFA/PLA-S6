package Actions;


public class Jump extends Action
{
	public Jump() {}

	@Override
	public Coordonnees execute(Coordonnees p, int orientation)
	{
		switch(orientation)
		{
			case Direction.NORTH :
				return new Coordonnees(p.getX(), p.getY() + 1, p.getZ() + 1);
			case Direction.WEST :
				return new Coordonnees(p.getX() - 1, p.getY(), p.getZ() + 1);
			case Direction.SOUTH :
				return new Coordonnees(p.getX(), p.getY() - 1, p.getZ() + 1);
			case Direction.EST :
			default :
				return new Coordonnees(p.getX() + 1, p.getY(), p.getZ() + 1);
		}
	}
}
