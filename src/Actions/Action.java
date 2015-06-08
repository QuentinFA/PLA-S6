package Actions;
import Entities.Character;

public abstract class Action 
{
	protected Color.COLOR couleur; // Couleur de l'action
	
	public Color.COLOR getColor() {return couleur;}
	public void setColor(Color.COLOR c) {couleur = c;}
	
	/**
	 * Application d'une action pour un personnage
	 * @param p : Le personnage dont il est question
	 */
	public abstract void execute(Character p);
}
