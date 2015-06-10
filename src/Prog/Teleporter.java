package Prog;

import Entities.Block;
import Entities.Character;
import Entities.LightBlock;
import Entities.TeleporterBlock;
import Game.World;

public class Teleporter extends Action{
	
	public Teleporter(Color c){couleur = c;}
	
	public void execute(Character p)
	{
		//a revoir
		Block b = World.WORLD.getUnderBlock(p.getCoord());
		if (b != null)
			if (b instanceof TeleporterBlock)
				((TeleporterBlock) b).perform(p);
	}
}

