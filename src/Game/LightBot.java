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
		
		Reader.READER.read("src/Levels/lvl1.txt");
		
		while (!Graphic.SFML.input()) //Boucle principale
		{
			//TODO
			//Main
			
			Graphic.SFML.afficher();
		}
	}
}
