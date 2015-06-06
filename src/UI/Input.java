package UI;

import org.jsfml.window.Keyboard;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.Mouse;
import org.jsfml.window.Mouse.Button;
import org.jsfml.window.event.Event;

public class Input {
	
	public static Input INPUT = new Input();
	private int[] TAB =  new int[BUTTON.getValeur(BUTTON.LASTKEY)];
	public enum BUTTON{
		MLEFT(0),MRIGHT(1),LEFT(2),RIGHT(3),UP(4),DOWN(5),LASTKEY(6);
		private int valeur;
		private BUTTON(int i)
		{
			this.valeur = i;
		}
		public static int getValeur(BUTTON b)
		{
			return b.valeur;
		}
	}
	public Input ()
	{
		for(int i = 0 ; i < BUTTON.getValeur(BUTTON.LASTKEY) ; i++)
			this.TAB[i] = 0;
	}
	public boolean isPressed(BUTTON b)
	{
		if (TAB[BUTTON.getValeur(b)] > 0)
			return true;
		else
			return false;
	}
	public boolean again(BUTTON b)
	{
		if (TAB[BUTTON.getValeur(b)] == 1)
			return true;
		else
			return false;
	}
	public boolean gerer()
	{
		Event event = Graphic.SFML.getEvent(); //Evenements
		if (event != null) 
		{ 
			if (event.type == Event.Type.CLOSED || Keyboard.isKeyPressed(Key.ESCAPE))
				return true;		
		}
		if(Keyboard.isKeyPressed(Key.RIGHT))
			this.TAB[BUTTON.getValeur(BUTTON.RIGHT)]++;
		else
			this.TAB[BUTTON.getValeur(BUTTON.RIGHT)] = 0; 
		
		if(Keyboard.isKeyPressed(Key.LEFT))
			this.TAB[BUTTON.getValeur(BUTTON.LEFT)]++;
		else
			this.TAB[BUTTON.getValeur(BUTTON.LEFT)] = 0;
		
		if(Keyboard.isKeyPressed(Key.UP))
			this.TAB[BUTTON.getValeur(BUTTON.UP)]++;
		else
			this.TAB[BUTTON.getValeur(BUTTON.UP)] = 0;
		
		if(Keyboard.isKeyPressed(Key.DOWN))
			this.TAB[BUTTON.getValeur(BUTTON.DOWN)]++;
		else
			this.TAB[BUTTON.getValeur(BUTTON.DOWN)] = 0; 
		
		if(Mouse.isButtonPressed(Button.LEFT))
			this.TAB[BUTTON.getValeur(BUTTON.MLEFT)]++;
		else
			this.TAB[BUTTON.getValeur(BUTTON.MLEFT)] = 0;
		if(Mouse.isButtonPressed(Button.RIGHT))
			this.TAB[BUTTON.getValeur(BUTTON.MRIGHT)]++;
		else
			this.TAB[BUTTON.getValeur(BUTTON.MRIGHT)] = 0; 
		
		
		
		return false;
	}
}
