package UI;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import Actions.Action;
import Actions.Forward;
import Actions.Jump;
import Actions.Left_turn;
import Actions.Light;
import Actions.Right_turn;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Game.World;
import UI.Input.BUTTON;

public class Gui 
{
	public static Gui GUI = null;
	
	private List<Sprite> spriteList = new ArrayList<Sprite>();
	private List<Action> actionList = new ArrayList<Action>();
	private int nbrAction;
	private int nbrProc;
	
	public Gui(List<Action> nameList, int nbrA, int nbrP)
	{
		Sprite spr;
		for (Action act : nameList)
		{
			spr = new Sprite();
			spr.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.ACTION));
			
			if (act instanceof Forward)
				spr.setTextureRect(new IntRect(1, 1, 80, 80));
			else if (act instanceof Right_turn)
				spr.setTextureRect(new IntRect(82, 1, 80, 80));
			else if (act instanceof Left_turn)
				spr.setTextureRect(new IntRect(163, 1, 80, 80));
			else if (act instanceof Jump)
				spr.setTextureRect(new IntRect(244, 1, 80, 80));
			else if (act instanceof Light)
				spr.setTextureRect(new IntRect(325, 1, 80, 80));
				
			spriteList.add(spr);
		}
		
		nbrAction = nbrA;
		nbrProc = nbrP;
		actionList = nameList;
		GUI = this;
	}
	
	public void afficher()
	{
		for (Sprite spr : spriteList)
			Graphic.SFML.draw(spr);
	}
	
	public void gerer()
	{
		for (int i=0; i < spriteList.size(); i++)
			spriteList.get(i).setPosition(new Vector2f(Graphic.SFML.getPositionCamera_f().x + i * spriteList.get(i).getTextureRect().width, Graphic.SFML.getPositionCamera_f().y + Graphic.SFML.getSizeCamera().y - spriteList.get(i).getTextureRect().height));
	
		if (Input.INPUT.again(BUTTON.MLEFT))
		{
			for (int i=0; i < spriteList.size(); i++)
			{
				if (Graphic.isOnSprite(spriteList.get(i)))
					World.WORLD.getCharacterList().get(0).use_Action(actionList.get(i));
			}
		}
	}
}