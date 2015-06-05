package UI;


import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import org.jsfml.window.Mouse.Button;

import Game.Ressources;

public class Menu 
{

	boolean mute = true;
	Sprite bouton = new Sprite();
	Sprite boutonSound = new Sprite();
	
	
	
	public static Menu MENU = null;
	public static void init()
	{
		MENU  = new Menu();
	}
	public void afficher()
	{
		Graphic.SFML.draw(bouton);
		Graphic.SFML.draw(boutonSound);
	}
	public Menu()
	{
		bouton.setOrigin(bouton.getTextureRect().width/2 , bouton.getTextureRect().height/2);
		bouton.setPosition(Graphic.SFML.getCenterCamera());		
		bouton.setTexture(Ressources.RESSOURCES.getTextureBouton());		
		boutonSound.setPosition(Graphic.SFML.getPositionCamera_f());
		boutonSound.setTexture(Ressources.RESSOURCES.getTextureBoutonSound());
		boutonSound.setTextureRect(new IntRect(1 , 1 , 100 , 100));
//		boutonSound.setPosition(Graphic.SFML.getPositionCamera_f());
//		boutonSound.setTexture(Ressources.RESSOURCES.getTextureBoutonSound());
//		boutonSound.setTextureRect(new IntRect(102 , 1 , 100 , 100));
		
	}
	public boolean gerer()
	{
		if (Input.INPUT.again(Input.BUTTON.MLEFT))
		{
		   // System.out.println("mouse: " + Mouse.getPosition());
		   // System.out.println("boutonSound" + boutonSound.getPosition());
		    
			if (isOnSprite(bouton)) //Play
			{				
				MENU = null;
				Menu_Level.MENU_LEVEL  = new Menu_Level();
				return true;
			}
			if (isOnSprite(boutonSound))
			{
				 System.out.println(mute);
				if(mute == true)
				{
					mute = false;
					boutonSound.setTextureRect(new IntRect(102 , 1 , 100 , 100));
					Ressources.RESSOURCES.getMusic().stop();
				}
				else if(mute == false)
				{
					mute = true;
					boutonSound.setTextureRect(new IntRect(1 , 1 , 100 , 100));
					Ressources.RESSOURCES.getMusic().play();
				}
			}
		}
		
		return false;
	}
	static boolean isOnSprite(Sprite s)
	{
		FloatRect rectangle = s.getGlobalBounds();
		
		Vector2i pos_mouse = Graphic.SFML.getPositionMouse();
		System.out.println("mouse in siOnSprite: " + pos_mouse);
		Vector2i real_pos = Vector2i.add(pos_mouse, Graphic.SFML.getPositionCamera_i());
		
		if (real_pos.x > rectangle.left && 
				real_pos.x < (rectangle.left+rectangle.width) &&
				   real_pos.y > rectangle.top &&
				     real_pos.y < (rectangle.top+rectangle.height))
				return true;
		return false;
	}
}
