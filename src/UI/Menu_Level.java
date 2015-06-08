package UI;

import java.util.ArrayList;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import Game.Block;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Game.World;
import Levels.Reader;

public class Menu_Level extends Menu
{
	ArrayList<Sprite> monde_list = new ArrayList<Sprite>();
	ArrayList<ArrayList<Sprite>> nbr_level_list = new ArrayList<ArrayList<Sprite>>();
	
	Sprite fleche_right = new Sprite();
	Sprite fleche_left = new Sprite();

	public Menu_Level()
	{			
		Sprite spr;
		
		spr = new Sprite();
		spr.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.MONDE1));
		spr.setOrigin(Ressources.TEXTURE.getHalfSize(TEXTURE.MONDE1));
		spr.setPosition(Graphic.SFML.getCenterCamera());
		monde_list.add(spr);
		
		nbr_level_list.add(new ArrayList<Sprite>());
		spr = new Sprite();
		spr.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.NBR_LEVEL));
		spr.setTextureRect(new IntRect(1 , 1 , 80 , 80));
		spr.setOrigin(new Vector2f(spr.getTextureRect().width/2.f, spr.getTextureRect().height/2.f));
		nbr_level_list.get(0).add(spr);
		
		fleche_right.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BOUTON_FLECHE));
		fleche_right.setTextureRect(new IntRect(1 , 1 , 100 , 100));
		fleche_right.setOrigin(new Vector2f(fleche_right.getTextureRect().width/2.f, fleche_right.getTextureRect().height/2.f));
		fleche_right.setPosition(Graphic.SFML.getCenterCamera().x + Graphic.SFML.getSizeCamera().x/2.f - fleche_right.getTextureRect().width, Graphic.SFML.getCenterCamera().y);
	}
	
	public void afficher()
	{
		Graphic.SFML.draw(fleche_right);
		for (Sprite spr : monde_list)
			Graphic.SFML.draw(spr);
		
		for (ArrayList<Sprite> arr : nbr_level_list)
			for (Sprite spr : arr)
				Graphic.SFML.draw(spr);
	}
	
	public boolean gerer()
	{
		for (Sprite spr : monde_list)
			spr.setPosition(Graphic.SFML.getCenterCamera());
		
		for (ArrayList<Sprite> arr : nbr_level_list)
			for (int i=0; i < arr.size(); i++)
				arr.get(i).setPosition(new Vector2f(Graphic.SFML.getCenterCamera().x + (i-1.5f)*arr.get(i).getTextureRect().width, Graphic.SFML.getCenterCamera().y + Graphic.SFML.getSizeCamera().y/4.f));
			
		fleche_right.setPosition(Graphic.SFML.getCenterCamera().x + Graphic.SFML.getSizeCamera().x/2.f - fleche_right.getTextureRect().width, Graphic.SFML.getCenterCamera().y);
		
		if (Input.INPUT.again(Input.BUTTON.MLEFT))
		{
			for (ArrayList<Sprite> arr : nbr_level_list)
				for (int i=0; i < arr.size(); i++)
				{
					if (Graphic.isOnSprite(arr.get(i)))
					{
						if (i == 0)
						{
							Reader.read("src/Levels/lvl1.txt");
							Graphic.SFML.setCenterCamera(World.WORLD.getCenterWorld());
						}
						return true;
					}
				}
		}
		return false;
	}
}
