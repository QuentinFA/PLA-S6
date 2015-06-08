package Entities;

import Actions.*;
import Game.World;


public class Character 
{	
	//private List<Action> _l;
	
	private Coordonnees coord;
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
	
	/*public void add_action(Action a) {_l.add(a);}
	
	public void use_Actions(Character p) throws BoundException 
	{
		Action a;
		int i=0;
		while (!_l.isEmpty()) 
		{
			a=_l.get(i);
			_l.remove(i);
			i++;
			p.use_Action(a);
		}
	}*/
	
	/**
	 *  Effectue l'action pour le personnage
	 * @param a Action a  effectuer par le personnage
	 * @throws OutOfMapException Exception si le personnage sors de la map a l'issue de son deplacement
	 */
	public void use_Action(Action a)
	{
		/*Coordonnees np = a.execute(position, orientation);
		
		try
		{
			if(a instanceof Jump)
			{
				Coordonnees zm2 = a.execute(new Coordonnees(position.getX(),  position.getY(), position.getZ() - 2), orientation);
				
				if(World.WORLD.isValidPosition(np))
					this.position = np;
				//TODO Animation
				else if(World.WORLD.isValidPosition(zm2))
					this.position = zm2;
				//TODO Animation
				else
					// TODO Animation
				{}
			}
			else if(a instanceof Light)
			{
				World.WORLD.light(np);
			}
			else if(World.WORLD.isValidPosition(np))
				this.position = np;
			//TODO Animation
			else
				// TODO Animation
			{}
		} 
		catch (OutOfMapException e)
		{
			// TODO Tomber Animation
			e.printStackTrace();
		}*/
	}
}