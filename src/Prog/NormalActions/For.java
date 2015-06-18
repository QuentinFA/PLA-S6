package Prog.NormalActions;
import java.util.HashMap;

import Entities.Character;
import Prog.Action;
import Prog.Color;

public class For extends Action 
{
	private int nbBoucles;
	private HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	
	public For(Color c) {couleur = c; nbBoucles = 1;}
	
	public int getForValue() {return nbBoucles;}
	
	/**
	 * Incrémente le nombre de boucles restantes
	 */
	public void incrementer()
	{
		nbBoucles += 1;
		if (nbBoucles == 10)
			nbBoucles = 1;
	}
	
	/**
	 * Décrémente le nombre de boucles restantes
	 */
	public void decrementer(Character p) 
	{
		if (map.containsKey(p))
			map.put(p, map.get(p) - 1);
		else
			map.put(p, nbBoucles - 1);
	}
	
	/**
	 * Vérification du nombre de boucle et réinisialisation si = 0
	 * @return Si le nombre de boucles restantes est à 0 ou non
	 */
	public boolean isZero(Character p)
	{
		if (map.containsKey(p) && map.get(p) <= 0)
		{
			map.remove(p);
			return true;
		}
		else 
			return false;
	}
	
	public boolean execute(Character p) {return false;}
	
	public void reset() {map.clear();}
}
