package Actions;

public class Right_turn {
	
	public Right_turn(){}
	
	public void execute(Character p) {
		Sens s=p.getSens();
		
		if(s==Sens.NORD) {
			p.setSens(Sens.EST);
		}
		else if(s==Sens.SUD) {
			p.setSens(Sens.OUEST);
		}
		
		else if(s==Sens.EST) {
			p.setSens(Sens.SUD);
		}
		
		else {
			p.setSens(Sens.NORD);
		}
	}
}
