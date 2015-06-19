package Prog.NormalActions;

import Entities.Character;
import Prog.Action;
import Prog.Color;

/**
 * Action remettant la couleur d'origine du personnage.
 * @author edwin
 *
 */
public class Shower extends Action 
{
		public Shower(Color c) {couleur = c;}
		
		/**
		 * Couleur du personnage p = defaut
		 */
		public boolean execute(Character p) 
		{
			p.setColor(Color.DEFAUT);
			
			return true;
		}
}
