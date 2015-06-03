package Game;
import org.jsfml.graphics.*;
import org.jsfml.window.*;
import org.jsfml.window.event.Event;

public class SFML 
{
	static RenderWindow fenetre; //Fenetre du programme
	
	/**
	 * Initialise la fenetre avec une taille
	 * @param width
	 * @param height
	 */
	static void initialiser(int width, int height)
	{
		 fenetre = new RenderWindow(new VideoMode(width, height), "LightBot");
	}
	
	/**
	 * Afficher le programme
	 */
	static void afficher()
	{
		fenetre.clear(Color.WHITE);
		
		World.WORLD.afficherBlocks();
		
		fenetre.display();
	}
	
	/**
	 * Gère les évenements clavier et souris
	 */
	static void input()
	{
        Event event = fenetre.pollEvent(); //Evenements
        if (event != null) {
            
            if (event.type == Event.Type.CLOSED)
                fenetre.close();
        }
	}
	
	/**
	 * Boucle principale du programme
	 */
	static void gerer()
	{
		input();
		
		//Placer ici les actions à faire
		
		afficher();
	}
}
