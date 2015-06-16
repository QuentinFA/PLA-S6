package Entities;

public abstract class Block extends Entities implements Cloneable
{
	public Object clone() throws CloneNotSupportedException {return super.clone();} 
	
	public void initialiser() {}
	public abstract void perform(Character p);
}
