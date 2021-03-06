package UI;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import Game.Input;
import Game.Ressources;
import Game.Ressources.TEXTURE;

/**
 * TODO
 *
 */
public class Menu_Main extends Menu
{
	private static float boutonPlay_scale;
	private boolean increase_boutonPlay_scale;
	
	Sprite boutonPlay = new Sprite();
	
	Sprite title = new Sprite();
	private static float title_scale;
	private boolean increase_title_scale;
	private static float title_rotation;
	private boolean increase_title_rotation;
	
	public Menu_Main()
	{
		boutonPlay.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BOUTON_PLAY));
		boutonPlay.setOrigin(Ressources.TEXTURE.getHalfSize(TEXTURE.BOUTON_PLAY));
		
		boutonPlay_scale = 1.f;
		increase_boutonPlay_scale = true;
		
		title.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.TITLE));
		title.setOrigin(Ressources.TEXTURE.getHalfSize(TEXTURE.TITLE));
		
		title_scale = 1.f;
		increase_title_scale = true;
		title_rotation = 0.f;
		increase_title_rotation = true;
		
		placeMenu();
	}
	//donner les coordonnées aux boutons
	public void placeMenu()
	{
		boutonPlay.setPosition(new Vector2f(Graphic.SFML.getCenterCamera().x, Graphic.SFML.getCenterCamera().y + Graphic.SFML.getSizeCamera().y/4.f));
		title.setPosition(new Vector2f(Graphic.SFML.getCenterCamera().x, Graphic.SFML.getCenterCamera().y - Graphic.SFML.getSizeCamera().y/4.f));
	}
	
	public void afficher()
	{
		Graphic.SFML.draw(boutonPlay);
		Graphic.SFML.draw(title);
	}
	
	public void gerer()
	{
		boutonPlay.setScale(boutonPlay_scale, boutonPlay_scale);
		if (increase_boutonPlay_scale)
		{
			boutonPlay_scale *= 1.005f;
			if (boutonPlay_scale >= 1.1f)
				increase_boutonPlay_scale = false;
		}
		else
		{
			boutonPlay_scale /= 1.005f;
			if (boutonPlay_scale <= 0.9f)
				increase_boutonPlay_scale = true;
		}

		title.setScale(title_scale, title_scale);
		title.setRotation(title_rotation);
		
		if (increase_title_scale)
		{
			title_scale *= 1.005f;
			if (title_scale >= 1.25f)
				increase_title_scale = false;
		}
		else
		{
			title_scale /= 1.005f;
			if (title_scale <= 0.75f)
				increase_title_scale = true;
		}
		
		if (increase_title_rotation)
		{
			title_rotation += 0.5f;
			if (title_rotation >= 15.f)
				increase_title_rotation = false;
		}
		else
		{
			title_rotation -= 0.5f;
			if (title_rotation <= -15.f)
				increase_title_rotation = true;
		}
			
		if (Graphic.isOnSprite(boutonPlay)) //Play
		{
			if (Input.INPUT.again(Input.BUTTON.MLEFT))
				Menu.change_menu(Menu.MENU.LEVEL);
		}
		
		placeMenu();
	}
}
