package Prog;

import Entities.Block;
import Entities.Character;
import Entities.Entities;
import Entities.LightBlock;
import Entities.NormalBlock;
import Game.World;

public class Pipette extends Action{
	
	public Pipette(Color c){couleur = c;}
	
	public boolean execute(Character p) 
	{
		Entities b = World.WORLD.getEntitiesAt(new Coordonnees(p.getCoord().getX(), p.getCoord().getY(), p.getCoord().getZ()-1));
		if(!(b instanceof NormalBlock && b instanceof LightBlock))
			((Block) b).perform(p);
		else
			p.setColor(Color.DEFAUT);
		
		return true;
	}
}
