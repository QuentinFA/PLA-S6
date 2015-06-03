package Game;

import java.util.Comparator;

public class BlockComparator implements Comparator<Block> 
{
	@Override
	public int compare(Block b1, Block b2) 
	{
		if (b1.getCoord().y > b2.getCoord().y)
			return -1;
		else if (b1.getCoord().y < b2.getCoord().y)
			return 1;
		
		if (b1.getCoord().x > b2.getCoord().x)
			return -1;
		else if (b1.getCoord().x < b2.getCoord().x)
			return 1;
		
		if (b1.getCoord().z > b2.getCoord().z)
			return 1;
		else if (b1.getCoord().z < b2.getCoord().z)
			return -1;
		
		return 0;
	}

}
