package Game;

import org.jsfml.graphics.IntRect;

import Actions.Coordonnees;
import Entities.Character;
import Game.Ressources.TEXTURE;

public class NormalBlock extends Block 
{
	/**
	 * Cree un bloc normal (gris)
	 * @param pos : Coordonnees de ce bloc
	 */
	public NormalBlock(Coordonnees pos)
	{
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK));
		sprite.setTextureRect(new IntRect(1, 1, 81, 81));
		
		coord = pos;
	}
	
	public void perform(Character p) {} //Ne fait rien
}
