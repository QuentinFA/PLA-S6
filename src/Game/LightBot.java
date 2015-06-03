package Game;

import java.io.IOException;

import Levels.Reader;

public class LightBot
{
	public static void main(String[] args)
	{
		SFML.initialiser(800, 600);
		
		try {Ressources.initialiser();}
		catch (IOException e) {e.printStackTrace();}
		
		Reader.read("src/Levels/lvl1.txt");
		
		while (SFML.fenetre.isOpen()) //Boucle principale
			SFML.gerer();
	}
}
