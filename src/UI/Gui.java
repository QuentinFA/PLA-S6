package UI;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import Game.Input;
import Game.Input.BUTTON;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Game.World;
import Prog.*;

public class Gui 
{
	public static Gui GUI = null;

	private List<Sprite> spriteList = new ArrayList<Sprite>();
	private List<Sprite> spriteList_main = new ArrayList<Sprite>();
	private List<Sprite> spriteList_proc1 = new ArrayList<Sprite>();
	private List<Sprite> spriteList_proc2 = new ArrayList<Sprite>();
	private List<Sprite> spriteList_occupied = new ArrayList<Sprite>();
	private int position_de_des = -1;
	private int counteur_des_main = 0;
	private int counteur_des_proc1 = 0;
	private int counteur_des_proc2 = 0;
	private boolean courant_main = true;
	private boolean courant_proc1 = false;
	private boolean courant_proc2 = false;
	private org.jsfml.graphics.Color color;
	private Sprite proc1 = new Sprite();
	private Sprite proc2 = new Sprite();
	private Sprite returnMenu = new Sprite();
	private Sprite gui_main = new Sprite();
	private List<Action> actionList = World.WORLD.getActionList();
	private Vector2f main1 = new Vector2f(Graphic.SFML.getCenterCamera().x + Graphic.SFML.getSizeCamera().x/2.f - Ressources.TEXTURE.getTexture(TEXTURE.GUI_MAIN).getSize().x-20,
			Graphic.SFML.getCenterCamera().y-Graphic.SFML.getSizeCamera().y/3.f);
	private Vector2f main2 = new Vector2f(Graphic.SFML.getCenterCamera().x + Graphic.SFML.getSizeCamera().x/2.f - Ressources.TEXTURE.getTexture(TEXTURE.GUI_MAIN).getSize().x-20,
			Graphic.SFML.getCenterCamera().y-Graphic.SFML.getSizeCamera().y*2/5.f); 
	private Vector2f main;
	private int nbrAction;
	private int nbrProc;

	public Gui(int nbrA, int nbrP)
	{
		Sprite spr;

		returnMenu.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.RETURN_MENU));
		returnMenu.setTextureRect(new IntRect(1,1,100,100));
		gui_main.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.GUI_MAIN));
		actionList = World.WORLD.getActionList();
        color = gui_main.getColor();

		//for (Action act : actionList)
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
			else if (act instanceof Des)
			{
				this.position_de_des = i;
				spr.setTextureRect(new IntRect(1, 82, 80, 80));
			}

			spriteList.add(spr);
		}
		nbrAction = nbrA;
		nbrProc = nbrP;
		if(nbrProc == 0)
			main = main1;
		else 
			main = main2;
		for (int i=0; i < nbrAction; i++)
		{  
			Sprite temp = new Sprite();
			temp.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK_OCCUPIED));
			temp.setPosition(new Vector2f((i%4)*80+main.x, 
					80*(i/4)+main.y+30));	
			spriteList_occupied.add(temp);
		}
		if(nbrProc != 0 )
		{
			proc1.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.PROC1));
			proc1.setPosition(main.x,main.y+Ressources.TEXTURE.getTexture(TEXTURE.GUI_MAIN).getSize().y+20);
			if(nbrProc == 2)
			{
				proc2.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.PROC2));
				proc2.setPosition(main.x, proc1.getPosition().y+Ressources.TEXTURE.getTexture(TEXTURE.PROC1).getSize().y+20);
			}
		}

		GUI = this;
	}

	public void afficher()
	{


		Graphic.SFML.draw(this.returnMenu);
		Graphic.SFML.draw(this.gui_main);
		if(nbrProc != 0 )
		{
			Graphic.SFML.draw(this.proc1);
			if(nbrProc == 2)
				Graphic.SFML.draw(this.proc2);
		}
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
		returnMenu.setPosition(new Vector2f(Graphic.SFML.getPositionCamera_f().x+150,Graphic.SFML.getPositionCamera_f().y));

		gui_main.setPosition(main);
		for (int i=0; i < spriteList.size(); i++)
			spriteList.get(i).setPosition(new Vector2f(Graphic.SFML.getPositionCamera_f().x + i * spriteList.get(i).getTextureRect().width, 
					Graphic.SFML.getPositionCamera_f().y + Graphic.SFML.getSizeCamera().y - spriteList.get(i).getTextureRect().height));

		if (Input.INPUT.again(BUTTON.MLEFT))
		{
			if (Graphic.isOnSprite(this.returnMenu))
			{
				World.WORLD = null;
				Gui.GUI = null;
				Menu.change_menu(Menu.MENU.LEVEL);
			}
			//pour choisir la fenetre d'ajouter les actions
			if (Graphic.isOnSprite(this.gui_main))
			{
				if(this.nbrProc != 0)
				{
					gui_main.setColor(new org.jsfml.graphics.Color(180,209,212));
				    proc1.setColor(new org.jsfml.graphics.Color(this.color.a,this.color.b,this.color.g));
				    proc2.setColor(new org.jsfml.graphics.Color(this.color.a,this.color.b,this.color.g));
				}
				this.courant_main = true;
				this.courant_proc1 = false;
				this.courant_proc2 = false;
			}
			if (Graphic.isOnSprite(this.proc1))
			{
				gui_main.setColor(new org.jsfml.graphics.Color(this.color.a,this.color.b,this.color.g));
				proc1.setColor(new org.jsfml.graphics.Color(180,209,212));
				proc2.setColor(new org.jsfml.graphics.Color(this.color.a,this.color.b,this.color.g));
				this.courant_main = false;
				this.courant_proc1 = true;
				this.courant_proc2 = false;
			}
			if (Graphic.isOnSprite(this.proc2))
			{	
				gui_main.setColor(new org.jsfml.graphics.Color(this.color.a,this.color.b,this.color.g));
			    proc1.setColor(new org.jsfml.graphics.Color(this.color.a,this.color.b,this.color.g));
			    proc2.setColor(new org.jsfml.graphics.Color(180,209,212));
				this.courant_main = false;
				this.courant_proc1 = false;
				this.courant_proc2 = true;
			}
			//ajouter les actions dans la fenentre
			for (int i=0; i < spriteList.size(); i++)
				if (Graphic.isOnSprite(spriteList.get(i)))
				{
					Sprite temp = new Sprite(spriteList.get(i).getTexture(),spriteList.get(i).getTextureRect());
					if(spriteList_main.size() <= nbrAction && courant_main)						
					{
						int j = spriteList_main.size();
						//j>0  il y a les elements dans le spriteList_main
						//i == this.position_de_des  utilisateur clique sur le bouton de des
						//this.counteur_des_main!=0  l'operation precedente est le des
						if ( j>0 && i == this.position_de_des && this.counteur_des_main!= 0)
						{
							if(this.counteur_des_main == 9)
							{
								this.counteur_des_main = 1;
								spriteList_main.get(j-1).setTextureRect(new IntRect(1, 82, 80, 80));
							}
							else
							{
								this.counteur_des_main++;
								spriteList_main.get(j-1).setTextureRect(new IntRect(80*(this.counteur_des_main-1)+this.counteur_des_main, 82, 80, 80));
							}								   
						}
						//ici on peut ajouter le block des dans la derniere place
						else if(j < nbrAction)
						{
							if(i == this.position_de_des)
								this.counteur_des_main = 1;
							else
								this.counteur_des_main = 0;
							spriteList_main.add(temp);
						}
					}
					else if(spriteList_proc1.size()<=8 && courant_proc1)
					{
						int j = spriteList_proc1.size();
						if ( j>0 && i == this.position_de_des && this.counteur_des_proc1!= 0)
						{
							if(this.counteur_des_proc1 == 9)
							{
								this.counteur_des_proc1 = 1;
								spriteList_proc1.get(j-1).setTextureRect(new IntRect(1, 82, 80, 80));
							}
							else
							{
								this.counteur_des_proc1++;
								spriteList_proc1.get(j-1).setTextureRect(new IntRect(80*(this.counteur_des_proc1-1)+this.counteur_des_proc1, 82, 80, 80));
							}								   
						}
						else if(j < 8)
						{
							if(i == this.position_de_des)
								this.counteur_des_proc1 = 1;
							else
								this.counteur_des_proc1 = 0;
							spriteList_proc1.add(temp);
						}
					}

					else if(spriteList_proc2.size()<=8 && courant_proc2)
					{
						int j = spriteList_proc2.size();
						if ( j>0 && i == this.position_de_des && this.counteur_des_proc2!= 0)
						{
							if(this.counteur_des_proc2 == 9)
							{
								this.counteur_des_proc2 = 1;
								spriteList_proc2.get(j-1).setTextureRect(new IntRect(1, 82, 80, 80));
							}
							else
							{
								this.counteur_des_proc2++;
								spriteList_proc2.get(j-1).setTextureRect(new IntRect(80*(this.counteur_des_proc2-1)+this.counteur_des_proc2, 82, 80, 80));
							}								   
						}
						else if(j < 8)
						{
							if(i == this.position_de_des)
								this.counteur_des_proc2 = 1;
							else
								this.counteur_des_proc2 = 0;
							spriteList_proc2.add(temp);
						}
					}
				}
			//si on clique sur la fenentre de main , proc1 ou proc2, il efface l'action
			for (int i=0; i < spriteList_main.size(); i++)
				if (Graphic.isOnSprite(spriteList_main.get(i)))
				{
					spriteList_main.remove(i);
				}
			for (int i=0; i < spriteList_proc1.size(); i++)
				if (Graphic.isOnSprite(spriteList_proc1.get(i)))
				{
					spriteList_proc1.remove(i);
				}
			for (int i=0; i < spriteList_proc2.size(); i++)
				if (Graphic.isOnSprite(spriteList_proc2.get(i)))
				{
					spriteList_proc2.remove(i);
				}
		}
		//arranger les positions des actions dans la fenetred
		for (int i=0; i < spriteList_main.size(); i++)
		{  
			spriteList_main.get(i).setPosition((i%4)*80+main.x, 80*(i/4)+main.y+30);	   
		}
		for (int i = 0; i < spriteList_proc1.size(); i++)
		{
			spriteList_proc1.get(i).setPosition((i%4)*80+main.x,80*(i/4)+main.y+Ressources.TEXTURE.getTexture(TEXTURE.GUI_MAIN).getSize().y+20+30);
		}
		for (int i = 0; i < spriteList_proc2.size(); i++)
		{
			spriteList_proc2.get(i).setPosition((i%4)*80+main.x,80*(i/4)+proc1.getPosition().y+Ressources.TEXTURE.getTexture(TEXTURE.PROC1).getSize().y+20+30);
		}
	}

	public int getNbrAction() {return nbrAction;}

	public int getNbrProc() {return nbrProc;}

	public List<Action> getActionList() {return actionList;}
}