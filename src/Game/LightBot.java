package Game;

import java.io.IOException;


import Levels.Reader;
import UI.Graphic;
import UI.Menu;
import UI.Input;

public class LightBot
{
	public static void main(String[] args)
	{
		Graphic.SFML.initialiser(800, 600);
		
		try {Ressources.RESSOURCES.initialiser();}
		catch (IOException e) {e.printStackTrace();}
		
		//Reader.READER.read("src/Levels/lvl1.txt");
		//Graphic.SFML.placeCamera();
		
	    Menu.init();
		
	    while (!Input.INPUT.gerer()) //Boucle principale
		{
			if (Menu.MENU.gerer())
			{
				Menu.MENU = null;
				Reader.READER.read("src/Levels/lvl1.txt");
			}
			//TODO
			//Main
			
			Graphic.SFML.afficher();
		}
	}
}
