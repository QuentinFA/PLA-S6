package Actions;
import Entities.Block;
import Entities.Character;
import Entities.LightBlock;
import Game.World;

public class Light extends Action
{
	public Light(Color.COLOR c) {couleur = c;}
	
	public void execute(Character p) 
	{
		Block b = World.WORLD.getUnderBlock(p.getCoord());
		if (b != null)
			if (b instanceof LightBlock)
				((LightBlock) b).reverseLight();
	}
}
