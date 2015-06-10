package Prog;
import Entities.Block;
import Entities.Character;
import Entities.LightBlock;
import Game.World;

public class Light extends Action
{
	public Light(Color c) {couleur = c;}

	public void execute(Character p) 
	{
		if ( (p.getColor() == Color.DEFAUT) || (p.getColor() == couleur) )
		{
			Block b = World.WORLD.getUnderBlock(p.getCoord());
			if (b != null)
				if (b instanceof LightBlock)
					((LightBlock) b).reverseLight();
		}
	}
}
