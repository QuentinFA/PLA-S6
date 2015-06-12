package Prog;
import Entities.Character;

public class For extends Action {
	
	private int nbBouclesRestantes; //nombre d'it�ration
	
	public For(int i) {nbBouclesRestantes = i;}
	public int getForValue(){return nbBouclesRestantes;}
	public void setFor(int a){this.nbBouclesRestantes = a;}
	
	public void decrementer()
	{
		this.nbBouclesRestantes -= 1;
	}
	
	public boolean isZero(){
		if (nbBouclesRestantes<=0)
			return true;
		else 
			return false;
	}
	
	public boolean execute(Character p) {return false;}
}
