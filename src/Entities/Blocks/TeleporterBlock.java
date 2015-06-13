package Entities.Blocks;

import Entities.Block;
import Entities.Character;
import Prog.Color;
import Prog.Coordonnees;

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
	
	public TeleporterBlock(Coordonnees c)
	{
		coord = c;
		couleur = Color.DEFAUT;
		this.destination = null;
	}
	
	public TeleporterBlock(Coordonnees c, TeleporterBlock dest)
	{
		coord = c;
		this.destination = dest;
		couleur = Color.DEFAUT;
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
	
	public TeleporterBlock getDest()
	{
		return this.destination;
	}
}
