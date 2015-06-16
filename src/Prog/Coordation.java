package Prog;

public class Coordation {
	private Coordonnees coord;
	private int orientation;
	
	public Coordation(Coordonnees c, int o){
		coord = c;
		orientation = o;
	}
	
	//accesseur
	public Coordonnees getCoord(){return coord;}
	public int getOrientation(){return orientation;}

	//mutateur
	public void setCoord(Coordonnees c){coord = c;}
	public void setOrientation(int o){orientation = o;}
}
