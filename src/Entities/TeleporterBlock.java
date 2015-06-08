package Entities;

import Actions.Color;
import Actions.Coordonnees;

public class TeleporterBlock extends Block
{
	private TeleporterBlock destination = null;
	private Color couleur;
	
	public TeleporterBlock()
	{
		coord = new Coordonnees(0,0,0);
		couleur = Color.DEFAUT;
	}
	
	public void setColor(Color c) {couleur = c;}
	public Color getColor(){return couleur;}
	
	public TeleporterBlock(Coordonnees c, Color col)
	{
		coord = c;
		couleur = col;
	}
	
	public void lier(TeleporterBlock dest) {destination = dest;}
	
	Coordonnees getSortie() 
	{
		if (destination != null)
			return destination.coord;
		else
			return null;
	}
	
	public void perform(Character p) 
	{
		Coordonnees s = getSortie();
		if (s != null)
			p.setCoord(s);
	}
}
