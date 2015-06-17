package Prog;

import java.util.ArrayList;
import java.util.List;

public class Procedure extends Prog 
{
	private List<Prog> l;
	private TypeProcedure t;
	
	public Procedure(Color c, TypeProcedure type) 
	{
		couleur = c;
		t = type ;
		l = new ArrayList<Prog>();
	}
	
	public Procedure(Procedure p) 
	{
		couleur = p.getColor();
		t = p.getTypeProcedure();
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
	
	public TypeProcedure getTypeProcedure() {return t;}
	public void setTypeProcedure(TypeProcedure type) {t = type;}
	public List<Prog> getListProcedure() {return l;}
	
	/**
	 * Ajout d'une Prog à la procédure
	 * @param p : La Prog à ajouter (clonée)
	 */
	public void addProg(Prog p) 
	{
		try {l.add((Prog) p.clone());} 
		catch (CloneNotSupportedException e) {e.printStackTrace();}
	}
}
