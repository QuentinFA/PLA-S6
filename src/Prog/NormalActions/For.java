package Prog.NormalActions;
import Entities.Character;
import Prog.Action;
import Prog.Color;

/**
 * Concept de la boucle For
 * @author edwin
 *
 */
public class For extends Action 
{	
	private int nbBouclesRestantes; //Nombre d'iteration
	private int nbBoucles;
	
	public For(Color c) {couleur = c; nbBouclesRestantes = 1; nbBoucles = 1;}
	public For(Color c, int valeur){couleur= c; nbBoucles = valeur; nbBouclesRestantes = valeur;}
	
	public int getForValue() {return nbBouclesRestantes;}
	
	/**
	 * Incrémente le nombre de boucles restantes
	 */
	public void incrementer()
	{
		nbBouclesRestantes += 1;
		nbBoucles = nbBouclesRestantes;
		if (nbBouclesRestantes == 10)
			{
			nbBouclesRestantes = 1;
			nbBoucles = nbBouclesRestantes;
			}
	}
	
	/**
	 * Décrémente le nombre de boucles restantes
	 */
	public void decrementer() {nbBouclesRestantes -= 1;}
	
	/**
	 * Vérification du nombre de boucle et réinisialisation si = 0
	 * @return Si le nombre de boucles restantes est à 0 ou non
	 */
	public boolean isZero()
	{
		if (nbBouclesRestantes <= 0)
		{
			nbBouclesRestantes = nbBoucles;
			return true;
		}
		else 
			return false;
	}
	
	public boolean execute(Character p) {return false;}
	
	public void reset() {nbBouclesRestantes = nbBoucles;}
}
