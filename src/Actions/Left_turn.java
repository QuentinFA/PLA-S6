package Actions;
import Entities.Character;

public class Left_turn extends Action
{
	public Left_turn(Color c) {couleur = c;}
	
	public void execute(Character p)  
	{
		p.setOrientation((p.getOrientation()+1) % 4);
	}
}
