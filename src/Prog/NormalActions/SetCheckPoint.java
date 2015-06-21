package Prog.NormalActions;

import Entities.Character;
import Prog.Action;
import Prog.Color;
import Prog.Coordonnees;

public class SetCheckPoint extends Action 
{
	public SetCheckPoint(Color color)
	{
		super(color);
	}
	
	public boolean execute(Character p)
	{
		p.setCheckPoint(new Coordonnees(p.getCoord()));
		return true;
	}
}
