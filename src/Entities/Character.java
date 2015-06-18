package Entities;

import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import org.jsfml.graphics.IntRect;

import Game.Controler;
import Game.Interpreter;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Game.World;
import Prog.Action;
import Prog.Color;
import Prog.Coordonnees;
import Prog.Procedure;
import Prog.Prog;
import UI.Gui;

public class Character extends Entities
{	
	private int orientation; // Cf Orientation.java
	private Color couleur = Color.DEFAUT;
	private Chest coffre = null;
	private Action actionCourante = null;
	private int compteurActions;
	private List<Procedure> actionList;
	
	Stack<ListIterator<Prog>> pile = new Stack<ListIterator<Prog>>();
	Stack<Integer> pileFor = new Stack<Integer>();

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
	
	public void setColor(Color c) 
	{
		couleur = c;
		if (c == Color.BLEU)
			this.sprite.setColor(org.jsfml.graphics.Color.CYAN);
		else if (c== Color.ROUGE)
			this.sprite.setColor(org.jsfml.graphics.Color.RED);
		else if (c == Color.VERT)
			this.sprite.setColor(org.jsfml.graphics.Color.GREEN);
		else
			this.sprite.setColor(org.jsfml.graphics.Color.WHITE);
		
			
	}

	public Color getColor() {return couleur;}
	
	public Chest getChest() {return coffre;}
	public void setChest(Chest c) {coffre = c;}
	
	public void setActionCourante(Action a) {actionCourante = a;}
	public Action getAction () {return actionCourante;}
	
	public void incrementNbActions() {compteurActions++;}
	public int getNbActions() {return compteurActions;}
	
	public void setMain() 
	{
		actionList = Prog.clone_actionList(Gui.GUI.getFinalActionList());
		
		ListIterator<Prog> it;
		
		if (World.WORLD.getCharacterList().size() == 1)
			it = actionList.get(0).getListProcedure().listIterator();
		else
			it = actionList.get(actionList.size()-1).getListProcedure().listIterator();	
		
		pile.clear();
		pile.push(it);
	}

	public Stack<ListIterator<Prog>> getPile() {return pile;}
	public Stack<Integer> getPileFor() {return pileFor;}

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
					Controler.CONTROLER.workOver(this);
				}
			}
			else 
				Controler.CONTROLER.workOver(this);
			
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