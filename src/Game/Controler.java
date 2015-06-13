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
	
	List<PersonnageTravaille> lPT = new ArrayList<PersonnageTravaille>();
	public static Controler CONTROLER = new Controler();
	
	public void addCharacter(Character p)
	{
		for (PersonnageTravaille pt : lPT)
			if (pt.p == p) 
			{
				pt.travail = false;
				return;
			}

		lPT.add(new PersonnageTravaille(p, false));
	}
	
	public void manage(Character p) 
	{
		addCharacter(p);
		
		for (PersonnageTravaille pt : lPT)
			if (pt.travail == true)
				return;
		
		for (PersonnageTravaille pt : lPT) {
			try {
				pt.p.next();
			}catch (StackOverflowError e) {
				//TODO Affichier message à l'utilisateur : erreur, boucle infinie
			}
			pt.travail = true;
		}
	}

}
