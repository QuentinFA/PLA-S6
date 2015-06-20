package Entities;

import Prog.Color;
import Prog.Coordonnees;

/**
 * Classe permettant d'identifier les blocs de couleurs avec l'action Pipette
 *
 */
public abstract class ColorBlock extends Block
{
	public ColorBlock(Coordonnees coord, Color color)
	{
		super(coord);
		this.color = color;
	}
	
	private Color color;
	
	/**
	 * Appele par l'action Pipette pour changer la couleur du personnage.
	 */
	public void perform(Character p)
	{
		p.setColor(this.color);
	}
}
