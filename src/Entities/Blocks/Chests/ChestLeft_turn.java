package Entities.Blocks.Chests;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import Entities.Chest;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Prog.Color;
import Prog.Coordonnees;
import Prog.NormalActions.Left_turn;
import UI.Graphic;

/**
 * Chest contenant l'action left_turn pour introduire le concept d'objet de la programmation.
 * @author edwin
 *
 */

public class ChestLeft_turn extends Chest 
{
	Sprite sprite_action = new Sprite();
	
	/**
	 * Constructeur
	 * @param pos : Position du Chest
	 */
	public ChestLeft_turn(Coordonnees pos) 
	{
		this.coord = pos;
		this.action = new Left_turn(Color.DEFAUT);
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
		sprite_action.setTextureRect(new IntRect(82, 1, 80, 80));
	}
	public void afficher() 
	{
		Graphic.SFML.draw(sprite);
		Graphic.SFML.draw(sprite_action);
	}
}
