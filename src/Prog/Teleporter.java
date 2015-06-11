package Prog;

import Entities.Character;
import Entities.Entities;
import Entities.TeleporterBlock;
import Game.World;

public class Teleporter extends Action
{
	public Teleporter(Color c) {couleur = c;}
	private int frame = 0;
	private int last_frame = 18;
	
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

