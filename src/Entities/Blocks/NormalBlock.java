package Entities.Blocks;

import org.jsfml.graphics.IntRect;

import Entities.Block;
import Entities.Character;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Prog.Coordonnees;

public class NormalBlock extends Block 
{
	public NormalBlock(Coordonnees pos)
	{
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK));
		sprite.setTextureRect(new IntRect(1, 1, 81, 81));
		
		coord = pos;
	}
	
	public void perform(Character p) {} //Ne fait rien
}
