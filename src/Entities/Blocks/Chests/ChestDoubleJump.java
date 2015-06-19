package Entities.Blocks.Chests;

import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2f;

import Entities.Chest;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Prog.Color;
import Prog.Coordonnees;
import Prog.SpecialActions.DoubleJump;
import UI.Graphic;

public class ChestDoubleJump extends Chest 
{
	/**
	 * Constructeur
	 * @param pos : Position du Chest
	 */
	public ChestDoubleJump(Coordonnees pos) 
	{
		this.coord = pos;
		this.action = new DoubleJump(Color.DEFAUT);
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
		sprite.setTextureRect(new IntRect(1, 83, 81, 81));
		sprite_action.setTextureRect(new IntRect(83, 83, 81, 81));
	}
	public void afficher() 
	{
		Graphic.SFML.draw(sprite);
		Graphic.SFML.draw(sprite_action);
	}
}
