package Prog.NormalActions;
import Entities.Character;
import Entities.Chest;
import Prog.Action;
import Prog.Color;

public class UseChest extends Action 
{
	public UseChest(Color c) {super(c);}

	public boolean execute(Character p) 
	{

		Chest c = p.getChest();
		if (c != null) 
		{
			Action a = c.getAction();
			p.setActionCourante(null);
			p.use_Action(a);
			//a.execute(p);
			//p.setChest(null); //TODO A réflechir
		}
				return false;
	}
}
