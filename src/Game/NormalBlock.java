package Game;

import org.jsfml.graphics.IntRect;

import Actions.Coordonnees;
import Entities.Character;

public class NormalBlock extends Block {
	
	/**
	 * Crée un bloc normal (gris)
	 * @param pos : Coordonnées de ce bloc
	 */
	public NormalBlock(Coordonnees pos)
	{
		sprite.setTexture(Ressources.RESSOURCES.getTexture1());
		sprite.setTextureRect(new IntRect(1, 1, 81, 81));
				
		position = pos;
		
		sprite.setPosition(World.WORLD.placeMe(pos));
	}
	
	public void perform(Character p) {
		//Ne fait rien
	}
}
