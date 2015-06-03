package Actions;



public class Forward extends Action{

	public Forward(){}
	
	public void execute(Character p) {
		Coordonnees c=p.getPosition();
		Sens s=p.getSens();
		
		if(s==Sens.NORD) {
			c._y+=1;
		}
		else if(s==Sens.SUD) {
			c._y-=1;
		}
		
		else if(s==Sens.EST) {
			c._x+=1;
		}
		
		else {
			c._x-=1;
		}
		
		if(world.checkNewPosition(p,c)) { //personnage et nouvelle cordonnées
				p.setPosition(c);
			}
		else {//le personnage nebouge pas) }
		
		
		
		
	}

}
