package Prog;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.graphics.Sprite;

/**
 * Permet d'identifier les differentes procedures (Main,P1,P2, Fork...)
 *
 */
public class Procedure extends Prog 
{
	private List<Prog> l; //Liste des procedures et actions contenu dans cette Procedure.
	private int index;
	public int getIndex() {return index;}
	
	public Procedure(Color color, int i) 
	{
		super(color);
		index = i;
		l = new ArrayList<Prog>();
	}
	
	public Procedure(Procedure p) 
	{
		super(p.getColor());
		index = p.getIndex();
		l = new ArrayList<Prog>();
		for(Prog pr : p.getListProcedure())
		{
			try
			{
				l.add((Prog) pr.clone());
			}
			catch (CloneNotSupportedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public List<Prog> getListProcedure() {return l;}
	
	/**
	 * Ajout d'une Prog à la procédure
	 * @param p : La Prog à ajouter (clonée)
	 */
	public void addProg(Prog p, Sprite spr) 
	{
		try 
		{
			l.add((Prog) p.clone());
			if (l.get(l.size()-1) instanceof Action)
				((Action) l.get(l.size()-1)).setSprite(spr);
		} 
		catch (CloneNotSupportedException e) {e.printStackTrace();}
	}
}
