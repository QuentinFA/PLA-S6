package UI;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;


import org.jsfml.window.Mouse.Button;

import Game.Ressources;

public class Menu 
{
	Sprite bouton = new Sprite();
	
	public Menu()
	{
		bouton.setPosition(new Vector2f(0, 0));
		
		bouton.setTexture(Ressources.RESSOURCES.getTextureBouton());
		//rectangle = bouton.getGlobalBounds().
		Vector2i pos_mouse = Mouse.getPosition();
		Vector2i real_pos = pos_mouse + Graphic.SFML.getPositionCamera();
		
		Vector2i.add(arg0, arg1)
		//Mouse.
		//Mouse lol = new Mouse();
		//position = lol.getPosition()
		
		//if (Mouse.isButtonPressed(Button.LEFT))
		//		if (position.x > rectangle.left)
		
		
		
	}
}
