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
	
	public TypeProcedure getTypeProcedure() {return t;}
	public void setTypeProcedure(TypeProcedure type) {t = type;}
	public List<Prog> getListProcedure() {return l;}
	public void addProg(Prog p) 
	{
		try {l.add((Prog) p.clone());} 
		catch (CloneNotSupportedException e) {e.printStackTrace();}
	}
}
