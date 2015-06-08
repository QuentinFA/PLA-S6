package Game;

import Actions.Color;
import Actions.Coordonnees;
import Entities.Character;

public class TeleporterBlock extends Block
{
	private TeleporterBlock destination = null;
	private Color.COLOR couleur;
	
	public TeleporterBlock()
	{
		coord = new Coordonnees(0,0,0);
		couleur = Color.COLOR.DEFAUT;
	}
	
	public TeleporterBlock(Coordonnees c, Color.COLOR col)
	{
		coord = c;
		couleur = col;
	}
	
	public void lier(TeleporterBlock dest) {destination = dest;}
	
	Coordonnees getSortie() {return destination.coord;}
	
	public void perform(Character p) {p.setCoord(getSortie());}
}
