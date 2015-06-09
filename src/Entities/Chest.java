package Entities;

import Actions.Action;

public abstract class Chest extends Entities 
{
	protected int orientation;
	protected Action action;
	protected boolean open;
	
	public Action getAction() {return action;}
	public void setAction(Action a) {action = a;}
	public int getOrientation() {return orientation;}
	public void setOrientation(int o) {orientation = o;}
	public boolean isOpen() {return open;}
	public void setOpen(boolean o) {open = o;}
	
	public Chest openChest() 
	{
		setOpen(true);
		setTextureChest();
		return this;
	}
	public abstract void setTextureChest();
}
