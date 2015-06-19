package Prog;

/**
 * Coordonnees des Entities. Permet de les afficher a l'ecran
 * @author edwin
 *
 */
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
	
	public float round(float nbr) {return Math.round(nbr*100)/100.f;}
	
	//Get
	public float getX() {return x;}
	public float getY() {return y;}
	public float getZ() {return z;}
	
	//Set
	public void setX(int _x) {x = _x;}
	public void setY(int _y) {y = _y;}
	public void setZ(int _z) {z = _z;}
	
	//Incrementer d'une valeur
	public void incrX(int incr) {x = round(x + incr);}
	public void incrY(int incr) {y = round(y + incr);}
	public void incrZ(int incr) {z = round(z + incr);}
	public void increment(Coordonnees coord) 
	{
		x = round(x + coord.x);
		y = round(y + coord.y);
		z = round(z + coord.z);
	}
	
	public boolean equals(Object o)
	{
		if (o == this) return true;
		if (!(o instanceof Coordonnees)) return false;
		
		Coordonnees t = (Coordonnees) o;
		return t.getX() == x && t.getY() == y && t.getZ() == z;
	}
	
	public boolean int_equals(Object o)
	{
		if (!(o instanceof Coordonnees)) return false;
		if (o == this) return true;
		
		Coordonnees t = (Coordonnees) o;
		return (int)(t.getX()) == (int)(x) && (int)(t.getY()) == (int)(y) && (int)(t.getZ()) == (int)(z);
	}
	
	public String toString() {return "(" + x  + ", " + y + ", " + z + ")";}
}
