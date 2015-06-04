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
	public static Menu MENU = null;
	public static void init()
	{
		MENU  = new Menu();
	}
	public void afficher()
	{
		Graphic.SFML.draw(bouton);
	}
	public Menu()
	{
		
		bouton.setPosition(Graphic.SFML.getCenterCamera());		
		bouton.setTexture(Ressources.RESSOURCES.getTextureBouton());
		
		
		
		
	}
	public boolean gerer()
	{
		Vector2i pos_mouse = Mouse.getPosition();
		Vector2i real_pos = Vector2i.add(pos_mouse, Graphic.SFML.getPositionCamera());
		
		FloatRect rectangle = bouton.getGlobalBounds();		
		
		if (Mouse.isButtonPressed(Button.LEFT))
				if (real_pos.x > rectangle.left && 
						real_pos.x < (rectangle.left+rectangle.width) &&
						   real_pos.y > rectangle.top &&
						     real_pos.y < (rectangle.top+rectangle.height))
				{
					return true;
				}
		return false;
	}
}
