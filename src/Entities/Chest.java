package Entities;

import org.jsfml.graphics.Sprite;

import Game.Ressources;
import Game.World;
import Game.Ressources.TEXTURE;
import Prog.Action;
import Prog.Coordonnees;

/**
 * Classe permettant la representation du concept d'objet (voir doc). Elle herite de block car elle a les mêmes proprietes de base.
 *
 */
public abstract class Chest extends Block 
{
	public Chest(Coordonnees coord)
	{
		super(coord);
	}

	protected Action action;
	protected Sprite sprite_action = new Sprite(Ressources.TEXTURE.getTexture(TEXTURE.CHEST));
	
	public Sprite getBulle() {return sprite_action;}
	
	public Action getAction() {return action;}
	public void setAction(Action a) {action = a;}
	
	public abstract void setTextureChest();
	public void setAlpha(int a) {sprite.setColor(new org.jsfml.graphics.Color(255, 255, 255, a));}
	
	public void initialiser() 
	{
		sprite.setColor(org.jsfml.graphics.Color.WHITE);
		World.WORLD.addFront(sprite_action);
	}
	
	/**
	 * Utiliser un Chest equivaut a le ranger dans le personnage.
	 */
	public void perform(Character p) {p.setChest(this);}
}
