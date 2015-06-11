
package Entities;

import java.util.Comparator;

public class EntitieComparator implements Comparator<Entities> 
{
	public int compare(Entities b1, Entities b2) 
	{
		/* TODO : Optimisation sur ce modèle
		int x = b2.getCoord().getX() - b1.getCoord().getX(),
				y = b2.getCoord().getY() - b1.getCoord().getY(),
				z = b2.getCoord().getZ() - b1.getCoord().getZ();
		
		if(y == 0)
		{
			if(x == 0)
				return z;
			return x;
		}
		
		return y;*/
		if ((int)(b1.getCoord().getZ()) > (int)(b2.getCoord().getZ()))
			return 1;
		else if ((int)(b1.getCoord().getZ()) < (int)(b2.getCoord().getZ()))
			return -1;
		
		if ((int)(b1.getCoord().getY()+0.9999999f) > (int)(b2.getCoord().getY()+0.9999999f))
			return -1;
		else if ((int)(b1.getCoord().getY()+0.9999999f) < (int)(b2.getCoord().getY()+0.9999999f))
			return 1;
		
		if ((int)(b1.getCoord().getX()) > (int)(b2.getCoord().getX()))
			return -1;
		else if ((int)(b1.getCoord().getX()) < (int)(b2.getCoord().getX()))
			return 1;
		
		return 0;
	}
}
