package Prog;

import Entities.Character;

public class Shower extends Action 
{
		public Shower(Color c) {couleur = c;}
		
		public void execute(Character p) {p.setColor(Color.DEFAUT);}
}
