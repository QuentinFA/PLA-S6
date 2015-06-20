package Prog.NormalActions;

import Entities.Character;
import Prog.Action;
import Prog.Color;

/**
 * Concept du return de fonction. Permet aussi de casser les boucles infinies.
 * Sert a etre identifier dans l'interpreteur
 *
 */
public class Break extends Action 
{
	
	public Break(Color color)
	{
		super(color);
	}
	
	public boolean execute(Character p) {return false;}
}
