package UI;

import java.util.ArrayList;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import Game.Ressources;
import Game.Ressources.TEXTURE;
import Levels.Reader;

public class Menu_Level extends Menu
{
	ArrayList<Sprite> monde_list = new ArrayList<Sprite>();
	ArrayList<ArrayList<Sprite>> nbr_level_list = new ArrayList<ArrayList<Sprite>>();

	ArrayList<Sprite> aura_list = new ArrayList<Sprite>();
	//private Sprite aura
	private static float title_scale;
	private boolean increase_title_scale;
	private int nbr_monde = 0;

	Sprite fleche_right = new Sprite();
	Sprite fleche_left = new Sprite();

	public Menu_Level()
	{	
		Graphic.SFML.visible_cursor();
		Sprite spr;
		for(int i = 0 ; i < 2 ; i++)
		{
			spr = new Sprite();
			spr.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.MONDE));
			spr.setTextureRect(new IntRect(1+i*401, 1, 400, 400));
			spr.setOrigin(new Vector2f(spr.getTextureRect().width/2.f, spr.getTextureRect().height/2.f));
			spr.setPosition(Graphic.SFML.getCenterCamera());
			monde_list.add(spr);
			spr = new Sprite();

			spr.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.AURA));
			spr.setOrigin(Ressources.TEXTURE.getHalfSize(TEXTURE.AURA));
			aura_list.add(spr);

		}

		title_scale = 1.25f;
		increase_title_scale = true;

		for (int i=0; i < monde_list.size(); i++)
		{
			nbr_level_list.add(new ArrayList<Sprite>());
			for (int j=0; j < 4; j++)
			{
				spr = new Sprite();
				spr.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.NBR_LEVEL));
				spr.setTextureRect(new IntRect(1+89*j, 1, 88, 88));
				spr.setOrigin(new Vector2f(spr.getTextureRect().width/2.f, 0));
				nbr_level_list.get(i).add(spr);
			}
		}

		fleche_right.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BOUTON_FLECHE));
		fleche_right.setTextureRect(new IntRect(1 , 1 , 100 , 100));
		fleche_right.setOrigin(new Vector2f(fleche_right.getTextureRect().width/2.f, fleche_right.getTextureRect().height/2.f));
		fleche_right.setPosition(Graphic.SFML.getCenterCamera().x + Graphic.SFML.getSizeCamera().x/2.f - fleche_right.getTextureRect().width, Graphic.SFML.getCenterCamera().y);

		fleche_left.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BOUTON_FLECHE));
		fleche_left.setTextureRect(new IntRect(102 , 1 , 100 , 100));
		fleche_left.setOrigin(new Vector2f(fleche_left.getTextureRect().width/2.f, fleche_left.getTextureRect().height/2.f));
		fleche_left.setPosition(Graphic.SFML.getCenterCamera().x - Graphic.SFML.getSizeCamera().x/2.f + fleche_left.getTextureRect().width, Graphic.SFML.getCenterCamera().y);



	}

	public void afficher()
	{
		if(nbr_monde<3)
			Graphic.SFML.draw(fleche_right);
		for(Sprite spr: aura_list)
		{
			Graphic.SFML.draw(spr);
		}
		for (Sprite spr : monde_list)
		{
			Graphic.SFML.draw(spr);
		}
		

		for (ArrayList<Sprite> arr : nbr_level_list)
			for (Sprite spr : arr)
				Graphic.SFML.draw(spr);
		if(nbr_monde > 0)
			Graphic.SFML.draw(fleche_left);
	}

	public void gerer()
	{
		for (int i = 0 ; i < monde_list.size(); i++)
		{
			monde_list.get(i).setPosition(new Vector2f((i-nbr_monde)*Graphic.SFML.getSizeCamera().x+Graphic.SFML.getCenterCamera().x , Graphic.SFML.getCenterCamera().y));

		}
		for(int i = 0; i < aura_list.size(); i++)
		{
			aura_list.get(i).setPosition(monde_list.get(i).getPosition());
			aura_list.get(i).setScale(title_scale, title_scale);
			aura_list.get(i).setRotation(aura_list.get(i).getRotation()+1);
		}
		if (increase_title_scale)
		{
			title_scale *= 1.01f;
			if (title_scale >= 2.25f)
				increase_title_scale = false;
		}
		else
		{
			title_scale /= 1.01f;
			if (title_scale <= 1.25f)
				increase_title_scale = true;
		}

		for (int j=0; j < nbr_level_list.size(); j++)
			for (int i=0; i < nbr_level_list.get(j).size(); i++)
				nbr_level_list.get(j).get(i).setPosition(new Vector2f(monde_list.get(j).getPosition().x + (i-1.5f)*nbr_level_list.get(j).get(i).getTextureRect().width, 
						monde_list.get(j).getGlobalBounds().top + monde_list.get(j).getTextureRect().height));

		fleche_right.setPosition(Graphic.SFML.getCenterCamera().x + Graphic.SFML.getSizeCamera().x/2.f - fleche_right.getTextureRect().width, Graphic.SFML.getCenterCamera().y);

		if (Input.INPUT.again(Input.BUTTON.MLEFT))
		{
			for (ArrayList<Sprite> arr : nbr_level_list)
				for (int i=0; i < arr.size(); i++)
					if (Graphic.isOnSprite(arr.get(i)))
					{
						if (i == 0)
							Reader.read("levels/level1-1.xml");
						else if (i == 1)
							Reader.read("levels/level1-2.xml");
						else if (i == 2)
							Reader.read("levels/level1-3.xml");
					}
			if(Graphic.isOnSprite(fleche_right))
			{
				if(nbr_monde<3)//nombre de niveau
					nbr_monde++;


			}
			if(Graphic.isOnSprite(fleche_left))
			{
				if(nbr_monde>0)
					nbr_monde--;

			}
		}


	}
}
