package Prog.NormalActions;

import Entities.Character;
import Entities.Entities;
import Entities.Blocks.TeleporterBlock;
import Game.World;
import Prog.Action;
import Prog.Color;
import Prog.Coordonnees;

/** 
 * Action pour teleporter un personnage
 * @author edwin
 *
 */
public class Teleporter extends Action
{
	public Teleporter(Color c) {couleur = c;}
	private int frame = 0;
	private int last_frame = 18;
	
	/**
	 * Si le personnage p se trouve sur un bloc teleporteur, il est teleporte sur la case indique par ce bloc.
	 */
	public boolean execute(Character p)
	{
		if (frame == 0) //Initialisation
		{
			Entities b = World.WORLD.getEntitiesAt(new Coordonnees(p.getCoord().getX(), p.getCoord().getY(), p.getCoord().getZ()-1));
			if (b != null)
				if (b instanceof TeleporterBlock)
					((TeleporterBlock) b).perform(p);
		}
		else if (frame == 8)
		{
			
		}
		else if (frame == 16)
		{
			
		}
		//TODO other animation
		frame ++;
		
		return frame == last_frame;
	}
}

