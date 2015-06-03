package Actions;

public class Left_turn {
	
	public Left_turn() {}
	
	public void execute(Character p) {
		Sens s=p.getSens();
		
		if(s==Sens.NORD) {
			p.setSens(Sens.OUEST);
		}
		else if(s==Sens.SUD) {
			p.setSens(Sens.EST);
		}
		
		else if(s==Sens.EST) {
			p.setSens(Sens.NORD);
		}
		
		else {
			p.setSens(Sens.SUD);
		}
	}
}
