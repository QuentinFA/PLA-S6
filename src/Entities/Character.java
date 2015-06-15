package Entities;

import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import org.jsfml.graphics.IntRect;

import Game.Controler;
import Game.Interpreter;
import Game.Ressources;
import Game.World;
import Game.Ressources.TEXTURE;
import Prog.Action;
import Prog.Color;
import Prog.Coordonnees;
import Prog.Procedure;
import Prog.Prog;

public class Character extends Entities
{	
	private int orientation; //0: haut, 1: droite, 2: bas, 3: gauche, voir Orientation.java
	private Color couleur = Color.ROUGE;
	private Chest coffre = null;
	private Action actionCourante = null;
	private int compteurActions;
	Stack<ListIterator<Prog>> pile = new Stack<ListIterator<Prog>>();
	

	public Character(Coordonnees pos, int ori) 
	{
		coord = pos;
		orientation = ori;
		compteurActions = 0;
		
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.PERSO));
		setTextureOrientation();
	}
	public void setTextureOrientation() {sprite.setTextureRect(new IntRect(247, 329+82*orientation, 81, 81));}

	public Coordonnees getCoord() {return coord;}
	public void setCoord(Coordonnees pos) {coord = pos;}
	public int getOrientation() {return orientation;}
	public void setOrientation(int ori) {orientation = ori;}
	public void setColor(Color c) {couleur = c;}
	public Color getColor() {return couleur;}
		public Chest getChest() {return coffre;}
	public void setChest(Chest c) {coffre = c;}
	public void setActionCourante(Action a) {actionCourante = a;}
	public Action getAction () {return actionCourante;}
	public void incrementNbActions() {this.compteurActions++;}
	public int getNbActions() {return this.compteurActions;}
	public void setMain(Procedure l) 
	{
		List<Prog> t = l.getListProcedure();
		ListIterator<Prog> it = t.listIterator();
		pile.clear();
		pile.push(it);
	}

	public Stack<ListIterator<Prog>> getPile() {return pile;}

	public void setTextureRect(IntRect rect) {sprite.setTextureRect(rect);}

	public boolean gerer() 
	{
		if (World.WORLD.isPlaying())
		{
			if (actionCourante != null)
			{
				if (actionCourante.execute(this))
				{
					actionCourante = null;
					Controler.CONTROLER.manage(this);
				}
			}
			else 
				Controler.CONTROLER.manage(this);
			
			if (actionCourante == null)
				setTextureOrientation();
		}

		return true;
	}

	public void next()
	{
		Action a=null;
		try {a = Interpreter.INTERPRETER.eval(this);}
		catch(StackOverflowError e) 
		{throw new StackOverflowError();}
		
		if (a!=null)
			use_Action(a);
	}

	/**
	 *  Effectue l'action pour le personnage
	 * @param a Action a effectuer par le personnage
	 * @throws CloneNotSupportedException 
	 */
	public void use_Action(Action a)
	{
		try {actionCourante = (Action) a.clone();}
		catch (CloneNotSupportedException e) {e.printStackTrace();}
	}
}