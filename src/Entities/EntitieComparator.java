
package Entities;

import java.util.Comparator;

public class EntitieComparator implements Comparator<Entities> 
{
	public int compare(Entities b1, Entities b2) 
	{
		if ((int)(b1.getCoord().getZ()) < (int)(b2.getCoord().getZ()))
			return -1;
		else if ((int)(b1.getCoord().getZ()) > (int)(b2.getCoord().getZ()))
			return 1;
		
		if ((int)(b1.getCoord().getY()) < (int)(b2.getCoord().getY()))
			return -1;
		else if ((int)(b1.getCoord().getY()) > (int)(b2.getCoord().getY()))
			return 1;
		
		if ((int)(b1.getCoord().getX()) < (int)(b2.getCoord().getX()))
			return -1;
		else if ((int)(b1.getCoord().getX()) > (int)(b2.getCoord().getX()))
			return 1;
		
		return 0;
	}
}
