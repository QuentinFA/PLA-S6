package Game;

import java.util.ArrayList;
import java.util.List;
import Entities.Character;


public class Controler 
{
	public class PersonnageTravaille 
	{
		public Character p;
		public boolean travail;
		
		public PersonnageTravaille(Character ch, boolean b) 
		{
			p = ch;
			travail = b;
		}
	}
	
	List<PersonnageTravaille> lPT;
	public static Controler CONTROLER;
	
	public Controler() 
	{
		CONTROLER = this;
		lPT = new ArrayList<PersonnageTravaille>();
	}
	
	public void addCharacter(Character p)
	{
		for (PersonnageTravaille pt : lPT) {
			if(pt.p == p) 
			{
				pt.travail = true;
				return;
			}
		}
		lPT.add(new PersonnageTravaille(p,false));
	}
	
	public void manage(Character p) {
		addCharacter(p);
		for (PersonnageTravaille pt : lPT) {
			if(pt.travail == true)
				return;
		}
		
		for (PersonnageTravaille pt : lPT) {
			pt.p.next();
			pt.travail = true;
		}
	}

}
