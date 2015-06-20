package Prog.NormalActions;
import Entities.Character;
import Prog.Action;
import Prog.Color;

/**
 * Action pour faire tourner a gauche le personnage
 *
 */
public class Right_turn extends Action
{
	public Right_turn(Color color)
	{
		super(color);
	}

	public boolean execute(Character p)  
	{
		p.setOrientation((p.getOrientation()+1) % 4);
		
		return true;
	}
}
