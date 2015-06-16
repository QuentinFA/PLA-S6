package Entities.Blocks.Chests;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import Entities.Chest;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Prog.Color;
import Prog.Coordonnees;
import Prog.NormalActions.Light;
import UI.Graphic;

public class ChestLight extends Chest 
{
	Sprite sprite_action = new Sprite();
	public ChestLight(Coordonnees pos, int ori) 
	{
		this.coord = pos;
		this.orientation = ori;
		this.action = new Light(Color.DEFAUT);
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.CHEST));
		sprite_action.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.CHEST));
		setTextureChest();
	}
	public void setPosSprite(Vector2f pos) 
	{
		sprite.setPosition(pos);
	    sprite_action.setPosition(pos.x,pos.y-60);
	}
	public void setTextureChest() 
	{
		sprite.setTextureRect(new IntRect(1, 82, 80, 80));
		sprite_action.setTextureRect(new IntRect(325, 1, 80, 80));
	}
	public void afficher() 
	{
		Graphic.SFML.draw(sprite);
		Graphic.SFML.draw(sprite_action);
	}
}
