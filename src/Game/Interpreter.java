 package Game;
import java.util.EmptyStackException;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import Entities.Character;
import Prog.Action;
import Prog.Color;
import Prog.Procedure;
import Prog.Prog;
import Prog.NormalActions.Break;
import Prog.NormalActions.For;

/**
 * Classe permettant de donner a un personnage sa prochaine action a effectuer.
 * @author edwin
 *
 */
public class Interpreter
{
	public static Interpreter INTERPRETER;

	/**
	 * Fonction appele par un personnage pour savoir sa prochaine action.
	 * @param p : Personnage qui veut savoir sa prochaine action
	 * @return :  Prochaine action a effectuer par p. Return Null si p a consomme toutes ses actions.
	 */
	public Action eval(Character p)
	{
		Stack<ListIterator<Prog>> pile = p.getPile(); //Recuperation de la pile de p pour savoir quelle est sa prochaine action
		ListIterator<Prog> it;

		try {it = pile.pop();} //Si la pile est vide, le personnage n'a plus rien a fairre
		catch (EmptyStackException e) {return null;}

		if (!it.hasNext()) //Retour de fonction
			return eval(p);

		Prog act = it.next(); //Recuperer l'action a effectuer
		
		if ( !(act.getColor() == Color.DEFAUT || act.getColor() == p.getColor()) ) //Si l'action recupere n'est pas de la bonne couleur, on la passe
		{
			if (it.hasNext())
				pile.push(it);
			return eval(p);
		}
		
		if (act instanceof For) //Si l'action recupere est un For.
		{
			For actFor = (For) act;
			actFor.decrementer(); //On decremente de 1 sa valeur  
			
			if (!it.hasNext()) //Si il n'y a rien apres, on ignore le For et on sort de la procedure.
				return eval(p);
			act = it.next();  //Sinon on regarde l'action apres le For.
			
			if ( !(act.getColor() == Color.DEFAUT || act.getColor() == p.getColor()) )  //Si elle n'est pas de la bonne couleur, on la passe et on ignore le for aussi.
			{
				if (it.hasNext())
					pile.push(it);
				return eval(p);
			}
				
			if (!actFor.isZero())  //Si il reste des tours de for a faire, on replace l'iterateur sur le for pour la prochaine evaluation.
			{
				it.previous();
				it.previous();
			}

			if (act instanceof Break)  //Si l'action recupere est un Break, on sort de la procedure mais on ne fait rien. Le Break est considerer comme une action.
			{
				p.incrementNbActions(); 
				return null;			
			}
			
			if (it.hasNext()) //Si il reste des actions dans la procedure ou l'on se trouve, on remet l'iteratorr dans la pile.
				pile.push(it);
				
			if (act instanceof Procedure) //Si l'action recuperer est une procedure, on evalue la premiere action de cette procedure.
			{
				List<Prog> l = ((Procedure) act).getListProcedure();
				ListIterator <Prog> it2 = l.listIterator(); //Creation de l'iterator pour cette nouvelle procedure.
				pile.push(it2);  //On empile au sommet de la pile l'iterator pour la nouvelle procedure et on evalue la premiere action de celle-ci.
				return eval(p);
			}
			
			p.incrementNbActions();
			return (Action) act;
		}
		
		else if (act instanceof Procedure) //Si l'action recuperer est une procedure, on evalue la premiere action de cette procedure.
		{
			List<Prog> l = ((Procedure) act).getListProcedure();  //Creation de l'iterator pour cette nouvelle procedure.
			ListIterator <Prog> it2 = l.listIterator();
			if (it.hasNext()) //Si il reste des actions dans la procedure actuelle ou l'on se trouve, on remet l'iterateur dans la pile.
				pile.push(it);
			pile.push(it2);  //On empile au sommet de la pile l'iterator pour la nouvelle procedure et on evalue la premiere action de celle-ci.
			return eval(p);
		}
		else if (act instanceof Break) //Si l'action recupere est un Break, on sort de la procedure mais on ne fait rien. Le Break est considerer comme une action.
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