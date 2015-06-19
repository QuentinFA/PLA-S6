package Prog.NormalActions;
import Entities.Character;
import Entities.Chest;
import Prog.Action;
import Prog.Color;

/**
 * Action permettant d'utiliser l'action contenu dans le coffre du personnage
 * @author edwin
 *
 */
public class UseChest extends Action 
{
	public UseChest(Color c) {couleur = c;}

	/**
	 * Si le personnage p a un Chest sur lui, cela utilise l'action contenu dans ce Chest.
	 */
	public boolean execute(Character p) 
	{
		Chest c = p.getChest();
		if (c != null) 
		{
			Action a = c.getAction();
			//a.setSprite(c.getSprite());
			p.setActionCourante(null);
			p.use_Action(a);
		}
		return false;
	}
}
