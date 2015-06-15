package Game;
import java.util.EmptyStackException;
import java.util.ListIterator;
import java.util.List;

import java.util.Stack;

import Entities.Character;
import Prog.Action;
import Prog.Break;
import Prog.Procedure;
import Prog.Prog;
import Prog.NormalActions.For;



public class Interpreter 
{
	public static Interpreter INTERPRETER = new Interpreter();

	public Action eval(Character p)
	{
		Stack<ListIterator<Prog>> pile = p.getPile();
		ListIterator<Prog> it;

		try {it = pile.pop();}
		catch (EmptyStackException e) {return null;}
		
		Prog act = it.next();
		
		if (act instanceof For)
		{
			For actFor = (For) act;
			actFor.decrementer();
			if(!it.hasNext())
				return null;
			act = it.next();
			if (!actFor.isZero()) 
			{ 
				it.previous();
				it.previous();
			}

			if (it.hasNext())
				pile.push(it);

			if (act instanceof Procedure)
			{
				List<Prog> l = ((Procedure) act).getListProcedure();
				ListIterator <Prog> it2 = l.listIterator();
				pile.push(it2);
				act = eval(p);
			}
		}
		else if (act instanceof Procedure)
		{
			List<Prog> l = ((Procedure) act).getListProcedure();
			ListIterator <Prog> it2 = l.listIterator();
			if(it.hasNext())
				pile.push(it);
			pile.push(it2);
			act = eval(p);
		}
		else if (act instanceof Break)
		{
			ListIterator <Prog> it2;
			try {it2 = pile.pop();}
			catch (EmptyStackException e) {return null;}
			Prog a = it2.next();
			if(a instanceof For) {
				if(!it2.hasNext())
					return null;
				it2.next();
				pile.push(it2);
			}
			else {
				it2.previous();
				pile.push(it2);
				pile.push(it);
			}
		}
		else //C'est une action
		{
			if (it.hasNext())
				pile.push(it);
		}
		return (Action) act;
	}
}