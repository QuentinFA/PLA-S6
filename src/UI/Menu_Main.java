package UI;


import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2i;

import Game.Ressources;

public class Menu_Main extends Menu
{
	
	boolean mute = true;
	Sprite bouton = new Sprite();
	Sprite boutonSound = new Sprite();
	
	
	
//	public static Menu_Main MENU_MAIN = null;
	public  void init()
	{
<<<<<<< HEAD
//       MENU_MAIN  = new Menu_Main();
=======
		MENU_MAIN  = new Menu_Main();
>>>>>>> 2cbeb62f718326bdd12195f1cb41bba2e7ddd194
	}
	public void afficher()
	{
		Graphic.SFML.draw(bouton);
		Graphic.SFML.draw(boutonSound);
	}
	public Menu_Main()
	{
		
		bouton.setTexture(Ressources.RESSOURCES.getTextureBouton());
		bouton.setOrigin(Ressources.RESSOURCES.getSizeTextureBouton());
		bouton.setPosition(Graphic.SFML.getCenterCamera());		
		
		
		boutonSound.setPosition(Graphic.SFML.getPositionCamera_f());
		boutonSound.setTexture(Ressources.RESSOURCES.getTextureBoutonSound());
		boutonSound.setTextureRect(new IntRect(1 , 1 , 100 , 100));
		
		
	}
	public boolean gerer()
	{
		if (Input.INPUT.again(Input.BUTTON.MLEFT))
		{
			
			if (isOnSprite(bouton)) //Play
			{				
				Menu.change = 2;
				Menu.change_menu();
				System.out.println("mute");
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
		//		System.out.println("mouse in isOnSprite: " + pos_mouse);
		Vector2i real_pos = Vector2i.add(pos_mouse, Graphic.SFML.getPositionCamera_i());
		
		if (real_pos.x > rectangle.left && 
				real_pos.x < (rectangle.left+rectangle.width) &&
				real_pos.y > rectangle.top &&
				real_pos.y < (rectangle.top+rectangle.height))
			return true;
		return false;
	}
}