package Game;

import java.io.IOException;

import Levels.Reader;
import UI.Graphic;

public class LightBot
{
	public static void main(String[] args)
	{
		Graphic.SFML.initialiser(800, 600);
		
		try {Ressources.RESSOURCES.initialiser();}
		catch (IOException e) {e.printStackTrace();}
		
		Menu.init();
		
		while (!Graphic.SFML.input()) //Boucle principale
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
