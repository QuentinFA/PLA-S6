package Entities;


import java.util.List;
import java.util.ListIterator;

import org.jsfml.graphics.IntRect;

import Game.Controler;
import Game.Interpreter;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Prog.Action;
import Prog.Color;
import Prog.Coordonnees;
import Prog.Procedure;
import Prog.Prog;
import Structures.LIFO;


public class Character extends Entities
{	
	private int orientation; //0: haut, 1: droite, 2: bas, 3: gauche, voir Orientation.java
	private Color couleur;

	private Chest coffre;
	private Procedure main;
	//private TypeCharacter type;
	private Action actionCourante;
	LIFO<ListIterator<Prog>> pile = new LIFO<ListIterator<Prog>>();

	public Character(Coordonnees pos, int ori) 
	{
		coord = pos;

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
	public void setMain(Procedure l) {
		this.main = l;
		List<Prog>t =l.getListProcedure();
		ListIterator<Prog> it = t.listIterator();
		pile.put(it);
	}
	public Procedure getMain(){
		return main;
	}

	public LIFO<ListIterator<Prog>> getPile(){
		return pile;
	}

	public void setTextureRect(IntRect rect) {sprite.setTextureRect(rect);}

	public boolean gerer() 
	{
		if (this.actionCourante != null)
		{
			if (actionCourante.execute(this))
			{
				Controler.CONTROLER.manage(this);
				actionCourante = null;
				return true;
			}
		}

		else 
			Controler.CONTROLER.manage(this);

		return true;
	}

	public void next() 
	{
		Action a = Interpreter.INTERPRETER.eval(this);
		if(a!=null)
		{
			use_Action(a);
		}
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