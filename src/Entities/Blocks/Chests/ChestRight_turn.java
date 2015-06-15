package Entities.Blocks.Chests;

import org.jsfml.graphics.IntRect;

import Entities.Chest;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Prog.Color;
import Prog.Coordonnees;
import Prog.NormalActions.Right_turn;

public class ChestRight_turn extends Chest 
{
	public ChestRight_turn(Coordonnees pos, int ori) 
	{
		this.coord = pos;
		this.orientation = ori;
		this.action = new Right_turn(Color.DEFAUT);
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.CHEST));
		setTextureChest();
	}
	
	public void setTextureChest()
	{
		sprite.setTextureRect(new IntRect(1, 1, 81, 81));
	}
}
