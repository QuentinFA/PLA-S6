package Actions;

public class Right_turn 
{
	public Right_turn() {}
	
	/**
	 * Tourne le character
	 * @param p Character à tourner
	 */
	public void execute(Character p)  {p.setOrientation((p.getOrientation()+1) % 4);}
}
