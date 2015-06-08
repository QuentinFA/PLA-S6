package UI;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import Game.Ressources;
import Game.Ressources.TEXTURE;

public class Menu_Level extends Menu
{
	Sprite fleche_right = new Sprite();
	Sprite fleche_left = new Sprite();

	public Menu_Level()
	{				
		fleche_right.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BOUTON_FLECHE));
		fleche_right.setTextureRect(new IntRect(1 , 1 , 100 , 100));
		fleche_right.setOrigin(new Vector2f(fleche_right.getTextureRect().width/2.f, fleche_right.getTextureRect().height/2.f));
		fleche_right.setPosition(Graphic.SFML.getCenterCamera().x + Graphic.SFML.getSizeCamera().x/2.f, Graphic.SFML.getCenterCamera().y);
	}
	
	public void afficher()
	{
		Graphic.SFML.draw(fleche_right);
	}
	
	public boolean gerer()
	{
		fleche_right.setPosition(Graphic.SFML.getCenterCamera().x + Graphic.SFML.getSizeCamera().x/2.f, Graphic.SFML.getCenterCamera().y);
		
		if (Input.INPUT.again(Input.BUTTON.MLEFT))
		{
		}
		return false;
	}
}
