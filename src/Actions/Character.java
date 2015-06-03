package Actions;
import java.util.List;


public class Character {
	
	
	private Coordonnees _position;
	private List<Action> _l;
	private Sens _sens;
	
	Character(Coordonnees c,Sens s) {
		_position=c;
		_sens=s;
	}
	
	public Coordonnees getPosition() {
		return _position;
	}
	
	public void setPosition(Coordonnees c) {
		this._position=c;
	}
	
	public Sens getSens() {
		return _sens;
	}
	
	public void setSens(Sens s) {
		this._sens=s;
	}
	public void add_action(Action a) {
		_l.add(a);
	}
	
	
	public void use_actions() {
		try {
			Action a;
			int i=0;
			while(!(_l.isEmpty())) {
				a=_l.get(i);
				_l.remove(i);
				i++;
				a.execute(this);
			}
		}catch(deplacement_impossible e) { }
	}*/
	
	
	
	
	
	

}
