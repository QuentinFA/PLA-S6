package Prog;
import Entities.Character;
import Game.World;

public class Forward extends Action
{
	public Forward(Color c) {couleur = c;}
	private int frame = 0;
	private int last_frame_phase1 = 20;
	
	private Coordonnees delta;
	private Coordonnees futur_coord;
	
	private PHASE phase;
	private enum PHASE
	{
		P1,
		CHUTE
	}

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

			if (World.WORLD.isValidPosition(check))
				futur_coord = check;
			else
				delta = new Coordonnees(0, 0, 0);
			
			phase = PHASE.P1;
		}
		else if (frame == last_frame_phase1)
		{
			phase = PHASE.CHUTE;
			delta = new Coordonnees(0, 0, -0.2f);
		}
		//TODO other animation
		frame ++;
		
		if (phase == PHASE.P1)
			p.getCoord().increment(delta);
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
		
		//System.out.println(p.getCoord());
		p.setPosSprite(World.WORLD.placeMe(p.getCoord()));
		
		return false;
	}
}
