package Entities.Blocks;

import Entities.Block;
import Entities.Character;
import Prog.Color;
import Prog.Coordonnees;

public class TeleporterBlock extends Block
{
	private TeleporterBlock destination;
	private Color couleur;
	
	public TeleporterBlock()
	{
		coord = new Coordonnees(0,0,0);
		couleur = Color.DEFAUT;
	}
	
	public Color getColor(){return couleur;}
	
	public TeleporterBlock(Coordonnees c, Color col)
	{
		coord = c;
		couleur = col;
		this.destination = null;
	}
	
	public TeleporterBlock(Coordonnees c)
	{
		coord = c;
		couleur = Color.DEFAUT;
		this.destination = null;
	}
	
	public TeleporterBlock(Color cl, Coordonnees c)
	{
		coord = c;
		couleur = cl;
		this.destination = null;
	}
	
	public TeleporterBlock(Coordonnees c, TeleporterBlock dest)
	{
		coord = c;
		this.destination = dest;
		couleur = Color.DEFAUT;
	}
	
	public void perform(Character p) 
	{
		if (destination != null)
			p.setCoord(destination.getCoord());
	}
	
	public TeleporterBlock getDest()
	{
		return this.destination;
	}
}