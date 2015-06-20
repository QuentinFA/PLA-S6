package Prog.NormalActions;

import Entities.Character;
import Entities.Chest;
import Entities.Entities;
import Game.World;
import Prog.Action;
import Prog.Color;
import Prog.Coordonnees;
import Prog.Orientation;

/**
 * Permet d'ouvrir un coffre
 *
 */
public class OpenChest extends Action
{
	public OpenChest(Color color)
	{
		super(color);
	}
	
	private int frame = 0;
	private int last_frame_phase1 = 20;
	private Chest chestConcerned = null;
	
	/**
	 * Si un Chest se trouve en face du personnage p, celui-ci l'ouvre. C'est a dire, il le sauvegarde dans son attribue Chest. Le coffre disparait ensuite de la map.
	 */
	public boolean execute(Character p)
	{
		if (frame == 0)
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
				chestConcerned = (Chest) e;
				chestConcerned.perform(p);
				p.setBulle(chestConcerned.getBulle());
			}
			else
				return true;
		}
		
		frame ++;
		
		chestConcerned.setAlpha((int) (255 - (float)frame/last_frame_phase1*255));
		
		if (frame == last_frame_phase1)
		{
			World.WORLD.deleteBlock(chestConcerned);
			return true;
		}
		else
			return false;
	}
}
