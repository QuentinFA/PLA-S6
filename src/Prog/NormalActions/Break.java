package Prog.NormalActions;

import Entities.Character;
import Prog.Action;

public class Break extends Action 
{
	public Break() {} //Sert a etre identifier dans l'interpreteur
	
	public boolean execute(Character p) {return false;}
}
