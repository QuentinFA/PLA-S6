package Entities;

import Prog.Color;


public abstract class ColorBlock extends Block
{
	protected Color color;
	protected int sprite_pos;

	@Override
	public void perform(Character p)
	{
		p.setColor(this.color);
	}
}
