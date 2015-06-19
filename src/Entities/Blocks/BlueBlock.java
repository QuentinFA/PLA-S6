package Entities.Blocks;
import org.jsfml.graphics.IntRect;

import Entities.ColorBlock;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Prog.Color;
import Prog.Coordonnees;

public class BlueBlock extends ColorBlock 
{
	
	public BlueBlock(Coordonnees c)
	{
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK));
		sprite.setTextureRect(new IntRect(247, 1, 81, 81));
		
		coord = c;
		this.color = Color.BLEU;
	}
}