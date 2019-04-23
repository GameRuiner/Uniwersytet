// Marko Golovko
// Pracownia PO, piątek, s. 137
// L6, z2, Collection<E>
// implementacja interfejsu 
// klas listy
// 2018-13-05

import java.util.*;
public class SortedList<E extends Comparable<E>> implements Collection<E>{
	private List<E> list;
	
	//konstruktor
    public SortedList()
    {
        list = new LinkedList<E>();
    }
    
    // dodaje element, zachowując porządek 
    public boolean add(E e) {
		int position = Collections.binarySearch(list, e);
		position = (position < 0) ? -position - 1 : position;
		list.add(position, e);
	    return true;

		
	}
	
	//usuwa pierwszy element
	E Pop (){
		E e = list.get(0);
		list.remove(0);
		return e;
	}
	
	
	//wypisuje elementy listy
	void Print() {
		Iterator<E> itr = list.iterator();
		 System.out.println("----Start----");
		while(itr.hasNext()) {
	         Object element = itr.next();
	         System.out.println(element);
	      }
		System.out.println("----End----");
	}

	@Override
	public void clear() {
		list.clear();
		
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		Iterator<? extends E> itr = c.iterator();
		while(itr.hasNext()) {
	         E element = itr.next();
	         this.add(element);
	      }
		return true;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

}




