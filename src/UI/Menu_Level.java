package UI;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2i;

import Game.Ressources;

public class Menu_Level extends Menu{
	//public static Menu_Level MENU_LEVEL = null;
	boolean mute = true;
	Sprite boutonSound = new Sprite();
	Sprite fleche_right = new Sprite();
	Sprite fleche_left = new Sprite();

	public  void init()
	{
//		MENU_LEVEL  = new Menu_Level();
	}
	public void afficher()
	{
		Graphic.SFML.draw(boutonSound);
		Graphic.SFML.draw(fleche_right);
	}
	public Menu_Level()
	{				
		boutonSound.setPosition(Graphic.SFML.getPositionCamera_f());
		boutonSound.setTexture(Ressources.RESSOURCES.getTextureBoutonSound());
		boutonSound.setTextureRect(new IntRect(1 , 1 , 100 , 100));		
		
		fleche_right.setTexture(Ressources.RESSOURCES.getTextureFleche());
		fleche_right.setOrigin(Ressources.RESSOURCES.getSizeTextureFleche());
		fleche_right.setPosition(Graphic.SFML.getCenterCamera().x+Graphic.SFML.getSizeFenetre().x/4.f,
				Graphic.SFML.getCenterCamera().y);	
		fleche_right.setTextureRect(new IntRect(1 , 1 , 100 , 100));
	}
	public boolean gerer()
	{
		if (Input.INPUT.again(Input.BUTTON.MLEFT))
		{
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
		System.out.println("mouse in isOnSprite: " + pos_mouse);
		Vector2i real_pos = Vector2i.add(pos_mouse, Graphic.SFML.getPositionCamera_i());
		
		if (real_pos.x > rectangle.left && 
				real_pos.x < (rectangle.left+rectangle.width) &&
				   real_pos.y > rectangle.top &&
				     real_pos.y < (rectangle.top+rectangle.height))
				return true;
		return false;
	}
}
