package Prog.NormalActions;

import Entities.Block;
import Entities.Character;
import Entities.ColorBlock;
import Entities.Entities;
import Game.World;
import Prog.Action;
import Prog.Color;
import Prog.Coordonnees;

/** 
 * Action pour changer la couleur d'un personnage.
 *
 */
public class Pipette extends Action{
	public Pipette(Color color)
	{
		super(color);
	}
	
	/**
	 * Colorie le personnage de la couleur du bloc sous lui si c'est un bloc de couleur
	 */
	public boolean execute(Character p) 
	{
		Entities b = World.WORLD.getEntitiesAt(new Coordonnees(p.getCoord().getX(), p.getCoord().getY(), p.getCoord().getZ()-1));
		if(b instanceof ColorBlock)
			((Block) b).perform(p);
		
		return true;
	}
}
