package Game;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;

import Entities.Character;
import Prog.Action;
import Prog.For;
import Prog.Procedure;
import Prog.Prog;
import Structures.LIFO;


public class Interpreter {

	public static Interpreter Interpreter;


	public Action interpret(Character p)
	{
		Interpreter= this;


		LIFO<Iterator<Prog>> pile = p.getPile();
		Iterator<Prog> save1;
		Iterator<Prog> it;

		try {
			it =  pile.get();

		}
		catch (EmptyStackException e) {
			return null;
		}

			//Il faut pop l'iterateur apres la fin de get
			save1=it;
			Prog act = it.next();
			if(act instanceof For)
			{
				For actFor = (For) act;
				actFor.decrementer();
				act = it.next();
				if (!(actFor.isZero())) 
					it = save1;

				if(it.hasNext())
					pile.put(it);
				
				if (act instanceof Procedure)
				{
					List<Prog> l = ((Procedure)act).getListProcedure();
					Iterator <Prog> it2 = l.iterator();
					pile.put(it2);
					act = this.interpret(p);
					
				}
			}
			else if (act instanceof Procedure)
			{
				List<Prog> l = ((Procedure)act).getListProcedure();
				Iterator <Prog> it2 = l.iterator();
				if(it.hasNext())
					pile.put(it);
				pile.put(it2);
				act = this.interpret(p);
			}
			else //C'est une action
			{
				if(it.hasNext())
					pile.put(it);
			}
			return (Action)act;

	}
}