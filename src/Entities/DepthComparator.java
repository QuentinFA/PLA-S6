package Entities;

import java.util.Comparator;
import org.jsfml.graphics.Sprite;

public class DepthComparator implements Comparator<Sprite> 
{

	@Override
	public int compare(Sprite s1, Sprite s2) 
	{
		return (int) (s1.getPosition().y - s2.getPosition().y);
	}

}
