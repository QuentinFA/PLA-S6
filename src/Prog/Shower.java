package Prog;

import Entities.Block;
import Entities.Character;
import Entities.LightBlock;
import Entities.NormalBlock;
import Game.World;


public class Shower extends Action 
{
		public Shower(Color c) { couleur = c;}
		
		public void execute(Character p)
		{
		p.setColor(Color.DEFAUT);
		}
}
