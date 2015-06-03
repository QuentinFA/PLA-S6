package Game;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.window.*;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.Event;

public class SFML 
{
	static RenderWindow fenetre; //Fenetre du programme
	static int width;
	static int height;
	static VertexArray fond = new VertexArray(PrimitiveType.QUADS);
	
	/**
	 * Initialise la fenetre avec une taille
	 * @param width
	 * @param height
	 */
	static void initialiser(int w, int h)
	{
		 fenetre = new RenderWindow(new VideoMode(w, h), "LightBot");
		 fenetre.setVerticalSyncEnabled(true);
		 width = w;
		 height = h;
		 
		 fond.add(new Vertex(new Vector2f(0, 0), Color.CYAN));
		 fond.add(new Vertex(new Vector2f(width, 0), Color.CYAN));
		 fond.add(new Vertex(new Vector2f(width, height), Color.WHITE));
		 fond.add(new Vertex(new Vector2f(0, height), Color.WHITE));
	}
	
	/**
	 * Afficher le programme
	 */
	static void afficher()
	{
		fenetre.clear(Color.WHITE);
		fenetre.draw(fond);
		
		World.WORLD.afficherBlocks();
		
		fenetre.display();
	}
	
	/**
	 * Gère les évenements clavier et souris
	 */
	static void input()
	{
        Event event = fenetre.pollEvent(); //Evenements
        if (event != null) 
        { 
            if (event.type == Event.Type.CLOSED || Keyboard.isKeyPressed(Keyboard.Key.ESCAPE))
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
