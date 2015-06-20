package Entities.Blocks.Chests;

import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2f;

import Entities.Chest;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Prog.Color;
import Prog.Coordonnees;
import Prog.NormalActions.Jump;

/**
 * Chest contenant l'action jump pour introduire le concept d'objet de la programmation.
 *
 */

public class ChestJump extends Chest 
{
	/**
	 * Constructeur
	 * @param pos : Position du Chest
	 */
	public ChestJump(Coordonnees pos) 
	{
		super(pos);
		this.action = new Jump(Color.DEFAUT);
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
		sprite_action.setTextureRect(new IntRect(247, 1, 81, 81));
	}
}
