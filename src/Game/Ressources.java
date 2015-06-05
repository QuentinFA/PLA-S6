package Game;

import java.io.IOException;
import java.nio.file.Paths;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.jsfml.audio.Music;
import org.jsfml.graphics.Texture;



/**
 * Contient toutes les images charg�es
 * @author Florian
 *
 */

public class Ressources 
{
	public enum Sound{
		On, Off
	}
	public static Ressources RESSOURCES = new Ressources();
	
	private Texture texture_block1 = new Texture(); //Block de base
	public Texture getTexture1() {return texture_block1;}
	
	private Texture texture_bouton = new Texture(); //Block de base
	public Texture getTextureBouton() {return texture_bouton;}
	
	private Texture texture_bouton_sound = new Texture(); //Block de base
	public Texture getTextureBoutonSound() {return texture_bouton_sound;}
	
	/**
	 * pour le audio
     */
	public Sound  on_off = Sound.On;
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

		music.openFromFile(Paths.get("audio/audio.wav"));
		music.setLoop(true);
		music.play();
	}
}
