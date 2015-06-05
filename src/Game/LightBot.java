package Game;

import java.io.IOException;

import Levels.Reader;
import UI.Graphic;
import UI.Input;
import UI.Menu_Main;
import UI.Menu_Level;
import UI.Menu;

public class LightBot
{
	public static void main(String[] args)
	{
		Graphic.SFML.initialiser(800, 600);
		
		try {Ressources.RESSOURCES.initialiser();}
		catch (IOException e) {e.printStackTrace();}
		
		//Reader.READER.read("src/Levels/lvl1.txt");
		//Graphic.SFML.placeCamera();
		Menu.change = 1;
		Menu.change_menu();


		
	    while (!Input.INPUT.gerer()) //Boucle principale
		{
	    	
//			if (Menu.Mymenu.gerer())
//			{
//				System.out.println("lightbot");
//				Menu_Main.MENU_MAIN = null;
//				Menu_Level.MENU_LEVEL.init();
				//Reader.READER.read("src/Levels/lvl1.txt");
//			}
			//TODO
			//Main
	    	Menu.Mymenu.gerer();
			Graphic.SFML.afficher();
		}
	}
}
