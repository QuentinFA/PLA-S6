package Prog.NormalActions;
import Entities.Character;
import Prog.Action;
import Prog.Color;

public class Left_turn extends Action
{
	public Left_turn(Color c) {couleur = c;}

	public boolean execute(Character p) 
	{
		p.setOrientation((4+p.getOrientation()-1) % 4);
		
		return true;
	}
}
