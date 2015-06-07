package UI;


import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import Game.Ressources;

public class Menu_Main extends Menu
{

	boolean mute = true;
	boolean cursor_control = false;
	Sprite bouton = new Sprite();
	Sprite boutonSound = new Sprite();
	Sprite cursor = new Sprite();


	public void afficher()
	{
		Graphic.SFML.draw(bouton);
		Graphic.SFML.draw(boutonSound);
		if(!cursor_control)
			Graphic.SFML.draw(cursor);
	}
	public Menu_Main()
	{

		bouton.setTexture(Ressources.RESSOURCES.getTextureBouton());
		bouton.setOrigin(Ressources.RESSOURCES.getSizeTextureBouton());
		bouton.setPosition(Graphic.SFML.getCenterCamera());		


		boutonSound.setPosition(Graphic.SFML.getPositionCamera_f());
		boutonSound.setTexture(Ressources.RESSOURCES.getTextureBoutonSound());
		boutonSound.setTextureRect(new IntRect(1 , 1 , 100 , 100));

		// cursor.setTexture(Ressources.RESSOURCES.getTextureCursor()); TODO PB
		cursor.setTexture(Ressources.RESSOURCES.getTexture1());


	}
	public void gerer()
	{
		if (Input.INPUT.again(Input.BUTTON.MLEFT))
		{

			if (isOnSprite(bouton)) //Play
			{				
				Menu.change = 2;
				Menu.change_menu();
				System.out.println("mute");
				//				return true;
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
		if(isOnSprite(bouton))
		{
			if(cursor_control)
			{
				cursor_control = false;//show another image for remplacing the cursor
			    Graphic.SFML.invisible_cursor();
			}
			    Vector2i pos_mouse = Graphic.SFML.getPositionMouse();
			    Vector2i real_pos = Vector2i.add(pos_mouse, Graphic.SFML.getPositionCamera_i());
			    cursor.setPosition(new Vector2f((float)real_pos.x,(float)real_pos.y));
		
		}
		else
		{
			System.out.println("show my cursor");
			if(!cursor_control)
			{
				Graphic.SFML.visible_cursor();
				cursor_control = true;
			}
		}

		//		return false;
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
