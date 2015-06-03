package Actions;

public class Jump {

	public Jump() {}
	
	public void execute(Character p) {
		Coordonnees c=p.getPosition();
		Coordonnees c2=p.getPosition();
		Sens s=p.getSens();
		
		if(s==Sens.NORD) {
			c._y+=1;
			c._z+=1;
			c2._y+=1;
			c2._z-=1;
		}
		else if(s==Sens.SUD) {
			c._y-=1;
			c._z+=1;
			c2._y-=1;
			c2._z-=1;
		}
		
		else if(s==Sens.EST) {
			c._x+=1;
			c._z+=1;
			c2._x+=1;
			c2._z-=1;
		}
		
		else {
			c._x-=1;
			c._z+=1;
			c2._x-=1;
			c2._z-=1;
		}
		
		if(world.checkNewPosition(p,c)) { //personnage et nouvelle cordonnées. Saut en montant
				p.setPosition(c);
			}
		else if(world.checkNewPosition(p,c2)) {//Saut en descendant
			p.setPosition(c2);
		}
		else {//le personnage nebouge pas) }
		
		
		
		
	}
	}
}
