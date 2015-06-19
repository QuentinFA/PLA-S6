package Levels;

import java.io.IOException;

import Game.Input;
import Game.Ressources;
import UI.Graphic;
import UI.Gui;
import UI.Menu;

public class LevelPreview
{

	private static String levelPath = "levels/Demo1.xml"; //Le level a charger

	
	public static void main(String[] args)
	{
		Graphic.SFML.initialiser(1240, 780);
		
		try {Ressources.initialiser();}
		catch (IOException e) {e.printStackTrace();}
		
		Reader.read(levelPath);
		
		while (!Input.INPUT.gerer()) //Boucle principale
		{
			if (Graphic.SFML.gerer()) 
				return;
			
			if (Menu.Mymenu != null)
				Menu.Mymenu.gerer();
			
			if (Gui.GUI != null)
				Gui.GUI.gerer();
			
			Graphic.SFML.afficher();
		}
	}
	
}
