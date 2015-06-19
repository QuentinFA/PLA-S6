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
import Prog.NormalActions.*;

public class Gui 
{
	public static Gui GUI = null;

	private int offset = 0; //Scroll fork
	private int final_offset = 0;

	//Option
	private Sprite sprite_return = new Sprite();
	private Sprite sprite_play_retry = new Sprite();
	private Sprite sprite_fast_forward = new Sprite();
	private Sprite sprite_aide = new Sprite();
	private boolean exitGui = false;

	//Panneaux
	private Sprite sprite_main = new Sprite();
	private Sprite sprite_proc1 = null;
	private Sprite sprite_proc2 = null;
	private Sprite sprite_fork = null;

	//Fin
	private Sprite sprite_next = new Sprite(Ressources.TEXTURE.getTexture(TEXTURE.NEXT));
	private Sprite sprite_end_of_game = new Sprite(Ressources.TEXTURE.getTexture(TEXTURE.END_OF_GAME));
	private Sprite sprite_return_eog = new Sprite(Ressources.TEXTURE.getTexture(TEXTURE.RETURN_ON_BILLBOARD));
	private List<Sprite> sprite_star = new ArrayList<Sprite>();
	private boolean level_completed = false;
	private boolean is_fast_forward = false;
	private boolean afficher_main = true;

	//Selection de la couleur
	private List<Sprite> colorList = new ArrayList<Sprite>();
	private Sprite cercleColor = new Sprite(Ressources.TEXTURE.getTexture(TEXTURE.CHOIX_COULEUR));

	private List<Sprite> spriteList = new ArrayList<Sprite>();
	private List<Sprite> spriteList_main = new ArrayList<Sprite>();
	private List<Sprite> spriteList_proc1 = new ArrayList<Sprite>();
	private List<Sprite> spriteList_proc2 = new ArrayList<Sprite>();
	private List<Sprite> spriteList_fork = new ArrayList<Sprite>();

	private List<Sprite> spriteList_occupied = new ArrayList<Sprite>();

	private List<Action> actionList = World.WORLD.getActionList(); //Liste des actions disponibles
	private List<Procedure> final_actionList = new ArrayList<Procedure>(); //Liste des actions a �x�cuter

	////////////////////////////////////////

	private int nbrAction;
	private int wichProc = 0; //0: Main, 1: P1, 2: P2, <Last>: Fork

	public List<Procedure> getFinalActionList() {return final_actionList;}

	public Gui(int nbrA)
	{
		//Options
		sprite_return.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.RETURN_MENU));
		sprite_return.setTextureRect(new IntRect(1, 1, 100, 100));
		sprite_play_retry.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.PLAY_ACTION));
		sprite_fast_forward.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.FAST_FORWARD));
		sprite_fast_forward.setTextureRect(new IntRect(1,1,127,127));
		sprite_aide.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.HELP_ME));
		sprite_aide.setTextureRect(new IntRect(1, 1, 80, 80));
		
		for (int i=0; i < 4; i++)
		{
			Sprite spr = new Sprite(Ressources.TEXTURE.getTexture(TEXTURE.CHOIX_COULEUR));
			spr.setTextureRect(new IntRect(64+i*31, 1, 30, 30));
			if (i > 0)
				spr.setColor(new org.jsfml.graphics.Color(175, 175, 175));
			colorList.add(spr);
		}
		cercleColor.setTextureRect(new IntRect(1, 1, 62, 62));

		//EOG
		sprite_end_of_game.setOrigin(Ressources.TEXTURE.getHalfSize(TEXTURE.END_OF_GAME));
		sprite_return_eog.setTextureRect(new IntRect(1, 1, 100, 100));

		nbrAction = nbrA;

		for (int i=0; i < nbrAction; i++)
			spriteList_occupied.add(new Sprite(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK_OCCUPIED)));

		// Main
		sprite_main.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.GUI_MAIN));
		final_actionList.add(new Procedure(Color.DEFAUT, 0));

		load_sprite();		

		GUI = this;

		sprite_main.setColor(new org.jsfml.graphics.Color(128, 255, 128));

		placeGui();
	}
    //charger les choix des actions
	public void load_sprite()
	{
		for (int i = 0; i < actionList.size(); i++)
		{
			Action act = actionList.get(i); 
			Sprite spr = new Sprite();

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
			else if (act instanceof P1)
			{
				spr.setTextureRect(new IntRect(730, 82, 80, 80));
				sprite_proc1 = new Sprite(Ressources.TEXTURE.getTexture(TEXTURE.PROC1));
				final_actionList.add(new Procedure(Color.DEFAUT, 1));
			}	
			else if (act instanceof P2)
			{
				spr.setTextureRect(new IntRect(811, 82, 80, 80));
				sprite_proc2 = new Sprite(Ressources.TEXTURE.getTexture(TEXTURE.PROC2));
				final_actionList.add(new Procedure(Color.DEFAUT, 2));
			}
			else if (act instanceof Fork)
			{
				spr.setTextureRect(new IntRect(811, 1, 80, 80));
				sprite_fork = new Sprite(Ressources.TEXTURE.getTexture(TEXTURE.FORK));
				final_actionList.add(new Procedure(Color.DEFAUT, final_actionList.size()));
			}
			else if (act instanceof Break)
				spr.setTextureRect(new IntRect(892, 1, 80, 80));

			spriteList.add(spr);
		}
	}
    //donner les coordonnées a tous les sprites.
	private void placeGui()
	{
		scroll();

		sprite_return.setPosition(new Vector2f(Graphic.SFML.getPositionCamera_f().x+150,Graphic.SFML.getPositionCamera_f().y));

		sprite_play_retry.setPosition(Graphic.SFML.getPositionCamera_f().x ,
				Graphic.SFML.getPositionCamera_f().y + Ressources.TEXTURE.getTexture(TEXTURE.BOUTON_SOUND).getSize().y + 50);
		sprite_fast_forward.setPosition(Graphic.SFML.getPositionCamera_f().x +3,
				Graphic.SFML.getPositionCamera_f().y + Ressources.TEXTURE.getTexture(TEXTURE.BOUTON_SOUND).getSize().y + Ressources.TEXTURE.getTexture(TEXTURE.PLAY_ACTION).getSize().y + 100);
		
		sprite_aide.setPosition(new Vector2f(Graphic.SFML.getPositionCamera_f().x+250,Graphic.SFML.getPositionCamera_f().y));

		
		for (int i=0; i < spriteList.size(); i++)
			spriteList.get(i).setPosition(new Vector2f(Graphic.SFML.getPositionCamera_f().x + i * spriteList.get(i).getTextureRect().width, Graphic.SFML.getPositionCamera_f().y + Graphic.SFML.getSizeCamera().y - spriteList.get(i).getTextureRect().height));

		//Color
		cercleColor.setPosition(new Vector2f(spriteList.get(0).getPosition().x + 15, spriteList.get(0).getPosition().y - 62 - 15));
		colorList.get(0).setPosition(new Vector2f(cercleColor.getPosition().x + 1, cercleColor.getPosition().y + 1));
		colorList.get(1).setPosition(new Vector2f(cercleColor.getPosition().x + 31, cercleColor.getPosition().y + 1));
		colorList.get(2).setPosition(new Vector2f(cercleColor.getPosition().x + 1, cercleColor.getPosition().y + 31));
		colorList.get(3).setPosition(new Vector2f(cercleColor.getPosition().x + 31, cercleColor.getPosition().y + 31));
		

		//Panneau
		sprite_main.setPosition(new Vector2f(Graphic.SFML.getCenterCamera().x + Graphic.SFML.getSizeCamera().x/2.f - Ressources.TEXTURE.getTexture(TEXTURE.GUI_MAIN).getSize().x - 20, 
				Graphic.SFML.getCenterCamera().y - Graphic.SFML.getSizeCamera().y/2.f + 20 - offset));

		for (int i=0; i < nbrAction; i++)
			spriteList_occupied.get(i).setPosition(new Vector2f((i%4)*80 + sprite_main.getPosition().x + 1, 80*(i/4) + sprite_main.getPosition().y + 25));

		if (sprite_proc1 != null)
			sprite_proc1.setPosition(new Vector2f(sprite_main.getPosition().x, sprite_main.getPosition().y + Ressources.TEXTURE.getTexture(TEXTURE.GUI_MAIN).getSize().y + 20));
		if (sprite_proc2 != null)
			sprite_proc2.setPosition(new Vector2f(sprite_proc1.getPosition().x, sprite_proc1.getPosition().y + Ressources.TEXTURE.getTexture(TEXTURE.PROC1).getSize().y + 20));
		if (sprite_fork != null)
		{
			if (sprite_proc1 == null)
				sprite_fork.setPosition(new Vector2f(sprite_main.getPosition().x, sprite_main.getPosition().y + Ressources.TEXTURE.getTexture(TEXTURE.GUI_MAIN).getSize().y + 20));
			else if (sprite_proc1 != null && sprite_proc2 == null)
				sprite_fork.setPosition(new Vector2f(sprite_proc1.getPosition().x, sprite_proc1.getPosition().y + Ressources.TEXTURE.getTexture(TEXTURE.PROC1).getSize().y + 20));
			else
				sprite_fork.setPosition(new Vector2f(sprite_proc2.getPosition().x, sprite_proc2.getPosition().y + Ressources.TEXTURE.getTexture(TEXTURE.PROC1).getSize().y + 20));
		}

		//Arranger les positions des actions dans la fenetre
		for (int i=0; i < spriteList_main.size(); i++)
			spriteList_main.get(i).setPosition((i%4)*80 + sprite_main.getPosition().x + 1, 80*(i/4) + sprite_main.getPosition().y + 25);	   
		for (int i = 0; i < spriteList_proc1.size(); i++)
			spriteList_proc1.get(i).setPosition((i%4)*80 + sprite_proc1.getPosition().x, 80*(i/4) + sprite_proc1.getPosition().y + 25);	 
		for (int i = 0; i < spriteList_proc2.size(); i++)
			spriteList_proc2.get(i).setPosition((i%4)*80 + sprite_proc2.getPosition().x, 80*(i/4) + sprite_proc2.getPosition().y + 25);	 
		for (int i = 0; i < spriteList_fork.size(); i++)
			spriteList_fork.get(i).setPosition((i%4)*80 + sprite_fork.getPosition().x, 80*(i/4) + sprite_fork.getPosition().y + 25);

		if (level_completed)
		{
			sprite_end_of_game.setPosition(Graphic.SFML.getCenterCamera());
			sprite_return_eog.setPosition(Graphic.SFML.getCenterCamera().x-150 , Graphic.SFML.getCenterCamera().y-10);
			sprite_next.setPosition(Graphic.SFML.getCenterCamera().x+100 , Graphic.SFML.getCenterCamera().y);
			//TODO stars
		}
	}

	public void afficher()
	{
		Graphic.SFML.draw(sprite_return);
		Graphic.SFML.draw(sprite_play_retry);
		Graphic.SFML.draw(sprite_fast_forward);
		Graphic.SFML.draw(sprite_main);
		Graphic.SFML.draw(sprite_aide);
		
		//Color
		Graphic.SFML.draw(cercleColor);
		for (Sprite spr : colorList)
			Graphic.SFML.draw(spr);
		
		//Panneau
		if (sprite_proc1 != null)
			Graphic.SFML.draw(sprite_proc1);
		if (sprite_proc2 != null)
			Graphic.SFML.draw(sprite_proc2);
		if (sprite_fork != null)
			Graphic.SFML.draw(sprite_fork);

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
		for (Sprite spr : spriteList_fork)
			Graphic.SFML.draw(spr);

		if (level_completed)
		{
			Graphic.SFML.draw(sprite_end_of_game);
			Graphic.SFML.draw(sprite_return_eog);
			if(Menu_Level.get_level()<3)
				Graphic.SFML.draw(sprite_next); //TODO no more level
			for (Sprite spr : sprite_star)
				Graphic.SFML.draw(spr);

		}
	}

	public void gerer()
	{
		completeLevel();

		if (Input.INPUT.again(BUTTON.MLEFT))
		{
			if (Graphic.isOnSprite(sprite_return))
				exit();

			if (Graphic.isOnSprite(sprite_play_retry))
			{
				if (World.WORLD.isPlaying())
				{
					sprite_play_retry.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.PLAY_ACTION));

					World.WORLD.setPlaying(false);
					World.WORLD.initialiser();
					Graphic.SFML.setCenterCamera(World.WORLD.getCenterWorld());
				}
				else
				{
					sprite_play_retry.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.RETRY_ACTION));

					World.WORLD.setPlaying(true);
					
					for (Procedure pr : final_actionList)
						for(Prog pro : pr.getListProcedure())
							if(pro instanceof For)
								((For) pro).reset();

					World.WORLD.getCharacterList().get(0).setMain();
				}
			}

			if (Graphic.isOnSprite(sprite_fast_forward))
			{
				if (is_fast_forward)
				{
					Graphic.SFML.speedUp(false);
					is_fast_forward = false;
					sprite_fast_forward.setTextureRect(new IntRect(1, 1, 127, 127));
				}
				else
				{
					Graphic.SFML.speedUp(true);
					is_fast_forward = true;
					sprite_fast_forward.setTextureRect(new IntRect(130, 1, 127, 127));
				}
			}
		}

		if (exitGui)
			return;

		if (!World.WORLD.isPlaying())
		{
			if (Input.INPUT.again(BUTTON.MLEFT))
			{	
				//if(Graphic.isOnSprite(sprite_aide))
				//	World.WORLD.getActionListSoluce().ajoute_actions();
				//else{
				selecPanneau();
				selecColor();
				
				//Ajouter les actions dans la fenentre
				for (int i=0; i < spriteList.size(); i++)
					if (Graphic.isOnSprite(spriteList.get(i)))
					{
						Sprite temp = new Sprite(spriteList.get(i).getTexture(), spriteList.get(i).getTextureRect());
						temp.setColor(spriteList.get(i).getColor());
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
							else if (sprite_proc1 != null && wichProc == 1)
							{
								j = spriteList_proc1.size();
								max_action = 8;
								sprite_list = spriteList_proc1;
							}
							else if (sprite_proc2 != null && wichProc == 2)
							{
								j = spriteList_proc2.size();
								max_action = 8;
								sprite_list = spriteList_proc2;
							}
							else
							{
								j = spriteList_fork.size();
								max_action = 8;
								sprite_list = spriteList_fork;
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
									if (j < max_action) 
									{
										final_actionList.get(wichProc).addProg(actionList.get(i), temp);
										sprite_list.add(temp);
									}
							}
							else if (j < max_action) 
							{
								if (actionList.get(i) instanceof P1)
								{
									final_actionList.get(wichProc).addProg(final_actionList.get(1), temp);
									sprite_list.add(temp);
								}
								else if (actionList.get(i) instanceof P2)
								{
									final_actionList.get(wichProc).addProg(final_actionList.get(2), temp);
									sprite_list.add(temp);
								}
								else if (j < max_action) 
								{
									final_actionList.get(wichProc).addProg(actionList.get(i), temp);
									sprite_list.add(temp);
								}
							}
						}
					}

				////////////////

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
				for (int i=0; i < spriteList_fork.size(); i++)
					if (Graphic.isOnSprite(spriteList_fork.get(i)))
					{
						spriteList_fork.remove(i);
						final_actionList.get(final_actionList.size()-1).getListProcedure().remove(i);
					}
			}
		}

		placeGui();
	}
    //pour le cas  "ifthenelse"
	private void selecColor()
	{
		for (int i=0; i < colorList.size(); i++)
			if (Graphic.isOnSprite(colorList.get(i)))
			{
				for (int j=0; j < colorList.size(); j++)
					colorList.get(j).setColor(new org.jsfml.graphics.Color(175, 175, 175));
				colorList.get(i).setColor(org.jsfml.graphics.Color.WHITE);
				
				if (i == 0)
					for (int j=0; j < spriteList.size(); j++)
					{
						spriteList.get(j).setColor(org.jsfml.graphics.Color.WHITE);
						actionList.get(j).setColor(Color.DEFAUT);
					}
				else if (i == 1)
					for (int j=0; j < spriteList.size(); j++)
					{
						spriteList.get(j).setColor(org.jsfml.graphics.Color.RED);
						actionList.get(j).setColor(Color.ROUGE);
					}
				else if (i == 2)
					for (int j=0; j < spriteList.size(); j++)
					{
						spriteList.get(j).setColor(org.jsfml.graphics.Color.CYAN);
						actionList.get(j).setColor(Color.BLEU);
					}
				else
					for (int j=0; j < spriteList.size(); j++)
					{
						spriteList.get(j).setColor(org.jsfml.graphics.Color.GREEN);
						actionList.get(j).setColor(Color.VERT);
					}
					
			}
	}
    //si on a fini le niveau, on donne les etoiles pour evaluer
	private void completeLevel()
	{
		if (level_completed == false && World.WORLD.isComplete())
		{
			level_completed = true;
			World.WORLD.setPlaying(false);

			int compteur = 0;
			for (Entities.Character ch: World.WORLD.getCharacterList())
				compteur = compteur+ch.getNbActions();	

			if (compteur <= World.WORLD.getMinStar()) // 3 etoiles
				for (int i=0; i<3; i++)
				{
					sprite_star.add(new Sprite(Ressources.TEXTURE.getTexture(TEXTURE.STAR_FULL)));
					sprite_star.get(i).setPosition(Graphic.SFML.getCenterCamera().x-30-(1-i)*100 , Graphic.SFML.getCenterCamera().y-100);
				}
			else if (compteur < World.WORLD.getMaxStar() && compteur > World.WORLD.getMinStar()) // 2 etoiles
				for (int i=0; i<2; i++)
				{
					sprite_star.add(new Sprite(Ressources.TEXTURE.getTexture(TEXTURE.STAR_FULL)));
					sprite_star.get(i).setPosition(Graphic.SFML.getCenterCamera().x-30-(1-i)*100 , Graphic.SFML.getCenterCamera().y-100);					
				}
			else //1 etoile			
			    {
				    sprite_star.add(new Sprite(Ressources.TEXTURE.getTexture(TEXTURE.STAR_FULL)));				
				    sprite_star.get(0).setPosition(Graphic.SFML.getCenterCamera().x-30-100 , Graphic.SFML.getCenterCamera().y-100);
			    }

		}

		if (level_completed == true && Input.INPUT.again(BUTTON.MLEFT))
		{
			if (Graphic.isOnSprite(sprite_return_eog))
				exit();

			if (Graphic.isOnSprite(sprite_next))
				next();
		}
	}
    //retourner dans le menu_level
	private void exit()
	{
		Graphic.SFML.speedUp(false);
		World.WORLD = null;
		Gui.GUI = null;
		Interpreter.INTERPRETER = null;
		Controler.CONTROLER = null;
        Menu_Level.set_nbr_monde(Menu_Level.get_monde());
		Menu.change_menu(Menu.MENU.LEVEL);

		exitGui = true;
	}
    //entrer dans le niveau suivant
	private void next()
	{
		if (Menu_Level.get_monde() == 0)
			Reader.read("levels/level1"+"-"+(Menu_Level.get_level()+2)+".xml");
		else if (Menu_Level.get_monde() == 1)
			Reader.read("levels/levelprocedure"+"-"+(Menu_Level.get_level()+2)+".xml");
		else if(Menu_Level.get_monde() == 2)
			Reader.read("levels/levelfor"+"-"+(Menu_Level.get_level()+2)+".xml");
		else if(Menu_Level.get_monde() == 3)
			Reader.read("levels/levelifthenelse"+"-"+(Menu_Level.get_level()+2)+".xml");
//		else if(Menu_Level.get_monde() == 4)
//			Reader.read("levels/levelifthenelse"+"-"+(Menu_Level.get_level()+2)+".xml");
		else if(Menu_Level.get_monde() == 5)
			Reader.read("levels/levelpoint"+"-"+(Menu_Level.get_level()+2)+".xml");
		else if(Menu_Level.get_monde() == 6)
			Reader.read("levels/levelfork"+"-"+(Menu_Level.get_level()+2)+".xml");
		else if(Menu_Level.get_monde() == 7)
			Reader.read("levels/levelchest"+"-"+(Menu_Level.get_level()+2)+".xml");
//		else if(Menu_Level.get_monde() == 8)
//			Reader.read("levels/levelexpert"+"-"+(Menu_Level.get_level()+2)+".xml");
		Menu_Level.storeLevel(Menu_Level.get_monde(), Menu_Level.get_level()+1);
	}
    /**
     * si on a les fenetres main proc1 proc2, les ecrans ne sont pas assez d'afficher toutes les fenetres avec fork, 
     * donc on donne un "offset" a la fenetre main pour afficher la fenetre fork.
     */
	private void scroll()
	{
		if (offset < final_offset)
		{
			offset += 10;
			if (offset > final_offset) offset = final_offset;
		}
		else
		{
			offset -= 10;
			if (offset < final_offset) offset = final_offset;
		}
	}
	
    //Pour choisir la fenetre d'ajouter les actions
	private void selecPanneau()
	{
		
		if (Graphic.isOnSprite(sprite_main))
		{
			
			sprite_main.setColor(new org.jsfml.graphics.Color(128, 255, 128));
			if (sprite_proc1 != null) sprite_proc1.setColor(org.jsfml.graphics.Color.WHITE);
			if (sprite_proc2 != null) sprite_proc2.setColor(org.jsfml.graphics.Color.WHITE);
			if (sprite_fork != null) sprite_fork.setColor(org.jsfml.graphics.Color.WHITE);
			if(!afficher_main)
			{
				final_offset = 0;
			    afficher_main = true;
			}
			wichProc = 0;
		}
		if (sprite_proc1 != null && Graphic.isOnSprite(sprite_proc1))
		{
			sprite_main.setColor(org.jsfml.graphics.Color.WHITE);
			sprite_proc1.setColor(new org.jsfml.graphics.Color(128, 255, 128));
			if (sprite_proc2 != null) sprite_proc2.setColor(org.jsfml.graphics.Color.WHITE);
			if (sprite_fork != null) sprite_fork.setColor(org.jsfml.graphics.Color.WHITE);

			final_offset = 0;
			wichProc = 1;
		}
		if (sprite_proc2 != null && Graphic.isOnSprite(sprite_proc2))
		{
			sprite_main.setColor(org.jsfml.graphics.Color.WHITE);
			sprite_proc1.setColor(org.jsfml.graphics.Color.WHITE);
			sprite_proc2.setColor(new org.jsfml.graphics.Color(128, 255, 128));
			if (sprite_fork != null) sprite_fork.setColor(org.jsfml.graphics.Color.WHITE);

			final_offset = 0;
			wichProc = 2;
		}
		if (sprite_fork != null && Graphic.isOnSprite(sprite_fork))
		{   
			   
			sprite_main.setColor(org.jsfml.graphics.Color.WHITE);
			if (sprite_proc2 != null) sprite_proc2.setColor(org.jsfml.graphics.Color.WHITE);
			if (sprite_fork != null) sprite_fork.setColor(org.jsfml.graphics.Color.WHITE);
			sprite_fork.setColor(new org.jsfml.graphics.Color(128, 255, 128));
            if(afficher_main)
			{
            	final_offset = (int)(sprite_fork.getPosition().y + Ressources.TEXTURE.getHalfSize(TEXTURE.FORK).y*2 + 20)
					- (int)(Graphic.SFML.getPositionCamera_f().y + Graphic.SFML.getSizeCamera().y);
			if (final_offset < 0)
				final_offset = 0;
			  afficher_main = false;
			}
			wichProc = final_actionList.size()-1;
		}

	}
}