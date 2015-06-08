package Actions;
import Entities.Character;

public class Right_turn extends Action
{
	public Right_turn(Color c) {couleur = c;}
	
	/**
	 * Tourne le character
	 * @param p Character a�tourner
	 */
	public void execute(Character p)  
	{
		if (p.getColor() == couleur)
			p.setOrientation((p.getOrientation()+1) % 4);
	}
}
