package Prog;
import Entities.Character;

public class Right_turn extends Action
{
	public Right_turn(Color c) {couleur = c;}

	public void execute(Character p)  
	{
		if (p.getColor() == Color.DEFAUT || p.getColor() == couleur)
			p.setOrientation((p.getOrientation()+1) % 4);
	}
}
