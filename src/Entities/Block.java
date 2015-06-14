package Entities;

public abstract class Block extends Entities implements Cloneable
{
	public Object clone() throws CloneNotSupportedException {return super.clone();} 
	
	public void initialiser() {}
	public void perform(Character p) {}
}
