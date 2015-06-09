package Entities;

import Actions.Color;
import Actions.Coordonnees;
import Actions.Light;
import Game.Ressources;
import Game.Ressources.TEXTURE;

public class ChestLight extends Chest 
{
	public ChestLight(Coordonnees pos, int ori) {
		this.coord = pos;
		this.orientation = ori;
		this.action = new Light(Color.DEFAUT);
		this.open = false;
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.CHEST));
		setTextureChest();
	}
	
	public void setTextureChest() {//sprite.setTextureRect(new IntRect(1+82*ori, 1, 81, 81));
		//TODO mettre les coordonnees de la texture coffreLight en fonction de l'orientation et de si le coffre est ouvert ou non
	}
}
