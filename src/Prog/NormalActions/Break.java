package Prog.NormalActions;

import Entities.Character;
import Prog.Action;
import Prog.Color;

public class Break extends Action 
{
	public Break(Color c) {super(c);} //Sert a etre identifier dans l'interpreteur
	
	public boolean execute(Character p) {return false;}
}
