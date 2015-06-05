package Game;

import org.jsfml.graphics.IntRect;

import Actions.Color;
import Actions.Coordonnees;
import Entities.Character;

public class LightBlock extends Block {
	
	private boolean isOn;
	
	/**
	 * Crée un bloc lumière éteind. Peut être allumé.
	 * @param pos : Coordonnées de ce bloc
	 */
	public LightBlock(Coordonnees pos)
	{
		sprite.setTexture(Ressources.RESSOURCES.getTexture1());
		sprite.setTextureRect(new IntRect(1, 83, 81, 81));
				
		position = pos;
		isOn = false;
		
		//sprite.setPosition(World.WORLD.placeMe(pos));
	}

	public boolean getLight() {return this.isOn;}
	
	public void reverseLight() {
		this.isOn = !this.isOn;
		if(this.isOn)
			sprite.setTextureRect(new IntRect(83, 83, 81, 81));  
		else
			sprite.setTextureRect(new IntRect(1, 83, 81, 81));
	}
	
	public void perform(Character p) {
		this.reverseLight();
	}
	
}
