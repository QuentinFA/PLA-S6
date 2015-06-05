package Actions;
import Entities.Character;

public abstract class Action 
{
	/**
	 * Application d'une action à partir d'une position
	 * @param p : La position à partir de laquelle appliquer l'action
	 * @param orientation : L'orientation pour effectuer l'action
	 * @return : La coordonnées résultante
	 */
	
	protected int c;
	
	public int getColor() {return c;}
	
	public void setColor(int color) {c = color;}
	
	public abstract Coordonnees execute(Coordonnees p, int orientation);
	
	//Pour left_turn et right_turn
	public abstract void execute(Character p);
}
