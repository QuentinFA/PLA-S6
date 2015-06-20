package Entities.Blocks;

import org.jsfml.graphics.IntRect;
import Entities.Character;

import Entities.Block;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Prog.Coordonnees;

/**
 * Bloc de base quelconque. Represente par un carre gris.
 *
 */
public class NormalBlock extends Block 
{
	public NormalBlock(Coordonnees pos)
	{
		super(pos);
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK));
		sprite.setTextureRect(new IntRect(1, 1, 81, 81));
	}
	
	/**
	 * Aucun effet.
	 */
	public void perform(Character p) {}
}
