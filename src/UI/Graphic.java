package UI;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.PrimitiveType;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Vertex;
import org.jsfml.graphics.VertexArray;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import UI.Input.BUTTON;
import Game.World;

public class Graphic 
{
	public static Graphic SFML = new Graphic();
	
	private RenderWindow fenetre; //Fenetre du programme
	private View camera;
	private int width;
	private int height;
	private VertexArray fond = new VertexArray(PrimitiveType.QUADS);
	
	public void draw(Drawable object) {fenetre.draw(object);};
	public Vector2i getPositionMouse() {return Mouse.getPosition(fenetre);}
	public Vector2f getCenterCamera() {return new Vector2f(camera.getCenter().x , camera.getCenter().y);}
	public Vector2i getSizeFenetre() {return new Vector2i(width, height);}
	public Vector2i getPositionCamera_i() {return new Vector2i((int)(camera.getCenter().x - width/2.f) , (int)(camera.getCenter().y - height/2.f));};	
	public Vector2f getPositionCamera_f() {return new Vector2f(camera.getCenter().x - width/2.f,camera.getCenter().y - height/2.f);};
	public Event getEvent(){return fenetre.pollEvent();}
	public void invisible_cursor() 	{fenetre.setMouseCursorVisible(false);}
	public void visible_cursor() 	{fenetre.setMouseCursorVisible(true);}
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
	
	public void placeCamera() {camera.setCenter(World.WORLD.getCenterWorld());}
	
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
		if (input())
			return;
		
		
		if (fenetre.getSize().x != width || fenetre.getSize().y != height)
		{
			width = fenetre.getSize().x;
			height = fenetre.getSize().y;
			
			camera_ini();
		}
		
		fenetre.setView(camera);
		
		fenetre.clear(Color.WHITE);
		fenetre.draw(fond);
		
		if (World.WORLD != null)
			World.WORLD.afficherBlocks();
		if (Menu.Mymenu != null)
			Menu.Mymenu.afficher();
		
		fenetre.display();
	}
	
	public void moveCamera(Vector2f delta)
	{
		camera.move(delta);
		placerFond();
	}
	
	/**
	 * G�re les �venements clavier et souris
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
		
		if (Input.INPUT.isPressed(BUTTON.UP))
			moveCamera(new Vector2f(0, -5));
		if (Input.INPUT.isPressed(BUTTON.RIGHT))
			moveCamera(new Vector2f(5, 0));
		if (Input.INPUT.isPressed(BUTTON.DOWN))
			moveCamera(new Vector2f(0, 5));
		if (Input.INPUT.isPressed(BUTTON.LEFT))
			moveCamera(new Vector2f(-5, 0));
		
		return false;
	}
}
