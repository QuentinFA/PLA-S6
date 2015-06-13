package Prog.NormalActions;
import Entities.Character;
import Prog.Action;
import Prog.Color;

public class For extends Action 
{	
	private int nbBouclesRestantes; //Nombre d'iteration
	
	public For(Color c) {couleur = c; nbBouclesRestantes = 1;}
	public int getForValue() {return nbBouclesRestantes;}
	
	public void incrementer()
	{
		nbBouclesRestantes += 1;
		if (nbBouclesRestantes == 10)
			nbBouclesRestantes = 1;
	}
	public void decrementer() {nbBouclesRestantes -= 1;}
	
	public boolean isZero()
	{
		if (nbBouclesRestantes <= 0)
			return true;
		else 
			return false;
	}
	
	public boolean execute(Character p) {return false;}
}
