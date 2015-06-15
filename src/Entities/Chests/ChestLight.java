package Entities.Chests;

import org.jsfml.graphics.IntRect;

import Entities.Chest;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Prog.Color;
import Prog.Coordonnees;
import Prog.NormalActions.Light;

public class ChestLight extends Chest 
{
	public ChestLight(Coordonnees pos, int ori) 
	{
		this.coord = pos;
		this.orientation = ori;
		this.action = new Light(Color.DEFAUT);
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.CHEST));
		setTextureChest();
	}
	
	public void setTextureChest()
	{
		sprite.setTextureRect(new IntRect(1, 1, 81, 81));
	}
}
