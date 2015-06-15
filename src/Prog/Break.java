package Prog;

import Entities.Character;

public class Break extends Action 
{
	public Break() {} //Sert a etre identifier dans l'interpreteur
	
	public boolean execute(Character p) {return false;}
}
