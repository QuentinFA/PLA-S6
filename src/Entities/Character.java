package Entities;

import java.util.List;

import org.jsfml.graphics.IntRect;

import Actions.*;
import Game.Ressources;
import Game.Ressources.TEXTURE;


public class Character extends Entities
{	
	private List<Action> lActions;
	private int orientation; //0: haut, 1: droite, 2: bas, 3: gauche, voir Orientation.java
	private Color couleur;
	
	public Character(Coordonnees pos, int ori) 
	{
		coord = pos;
		orientation = ori;
		couleur = Color.DEFAUT;
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.PERSO));
		setTextureOrientation(orientation);
	}
	
	public Coordonnees getCoord() {return coord;}
	public void setCoord(Coordonnees pos) {coord = pos;}
	public int getOrientation() {return orientation;}
	public void setOrientation(int ori) 
	{
		orientation = ori;
		setTextureOrientation(orientation);
	}
	public void setColor(Color c) {couleur = c;}
	public Color getColor() {return couleur;}
	public void setTextureOrientation(int ori) {sprite.setTextureRect(new IntRect(1+82*ori, 1, 81, 81));}
	
	public void add_action(Action a) {lActions.add(a);}
	
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
	
	/**
	 *  Effectue l'action pour le personnage
	 * @param a Action a effectuer par le personnage
	 */
	public void use_Action(Action a) 
	{	
		a.execute(this);
	}
}