package Levels;

import java.io.IOException;

import Game.Ressources;
import UI.Graphic;
import UI.Gui;
import UI.Input;
import UI.Menu;

public class LevelPreview
{
	private static String levelPath = "levels/level1-3.xml";
	
	public static void main(String[] args)
	{
		Graphic.SFML.initialiser(1240, 780);
		
		try {Ressources.initialiser();}
		catch (IOException e) {e.printStackTrace();}
		
		Reader.read(levelPath);
		//Graphic.SFML.setCenterCamera(World.WORLD.getCenterWorld());
		
		while (!Input.INPUT.gerer()) //Boucle principale
		{
			if (Graphic.SFML.gerer()) 
				return;
			
			if (Menu.Mymenu != null)
			{
				if (Menu.Mymenu.gerer())
					Menu.Mymenu = null;
			}
			
			if (Gui.GUI != null)
				Gui.GUI.gerer();
			
			Graphic.SFML.afficher();
		}
	}
	
}
