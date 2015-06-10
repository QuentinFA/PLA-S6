package Entities;

import org.jsfml.graphics.IntRect;

import Game.Ressources;
import Game.Ressources.TEXTURE;
import Prog.*;


public class Character extends Entities
{	
	private Procedure main;
	private int orientation; //0: haut, 1: droite, 2: bas, 3: gauche, voir Orientation.java
	private Color couleur;
	private Chest coffre;
	
	public Character(Coordonnees pos, int ori) 
	{
		coord = pos;
		orientation = ori;
		couleur = Color.DEFAUT;
		coffre = null;
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.PERSO));
		setTextureOrientation();
	}
	
	public Coordonnees getCoord() {return coord;}
	public void setCoord(Coordonnees pos) {coord = pos;}
	public int getOrientation() {return orientation;}
	public void setOrientation(int ori) 
	{
		orientation = ori;
		setTextureOrientation();
	}
	public void setColor(Color c) {couleur = c;}
	public Color getColor() {return couleur;}
	public void setTextureOrientation() {sprite.setTextureRect(new IntRect(1+82*orientation, 1, 81, 81));}
	public Chest getChest() {return coffre;}
	public void setChest(Chest c) {coffre = c;}
	public void setMain (Procedure m) {main = m;}
	public Procedure getMain() {return main;}
	
	/*
	public void use_Actions() 
	{
		Action a;
		int i=0;
		while (!lActions.isEmpty()) 
		{
			a=lActions.get(i);
			lActions.remove(i);
			i++;
			this.use_Action(a);
		}
	}
	*/
	
	/**
	 *  Effectue l'action pour le personnage
	 * @param a Action a effectuer par le personnage
	 */
	public void use_Action(Action a) {a.execute(this);}
}