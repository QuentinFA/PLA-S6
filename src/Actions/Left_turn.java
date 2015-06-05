package Actions;
import Entities.Character;

public class Left_turn 
{
	public Left_turn() {}
	
	/**
	 * Tourne le character
	 * @param p Character à tourner
	 */
	public void execute(Character p)  {p.setOrientation((p.getOrientation()-1) % 4);}
}
