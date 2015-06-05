package Game;

import org.jsfml.graphics.IntRect;

import Actions.Coordonnees;

public class LightBlock extends Block {
	
	private boolean isOn;
	
	/**
	 * Crée un bloc lumière éteind. Peut être allumé.
	 * @param pos : Coordonnées de ce bloc
	 */
	public LightBlock(Coordonnees pos)
	{
		sprite.setTexture(Ressources.RESSOURCES.getTexture1());
		sprite.setTextureRect(new IntRect(1, 1, 81, 81));
				
		position = pos;
		isOn = false;
		
		//sprite.setPosition(World.WORLD.placeMe(pos));
	}

	public boolean getLight() {return this.isOn;}
	
	public void setLight(boolean lumiere) {this.isOn = lumiere;}
	
}
