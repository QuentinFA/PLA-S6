package Actions;
import Entities.Character;

public class Light extends Action
{
	public Light(int color) {c = color;}

	//Inutile
	public Coordonnees execute(Coordonnees p, int orientation){return p;}
	
	//Inutile
	public void execute(Character p) {}
}
