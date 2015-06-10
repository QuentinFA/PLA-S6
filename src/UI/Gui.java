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
	private Sprite returnMenu = new Sprite();
	private List<Action> actionList = new ArrayList<Action>();
	private int nbrAction;
	private int nbrProc;
	
	public Gui(List<Action> nameList, int nbrA, int nbrP)
	{
		Sprite spr;
		
		returnMenu.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.RETURN_MENU));
		returnMenu.setTextureRect(new IntRect(1,1,100,100));
		
		
		for (Action act : nameList)
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
	    System.out.println(spriteList.size());
		nbrAction = nbrA;
		nbrProc = nbrP;
		actionList = nameList;
		GUI = this;
	}
	
	public void afficher()
	{
		for (Sprite spr : spriteList)
			Graphic.SFML.draw(spr);
		Graphic.SFML.draw(this.returnMenu);
	}
	
	public void gerer()
	{
		returnMenu.setPosition(new Vector2f(Graphic.SFML.getPositionCamera_f().x+150,Graphic.SFML.getPositionCamera_f().y));
		for (int i=0; i < spriteList.size(); i++)
			spriteList.get(i).setPosition(new Vector2f(Graphic.SFML.getPositionCamera_f().x + i * spriteList.get(i).getTextureRect().width, Graphic.SFML.getPositionCamera_f().y + Graphic.SFML.getSizeCamera().y - spriteList.get(i).getTextureRect().height));
	
		if (Input.INPUT.again(BUTTON.MLEFT))
		{
			if (Graphic.isOnSprite(this.returnMenu))
			{
				World.WORLD = null;
				Gui.GUI = null;
				Menu.change_menu(Menu.MENU.LEVEL);
			}
			else
			{
				for (int i=0; i < spriteList.size(); i++)
				{
					if (Graphic.isOnSprite(spriteList.get(i)))
						World.WORLD.getCharacterList().get(0).use_Action(actionList.get(i));
				}
			}
		}
	}

	public int getNbrAction()
	{
		return nbrAction;
	}

	public int getNbrProc()
	{
		return nbrProc;
	}
	
	public List<Action> getActionList()
	{
		return actionList;
	}
}