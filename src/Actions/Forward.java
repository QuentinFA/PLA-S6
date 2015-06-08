package Actions;
import Entities.Character;
import Game.World;

public class Forward extends Action
{
	public Forward(Color c) {couleur = c;}
	
	public void execute(Character p)
	{
		if (p.getColor() == couleur || couleur == Color.DEFAUT)
		{
			Coordonnees coord = p.getCoord();
			Coordonnees check = new Coordonnees(0,0,0);
			switch (p.getOrientation())
			{
				case Direction.NORTH:
					check = new Coordonnees(coord.getX(), coord.getY()+1, coord.getZ()); break;
				case Direction.EAST:
					check = new Coordonnees(coord.getX()+1, coord.getY(), coord.getZ()); break;
				case Direction.SOUTH:
					check = new Coordonnees(coord.getX(), coord.getY()-1, coord.getZ()); break;
				case Direction.WEST:
					check = new Coordonnees(coord.getX()-1, coord.getY(), coord.getZ()); break;
			}
			
			if (World.WORLD.isValidPosition(check))
			{
				p.setCoord(check);
				p.setPosSprite(World.WORLD.placeMe(p.getCoord()));
			}
			//TODO Animation du personnage qui avance ou qui reste sur place ou qui tombe dans le vide
		}
	}
}
