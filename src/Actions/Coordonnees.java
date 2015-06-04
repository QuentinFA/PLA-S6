package Actions;

public class Coordonnees {
	public int x;
	public int y;
	public int z;
	
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
}
