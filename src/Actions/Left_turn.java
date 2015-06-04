package Actions;

public class Left_turn 
{
	public Left_turn() {}
	
	public void execute(Character p)  {p.setOrientation((p.getOrientation()-1) % 4);}
}
