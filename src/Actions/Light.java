package Actions;

public class Light {
	public Light() {}
	
	public void execute(Character p) {
		Coordonnees c=p.getPosition();
		c._z -= 1; //Car le personnage est une case au dessus
		world.changeColor(c);
	}
}
