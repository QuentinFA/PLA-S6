package Prog;
import Entities.Character;

public class Right_turn extends Action
{
	public Right_turn(Color c) {couleur = c;}
	
	public void execute(Character p)  
	{
		p.setOrientation((p.getOrientation()+1) % 4);
	}
}
