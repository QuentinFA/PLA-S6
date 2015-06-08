package Actions;
import Entities.Character;
import Game.World;

public class Jump extends Action
{
	public Jump(Color.COLOR c) {couleur = c;}
	
	public void execute(Character p)
	{
		if (p.getColor() == couleur)
		{
			Coordonnees coord = p.getCoord();
			Coordonnees check = new Coordonnees(0,0,0);
			switch (p.getOrientation())
			{
				case Direction.NORTH:
					check = new Coordonnees(coord.getX(), coord.getY()+1, coord.getZ()+1); break;
				case Direction.EAST:
					check = new Coordonnees(coord.getX()+1, coord.getY(), coord.getZ()+1); break;
				case Direction.SOUTH:
					check = new Coordonnees(coord.getX(), coord.getY()-1, coord.getZ()+1); break;
				case Direction.WEST:
					check = new Coordonnees(coord.getX()-1, coord.getY(), coord.getZ()+1); break;
			}
			if(World.WORLD.isValidPosition(check))	
				p.setCoord(check);
		}
	}
}
