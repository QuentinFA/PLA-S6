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
	
	public TeleporterBlock(Coordonnees c, Color col)
	{
		coord = c;
		couleur = col;
	}
	
	public void lier(TeleporterBlock dest) {destination = dest;}
	
	Coordonnees getSortie() {return destination.coord;}
	
	public void perform(Character p) {p.setCoord(getSortie());}
}