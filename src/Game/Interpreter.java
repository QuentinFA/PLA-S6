package Game;
import java.util.EmptyStackException;
import java.util.ListIterator;
import java.util.List;
import java.util.Stack;

import Entities.Character;
import Prog.Action;
import Prog.Break;
import Prog.Color;
import Prog.Procedure;
import Prog.Prog;
import Prog.NormalActions.For;



public class Interpreter 
{
	public static Interpreter INTERPRETER;

	public Action eval(Character p)
	{
		Stack<ListIterator<Prog>> pile = p.getPile();
		ListIterator<Prog> it;

		try {it = pile.pop();}
		catch (EmptyStackException e) {return null;}

		if (!it.hasNext())
			return eval(p);

		Prog act = it.next();

		if ( !((act.getColor() == Color.DEFAUT) || (act.getColor() == p.getColor())) ) {
			if (it.hasNext())
				pile.push(it);
			return eval(p);
		}
		
		if (act instanceof For)
		{
			For actFor = (For) act;
			actFor.decrementer();
			if (!it.hasNext())
				return eval(p);
			act = it.next();
			
			if ( !((act.getColor() == Color.DEFAUT) || (act.getColor() == p.getColor())) ) {
				if (it.hasNext())
					pile.push(it);
				return eval(p);
				
			}
				
			if (!actFor.isZero()) 
			{ 
				it.previous();
				it.previous();
			}

			if (it.hasNext())
				pile.push(it);
			
			if (act instanceof Break) {
				p.incrementNbActions();
				return null;			
			}
			
			if (act instanceof Procedure)
			{
				List<Prog> l = ((Procedure) act).getListProcedure();
				ListIterator <Prog> it2 = l.listIterator();
				pile.push(it2);
				return eval(p);
			}
			
			p.incrementNbActions();
			return (Action) act;
		}
		
		else if (act instanceof Procedure)
		{
			List<Prog> l = ((Procedure) act).getListProcedure();
			ListIterator <Prog> it2 = l.listIterator();
			if(it.hasNext())
				pile.push(it);
			pile.push(it2);
			return eval(p);
		}
		else if (act instanceof Break)
		{
			p.incrementNbActions();
			return null;	
		}
		
		else //C'est une action
		{
			if (it.hasNext())
				pile.push(it);
			p.incrementNbActions();
			return (Action) act;
		}
		
	}
}