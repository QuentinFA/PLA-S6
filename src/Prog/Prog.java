package Prog;

public abstract class Prog implements Cloneable
{
	protected Color couleur; // Couleur de l'action ou procédure
	
	public Color getColor() {return couleur;}
	public void setColor(Color c) {couleur = c;}
	
	public Object clone() throws CloneNotSupportedException {return super.clone();}
}
