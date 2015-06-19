package Prog.SpecialActions;

import org.jsfml.graphics.IntRect;

import Entities.Character;
import Game.World;
import Prog.Action;
import Prog.Color;
import Prog.Coordonnees;
import Prog.Orientation;


public class DoubleJump extends Action{
	public DoubleJump(Color c) {couleur = c;}
	private int frame = 0;
	
	private int last_frame_phase0 = 9;
	private int last_frame_phase1 = 18;
	private int last_frame_phase2 = 18;
	
	private Coordonnees delta;
	private Coordonnees futur_coord;
	
	private Coordonnees center_phase2;
	private boolean static_jump;
	private double angle;
	
	private PHASE phase;
	private enum PHASE
	{
		P0,
		P1,
		P1_5,
		P2,
		CHUTE
	}
	
	/** 
	 * Fait sauter le personnage p si c'est possible
	 */
	public boolean execute(Character p)
	{
		if (frame == last_frame_phase0) //Phase verticale
		{
			Coordonnees coord = p.getCoord();
			Coordonnees check = new Coordonnees(coord.getX(), coord.getY(), coord.getZ()+1);
			
			if (World.WORLD.isValidPosition(check))
				futur_coord = check;
			else
				return true;
			
			phase = PHASE.P1;
			delta = new Coordonnees(0, 0, 1.f/last_frame_phase1);
		}
		else if (frame ==  last_frame_phase0 + last_frame_phase1 ) 
		{
			Coordonnees coord = p.getCoord();
			Coordonnees check = new Coordonnees(coord.getX(), coord.getY(), coord.getZ()+1);
			
			if (World.WORLD.isValidPosition(check))
				futur_coord = check;
			else
				return true;
			
			phase = PHASE.P1_5;
			delta = new Coordonnees(0, 0, 1.f/last_frame_phase1);
		}
		else if (frame == last_frame_phase0 + 2*last_frame_phase1 - 1)
		{
			Coordonnees coord = futur_coord;
			Coordonnees check;
			
			switch (p.getOrientation())
			{
				case Orientation.NORTH:
					check = new Coordonnees(coord.getX(), coord.getY()+1, coord.getZ());
					center_phase2 = new Coordonnees(check.getX(), check.getY()-0.5f, check.getZ());
					break;
				case Orientation.EAST:
					check = new Coordonnees(coord.getX()+1, coord.getY(), coord.getZ()); 
					center_phase2 = new Coordonnees(check.getX()-0.5f, check.getY(), check.getZ());
					break;
				case Orientation.SOUTH:
					check = new Coordonnees(coord.getX(), coord.getY()-1, coord.getZ()); 
					center_phase2 = new Coordonnees(check.getX(), check.getY()+0.5f, check.getZ());
					break;
				case Orientation.WEST:
				default:
					check = new Coordonnees(coord.getX()-1, coord.getY(), coord.getZ()); 
					center_phase2 = new Coordonnees(check.getX()+0.5f, check.getY(), check.getZ());
					break;
			}
			
			Coordonnees check2 = new Coordonnees(check);
			check2.incrZ(-1);
			Coordonnees check3 = new Coordonnees(check2);
			check3.incrZ(-1);
			if (World.WORLD.isValidPosition(check) && (!World.WORLD.isValidPosition(check2) || World.WORLD.isValidPosition(check3)))
			{
				futur_coord = check;
				static_jump = false;
			}
			else
				static_jump = true;
			
			phase = PHASE.P2;
			angle = 0;
		}
		else if (frame == last_frame_phase0 + 2*last_frame_phase1 + last_frame_phase2)
		{
			phase = PHASE.CHUTE;
			delta = new Coordonnees(0, 0, -0.2f);
		}
		frame ++;
		
		///////////////////////////////////////////////////////////////////////////////////////////
		animation(p);
		
		if ( (phase == PHASE.P1) || (phase == PHASE.P1_5))
			p.getCoord().increment(delta);
		else if (phase == PHASE.P2)
		{
			if (static_jump)
				p.setCoord(new Coordonnees(p.getCoord().getX(), p.getCoord().getY(), center_phase2.getZ() + (float)(0.5*Math.sin(angle))));
			else
			{
				switch (p.getOrientation())
				{
					case Orientation.NORTH:
						p.setCoord(new Coordonnees(p.getCoord().getX(), center_phase2.getY() - (float)(0.5*Math.cos(angle)), center_phase2.getZ() + (float)(0.5*Math.sin(angle))));
						break;
					case Orientation.EAST:
						p.setCoord(new Coordonnees(center_phase2.getX() - (float)(0.5*Math.cos(angle)), p.getCoord().getY(), center_phase2.getZ() + (float)(0.5*Math.sin(angle))));
						break;
					case Orientation.SOUTH:
						p.setCoord(new Coordonnees(p.getCoord().getX(), center_phase2.getY() + (float)(0.5*Math.cos(angle)), center_phase2.getZ() + (float)(0.5*Math.sin(angle))));
						break;
					case Orientation.WEST:
					default:
						p.setCoord(new Coordonnees(center_phase2.getX() + (float)(0.5*Math.cos(angle)), p.getCoord().getY(), center_phase2.getZ() + (float)(0.5*Math.sin(angle))));
						break;
				}
			}
			
			angle += Math.PI/last_frame_phase2;
		}
		else if (phase == PHASE.CHUTE)
		{
			Coordonnees check = new Coordonnees(p.getCoord());
			check.increment(delta);
			
			if (p.getCoord().int_equals(check) || World.WORLD.isValidPosition(check))
			{
				p.getCoord().increment(delta);
				futur_coord = new Coordonnees((int)(p.getCoord().getX()), (int)(p.getCoord().getY()), (int)(p.getCoord().getZ()));
			}
			else
			{
				p.setCoord(new Coordonnees(futur_coord));
				p.setPosSprite(World.WORLD.placeMe(p.getCoord()));
				return true;
			}
		}
		p.setPosSprite(World.WORLD.placeMe(p.getCoord()));
		
		return false;
	}
	
	private int anim = 0;
	private void animation(Character p)
	{
		if (anim == 0)
			p.setTextureRect(new IntRect(1, 329+p.getOrientation()*82, 81, 81));
		if (anim == last_frame_phase0)
			p.setTextureRect(new IntRect(83, 329+p.getOrientation()*82, 81, 81));
		
		anim++;
	}
}


