package Game;

import org.jsfml.graphics.IntRect;

import Actions.Coordonnees;
import Entities.Character;
import Game.Ressources.TEXTURE;

public class LightBlock extends Block 
{
	private boolean isOn;
	
	/**
	 * Cree un bloc lumiere eteind. Peut etre allume.
	 * @param pos : Coordonnees de ce bloc
	 */
	public LightBlock(Coordonnees pos)
	{
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK));
		sprite.setTextureRect(new IntRect(1, 83, 81, 81));
		
		coord = pos;
		isOn = false;
	}
	
	public boolean getLight() {return isOn;}
	
	public void reverseLight() 
	{
		isOn = !isOn;
		if (isOn)
			sprite.setTextureRect(new IntRect(83, 83, 81, 81));  
		else
			sprite.setTextureRect(new IntRect(1, 83, 81, 81));
	}
	
	public void perform(Character p) {reverseLight();}
}
