package Entities;

import org.jsfml.graphics.IntRect;

import Actions.Color;
import Actions.Coordonnees;
import Game.Ressources;
import Game.Ressources.TEXTURE;

public class RedBlock extends Block
{
	/**
	 * Cree un bloc qui colorie le personnage en rouge quand il marche dessus
	 * @param pos : Coordonnees de ce bloc
	 */
	public RedBlock(Coordonnees pos)
	{
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK));
		sprite.setTextureRect(new IntRect(83, 1, 81, 81));
		
		coord = pos;
	}
	
	public void perform(Character p) {p.setColor(Color.COLOR.ROUGE);}
}


