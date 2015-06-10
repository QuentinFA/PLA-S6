package Prog;
import Entities.Character;

public class PersonnageIndice {
	
	private Character p;
	private int index;
	
	public PersonnageIndice(Character c, int i) 
	{
		p = c;
		index = i ;
	}
	
	public int getIndex() {return index;}
	public void setIndex(int i) {index = i;}
	public Character getCharacter() {return p;}
	public void setCharacter(Character c) {p = c;}
}
