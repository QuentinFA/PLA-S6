package Entities;

import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;

public abstract class Block extends Entities
{

	public Vector2f getPosSprite() {return sprite.getPosition();}
	public void setPosSprite(Vector2f pos) {sprite.setPosition(pos);}
	public FloatRect getGlobalBounds() {return sprite.getGlobalBounds();}
		
	public abstract void perform(Character p);
}
