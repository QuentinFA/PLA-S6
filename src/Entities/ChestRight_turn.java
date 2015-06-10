package Entities;

import Game.Ressources;
import Game.Ressources.TEXTURE;
import Prog.Color;
import Prog.Coordonnees;
import Prog.Right_turn;

public class ChestRight_turn extends Chest 
{
	public ChestRight_turn(Coordonnees pos, int ori) {
		this.coord = pos;
		this.orientation = ori;
		this.action = new Right_turn(Color.DEFAUT);
		this.open = false;
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.CHEST));
		setTextureChest();
	}
	
	public void setTextureChest() {//sprite.setTextureRect(new IntRect(1+82*ori, 1, 81, 81));
		//TODO mettre les coordonnees de la texture coffreRight_turn en fonction de l'orientation et de si le coffre est ouvert ou non
	}
}
