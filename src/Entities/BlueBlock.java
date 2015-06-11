package Entities;
import org.jsfml.graphics.IntRect;

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
	}
	
	public void perform(Character p) {p.setColor(Color.BLEU);}
}

