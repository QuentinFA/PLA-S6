package Prog.NormalActions;
import Entities.Character;
import Prog.Action;
import Prog.Color;

/**
 * Action pour faire tourner a gauche le personnage
 *
 */
public class Left_turn extends Action
{
	public Left_turn(Color color)
	{
		super(color);
	}

	private int frame = 0;
	private int last_frame = 10;
	
	public boolean execute(Character p) 
	{
		if (frame == 0)
			p.setOrientation((4+p.getOrientation()-1) % 4);
		frame ++;
		
		return frame == last_frame;
	}
}
