package Actions;
import Entities.Character;

public abstract class Action 
{
	protected Color couleur; // Couleur de l'action
	
	public Color getColor() {return couleur;}
	public void setColor(Color c) {couleur = c;}
	
	/**
	 * Application d'une action pour un personnage
	 * @param p : Le personnage dont il est question
	 */
	public abstract void execute(Character p);
}
