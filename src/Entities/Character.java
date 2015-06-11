package Entities;


import java.util.List;
import java.util.Iterator;

import org.jsfml.graphics.IntRect;

import Game.Ressources;
import Game.Ressources.TEXTURE;
import Prog.*;
import Structures.LIFO;


public class Character extends Entities
{	
	private int orientation; //0: haut, 1: droite, 2: bas, 3: gauche, voir Orientation.java
	private Color couleur;
	private Coordonnees moving_coord;
	private Chest coffre;
	private List<Prog> main;
	//private TypeCharacter type;
	private Action actionCourante;
	LIFO<Iterator<Prog>> pile = new LIFO<Iterator<Prog>>();
	
	public Character(Coordonnees pos, int ori, TypeCharacter t) 
	{
		coord = pos;
		moving_coord = new Coordonnees(pos);
		
		orientation = ori;
		couleur = Color.DEFAUT;
		coffre = null;
		actionCourante = null;
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
	//public void setType (TypeCharacter m) {type = m;}
	//public TypeCharacter getType() {return type;}
	public void setActionCourante(Action a) {this.actionCourante = a;}
	public Action getAction () {return actionCourante;}
	public void setMain(List<Prog> l) {
		this.main = l;
		Iterator<Prog> it = main.iterator();
		pile.put(it);
	}
	public List<Prog> getMain(){
			return main;
	}
	public void setMovingCoord(Coordonnees c) {moving_coord = c;}
	public Coordonnees getMovingCoord() {return moving_coord;}
	public void setTextureRect(IntRect rect) {sprite.setTextureRect(rect);}
	
	public boolean gerer() 
	{
		if (this.actionCourante != null)
		{
			
			if (actionCourante.execute(this))
			{
				actionCourante = null;
				return true;
			}
		}
		return true;
	}
	
	

	/**
	 *  Effectue l'action pour le personnage
	 * @param a Action a effectuer par le personnage
	 * @throws CloneNotSupportedException 
	 */
	public void use_Action(Action a)
	{
		try
		{
			if (actionCourante == null)
				actionCourante = (Action) a.clone();
		}
		catch (CloneNotSupportedException e) 
		{
			e.printStackTrace();
		}
	}
}