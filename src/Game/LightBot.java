package Game;

import java.io.IOException;

public class LightBot
{
	public static void main(String[] args)
	{
		SFML.initialiser(800, 600);
		
		try {Ressources.initialiser();}
		catch (IOException e) {e.printStackTrace();}
		
		while (SFML.fenetre.isOpen()) //Boucle principale
			SFML.gerer();
	}
}
