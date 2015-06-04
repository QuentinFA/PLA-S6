package Game;
import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.*;

/**
 * Contient toutes les images charg�es
 * @author Florian
 *
 */
public class Ressources 
{
	public static Ressources RESSOURCES = new Ressources();
	
	private Texture texture_block1 = new Texture(); //Block de base
	public Texture getTexture1() {return texture_block1;}
	
	private Texture texture_bouton = new Texture(); //Block de base
	public Texture getTextureBouton() {return texture_bouton;}
	
	private Texture texture_bouton_sound = new Texture(); //Block de base
	public Texture getTextureBoutonSound() {return texture_bouton_sound;}
	/**
	 * Charge les images
	 * @throws IOException 
	 */
	public void initialiser() throws IOException
	{
		texture_block1.loadFromFile(Paths.get("images/block1.png"));
		texture_bouton.loadFromFile(Paths.get("images/bouton.png"));
		texture_bouton_sound.loadFromFile(Paths.get("image/boutonsound.png"));
	}
}
