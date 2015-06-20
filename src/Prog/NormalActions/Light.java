package Prog.NormalActions;
import Entities.Character;
import Entities.Entities;
import Entities.Blocks.LightBlock;
import Game.World;
import Prog.Action;
import Prog.Color;
import Prog.Coordonnees;

/**
 * Action qui allume une case lumiere sous le personnage si elle est de type LightBlock
 *
 */
public class Light extends Action
{
	public Light(Color color)
	{
		super(color);
	}
	
	private int frame = 0;
	private int last_frame = 18;
	
	/**
	 * Allume la case sous le character p si elle est de type LightBlock
	 */
	public boolean execute(Character p) 
	{
		if (frame == 0) //Initialisation
		{
			Entities b = World.WORLD.getEntitiesAt(new Coordonnees(p.getCoord().getX(), p.getCoord().getY(), p.getCoord().getZ()-1));
			if (b != null && b instanceof LightBlock)
				((LightBlock) b).reverseLight();
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
