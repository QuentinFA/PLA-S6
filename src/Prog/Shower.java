package Prog;

import Entities.Character;

public class Shower extends Action 
{
		public Shower(Color c) {couleur = c;}
		
		public boolean execute(Character p) 
		{
			p.setColor(Color.DEFAUT);
			
			return true;
		}
}
