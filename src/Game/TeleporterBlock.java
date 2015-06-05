package Game;

import Actions.Color;
import Actions.Coordonnees;

public class TeleporterBlock extends Block{
	private TeleporterBlock destination = null;
	private int couleur;
	
	public TeleporterBlock()
	{
		this.position = new Coordonnees(0,0,0);
		this.couleur = Color.GRIS;
	}
	
	public TeleporterBlock(Coordonnees entree, int coul)
	{
		entree = this.position;
		coul = this.couleur;
	}
	
	public void lier(TeleporterBlock copain){
		this.destination = copain;
	}
	
	Coordonnees getSortie(){
		return destination.position;
	}
}
