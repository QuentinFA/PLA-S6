package Actions;
import Entities.Character;

public class Left_turn extends Action
{
	public Left_turn(int color) {c = color;}
	
	/**
	 * Tourne le character
	 * @param p Character à tourner
	 */
	public void execute(Character p)  {p.setOrientation((p.getOrientation()-1) % 4);}
	
	//Inutile
	public Coordonnees execute(Coordonnees p, int orientation) {return p;}
}
