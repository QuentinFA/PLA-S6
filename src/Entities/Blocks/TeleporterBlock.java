package Entities.Blocks;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import Entities.Block;
import Entities.Character;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Prog.Color;
import Prog.Coordonnees;
import UI.Graphic;

public class TeleporterBlock extends Block
{
	private Sprite portal = new Sprite();
	private TeleporterBlock destination;
	private Color couleur;
	private int anim = 0;
	
	public void gerer() 
	{
		if (anim % 5 == 0)
			portal.setTextureRect(new IntRect(1+anim/5*82, 165, 81, 81));
		
		anim++;
		
		if (anim == 35)
			anim = 0;
	}
	
	public void setPosSprite(Vector2f pos) 
	{
		portal.setPosition(pos);
		sprite.setPosition(pos);
	}
	
	public void afficher()
	{
		gerer();
		Graphic.SFML.draw(sprite);
		Graphic.SFML.draw(portal);
	}
	
	public Color getColor(){return couleur;}
	
	public TeleporterBlock(Coordonnees c, Color col)
	{
		coord = c;
		couleur = col;
		this.destination = null;
		
	}
	
	public TeleporterBlock(Coordonnees c)
	{
		coord = c;
		couleur = Color.DEFAUT;
		this.destination = null;
	}
	
	public TeleporterBlock(Color cl, Coordonnees c)
	{
		coord = c;
		couleur = cl;
		this.destination = null;
	}
	
	public TeleporterBlock(Coordonnees c, TeleporterBlock dest)
	{
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK));
		sprite.setTextureRect(new IntRect(1, 1, 81, 81));
		
		portal.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK));
		portal.setTextureRect(new IntRect(1, 165, 81, 81));
		portal.setColor(org.jsfml.graphics.Color.RED);
		
		coord = c;
		this.destination = dest;
		couleur = Color.DEFAUT;
	}
	
	public void perform(Character p) 
	{
		if (destination != null)
			p.setCoord(destination.getCoord());
	}
	
	public TeleporterBlock getDest()
	{
		return this.destination;
	}
}