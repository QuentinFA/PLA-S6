package Actions;

public class Right_turn 
{
	public Right_turn() {}
	
	public void execute(Character p)  {p.setOrientation((p.getOrientation()+1) % 4);}
}
