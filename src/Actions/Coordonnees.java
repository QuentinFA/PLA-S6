package Actions;

public class Coordonnees {
	private int x;
	private int y;
	private int z;
	
	public Coordonnees() 
	{
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Coordonnees(int _x, int _y, int _z) 
	{
		x = _x;
		y = _y;
		z = _z;
	}
	
	public Coordonnees(Coordonnees pos) 
	{
		x = pos.x;
		y = pos.y;
		z = pos.z;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public int getZ()
	{
		return this.z;
	}
	
	public void setX(int _x)
	{
		this.x = _x;
	}
	
	public void setY(int _y)
	{
		this.y = _y;
	}
	
	public void setZ(int _z)
	{
		this.z = _z;
	}
	
	public void incrX(int incr)
	{
		this.x += incr;
	}
	
	public void incrY(int incr)
	{
		this.y += incr;
	}
	
	public void incrZ(int incr)
	{
		this.z += incr;
	}
	
	/**
	 * Deux coordonnées égales : x = x', y = y', z = z'
	 */
	public boolean equals(Object o)
	{
		if(!(o instanceof Coordonnees))
			return false;
		if(o == this)
			return true;
		
		Coordonnees t = (Coordonnees) o;
		return t.getX() == x && t.getY() == y && t.getZ() == z;
	}
}
