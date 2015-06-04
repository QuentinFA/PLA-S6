package Actions;

public abstract class Action 
{
	/**
	 * Application d'une action à partir d'une position
	 * @param p : La position à partir de laquelle appliquer l'action
	 * @param orientation : L'orientation pour effectuer l'action
	 * @return : La coordonnées résultante
	 */
	public abstract Coordonnees execute(Coordonnees p, int orientation);
}
