package Prog;
import Entities.Character;

public class PersonnageIndice {
	
	private Character p;
	private int index;
	private Procedure proc;
	
	public PersonnageIndice(Character c, int i, Procedure pr) 
	{
		p = c;
		index = i ;
		proc = pr;
	}
	
	public int getIndex() {return index;}
	public void setIndex(int i) {index = i;}
	public Character getCharacter() {return p;}
	public void setCharacter(Character c) {p = c;}
	public Procedure getProcedure() {return proc;}
	public void setProcedure(Procedure pr) {proc=  pr;}
}
