package Prog;
import Entities.Character;

public class Left_turn extends Action
{
	public Left_turn(Color c) {couleur = c;}

	public void execute(Character p)  
	{
		if ( (p.getColor() == Color.DEFAUT) || (p.getColor() == couleur) )
		{
			p.setOrientation((4+p.getOrientation()-1) % 4);
		}
	}
}
