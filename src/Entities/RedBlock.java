package Entities;

import org.jsfml.graphics.IntRect;

import Actions.Color;
import Actions.Coordonnees;
import Game.Ressources;
import Game.Ressources.TEXTURE;

public class RedBlock extends Block
{
	public RedBlock(Coordonnees pos)
	{
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK));
		sprite.setTextureRect(new IntRect(83, 1, 81, 81));
		
		coord = pos;
	}
	
	public void perform(Character p) {p.setColor(Color.ROUGE);}
}