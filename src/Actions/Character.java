package Actions;
import java.util.List;


public class Character 
{	
	private List<Action> _l;
	
	private Coordonnees position;
	private int orientation; //0: haut, 1: droite, 2: bas, 3: gauche 
	
	Character(Coordonnees pos,int ori) {
		position = pos;
		orientation = ori;
	}
	
	public Coordonnees getPosition() {return position;}
	
	public void setPosition(Coordonnees pos) {position = pos;}
	
	public int getOrientation() {return orientation;}
	
	public void setOrientation(int ori) {orientation = ori;}
	
	public void add_action(Action a) {_l.add(a);}
	
	public void use_actions() throws BoundException 
	{
		Action a;
		int i=0;
		while (!_l.isEmpty()) 
		{
			a=_l.get(i);
			_l.remove(i);
			i++;
			a.execute(this);
		}
	}
	
	
	
	
	
	

}
