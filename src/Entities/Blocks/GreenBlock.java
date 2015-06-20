package Entities.Blocks;

import org.jsfml.graphics.IntRect;

import Entities.ColorBlock;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Prog.Color;
import Prog.Coordonnees;

/**
 * Bloc de couleur X permettant de changer un personnage dans cette même couleur X. Utilise pour le concept de ifthen/else
 *
 */
public class GreenBlock extends ColorBlock {
	public GreenBlock(Coordonnees c)
	{
		super(c, Color.VERT);
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK));
		sprite.setTextureRect(new IntRect(165, 1, 81, 81));
		
		coord = c;
	}
}


