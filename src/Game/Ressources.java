package Game;
import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.*;

/**
 * Contient toutes les images chargées
 * @author Florian
 *
 */
public class Ressources 
{
	static Ressources RESSOURCES = new Ressources();
	
	private Texture texture_block1 = new Texture(); //Block de base
	public Texture getTexture1() {return texture_block1;}
	
	/**
	 * Charge les images
	 * @throws IOException 
	 */
	public void initialiser() throws IOException
	{
		texture_block1.loadFromFile(Paths.get("images/block1.png"));
	}
}
