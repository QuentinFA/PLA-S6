package Game;
import org.jsfml.graphics.IntRect;

import Actions.Color;
import Actions.Coordonnees;
import Entities.Character;
import Game.Ressources.TEXTURE;

public class BlueBlock extends Block {
	
	/**
	 * Cree un bloc qui colorie le personnage en bleu 
	 * @param pos : Coordonnees de ce bloc
	 */
	public BlueBlock(Coordonnees c)
	{
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK));
		sprite.setTextureRect(new IntRect(247, 1, 81, 81));
		
		coord = c;
	}
	
	public void perform(Character p) {p.setColor(Color.COLOR.BLEU);}
}

