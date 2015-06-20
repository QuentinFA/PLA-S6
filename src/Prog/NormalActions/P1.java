package Prog.NormalActions;

import Entities.Character;
import Prog.Action;
import Prog.Color;

//TODO : Expliquez moi a quoi ca sert svp. Le personnage a une liste de Prog et on a deja le type Procedure. Florian/Anna
// Il faut bien avoir une action qui existe à ajouter, non ?
public class P1 extends Action{

	public P1(Color color)
	{
		super(color);
	}

	@Override
	public boolean execute(Character p) {

		return false;
	}
}
