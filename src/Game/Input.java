package Game;

import org.jsfml.window.Keyboard;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.Mouse;
import org.jsfml.window.Mouse.Button;
import org.jsfml.window.event.Event;

import UI.Graphic;

/**
 * TODO
 *
 */
public class Input 
{
	public static Input INPUT = new Input();
	private static int NBR_KEY = 0;
	
	public enum BUTTON
	{
		MLEFT(), MRIGHT(), LEFT(), RIGHT(), UP(), DOWN(), LASTKEY();
		private int valeur;
		private BUTTON() {valeur = NBR_KEY; NBR_KEY++;}
		public static int getValeur(BUTTON b) {return b.valeur;}
	}
	
	private int[] TAB =  new int[BUTTON.getValeur(BUTTON.LASTKEY)];
	public Input ()
	{
		for(int i = 0 ; i < BUTTON.getValeur(BUTTON.LASTKEY) ; i++)
			TAB[i] = 0;
	}
	
	public boolean isPressed(BUTTON b) {return TAB[BUTTON.getValeur(b)] > 0;}
	public boolean again(BUTTON b) {return TAB[BUTTON.getValeur(b)] == 1;}
	
	public boolean gerer()
	{
		Event event = Graphic.SFML.getEvent(); //Evenements
		if (event != null) 
		{ 
			if (event.type == Event.Type.CLOSED || Keyboard.isKeyPressed(Key.ESCAPE))
				return true;		
		}
		
		if(Keyboard.isKeyPressed(Key.RIGHT)) 	TAB[BUTTON.getValeur(BUTTON.RIGHT)]++; 	else TAB[BUTTON.getValeur(BUTTON.RIGHT)] = 0; 
		if(Keyboard.isKeyPressed(Key.LEFT)) 	TAB[BUTTON.getValeur(BUTTON.LEFT)]++; 	else TAB[BUTTON.getValeur(BUTTON.LEFT)] = 0;
		if(Keyboard.isKeyPressed(Key.UP))		TAB[BUTTON.getValeur(BUTTON.UP)]++;		else TAB[BUTTON.getValeur(BUTTON.UP)] = 0;
		if(Keyboard.isKeyPressed(Key.DOWN))		TAB[BUTTON.getValeur(BUTTON.DOWN)]++;	else TAB[BUTTON.getValeur(BUTTON.DOWN)] = 0; 
		
		if(Mouse.isButtonPressed(Button.LEFT)) 	TAB[BUTTON.getValeur(BUTTON.MLEFT)]++; 	else TAB[BUTTON.getValeur(BUTTON.MLEFT)] = 0;
		if(Mouse.isButtonPressed(Button.RIGHT))	TAB[BUTTON.getValeur(BUTTON.MRIGHT)]++;	else TAB[BUTTON.getValeur(BUTTON.MRIGHT)] = 0; 
		
		return false;
	}
}
