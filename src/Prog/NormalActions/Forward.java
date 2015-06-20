package Prog.NormalActions;
import org.jsfml.graphics.IntRect;

import Entities.Character;
import Game.World;
import Prog.Action;
import Prog.Color;
import Prog.Coordonnees;
import Prog.Orientation;

/**
 * Action avance pour le personnage
 *
 */
public class Forward extends Action
{
	public Forward(Color color)
	{
		super(color);
	}

	private int frame = 0;
	private int last_frame_phase1 = 20;

	private Coordonnees delta;

	/**
	 * Avance le personnage p si c'est possible
	 */
	public boolean execute(Character p)
	{
		if (frame == 0) //Initialisation
		{
			Coordonnees coord = p.getCoord();
			Coordonnees check;

			switch (p.getOrientation())
			{
			case Orientation.NORTH:
				check = new Coordonnees(coord.getX(), coord.getY()+1, coord.getZ());
				delta = new Coordonnees(0, 1.f/last_frame_phase1, 0);
				break;
			case Orientation.EAST:
				check = new Coordonnees(coord.getX()+1, coord.getY(), coord.getZ());
				delta = new Coordonnees(1.f/last_frame_phase1, 0, 0);
				break;
			case Orientation.SOUTH:
				check = new Coordonnees(coord.getX(), coord.getY()-1, coord.getZ());
				delta = new Coordonnees(0, -1.f/last_frame_phase1, 0);
				break;
			case Orientation.WEST:
			default:
				check = new Coordonnees(coord.getX()-1, coord.getY(), coord.getZ());
				delta = new Coordonnees(-1.f/last_frame_phase1, 0, 0);
				break;
			}

			Coordonnees check2 = new Coordonnees(check);
			check2.incrZ(-1);
			if (!(World.WORLD.isValidPosition(check) && !World.WORLD.isValidPosition(check2)))
				delta = new Coordonnees(0, 0, 0);
		}

		frame ++;

		p.getCoord().increment(delta);
		animation(p);
		p.setPosSprite(World.WORLD.placeMe(p.getCoord()));

		return frame == last_frame_phase1;
	}

	private int anim = 0;

	private void animation(Character p)
	{
		if (anim % 5 == 0)
			p.setTextureRect(new IntRect(1+anim/5*82, 1+p.getOrientation()*82, 81, 81));

		anim++;

		if (anim == 20)
			anim = 0;
	}
}
