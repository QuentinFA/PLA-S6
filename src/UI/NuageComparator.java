package UI;

import java.util.Comparator;
import org.jsfml.graphics.Sprite;

public class NuageComparator implements Comparator<Sprite> 
{
	public int compare(Sprite spr1, Sprite spr2) 
	{
		if (spr1.getScale().x >= spr2.getScale().x)
			return 1;
		else
			return -1;
	}
}
