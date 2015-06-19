package Prog.NormalActions;
import Entities.Character;
import Prog.Action;
import Prog.Color;

public class Right_turn extends Action
{
	public Right_turn(Color c) {super(c);}

	public boolean execute(Character p)  
	{
		p.setOrientation((p.getOrientation()+1) % 4);
		
		return true;
	}
}
