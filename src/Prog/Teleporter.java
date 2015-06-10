package Prog;

import Entities.Character;
import Entities.Entities;
import Entities.TeleporterBlock;
import Game.World;

public class Teleporter extends Action
{
	public Teleporter(Color c) {couleur = c;}
	
	public void execute(Character p)
	{
		Entities b = World.WORLD.getEntitiesAt(new Coordonnees(p.getCoord().getX(), p.getCoord().getY(), p.getCoord().getZ()-1));
		if (b != null)
			if (b instanceof TeleporterBlock)
				((TeleporterBlock) b).perform(p);
	}
}

