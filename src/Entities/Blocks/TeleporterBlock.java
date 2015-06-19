package Entities.Blocks;

import java.util.Stack;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import Entities.Block;
import Entities.Character;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Game.World;
import Prog.Coordonnees;
import UI.Graphic;

/**
 * Bloc illustrant le concept de pointeur. Permet de se teleporter sur une case precise.
 * @author edwin
 *
 */
public class TeleporterBlock extends Block
{
	//TODO : commente ta fonction Anna stp
	
	private static Stack<org.jsfml.graphics.Color> colorTaken;
	public static void initialiseStack()
	{
		Stack<org.jsfml.graphics.Color> l = new Stack<org.jsfml.graphics.Color>();
		
		l.push(org.jsfml.graphics.Color.GREEN);
		l.push(org.jsfml.graphics.Color.BLUE);
		l.push(org.jsfml.graphics.Color.RED);
		
		colorTaken = l;
	}
	
	private enum TYPE_TP
	{
		SOURCE,
		DEST;
	}
	
	private Sprite portal = new Sprite();
	private TeleporterBlock destination;
	private TYPE_TP type;
	
	public TeleporterBlock(Coordonnees c)
	{
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK));
		sprite.setTextureRect(new IntRect(1, 1, 81, 81));
		portal.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK));
		portal.setTextureRect(new IntRect(1, 165, 81, 81));
		
		coord = c;
		destination = null;
		type = TYPE_TP.DEST;
	}
	
	public TeleporterBlock(Coordonnees c, TeleporterBlock dest)
	{
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK));
		sprite.setTextureRect(new IntRect(1, 1, 81, 81));
		portal.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK));
		portal.setTextureRect(new IntRect(1, 165, 81, 81));
		
		coord = c;
		destination = dest;
		type = TYPE_TP.SOURCE;
	}
	
	private int anim = 0;
	private int one_frame = 8;
	public void gerer() 
	{
		if (anim % one_frame == 0)
		{
			if (type == TYPE_TP.DEST)
				portal.setTextureRect(new IntRect(1+anim/one_frame*82, 165, 81, 81));
			else
				portal.setTextureRect(new IntRect(1+anim/one_frame*82, 247, 81, 81));
		}
		
		anim++;
		
		if (anim == one_frame*8)
			anim = 0;
	}
	
	public void setColor(org.jsfml.graphics.Color c) {portal.setColor(c);}
	
	public void lier()
	{
		org.jsfml.graphics.Color c = colorTaken.pop();
		portal.setColor(c);
		destination.setColor(c);
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
	
	public void perform(Character p) 
	{
		if (destination != null)
		{
			Coordonnees c = destination.getCoord();
			p.setCoord(new Coordonnees(c.getX(), c.getY(), c.getZ() + 1));

			p.setPosSprite(World.WORLD.placeMe(p.getCoord()));
		}
	}
	
	public TeleporterBlock getDest() {return destination;}
}