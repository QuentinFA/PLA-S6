package UI;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import Game.Controler;
import Game.Input;
import Game.Interpreter;
import Game.Input.BUTTON;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Game.World;
import Prog.NormalActions.*;
import Prog.*;

public class Gui 
{
	public static Gui GUI = null;

	private Sprite sprite_return = new Sprite();

	private Sprite sprite_main = new Sprite();
	private Sprite sprite_proc1 = null;
	private Sprite sprite_proc2 = null;
	private Sprite sprite_play_retry = new Sprite();

	private List<Sprite> spriteList = new ArrayList<Sprite>();
	private List<Sprite> spriteList_main = new ArrayList<Sprite>();
	private List<Sprite> spriteList_proc1 = new ArrayList<Sprite>();
	private List<Sprite> spriteList_proc2 = new ArrayList<Sprite>();
	private List<Sprite> spriteList_occupied = new ArrayList<Sprite>();

	private List<Action> actionList = World.WORLD.getActionList();

	private List<Procedure> final_actionList = new ArrayList<Procedure>();


	private int nbrAction;
	private int nbrProc;

	private int wichProc = 0;

	//	private List<Action> actionList;

	public Gui(int nbrA, int nbrP)
	{
		Sprite spr;

		sprite_return.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.RETURN_MENU));
		sprite_return.setTextureRect(new IntRect(1, 1, 100, 100));
		sprite_play_retry.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.PLAY_ACTION));

		actionList = World.WORLD.getActionList();

		for(int i = 0; i < actionList.size(); i++)
		{
			Action act = actionList.get(i); 
			spr = new Sprite();
			spr.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.ACTION));

			if (act instanceof Forward)
				spr.setTextureRect(new IntRect(1, 1, 80, 80));
			else if (act instanceof Left_turn)
				spr.setTextureRect(new IntRect(82, 1, 80, 80));
			else if (act instanceof Right_turn)
				spr.setTextureRect(new IntRect(163, 1, 80, 80));
			else if (act instanceof Jump)
				spr.setTextureRect(new IntRect(244, 1, 80, 80));
			else if (act instanceof Light)
				spr.setTextureRect(new IntRect(325, 1, 80, 80));
			else if (act instanceof Teleporter)
				spr.setTextureRect(new IntRect(406, 1, 80, 80));
			else if (act instanceof OpenChest)
				spr.setTextureRect(new IntRect(487, 1, 80, 80));
			else if (act instanceof UseChest)
				spr.setTextureRect(new IntRect(568, 1, 80, 80));
			else if (act instanceof Pipette)
				spr.setTextureRect(new IntRect(649, 1, 80, 80));
			else if (act instanceof Shower)
				spr.setTextureRect(new IntRect(730, 1, 80, 80));
			else if (act instanceof For)
				spr.setTextureRect(new IntRect(1, 82, 80, 80));

			spriteList.add(spr);
		}

		nbrAction = nbrA;
		nbrProc = nbrP;

		for (int i=0; i < nbrAction; i++)
			spriteList_occupied.add(new Sprite(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK_OCCUPIED)));

		sprite_main.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.GUI_MAIN));
		final_actionList.add(new Procedure(Color.DEFAUT, TypeProcedure.COMMUN));

		if (nbrProc >= 1)
		{
			sprite_proc1 = new Sprite(Ressources.TEXTURE.getTexture(TEXTURE.PROC1));
			final_actionList.add(new Procedure(Color.DEFAUT, TypeProcedure.COMMUN));
		}
		if (nbrProc >= 2)
		{
			sprite_proc2 = new Sprite(Ressources.TEXTURE.getTexture(TEXTURE.PROC2));
			final_actionList.add(new Procedure(Color.DEFAUT, TypeProcedure.COMMUN));
		}

		GUI = this;

		placeGui();
	}

	public void placeGui()
	{
		sprite_return.setPosition(new Vector2f(Graphic.SFML.getPositionCamera_f().x+150,Graphic.SFML.getPositionCamera_f().y));
       
		sprite_play_retry.setPosition(Graphic.SFML.getPositionCamera_f().x ,
        		Graphic.SFML.getPositionCamera_f().y + Ressources.TEXTURE.getTexture(TEXTURE.BOUTON_SOUND).getSize().y + 50);

		sprite_main.setPosition(new Vector2f(Graphic.SFML.getCenterCamera().x + Graphic.SFML.getSizeCamera().x/2.f - Ressources.TEXTURE.getTexture(TEXTURE.GUI_MAIN).getSize().x - 20, 
				Graphic.SFML.getCenterCamera().y - Graphic.SFML.getSizeCamera().y/2.f + 20));

		if (sprite_proc1 != null)
			sprite_proc1.setPosition(new Vector2f(sprite_main.getPosition().x, sprite_main.getPosition().y + Ressources.TEXTURE.getTexture(TEXTURE.GUI_MAIN).getSize().y + 20));
		if (sprite_proc2 != null)
			sprite_proc2.setPosition(new Vector2f(sprite_proc1.getPosition().x, sprite_proc1.getPosition().y + Ressources.TEXTURE.getTexture(TEXTURE.PROC1).getSize().y + 20));

		for (int i=0; i < spriteList.size(); i++)
			spriteList.get(i).setPosition(new Vector2f(Graphic.SFML.getPositionCamera_f().x + i * spriteList.get(i).getTextureRect().width, Graphic.SFML.getPositionCamera_f().y + Graphic.SFML.getSizeCamera().y - spriteList.get(i).getTextureRect().height));

		for (int i=0; i < nbrAction; i++)
			spriteList_occupied.get(i).setPosition(new Vector2f((i%4)*80 + sprite_main.getPosition().x + 1, 80*(i/4) + sprite_main.getPosition().y + 25));

		//Arranger les positions des actions dans la fenetre
		for (int i=0; i < spriteList_main.size(); i++)
			spriteList_main.get(i).setPosition((i%4)*80 + sprite_main.getPosition().x + 1, 80*(i/4) + sprite_main.getPosition().y + 25);	   

		for (int i = 0; i < spriteList_proc1.size(); i++)
			spriteList_proc1.get(i).setPosition((i%4)*80 + sprite_proc1.getPosition().x, 80*(i/4) + sprite_proc1.getPosition().y + 25);	 

		for (int i = 0; i < spriteList_proc2.size(); i++)
			spriteList_proc2.get(i).setPosition((i%4)*80 + sprite_proc2.getPosition().x, 80*(i/4) + sprite_proc2.getPosition().y + 25);	 
	}

	public void afficher()
	{
		Graphic.SFML.draw(sprite_return);
        Graphic.SFML.draw(sprite_play_retry);
		Graphic.SFML.draw(sprite_main);
		if (sprite_proc1 != null)
			Graphic.SFML.draw(sprite_proc1);
		if (sprite_proc2 != null)
			Graphic.SFML.draw(sprite_proc2);

		for (Sprite spr : spriteList)
			Graphic.SFML.draw(spr);
		for (Sprite spr : spriteList_occupied)
			Graphic.SFML.draw(spr);
		for (Sprite spr : spriteList_main)
			Graphic.SFML.draw(spr);
		for (Sprite spr : spriteList_proc1)
			Graphic.SFML.draw(spr);
		for (Sprite spr : spriteList_proc2)
			Graphic.SFML.draw(spr);
	}

	public void gerer()
	{
		if (Input.INPUT.again(BUTTON.MLEFT))
		{
			if (Graphic.isOnSprite(sprite_return)) //Retour
			{
				World.WORLD = null;
				Gui.GUI = null;
				Interpreter.INTERPRETER = null;
				Controler.CONTROLER = null;
				
				Menu.change_menu(Menu.MENU.LEVEL);

				return;
			}

			if (Graphic.isOnSprite(sprite_play_retry))
			{
				if (World.WORLD.isPlaying())
				{
					sprite_play_retry.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.RETRY_ACTION));
					
					World.WORLD.setPlaying(false);
					World.WORLD.initialiser();
				}
				else
				{
					sprite_play_retry.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.PLAY_ACTION));
					
					World.WORLD.setPlaying(true);
					List<Entities.Character> l =  World.WORLD.getCharacterList();
					
					for (int i=0; i < l.size(); i++)
						l.get(i).setMain(final_actionList.get(i));
				}
			}
		}

		if (!World.WORLD.isPlaying())
		{
			if (Input.INPUT.again(BUTTON.MLEFT))
			{
				selecPanneau();

				//Ajouter les actions dans la fenentre
				for (int i=0; i < spriteList.size(); i++)
					if (Graphic.isOnSprite(spriteList.get(i)))
					{
						Sprite temp = new Sprite(spriteList.get(i).getTexture(), spriteList.get(i).getTextureRect());
						if (spriteList_main.size() <= nbrAction)						
						{
							int j, max_action;
							List<Sprite> sprite_list;

							if (wichProc == 0)
							{
								j = spriteList_main.size();
								max_action = nbrAction;
								sprite_list = spriteList_main;
							}
							else if (wichProc == 1)
							{
								j = spriteList_proc1.size();
								max_action = 8;
								sprite_list = spriteList_proc1;
							}
							else
							{
								j = spriteList_proc2.size();
								max_action = 8;
								sprite_list = spriteList_proc2;
							}

							if (j < max_action) 
							{
								if (actionList.get(i) instanceof For && j != 0)
								{
									Prog action = final_actionList.get(wichProc).getListProcedure().get(final_actionList.get(wichProc).getListProcedure().size() - 1);
									if (action instanceof For)
									{
										((For) action).incrementer();
										sprite_list.get(sprite_list.size()-1).setTextureRect(new IntRect(1+81*(((For) action).getForValue()-1), 82, 80, 80));
									}
									else
									{
										final_actionList.get(wichProc).addProg(actionList.get(i));
										sprite_list.add(temp);
									}
								}
								else
								{
									final_actionList.get(wichProc).addProg(actionList.get(i));
									sprite_list.add(temp);
								}
							}
						}
					}

				//Effacer des actions
				for (int i=0; i < spriteList_main.size(); i++)
					if (Graphic.isOnSprite(spriteList_main.get(i)))
					{
						spriteList_main.remove(i);
						final_actionList.get(0).getListProcedure().remove(i);
					}
				for (int i=0; i < spriteList_proc1.size(); i++)
					if (Graphic.isOnSprite(spriteList_proc1.get(i)))
					{
						spriteList_proc1.remove(i);
						final_actionList.get(1).getListProcedure().remove(i);
					}
				for (int i=0; i < spriteList_proc2.size(); i++)
					if (Graphic.isOnSprite(spriteList_proc2.get(i)))
					{
						spriteList_proc2.remove(i);
						final_actionList.get(2).getListProcedure().remove(i);
					}
			}
		}
		else
		{

		}

		placeGui();
	}

	public int getNbrAction() {return nbrAction;}
	public int getNbrProc() {return nbrProc;}
	public List<Action> getActionList() {return actionList;}

	public void selecPanneau()
	{
		//Pour choisir la fenetre d'ajouter les actions
		if (Graphic.isOnSprite(sprite_main))
		{
			sprite_main.setColor(new org.jsfml.graphics.Color(128, 128, 128));
			if (sprite_proc1 != null)
				sprite_proc1.setColor(org.jsfml.graphics.Color.WHITE);
			if (sprite_proc2 != null)
				sprite_proc2.setColor(org.jsfml.graphics.Color.WHITE);
			wichProc = 0;
		}
		if (sprite_proc1 != null && Graphic.isOnSprite(sprite_proc1))
		{
			sprite_proc1.setColor(new org.jsfml.graphics.Color(128, 128, 128));
			sprite_main.setColor(org.jsfml.graphics.Color.WHITE);
			if (sprite_proc2 != null)
				sprite_proc2.setColor(org.jsfml.graphics.Color.WHITE);
			wichProc = 1;
		}
		if (sprite_proc2 != null && Graphic.isOnSprite(sprite_proc2))
		{
			sprite_proc2.setColor(new org.jsfml.graphics.Color(128, 128, 128));
			sprite_main.setColor(org.jsfml.graphics.Color.WHITE);
			sprite_proc1.setColor(org.jsfml.graphics.Color.WHITE);
			wichProc = 2;
		}
	}
}