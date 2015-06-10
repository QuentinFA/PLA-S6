package Prog;
import Entities.Character;
import Entities.Chest;

public class useChest extends Action 
{
	public useChest(Color c) {couleur = c;}
	
	public void execute (Character p) {
		Chest c = p.getChest();
		if (c != null) {
			Action a = c.getAction();
			a.execute(p);
		}
	}
}