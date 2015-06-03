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
	static Texture texture_block1 = new Texture(); //Block de base
	
	/**
	 * Charge les images
	 * @throws IOException 
	 */
	static void initialiser() throws IOException
	{
		texture_block1.loadFromFile(Paths.get("images/block1.png"));
	}
}
