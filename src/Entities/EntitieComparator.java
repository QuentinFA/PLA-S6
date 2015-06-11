
package Entities;

import java.util.Comparator;

public class EntitieComparator implements Comparator<Entities> 
{
	public int compare(Entities b1, Entities b2) 
	{		
		if (b1.getCoord().getY() > b2.getCoord().getY())
			return -1;
		else if (b1.getCoord().getY() < b2.getCoord().getY())
			return 1;
		
		if (b1.getCoord().getX() > b2.getCoord().getX())
			return -1;
		else if (b1.getCoord().getX() < b2.getCoord().getX())
			return 1;
		
		if (b1.getCoord().getZ() > b2.getCoord().getZ())
			return 1;
		else if (b1.getCoord().getZ() < b2.getCoord().getZ())
			return -1;
		
		return 0;
	}
}
