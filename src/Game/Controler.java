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
	public static Controler CONTROLER = null;
	
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
	
	public void workOver(Character p)
	{
		addCharacter(p);
	}
	
	public void manage() 
	{	
		for (PersonnageTravaille pt : lPT)
			if (pt.travail == true)
				return;
		
		World.WORLD.popClone();
		
		for (PersonnageTravaille pt : lPT) 
		{
			try {pt.p.next();}
			catch (StackOverflowError e) 
			{
				//TODO Affichier message à l'utilisateur : erreur, boucle infinie
			}
			pt.travail = true;
		}
	}

}
