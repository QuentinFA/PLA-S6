package Entities;

import java.util.List;

import Actions.*;


public class Character extends Entities
{	
	private List<Action> lActions;
	private int orientation; //0: haut, 1: droite, 2: bas, 3: gauche 
	private Color.COLOR couleur;
	
	Character(Coordonnees pos, int ori) 
	{
		coord = pos;
		orientation = ori;
		couleur = Color.COLOR.DEFAUT;
	}
	
	public Coordonnees getCoord() {return coord;}
	public void setCoord(Coordonnees pos) {coord = pos;}
	public int getOrientation() {return orientation;}
	public void setOrientation(int ori) {orientation = ori;}
	public void setColor(Color.COLOR c) {couleur = c;}
	public Color.COLOR getColor() {return couleur;}
	
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
	 * @param a Action a� effectuer par le personnage
	 * @throws OutOfMapException Exception si le personnage sors de la map a�l'issue de son deplacement
	 */
	public void use_Action(Action a) 
	{	
		a.execute(this);
		//TODO Animation Et gérer exception si on sort de la map
	}
}