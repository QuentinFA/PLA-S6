
package Entities;

import java.util.Comparator;

public class EntitieComparator implements Comparator<Entities> 
{
	/* @Override : 
	 * Indicates that a method declaration is intended to override a method declaration in a supertype.
	 * If a method is annotated with this annotation type compilers are required 
	 * to generate an error message unless at least one of the following conditions hold:
	 * The method does override or implement a method declared in a supertype.
	 * The method has a signature that is override-equivalent to that of any public method declared in Object.
	 */
	@Override
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
