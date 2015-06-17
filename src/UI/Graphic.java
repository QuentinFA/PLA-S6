package UI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.PrimitiveType;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
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

import Game.Input;
import Game.Input.BUTTON;
import Game.Ressources;
import Game.Ressources.MUSIC;
import Game.Ressources.TEXTURE;
import Game.World;

public class Graphic 
{
	public static Graphic SFML = new Graphic();
	
	private RenderWindow fenetre; //Fenetre du programme
	private View camera; //Camera du programme
	private int width; //Taille horizontale de la fenetre
	private int height; //Taille verticale de la fenetre
	
	//Fond
	boolean mute = true;
	Sprite boutonSound = new Sprite();
	private List<Sprite> nuages = new ArrayList<Sprite>();
	int wait = 0;
	private VertexArray fond = new VertexArray(PrimitiveType.QUADS); //Fond
	/**
	 * level_x, level_y pour memoriser le level on vient de charger
	 */
	private static int level_x;
	private static int level_y;
	public void store_level(int x, int y){
		level_x = x;
		level_y = y;
	}
	public int get_level_x(){return level_x;}
	public int get_level_y(){return level_y;}
	
	//Fonctions
	public void draw(Drawable object) {fenetre.draw(object);} //Dessiner un objet
	public Vector2i getPositionMouse() {return Mouse.getPosition(fenetre);}
	
	public Vector2f getCenterCamera() {return new Vector2f(camera.getCenter().x , camera.getCenter().y);}
	public Vector2f getSizeCamera() {return camera.getSize();}
	public FloatRect getViewCamera() {return new FloatRect(getPositionCamera_f(), getSizeCamera());}
	
	public Vector2f getPositionCamera_f() {return Vector2f.sub(camera.getCenter(), Vector2f.div(camera.getSize(), 2));}
	public Vector2i getPositionCamera_i() {return new Vector2i((int)getPositionCamera_f().x , (int)getPositionCamera_f().y);}
	
	public void invisible_cursor() {fenetre.setMouseCursorVisible(false);}
	public void visible_cursor() {fenetre.setMouseCursorVisible(true);}
	public Event getEvent() {return fenetre.pollEvent();}
	
	/**
	 * Permet de savoir si la souris est placee sur un sprite
	 * @param s Le sprite a tester
	 * @return True si c'est le cas, false sinon
	 */
	static boolean isOnSprite(Sprite s)
	{
		FloatRect rectangle = s.getGlobalBounds();
		
		Vector2i pos_mouse = Graphic.SFML.getPositionMouse();
		Vector2i real_pos = Vector2i.add(pos_mouse, Graphic.SFML.getPositionCamera_i());
		
		if (real_pos.x > rectangle.left && 
				real_pos.x < (rectangle.left+rectangle.width) &&
				real_pos.y > rectangle.top &&
				real_pos.y < (rectangle.top+rectangle.height))
			return true;
		return false;
	}
	
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
		
		boutonSound.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BOUTON_SOUND));
		boutonSound.setTextureRect(new IntRect(1 , 1 , 100 , 100));		
		boutonSound.setPosition(getPositionCamera_f());
		
		for (int i=0; i < 6; i++)
		{
			Sprite sprite = new Sprite();
			sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.NUAGE));
			sprite.setTextureRect(new IntRect(1, 1+(new Random().nextInt(3)*155), 312, 154));
			
			int scale = new Random().nextInt(8)+1;
			sprite.setScale(5.f/scale, 5.f/scale);
			
			sprite.setPosition(new Vector2f(
					new Random().nextInt((int)(getSizeCamera().x)) + getPositionCamera_f().x, 
					new Random().nextInt((int)(getSizeCamera().y + sprite.getGlobalBounds().height)) + getPositionCamera_f().y - sprite.getGlobalBounds().height));
			
			nuages.add(sprite);
		}
	}
	
	/**
	 * Centre la camera sur une position (replace egalement le fond)
	 * @param pos La position
	 */
	public void setCenterCamera(Vector2f pos) 
	{
		camera.setCenter(pos);
		boutonSound.setPosition(getPositionCamera_f());
		placerFond();
	}
	
	/**
	 * Replace le fond en fonction de la camera
	 */
	private void placerFond()
	{
		FloatRect rect = getViewCamera();
		
		fond.clear();
		fond.add(new Vertex(new Vector2f(rect.left, rect.top), Color.CYAN));
		fond.add(new Vertex(new Vector2f(rect.left + rect.width, rect.top), Color.CYAN));
		fond.add(new Vertex(new Vector2f(rect.left + rect.width, rect.top + rect.height), Color.WHITE));
		fond.add(new Vertex(new Vector2f(rect.left, rect.top + rect.height), Color.WHITE));
	}
	
	/**
	 * Initialise la camera sur le centre de la fenetre (replace egalement le fond)
	 */
	public void camera_ini()
	{
		camera.setSize(new Vector2f(width, height));
		if (World.WORLD == null)
			setCenterCamera(new Vector2f(width/2.f, height/2.f));
		else
			setCenterCamera(World.WORLD.getCenterWorld());
	}
	
	/**
	 * Afficher le TOUT le programme (personnages, blocks, ...)
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
		
		Collections.sort(nuages, new NuageComparator());
		for (Sprite spr : nuages)
			draw(spr);
		
		draw(boutonSound);
		
		if (World.WORLD != null)
			World.WORLD.afficher();
		if (Menu.Mymenu != null)
			Menu.Mymenu.afficher();
		if (Gui.GUI != null)
			Gui.GUI.afficher();
		
		fenetre.display();
	}
	
	/**
	 * Translate la camera
	 * @param delta La translation
	 */
	public void moveCamera(Vector2f delta)
	{
		camera.move(delta);
		placerFond();
	}
	
	/**
	 * Gere les evenements camera, le fond, les nuages...
	 * @return True si fin du programme (fermeture de la fenetre), false sinon
	 */
	public boolean gerer()
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
		
		boutonSound.setPosition(getPositionCamera_f());
		if (Input.INPUT.again(Input.BUTTON.MLEFT))
		{
			if (isOnSprite(boutonSound))
			{
				if (mute == true)
				{
					mute = false;
					boutonSound.setTextureRect(new IntRect(102 , 1 , 100 , 100));
					Ressources.MUSIC.getMusic(MUSIC.MARIO).stop();
				}
				else if (mute == false)
				{
					mute = true;
					boutonSound.setTextureRect(new IntRect(1 , 1 , 100 , 100));
					Ressources.MUSIC.getMusic(MUSIC.MARIO).play();
				}
			}
		}
		
		if (nuages.size() < 30)
		{
			if (wait == 0)
			{
				if (new Random().nextInt(30) == 0)
				{
					Sprite sprite = new Sprite();
					sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.NUAGE));
					sprite.setTextureRect(new IntRect(1, 1+(new Random().nextInt(4)*155), 312, 154));
					
					int scale = new Random().nextInt(8)+1;
					sprite.setScale(5.f/scale, 5.f/scale);
					
					sprite.setPosition(new Vector2f(getPositionCamera_f().x + getSizeCamera().x, 
							new Random().nextInt((int)(getSizeCamera().y + sprite.getGlobalBounds().height)) 
								+ getPositionCamera_f().y - sprite.getGlobalBounds().height));
					
					nuages.add(sprite);
					wait = 60;
				}
			}
			else
				wait --;
		}
		for (int i=0; i < nuages.size(); i++)
		{
			Sprite spr = nuages.get(i);
			spr.move(-spr.getScale().x*2, 0);
			if (spr.getPosition().x + spr.getGlobalBounds().width < getPositionCamera_f().x)
			{
				nuages.remove(spr);
				i--;
			}
		}
		
		return false;
	}
}
