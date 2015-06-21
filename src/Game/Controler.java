package Game;

import java.util.ArrayList;
import java.util.List;
import Entities.Character;

/**
 * Classe permettant de reguler le deroulement des actions des personnages.
 *
 */
public class Controler 
{
	/**
	 * Sous-Classe permettant de faire une liste des personnages 
	 * du jeu en sachant s'ils sont en train de travailler ou non.
	 */
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
	
	List<PersonnageTravaille> lPT = new ArrayList<PersonnageTravaille>(); //Liste permettant de savoir quel Character travaille ou non.
	public static Controler CONTROLER = null;
	
	/**
	 * Ajoute un personnage dans la liste de PersonnageTravaille. Si celui-ci est deja dans la liste, cela met juste a jour pour dire qu'il ne travaille plus.
	 * @param p : Personnage a ajouter.
	 */
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
	
	/** 
	 * Si un personnage demande une action effectuer, on l'ajoute a la liste de PersonnageTravaille en disant qu'il ne travaille plus.
	 * @param p
	 */
	public void workOver(Character p)
	{
		addCharacter(p);
	}
	
	/**
	 * Tourne en boucle. Si tout les personnages ont fini de travailler, 
	 * leur demande de faire leur prochaine action. Gère aussi la création des clones.
	 */
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
