package Prog;

import java.util.ArrayList;
import java.util.List;

public abstract class Prog implements Cloneable
{
	protected Color couleur; // Couleur de l'action ou procédure
	
	public Color getColor() {return couleur;}
	public void setColor(Color c) {couleur = c;}
	
	public Prog(Color c)
	{
		this.couleur = c;
	}
	
	public Object clone() throws CloneNotSupportedException {return super.clone();}
	
	public static List<Procedure> clone_actionList(List<Procedure> l)
	{
		List<Procedure> result = new ArrayList<Procedure>();
		for (int i=0; i < l.size(); i++)
			result.add(new Procedure(l.get(i)));	
		
		for (int i=0; i < result.size(); i++)
		{
			for (int j=0; j < result.get(i).getListProcedure().size(); j++)
			{
				if (result.get(i).getListProcedure().get(j) instanceof Procedure)
				{
					int index = ((Procedure) result.get(i).getListProcedure().get(j)).getIndex();
					result.get(i).getListProcedure().set(j, result.get(index));
				}
			}
		}
		
		return result;
	}
}
