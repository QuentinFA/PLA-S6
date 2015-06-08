package UI;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import Game.Ressources;
import Game.Ressources.TEXTURE;

public class Menu_Main extends Menu
{
	private static float boutonPlay_scale;
	private boolean increase_boutonPlay_scale;
	private boolean cursor_control;
	
	Sprite boutonPlay = new Sprite();
	Sprite cursor = new Sprite();
	
	Sprite title = new Sprite();
	private static float title_scale;
	private boolean increase_title_scale;
	private static float title_rotation;
	private boolean increase_title_rotation;
	
	public Menu_Main()
	{
		boutonPlay.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BOUTON_PLAY));
		boutonPlay.setOrigin(Ressources.TEXTURE.getHalfSize(TEXTURE.BOUTON_PLAY));
		boutonPlay.setPosition(new Vector2f(Graphic.SFML.getCenterCamera().x, Graphic.SFML.getCenterCamera().y + Graphic.SFML.getSizeCamera().y/4.f));
		
		boutonPlay_scale = 1.f;
		increase_boutonPlay_scale = true;
		
		title.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.TITLE));
		title.setOrigin(Ressources.TEXTURE.getHalfSize(TEXTURE.TITLE));
		title.setPosition(new Vector2f(Graphic.SFML.getCenterCamera().x, Graphic.SFML.getCenterCamera().y - Graphic.SFML.getSizeCamera().y/4.f));
		
		title_scale = 1.f;
		increase_title_scale = true;
		title_rotation = 0.f;
		increase_title_rotation = true;
		
		cursor.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.CURSOR));
		cursor_control = false;
	}
	
	public void afficher()
	{
		Graphic.SFML.draw(boutonPlay);
		Graphic.SFML.draw(title);
		if (cursor_control)
			Graphic.SFML.draw(cursor);
	}
	
	public void gerer()
	{
		boutonPlay.setPosition(new Vector2f(Graphic.SFML.getCenterCamera().x, Graphic.SFML.getCenterCamera().y + Graphic.SFML.getSizeCamera().y/4.f));
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

		title.setPosition(new Vector2f(Graphic.SFML.getCenterCamera().x, Graphic.SFML.getCenterCamera().y - Graphic.SFML.getSizeCamera().y/4.f));
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

			if (!cursor_control)
			{
				Graphic.SFML.invisible_cursor();
				cursor_control = true;
			}
			Vector2i pos_mouse = Graphic.SFML.getPositionMouse();
			Vector2i real_pos = Vector2i.add(pos_mouse, Graphic.SFML.getPositionCamera_i());
			cursor.setPosition(new Vector2f((float)real_pos.x,(float)real_pos.y));
		
		}
		else if (cursor_control)
		{
			Graphic.SFML.visible_cursor();
			cursor_control = false;
		}
	}
}
