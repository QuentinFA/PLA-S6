package UI;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import Game.Controler;
import Game.Input;
import Game.Input.BUTTON;
import Game.Interpreter;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Game.World;
import Levels.Reader;
import Prog.Action;
import Prog.Color;
import Prog.Procedure;
import Prog.Prog;
import Prog.TypeProcedure;
import Prog.NormalActions.Fork;
import Prog.NormalActions.For;
import Prog.NormalActions.Forward;
import Prog.NormalActions.Jump;
import Prog.NormalActions.Left_turn;
import Prog.NormalActions.Light;
import Prog.NormalActions.OpenChest;
import Prog.NormalActions.P1;
import Prog.NormalActions.P2;
import Prog.NormalActions.Pipette;
import Prog.NormalActions.Right_turn;
import Prog.NormalActions.Shower;
import Prog.NormalActions.Teleporter;
import Prog.NormalActions.UseChest;

public class Gui 
{
	public static Gui GUI = null;

	private boolean level_completed = false;

	private Sprite sprite_return = new Sprite();
	private Sprite sprite_return_eog = new Sprite();

	private Sprite sprite_main = new Sprite();
	private Sprite sprite_proc1 = null;
	private Sprite sprite_proc2 = null;
	private Sprite sprite_play_retry = new Sprite();
	private Sprite sprite_end_of_game = new Sprite();
	private Sprite sprite_next = new Sprite();

	private Sprite sprite_end_of_game_text = new Sprite();

	private Sprite sprite_switch = new Sprite();

	private List<Sprite> spriteList = new ArrayList<Sprite>();
	private List<Sprite> spriteList_main = new ArrayList<Sprite>();
	private List<Sprite> spriteList_proc1 = new ArrayList<Sprite>();
	private List<Sprite> spriteList_proc2 = new ArrayList<Sprite>();
	private List<Sprite> spriteList_occupied = new ArrayList<Sprite>();
	private List<Sprite> sprite_star = new ArrayList<Sprite>();

	private List<Action> actionList = World.WORLD.getActionList();

	private List<Procedure> final_actionList = new ArrayList<Procedure>();

	public void setLevelCompleted(boolean t) {this.level_completed = t;}

	private int nbrAction;
	private int nbrProc;

	private int wichProc = 0;
	private int compteur_couleur=0;
	private int nbcouleurs=4;

	//	private List<Action> actionList;

	public Gui(int nbrA, int nbrP)
	{
		Sprite spr;

		sprite_return.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.RETURN_MENU));
		sprite_return.setTextureRect(new IntRect(1, 1, 100, 100));
		sprite_play_retry.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.PLAY_ACTION));
		
		sprite_switch.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.CHOIX_COULEUR));
		sprite_switch.setTextureRect(new IntRect(1, 1, 80, 80));
		sprite_switch.setPosition(new Vector2f(Graphic.SFML.getPositionCamera_f().x,Graphic.SFML.getPositionCamera_f().y+500));
		
		for(int i = 0 ; i < 3 ; i ++)
		{
			Sprite temp = new Sprite();
			sprite_star.add(temp);
		}

        load_sprite(actionList, Color.NOIR);



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

		sprite_main.setColor(new org.jsfml.graphics.Color(128, 255, 128));
		placeGui();
	}
	
	public void load_sprite(List<Action> actionList, Color c)
	{
		for(int i = 0; i < actionList.size(); i++)
		{
			Action act = actionList.get(i); 
			Sprite spr = new Sprite();
			if(c == Color.DEFAUT)
			spr.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.ACTION));
			/*else if(c == Color.BLEU)
			spr.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.ACTION_BLEU));	
			else if(c == Color.VERT)
				spr.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.ACTION_BLEU));
			else if(c == Color.ROUGE)
				spr.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.ACTION_ROUGE));*/

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
			else if (act instanceof P1)
				spr.setTextureRect(new IntRect(730, 82, 80, 80));
			else if (act instanceof P2)
				spr.setTextureRect(new IntRect(811, 82, 80, 80));
			else if (act instanceof Fork)
				spr.setTextureRect(new IntRect(811, 1, 80, 80));

			spriteList.add(spr);
		}
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
		Graphic.SFML.draw(sprite_switch);

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
		Graphic.SFML.draw(sprite_end_of_game);
		Graphic.SFML.draw(sprite_return_eog);
		if(Graphic.SFML.get_level_y()<3)
			Graphic.SFML.draw(sprite_next);
		if(Graphic.SFML.get_level_y() == 3)
			Graphic.SFML.draw(sprite_end_of_game_text);
		for (Sprite spr : sprite_star)
			Graphic.SFML.draw(spr);

	}

	public void gerer()
	{
		if(level_completed)
		{

			sprite_end_of_game.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.END_OF_GAME));
			sprite_end_of_game.setOrigin(Ressources.TEXTURE.getHalfSize(TEXTURE.END_OF_GAME));
			sprite_end_of_game.setPosition(Graphic.SFML.getCenterCamera());
			sprite_return_eog.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.RETURN_MENU));
			sprite_return_eog.setTextureRect(new IntRect(1, 1, 100, 100));
			sprite_return_eog.setPosition(Graphic.SFML.getCenterCamera().x-200 , Graphic.SFML.getCenterCamera().y-10);

			for(int i = 0 ; i < 3 ; i ++)
			{
				int compteur = 0;
				for(Entities.Character ch: World.WORLD.getCharacterList())
				{
					compteur = compteur+ch.getNbActions();
				}
				if(compteur <= World.WORLD.getMinStar())
				{
					sprite_star.get(i).setTexture(Ressources.TEXTURE.getTexture(TEXTURE.STAR_FULL));
					sprite_star.get(i).setOrigin(Ressources.TEXTURE.getHalfSize(TEXTURE.STAR_FULL));
					sprite_star.get(i).setPosition(Graphic.SFML.getCenterCamera().x+(1-i)*200,Graphic.SFML.getCenterCamera().y-100);
				}
				else if(compteur < World.WORLD.getMaxStar() &&
						compteur > World.WORLD.getMinStar())
				{
					if(i == 0)
					{sprite_star.get(i).setTexture(Ressources.TEXTURE.getTexture(TEXTURE.STAR_EMPTY));
					sprite_star.get(i).setOrigin(Ressources.TEXTURE.getHalfSize(TEXTURE.STAR_EMPTY));}
					else
					{sprite_star.get(i).setTexture(Ressources.TEXTURE.getTexture(TEXTURE.STAR_FULL));
					sprite_star.get(i).setOrigin(Ressources.TEXTURE.getHalfSize(TEXTURE.STAR_FULL));}
					sprite_star.get(i).setPosition(Graphic.SFML.getCenterCamera().x+(1-i)*200,Graphic.SFML.getCenterCamera().y-100);
				}
				else
				{
					if(i == 2)
					{sprite_star.get(i).setTexture(Ressources.TEXTURE.getTexture(TEXTURE.STAR_FULL));
					sprite_star.get(i).setOrigin(Ressources.TEXTURE.getHalfSize(TEXTURE.STAR_FULL));}
					else
					{sprite_star.get(i).setTexture(Ressources.TEXTURE.getTexture(TEXTURE.STAR_EMPTY));
					sprite_star.get(i).setOrigin(Ressources.TEXTURE.getHalfSize(TEXTURE.STAR_EMPTY));}
					sprite_star.get(i).setPosition(Graphic.SFML.getCenterCamera().x+(1-i)*200,Graphic.SFML.getCenterCamera().y-100);
				}
			}


			sprite_next.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.NEXT));
			sprite_next.setTextureRect(new IntRect(1, 1, 128, 85));
			sprite_next.setPosition(Graphic.SFML.getCenterCamera().x+100 , Graphic.SFML.getCenterCamera().y);
			sprite_end_of_game_text.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.END_OF_GAME_TEXT));
			sprite_end_of_game_text.setOrigin(Ressources.TEXTURE.getHalfSize(TEXTURE.END_OF_GAME_TEXT));
			sprite_end_of_game_text.setPosition(Graphic.SFML.getCenterCamera().x+100 , Graphic.SFML.getCenterCamera().y+50);

		}
		if (Input.INPUT.again(BUTTON.MLEFT))
		{
			if (Graphic.isOnSprite(sprite_return)||Graphic.isOnSprite(sprite_return_eog)) //Retour
			{
				World.WORLD = null;
				Gui.GUI = null;
				Interpreter.INTERPRETER = null;
				Controler.CONTROLER = null;

				Menu.change_menu(Menu.MENU.LEVEL);
				((Menu_Level)Menu.Mymenu).set_nbr_monde(Graphic.SFML.get_level_x());

				return;
			}
			if (Graphic.isOnSprite(sprite_next)) //Retour
			{
				if(Graphic.SFML.get_level_x() == 0 && Graphic.SFML.get_level_y()<3)
				{
					Reader.read("levels/level1-"+(Graphic.SFML.get_level_y()+2)+".xml");
					Graphic.SFML.store_level(Graphic.SFML.get_level_x(),Graphic.SFML.get_level_y()+1);
				}
				else if(Graphic.SFML.get_level_x() == 1 && Graphic.SFML.get_level_y()<3)
				{
					Reader.read("levels/levelfor-"+(Graphic.SFML.get_level_y()+2)+".xml");
					Graphic.SFML.store_level(Graphic.SFML.get_level_x(),Graphic.SFML.get_level_y()+1);
				}
				else if(Graphic.SFML.get_level_x() == 2 && Graphic.SFML.get_level_y()<3)
				{
					Reader.read("levels/levelfork-"+(Graphic.SFML.get_level_y()+2)+".xml");
					Graphic.SFML.store_level(Graphic.SFML.get_level_x(),Graphic.SFML.get_level_y()+1);
				}

				return;
			}
			if (Graphic.isOnSprite(sprite_play_retry))
			{
				if (World.WORLD.isPlaying())
				{
					sprite_play_retry.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.PLAY_ACTION));

					World.WORLD.setPlaying(false);
					World.WORLD.initialiser();
				}
				else
				{
					sprite_play_retry.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.RETRY_ACTION));

					World.WORLD.setPlaying(true);
					List<Entities.Character> l =  World.WORLD.getCharacterList();

					for (int i=0; i < l.size(); i++)
						l.get(i).setMain(final_actionList.get(i));
				}
			}
			if (Graphic.isOnSprite(sprite_switch))
			{
				compteur_couleur=(compteur_couleur+1)%nbcouleurs;
				if(compteur_couleur == 0)
				{
					load_sprite(actionList,Color.DEFAUT);
					sprite_switch.setTextureRect(new IntRect(1, 1, 80, 80));
				}
				if(compteur_couleur == 1)
				{
					load_sprite(actionList,Color.ROUGE);
					sprite_switch.setTextureRect(new IntRect(82, 1, 80, 80));
				}
				if(compteur_couleur == 2)
				{
					load_sprite(actionList,Color.VERT);
					sprite_switch.setTextureRect(new IntRect(163, 1, 80, 80));
				}
				if(compteur_couleur == 3)
				{
					load_sprite(actionList,Color.BLEU);
					sprite_switch.setTextureRect(new IntRect(244, 1, 80, 80));
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
									if (j < max_action) 
									{
										final_actionList.get(wichProc).addProg(actionList.get(i));
										sprite_list.add(temp);
									}
								}
							}
							else if (j < max_action) 
							{
								if (actionList.get(i) instanceof P1)
								{
									final_actionList.get(wichProc).addProg(final_actionList.get(1));
									sprite_list.add(temp);
								}
								else if (actionList.get(i) instanceof P2)
								{
									final_actionList.get(wichProc).addProg(final_actionList.get(2));
									sprite_list.add(temp);
								}
								else if (j < max_action) 
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
			sprite_main.setColor(new org.jsfml.graphics.Color(128, 255, 128));
			if (sprite_proc1 != null)
				sprite_proc1.setColor(org.jsfml.graphics.Color.WHITE);
			if (sprite_proc2 != null)
				sprite_proc2.setColor(org.jsfml.graphics.Color.WHITE);
			wichProc = 0;
		}
		if (sprite_proc1 != null && Graphic.isOnSprite(sprite_proc1))
		{
			sprite_proc1.setColor(new org.jsfml.graphics.Color(128, 255, 128));
			sprite_main.setColor(org.jsfml.graphics.Color.WHITE);
			if (sprite_proc2 != null)
				sprite_proc2.setColor(org.jsfml.graphics.Color.WHITE);
			wichProc = 1;
		}
		if (sprite_proc2 != null && Graphic.isOnSprite(sprite_proc2))
		{
			sprite_proc2.setColor(new org.jsfml.graphics.Color(128, 255, 128));
			sprite_main.setColor(org.jsfml.graphics.Color.WHITE);
			sprite_proc1.setColor(org.jsfml.graphics.Color.WHITE);
			wichProc = 2;
		}
	}
}