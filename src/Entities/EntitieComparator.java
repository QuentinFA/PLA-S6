
package Entities;

import java.util.Comparator;
//TODO : commentaires
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
		float delta_x = b1.getCoord().getX() - b2.getCoord().getX();
		float delta_y = b1.getCoord().getY() - b2.getCoord().getY();
		float delta_z = b1.getCoord().getZ() - b2.getCoord().getZ();
		
		if (delta_x + delta_y + delta_z > 0)
			return 1;
		else if (delta_x + delta_y +delta_z < 0)
			return -1;
		
		return 0;
	}
}
