package Game;
import org.jsfml.graphics.IntRect;

import Actions.Color;
import Actions.Coordonnees;
import Entities.Character;

public class BlueBlock extends Block {
	
	/**
	 * Crée un bloc qui colorie le personnage en bleu 
	 * @param pos : Coordonnées de ce bloc
	 */
	public BlueBlock(Coordonnees pos)
	{
		sprite.setTexture(Ressources.RESSOURCES.getTexture1());
		sprite.setTextureRect(new IntRect(247, 1, 81, 81));
		
		position = pos;
		
		//sprite.setPosition(World.WORLD.placeMe(pos));
	}
	
	public void perform(Character p) {
		int c = Color.BLEU;
		p.setColor(c);
		//TODO Colorer personnage
	}
	
}

