package Prog;

public class Coordonnees 
{
	private float x; //Cordonnee x
	private float y; //Cordonnee y
	private float z; //Cordonnee z
	
	//Constructeur
	public Coordonnees() {x = 0; y = 0; z = 0;}
	public Coordonnees(int _x, int _y, int _z) {x = _x; y = _y; z = _z;}
	public Coordonnees(float _x, float _y, float _z) {x = _x; y = _y; z = _z;}
	public Coordonnees(Coordonnees pos) {x = pos.x; y = pos.y; z = pos.z;}
	
	//Get
	public float getX() {return x;}
	public float getY() {return this.y;}
	public float getZ() {return this.z;}
	
	//Set
	public void setX(int _x) {x = _x;}
	public void setY(int _y) {y = _y;}
	public void setZ(int _z) {z = _z;}
	
	//Incrementer d'une valeur
	public void incrX(int incr) {x += incr;}
	public void incrY(int incr) {y += incr;}
	public void incrZ(int incr) {z += incr;}
	
	public boolean equals(Object o)
	{
		if (!(o instanceof Coordonnees)) return false;
		if (o == this) return true;
		
		Coordonnees t = (Coordonnees) o;
		return t.getX() == x && t.getY() == y && t.getZ() == z;
	}
}
