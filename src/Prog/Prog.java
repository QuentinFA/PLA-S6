package Prog;

import java.util.ArrayList;
import java.util.List;

/**
 * Permet de faire la liste d'actions a effectuer par le personnage. Une prog est soit une Action, soit une Procedure contenant une liste de Prog.
 * @author edwin
 *
 */
public abstract class Prog implements Cloneable
{
	protected Color couleur; // Couleur de l'action ou procédure
	
	public Color getColor() {return couleur;}
	public void setColor(Color c) {couleur = c;}
	
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
