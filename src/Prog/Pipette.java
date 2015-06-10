package Prog;

import Entities.Block;
import Entities.Character;
import Entities.LightBlock;
import Entities.NormalBlock;
import Game.World;

public class Pipette extends Action{
	
	public Pipette(Color c){couleur = c;}
	
	public void execute(Character p) {
		Block b = World.WORLD.getUnderBlock(p.getCoord());
		if(!(b instanceof NormalBlock && b instanceof LightBlock)){
		    b.perform(p);
		}else{
			p.setColor(Color.DEFAUT);
		}
	}
}
