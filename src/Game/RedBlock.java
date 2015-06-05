package Game;

import org.jsfml.graphics.IntRect;

import Actions.Color;
import Actions.Coordonnees;
import Entities.Character;

public class RedBlock extends Block{
	
	/**
	 * Crée un bloc qui colorie le personnage en rouge quand il marche dessus
	 * @param pos : Coordonnées de ce bloc
	 */
	public RedBlock(Coordonnees pos)
	{
		sprite.setTexture(Ressources.RESSOURCES.getTexture1());
		sprite.setTextureRect(new IntRect(83, 1, 81, 81));
				
		position = pos;
		
		sprite.setPosition(World.WORLD.placeMe(pos));
	}
	
	public void perform(Character p) {
		int c = Color.ROUGE;
		p.setColor(c);
		//TODO Colorer personnage
	}
}


