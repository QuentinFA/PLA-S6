package Prog.NormalActions;

import Entities.Character;
import Prog.Action;
import Prog.Color;

public class Shower extends Action 
{
		public Shower(Color c) {super(c);}
		
		public boolean execute(Character p) 
		{
			p.setColor(Color.DEFAUT);
			
			return true;
		}
}
