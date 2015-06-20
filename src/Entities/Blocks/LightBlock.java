package Entities.Blocks;

import org.jsfml.graphics.IntRect;

import Entities.Block;
import Entities.Character;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Prog.Coordonnees;
import UI.Graphic;

/**
 * Bloc de lumiere. Il faut allumer tout les blocks de lumieres pour terminer un niveau.
 *
 */
public class LightBlock extends Block
{
	/**
	 * Cree un bloc lumiere eteint
	 * @param pos Coordonnees de ce bloc
	 */
	public LightBlock(Coordonnees coord)
	{
		super(coord);
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.BLOCK));
		isOn = false;
		initialiser();
	}
	
	private boolean isOn;
	
	private int anim = 0;
	private int one_frame = 8;
	
	/**
	 * Gestion graphique
	 */
	public void gerer() 
	{
		if (isOn)
		{
			if (anim % one_frame == 0)
				sprite.setTextureRect(new IntRect(83+anim/one_frame*82, 83, 81, 81));
			
			anim++;
			
			if (anim == one_frame*5)
				anim = 0;
		}
	}
	
	public void afficher()
	{
		gerer();
		Graphic.SFML.draw(sprite);
	}
	
	public void initialiser() {sprite.setTextureRect(new IntRect(1, 83, 81, 81));}
	
	/**
	 * Permet de connaitre l'état du bloc (allumé ou non)
	 * @return
	 */
	public boolean getLight() {return isOn;}
	
	public void reverseLight() 
	{
		isOn = !isOn;
		if (isOn)
			sprite.setTextureRect(new IntRect(83, 83, 81, 81));  
		else
			sprite.setTextureRect(new IntRect(1, 83, 81, 81));
	}
	
	/**
	 * Appele par l'action Light. Allume le bloc si il est eteint ou l'inverse dans le cas contraire.
	 */
	public void perform(Character p) {reverseLight();}
}
