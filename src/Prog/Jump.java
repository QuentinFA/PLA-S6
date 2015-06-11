package Prog;
import Entities.Character;
import Game.World;
import java.lang.Math;

public class Jump extends Action
{
	public Jump(Color c) {couleur = c;}
	private int frame = 0;
	
	private int last_frame_phase1 = 9;
	private int last_frame_phase2 = 18;
	
	private Coordonnees delta;
	private Coordonnees futur_coord;
	
	private Coordonnees center_phase2;
	private boolean static_jump;
	private double angle;
	
	private PHASE phase;
	private enum PHASE
	{
		P1,
		P2,
		CHUTE
	}
	
	public boolean execute(Character p)
	{
		if (frame == 0) //Phase verticale
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
		else if (frame == last_frame_phase1 - 1)
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
			
			if (World.WORLD.isValidPosition(check))
			{
				futur_coord = check;
				static_jump = false;
			}
			else
				static_jump = true;
			
			phase = PHASE.P2;
			angle = 0;
		}
		else if (frame == last_frame_phase1 + last_frame_phase2)
		{
			phase = PHASE.CHUTE;
			delta = new Coordonnees(0, 0, -0.2f);
		}
		frame ++;
		
		///////////////////////////////////////////////////////////////////////////////////////////
		if (phase == PHASE.P1)
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
			
			if (World.WORLD.isValidPosition(check))
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
}
