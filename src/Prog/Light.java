package Prog;
import Entities.Character;
import Entities.Entities;
import Entities.LightBlock;
import Game.World;

public class Light extends Action
{
	public Light(Color c) {couleur = c;}

	public void execute(Character p) 
	{
		if (p.getColor() == Color.DEFAUT || p.getColor() == couleur)
		{
			Entities b = World.WORLD.getEntitiesAt(new Coordonnees(p.getCoord().getX(), p.getCoord().getY(), p.getCoord().getZ()-1));
			if (b != null)
				if (b instanceof LightBlock)
					((LightBlock) b).reverseLight();
		}
	}
}
