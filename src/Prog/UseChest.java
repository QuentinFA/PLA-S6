package Prog;
import Entities.Character;
import Entities.Chest;

public class UseChest extends Action 
{
	public UseChest(Color c) {couleur = c;}
	private int frame = 0;
	private int last_frame = 18;
	
	public boolean execute(Character p) 
	{
			
		if (frame == 0) //Initialisation
		{
			Chest c = p.getChest();
			if (c != null) 
			{
				Action a = c.getAction();
				a.execute(p);
				//p.setChest(null); //TODO A réflechir
			}
		}
		else if (frame == 8)
		{
			
		}
		else if (frame == 16)
		{
			
		}
		//TODO other animation
		frame ++;
		
		return frame == last_frame;
	}
}
