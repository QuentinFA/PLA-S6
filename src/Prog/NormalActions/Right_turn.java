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
	
	private int frame = 0;
	private int last_frame = 10;

	public boolean execute(Character p)  
	{
		if (frame == 0)
			p.setOrientation((p.getOrientation()+1) % 4);
		frame ++;
		
		return frame == last_frame;
	}
}
