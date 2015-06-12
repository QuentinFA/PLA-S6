package Prog.NormalActions;

import Entities.Block;
import Entities.Character;
import Entities.ColorBlock;
import Entities.Entities;
import Game.World;
import Prog.Action;
import Prog.Color;
import Prog.Coordonnees;

public class Pipette extends Action{
	
	public Pipette(Color c){couleur = c;}
	
	public boolean execute(Character p) 
	{
		Entities b = World.WORLD.getEntitiesAt(new Coordonnees(p.getCoord().getX(), p.getCoord().getY(), p.getCoord().getZ()-1));
		if(!(b instanceof ColorBlock))
			((Block) b).perform(p);

		return true;
	}
}
