
package Entities;

import java.util.Comparator;

public class EntitieComparator implements Comparator<Entities> 
{
	public int compare(Entities b1, Entities b2) 
	{
		if ((int)(b1.getCoord().getZ()) > (int)(b2.getCoord().getZ()))
			return 1;
		else if ((int)(b1.getCoord().getZ()) < (int)(b2.getCoord().getZ()))
			return -1;
		
		float y1 = b1.getCoord().getY();
		if (y1 != (int)(y1)) y1 += 1;
		else y1 = (int)(y1);
		
		float y2 = b2.getCoord().getY();
		if (y2 != (int)(y2)) y2 += 1;
		else y2 = (int)(y2);
		
		if (y1 > y2)
			return -1;
		else if (y1 < y2)
			return 1;
		
		if ((int)(b1.getCoord().getX()) > (int)(b2.getCoord().getX()))
			return -1;
		else if ((int)(b1.getCoord().getX()) < (int)(b2.getCoord().getX()))
			return 1;
		
		return 0;
	}
}
