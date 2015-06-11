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
import Prog.Action;
import Prog.Forward;
import Prog.Jump;
import Prog.Left_turn;
import Prog.Light;
import Prog.OpenChest;
import Prog.Right_turn;
import Prog.Teleporter;
import Prog.UseChest;

public class Gui 
{
	public static Gui GUI = null;

	private List<Sprite> spriteList = new ArrayList<Sprite>();
	private List<Sprite> spriteList_main = new ArrayList<Sprite>();
	private List<Sprite> spriteList_occupied = new ArrayList<Sprite>();
	private Sprite returnMenu = new Sprite();
	private Sprite gui_main = new Sprite();
	private List<Action> actionList = World.WORLD.getActionList();
	private int nbrAction;
	private int nbrProc;

	public Gui(int nbrA, int nbrP)
	{
		Sprite spr;

		returnMenu.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.RETURN_MENU));
		returnMenu.setTextureRect(new IntRect(1,1,100,100));
		gui_main.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.GUI_MAIN));
		actionList = World.WORLD.getActionList();


		for (Action act : actionList)
		{
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

			spriteList.add(spr);
		}
		nbrAction = nbrA;
		nbrProc = nbrP;
		for (int i=0; i < nbrAction; i++)
		{  
			Sprite temp = new Sprite();
			 temp.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK_OCCUPIED));
		     temp.setPosition(new Vector2f((i%4)*80+Graphic.SFML.getCenterCamera().x + Graphic.SFML.getSizeCamera().x/2.f - Ressources.TEXTURE.getTexture(TEXTURE.GUI_MAIN).getSize().x-50, 
				        80*(i/4)+Graphic.SFML.getCenterCamera().y-Graphic.SFML.getSizeCamera().y/3.f+30));	
		     spriteList_occupied.add(temp);
		}
		GUI = this;
	}

	public void afficher()
	{
		for (Sprite spr : spriteList)
			Graphic.SFML.draw(spr);

		Graphic.SFML.draw(this.returnMenu);
		Graphic.SFML.draw(this.gui_main);
		for (Sprite spr : spriteList_occupied)
			Graphic.SFML.draw(spr);
		for (Sprite spr : spriteList_main)
			Graphic.SFML.draw(spr);
	}

	public void gerer()
	{
		returnMenu.setPosition(new Vector2f(Graphic.SFML.getPositionCamera_f().x+150,Graphic.SFML.getPositionCamera_f().y));

		gui_main.setPosition(Graphic.SFML.getCenterCamera().x + Graphic.SFML.getSizeCamera().x/2.f - Ressources.TEXTURE.getTexture(TEXTURE.GUI_MAIN).getSize().x-50,
				Graphic.SFML.getCenterCamera().y-Graphic.SFML.getSizeCamera().y/3.f);
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
				
			for (int i=0; i < spriteList.size(); i++)
					if (Graphic.isOnSprite(spriteList.get(i)))
					{
						World.WORLD.getCharacterList().get(0).use_Action(actionList.get(i));
						/*if(spriteList_main.size()<nbrAction)
						{ 
							Sprite temp = new Sprite(spriteList.get(i).getTexture(),spriteList.get(i).getTextureRect());
						    spriteList_main.add(temp);
						}*/
					}
			for (int i=0; i < spriteList_main.size(); i++)
				if (Graphic.isOnSprite(spriteList_main.get(i)))
				{
					spriteList_main.remove(i);
				}
		}
		for (int i=0; i < spriteList_main.size(); i++)
		{  
		     spriteList_main.get(i).setPosition(new Vector2f((i%4)*80+Graphic.SFML.getCenterCamera().x + Graphic.SFML.getSizeCamera().x/2.f - Ressources.TEXTURE.getTexture(TEXTURE.GUI_MAIN).getSize().x-50, 
				        80*(i/4)+Graphic.SFML.getCenterCamera().y-Graphic.SFML.getSizeCamera().y/3.f+30));	   
		}
	}

	public int getNbrAction() {return nbrAction;}

	public int getNbrProc() {return nbrProc;}

	public List<Action> getActionList() {return actionList;}
}