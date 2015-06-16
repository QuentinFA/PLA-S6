package Prog.NormalActions;

import Entities.Character;
import Game.World;
import Prog.Action;
import Prog.Color;
import Prog.Coordonnees;

public class Fork extends Action{
	
	public Fork(Color c){couleur = c;}
	
	
	public boolean execute(Character p) {
		World.WORLD.setClone(new Coordonnees(p.getCoord()), p.getOrientation());
		return true;
	}

}
