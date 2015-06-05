package Game;

import java.io.IOException;
import java.nio.file.Paths;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.jsfml.audio.Music;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;



/**
 * Contient toutes les images charg�es
 * @author Florian
 *
 */

public class Ressources 
{
	public static Ressources RESSOURCES = new Ressources();
	
	private Texture texture_block1 = new Texture(); //Blocks
	public Texture getTexture1() {return texture_block1;}
	
	private Texture texture_bouton = new Texture(); //Blocks
	public Texture getTextureBouton() {return texture_bouton;}
	public Vector2f getSizeTextureBouton() {return new Vector2f(texture_bouton.getSize().x/2.f, texture_bouton.getSize().y/2.f);}
	
	private Texture texture_bouton_sound = new Texture(); //Blocks
	public Texture getTextureBoutonSound() {return texture_bouton_sound;}
	
	private Texture texture_fleche = new Texture(); //Blocks
	public Texture getTextureFleche() {return texture_fleche;}
	public Vector2f getSizeTextureFleche() {return new Vector2f(texture_fleche.getSize().x/2.f, texture_fleche.getSize().y/2.f);}
	
	/**
	 * pour le audio
	 */
	private Music music = new Music();
	public Music getMusic(){return music;}
	
	
	/**
	 * Charge les images
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 * @throws LineUnavailableException 
	 */
	public void initialiser() throws IOException
	{
		texture_block1.loadFromFile(Paths.get("images/block.png"));
		texture_bouton.loadFromFile(Paths.get("images/bouton.png"));
		texture_bouton_sound.loadFromFile(Paths.get("images/boutonSound.png"));
<<<<<<< HEAD
		texture_fleche.loadFromFile(Paths.get("images/fleches.png"));
		

=======
		
>>>>>>> 2cbeb62f718326bdd12195f1cb41bba2e7ddd194
		music.openFromFile(Paths.get("audio/audio.wav"));
		music.setLoop(true);
		music.play();
	}
}
