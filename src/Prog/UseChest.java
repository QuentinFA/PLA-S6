package Prog;
import Entities.Character;
import Entities.Chest;

public class UseChest extends Action 
{
	public UseChest(Color c) {couleur = c;}

	public void execute (Character p) {
		if (p.getColor() == Color.DEFAUT || p.getColor() == couleur)
		{
			Chest c = p.getChest();
			if (c != null) 
			{
				Action a = c.getAction();
				a.execute(p);
			}
		}
	}
}
