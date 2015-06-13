package Entities.Blocks;

import java.util.Comparator;

import Entities.Block;

public class BlockComparator implements Comparator<Block>
{
	@Override
	public int compare(Block o1, Block o2)
	{
		double z = o1.getCoord().getZ() - o2.getCoord().getZ(), 
				y = o1.getCoord().getY() - o2.getCoord().getY(), 
				x = o1.getCoord().getX() - o2.getCoord().getX();
		
		if (z == 0)
		{
			if (y == 0)
				return (int) x;
			return (int) y;
		}
		
		return (int) z;
	}
}
