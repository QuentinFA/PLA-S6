package UI;


import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import org.jsfml.window.Mouse.Button;

import Game.Ressources;

public class Menu 
{

	
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
		boutonSound.setPosition(Graphic.SFML.getCenterCamera());
		boutonSound.setTexture(Ressources.RESSOURCES.getTextureBoutonSound());
	}
	public boolean gerer()
	{
		if (Mouse.isButtonPressed(Button.LEFT))
		{
			if (isOnSprite(bouton)) //Play
			{
				MENU = null;
				Menu_Level.MENU_LEVEL  = new Menu_Level();
				return true;
			}
			if (isOnSprite(boutonSound))
			{
				//Change sound
				if(Ressources.RESSOURCES.on_off.equals(Ressources.Sound.On))
				{
					Ressources.RESSOURCES.on_off = Ressources.Sound.Off;
					Ressources.RESSOURCES.getMusic().stop();
				}
				if(Ressources.RESSOURCES.on_off.equals(Ressources.Sound.Off))
				{
					Ressources.RESSOURCES.on_off = Ressources.Sound.On;
					Ressources.RESSOURCES.getMusic().play();
				}
				return true;
			}
		}
		
		return false;
	}
	static boolean isOnSprite(Sprite s)
	{
		FloatRect rectangle = s.getGlobalBounds();
		
		Vector2i pos_mouse = Mouse.getPosition();
		Vector2i real_pos = Vector2i.add(pos_mouse, Graphic.SFML.getPositionCamera());
		
		if (real_pos.x > rectangle.left && 
				real_pos.x < (rectangle.left+rectangle.width) &&
				   real_pos.y > rectangle.top &&
				     real_pos.y < (rectangle.top+rectangle.height))
				return true;
		return false;
	}
}
