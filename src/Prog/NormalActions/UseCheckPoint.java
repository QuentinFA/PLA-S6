package Prog.NormalActions;

import Entities.Character;
import Game.World;
import Prog.Action;
import Prog.Color;
import Prog.Coordonnees;

public class UseCheckPoint extends Action 
{
	public UseCheckPoint(Color color)
	{
		super(color);
	}
	
	public boolean execute(Character p)
	{
		p.setCoord(new Coordonnees(p.getCheckPoint()));
		p.setPosSprite(World.WORLD.placeMe(p.getCoord()));
		
		return true;
	}
}
