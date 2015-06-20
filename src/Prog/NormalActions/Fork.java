package Prog.NormalActions;

import Entities.Character;
import Game.World;
import Prog.Action;
import Prog.Color;
import Prog.Coordonnees;

/**
 * Concept du fork
 *
 */
public class Fork extends Action{
	public Fork(Color color)
	{
		super(color);
	}
	
	/**
	 * Cree un clone du personnage p a l'endroit ou il se trouve
	 */
	public boolean execute(Character p) 
	{
		World.WORLD.setClone(new Coordonnees(p.getCoord()), p.getOrientation());
		return true;
	}
	
}
