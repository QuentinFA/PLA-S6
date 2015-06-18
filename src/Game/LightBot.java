package Game;

import java.io.IOException;

import Game.Ressources.MUSIC;
import UI.Graphic;
import UI.Gui;
import UI.Menu;
import UI.Menu.MENU;

public class LightBot
{
	public static void main(String[] args)
	{
		Graphic.SFML.initialiser(1240, 780);
		
		try {Ressources.initialiser();}
		catch (IOException e) {e.printStackTrace();}
		
		Menu.change_menu(MENU.MAIN);
		Ressources.MUSIC.getMusic(MUSIC.MARIO).play();
		Ressources.MUSIC.getMusic(MUSIC.MARIO).setLoop(true);
		
		while (!Input.INPUT.gerer()) //Boucle principale
		{
			if (Graphic.SFML.gerer()) 
				return;
			
			if (Menu.Mymenu != null)
				Menu.Mymenu.gerer();
			
			if (World.WORLD != null)
			{
				Controler.CONTROLER.manage();
				World.WORLD.gerer();
			}
			
			if (Gui.GUI != null)
				Gui.GUI.gerer();
			
			Graphic.SFML.afficher();
		}
	}
}
