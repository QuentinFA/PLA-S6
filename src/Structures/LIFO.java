package Structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Représentation d'une pile Last In First Out
 * @param <E> Type de la pile
 *
 */
public class LIFO<E>
{
	private List<E> l;
	
	public LIFO()
	{
		this.l = new ArrayList<E>();
	}
	
	public void put(E e)
	{
		this.l.add(e);
	}
	
	public void putAll(List<E> e)
	{
		this.l.addAll(e);
	}
	
	public E get()
	{
		int i = l.size() - 1;
		E e = l.get(i);
		l.remove(i);
		return e;
	}
}
