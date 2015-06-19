package Prog.NormalActions;

import Entities.Character;
import Prog.Action;
import Prog.Color;

/**
 * Concept du return de fonction. Permet aussi de casser les boucles infinies.
 * @author edwin
 *
 */
public class Break extends Action 
{
	public Break(Color c) {couleur = c;} //Sert a etre identifier dans l'interpreteur
	
	public boolean execute(Character p) {return false;}
}
