package Entities.Blocks.Chests;

import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2f;

import Entities.Chest;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Prog.Color;
import Prog.Coordonnees;
import Prog.NormalActions.Right_turn;

/**
 * Chest contenant l'action right_turn pour introduire le concept d'objet de la programmation.
 *
 */

public class ChestRight_turn extends Chest 
{
	/**
	 * Constructeur
	 * @param pos : Position du Chest
	 */
	public ChestRight_turn(Coordonnees pos) 
	{
		super(pos);
		this.action = new Right_turn(Color.DEFAUT);
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.CHEST));
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
		sprite_action.setTextureRect(new IntRect(165, 1, 81, 81));
	}
}
