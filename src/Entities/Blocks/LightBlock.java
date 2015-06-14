package Entities.Blocks;

import org.jsfml.graphics.IntRect;

import Entities.Block;
import Entities.Character;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Prog.Coordonnees;

public class LightBlock extends Block
{
	private boolean isOn = false;
	
	/**
	 * Cree un bloc lumiere eteind. Peut etre allume.
	 * @param pos Coordonnees de ce bloc
	 */
	public LightBlock(Coordonnees pos)
	{
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK));
		coord = pos;
	
		initialiser();
	}
	
	public void initialiser() {sprite.setTextureRect(new IntRect(1, 83, 81, 81));}
	
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
