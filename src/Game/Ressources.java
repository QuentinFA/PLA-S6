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
	static Texture texture_block0 = new Texture(); //Block de base
	
	/**
	 * Charge les images
	 * @throws IOException 
	 */
	static void initialiser() throws IOException
	{
		texture_block0.loadFromFile(Paths.get("images/block0.png"));
	}
}
