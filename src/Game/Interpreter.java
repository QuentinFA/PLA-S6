package Game;
import java.util.EmptyStackException;
import java.util.ListIterator;
import java.util.List;



import Entities.Character;
import Prog.Action;
import Prog.ReturnProc;
import Prog.For;
import Prog.Procedure;
import Prog.Prog;
import Structures.LIFO;


public class Interpreter {

	public static Interpreter INTERPRETER;

	public Interpreter() {
		INTERPRETER = this;
	}

	public Action eval(Character p)
	{
		LIFO<ListIterator<Prog>> pile = p.getPile();
		ListIterator<Prog> it;

		try 
		{
			it = pile.get();
		}
		catch (EmptyStackException e) {
			return null;
		}
		Prog act = it.next();
		if (act instanceof For)
		{
			For actFor = (For) act;
			actFor.decrementer();
			act = it.next();
			if (!(actFor.isZero())) { 
				it.previous();
				it.previous();
			}

			if (it.hasNext())
				pile.put(it);

			if (act instanceof Procedure)
			{
				List<Prog> l = ((Procedure)act).getListProcedure();
				ListIterator <Prog> it2 = l.listIterator();
				pile.put(it2);
				act = this.eval(p);

			}
		}
		else if (act instanceof Procedure)
		{
			List<Prog> l = ((Procedure)act).getListProcedure();
			ListIterator <Prog> it2 = l.listIterator();
			if(it.hasNext())
				pile.put(it);
			pile.put(it2);
			act = this.eval(p);
		}
		else if (act instanceof ReturnProc) 
			act = this.eval(p);
		else //C'est une action
		{
			if(it.hasNext())
				pile.put(it);
		}
		return (Action)act;

	}
}