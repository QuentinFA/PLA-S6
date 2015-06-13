package Game;
import java.util.EmptyStackException;
import java.util.ListIterator;
import java.util.List;

import java.util.Stack;

import Entities.Character;
import Prog.Action;
import Prog.ReturnProc;
import Prog.Procedure;
import Prog.Prog;
import Prog.NormalActions.For;



public class Interpreter 
{
	public static Interpreter INTERPRETER = null;

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
		else if (act instanceof ReturnProc) 
			act = eval(p);
		else //C'est une action
		{
			if (it.hasNext())
				pile.push(it);
		}
		return (Action) act;
	}
}