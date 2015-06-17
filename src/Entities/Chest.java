package Entities;

import Prog.Action;
import Entities.Character;

public abstract class Chest extends Block 
{
	protected Action action;
	
	public Action getAction() {return action;}
	public void setAction(Action a) {action = a;}
	
	public Chest openChest() 
	{
		setTextureChest(); //TODO : Supprimer le coffre sur la map
		return this;
	}
	public abstract void setTextureChest();
	
	public void perform(Character c) 
	{
		c.setChest(this);
	}
}
