package Prog;

public class For extends Prog {
	private int i; //nombre d'itération
	
	
	public int getForValue(){return i;}
	public void setFor(int a){this.i=a;}
	public int decrementer()
	{
		this.i = i-1;
		return i;
	}
	
	public boolean isZero(){
		if(i<=0)
			return true;
		else 
			return false;
	}
}
