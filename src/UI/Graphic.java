package UI;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.*;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.Event;

import Game.World;

public class Graphic 
{
	public static Graphic SFML = new Graphic();
	
	private RenderWindow fenetre; //Fenetre du programme
	private View camera;
	private int width;
	private int height;
	private VertexArray fond = new VertexArray(PrimitiveType.QUADS);
	
	void draw(Drawable object) {fenetre.draw(object);}
	Vector2i getSizeFenetre() {return new Vector2i(width, height);}
	
	/**
	 * Initialise la fenetre avec une taille
	 * @param width
	 * @param height
	 */
	public void initialiser(int w, int h)
	{
		 fenetre = new RenderWindow(new VideoMode(w, h), "LightBot");
		 fenetre.setVerticalSyncEnabled(true);
		 camera = new View();
		 
		 width = w;
		 height = h;
		 
		 camera_ini();
	}
	
	private void placerFond()
	{
		FloatRect rect = new FloatRect(camera.getCenter().x - width/2.f, camera.getCenter().y - height/2.f, width, height);
		
		fond.clear();
		fond.add(new Vertex(new Vector2f(rect.left, rect.top), Color.CYAN));
		fond.add(new Vertex(new Vector2f(rect.left + rect.width, rect.top), Color.CYAN));
		fond.add(new Vertex(new Vector2f(rect.left + rect.width, rect.top + rect.height), Color.WHITE));
		fond.add(new Vertex(new Vector2f(rect.left, rect.top + rect.height), Color.WHITE));
	}
	
	public void camera_ini()
	{
		camera.setCenter(new Vector2f(width/2.f, height/2.f));
		camera.setSize(new Vector2f(width, height));
		
		placerFond();
	}
	
	/**
	 * Afficher le programme
	 */
	public void afficher()
	{
		if (fenetre.getSize().x != width || fenetre.getSize().y != height)
		{
			width = fenetre.getSize().x;
			height = fenetre.getSize().y;
			
			camera_ini();
		}
		
		fenetre.setView(camera);
		 
		fenetre.clear(Color.WHITE);
		fenetre.draw(fond);
		
		World.WORLD.afficherBlocks();
		
		fenetre.display();
	}
	
	/**
	 * Gère les évenements clavier et souris
	 */
	public boolean input()
	{
        Event event = fenetre.pollEvent(); //Evenements
        if (event != null) 
        { 
            if (event.type == Event.Type.CLOSED || Keyboard.isKeyPressed(Key.ESCAPE))
            {
                fenetre.close();
                return true;
            }
        }
        if (Keyboard.isKeyPressed(Key.UP) || Keyboard.isKeyPressed(Key.RIGHT) || Keyboard.isKeyPressed(Key.DOWN) || Keyboard.isKeyPressed(Key.LEFT))
        {
	        if (Keyboard.isKeyPressed(Key.UP))
	        	camera.move(new Vector2f(0, -5));
	        if (Keyboard.isKeyPressed(Key.RIGHT))
	        	camera.move(new Vector2f(5, 0));
	        if (Keyboard.isKeyPressed(Key.DOWN))
	        	camera.move(new Vector2f(0, 5));
	        if (Keyboard.isKeyPressed(Key.LEFT))
	        	camera.move(new Vector2f(-5, 0));
	        
	        placerFond();
        }
        return false;
	}
}
