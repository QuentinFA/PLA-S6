package Structures;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Représentation d'une pile Last In First Out
 * @param <E> Type de la pile
 *
 */
public class LIFO<E>
{
	private List<E> l;
	private int index;
	
	public LIFO()
	{
		this.l = new ArrayList<E>();
		index = 0;
	}
	
	public void put(E e)
	{
		this.l.add(e);
		index++;
	}
	
	public void putAll(List<E> e)
	{
		this.l.addAll(e);
		index += e.size();
	}
	
	public E get() throws EmptyStackException
	{
		index--;
		
		if(index < 0)
			throw new EmptyStackException();
		
		E e = l.get(index);
		l.remove(index);
		return e;
	}
	
	public String toString()
	{
		return l.toString();
	}
}
