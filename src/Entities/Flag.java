package Entities;

import org.jsfml.graphics.IntRect;

import Game.Ressources;
import Game.Ressources.TEXTURE;
import Prog.Coordonnees;
import UI.Graphic;

public class Flag extends Entities
{
	public Flag(Coordonnees c) 
	{
		super(c);
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.FLAG));
	}
	
	public void setCoord(Coordonnees c)
	{
		coord = c;
	}
	
	private int anim = 0;
	private int one_frame = 8;
	public void gerer() 
	{
		if (anim % one_frame == 0)
			sprite.setTextureRect(new IntRect(1, 1+anim/one_frame*82, 81, 81));
		
		anim++;
		
		if (anim == one_frame*10)
			anim = 0;
	}
	
	public void afficher()
	{
		gerer();
		Graphic.SFML.draw(sprite);
	}
}
