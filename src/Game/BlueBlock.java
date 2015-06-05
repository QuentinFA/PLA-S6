package Game;

import org.jsfml.graphics.IntRect;

import Actions.Coordonnees;

public class BlueBlock extends Block {
	/**
	 * Crée un bloc qui colorie le personnage en bleu 
	 * @param pos : Coordonnées de ce bloc
	 */
	public BlueBlock(Coordonnees pos)
	{
		sprite.setTexture(Ressources.RESSOURCES.getTexture1());
		sprite.setTextureRect(new IntRect(250, 1, 81, 81));
				
		position = pos;
		
		//sprite.setPosition(World.WORLD.placeMe(pos));
	}
}

