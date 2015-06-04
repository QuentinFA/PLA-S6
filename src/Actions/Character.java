package Actions;
import java.util.List;


public class Character 
{	
	//private List<Action> _l;
	
	private Coordonnees position;
	
	private int orientation; //0: haut, 1: droite, 2: bas, 3: gauche 
	
    private int c;
	
    
	Character(Coordonnees pos,int ori) 
	{
		position = pos;
		orientation = ori;
		c = Color.GRIS;
	}
	
	public Coordonnees getPosition() {return position;}
	
	public void setPosition(Coordonnees pos) {position = pos;}
	
	public int getOrientation() {return orientation;}
	
	public void setOrientation(int ori) {orientation = ori;}
	
	public void setColor(int _c) {c = _c;}

	public int getColor() {return c;}
	
	/*public void add_action(Action a) {_l.add(a);}
	
	public void use_Actions(Character p) throws BoundException 
	{
		Action a;
		int i=0;
		while (!_l.isEmpty()) 
		{
			a=_l.get(i);
			_l.remove(i);
			i++;
			p.use_Action(a);
		}
	}*/
	
	/**
	 *  Effectue l'action pour le character
	 * @param a Action à effectuer par le character
	 * @throws OutOfMapException Exception si le character sors de la map à l'issue de son déplacement
	 */
	public void use_Action(Action a) throws OutOfMapException {
		a.execute(this);
	}
	
	
	
	
	
	

}
