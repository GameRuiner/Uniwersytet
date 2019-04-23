// Marko Golovko
// Pracownia PO, piątek, s. 137
// L6, z1, Serializable
// implementacja interfejsu 
// klas SortedList
// 2018-13-05

import java.io.Serializable;

@SuppressWarnings("serial")
public class SortedList<T extends Comparable<T>> implements Serializable{

	Node<T> head;
	int length;
	
	//konstruktor
    public SortedList()
    {
        length = 0;
    }
    
    // dodaje element zachowując porządek 
	void Add(T e) {
		Node<T> newNode = new Node<T>();
		newNode.Value = e;
		Node<T> current = head;
		Node<T> old = null;
		while(current!=null) {
			if(e.compareTo(current.Value) == 1) {
				old = current;
				current = current.Next;	
			}else {
				break;
			}
		}
		newNode.Next = current;
		if(length==0 || old==null) head = newNode;
		else old.Next = newNode;
		length+=1;
		
	}
	
	//usuwa pierwszy element
	T Pop (){
		if(length>0) {
		Node<T> current = head;
		head = current.Next; 
		length-=1;
		return current.Value;	
		}
		return null;
	}
	
	
	//wypisuje elementy listy
	void Print() {
		Node<T> current = head;
		while(current != null) {
			System.out.println(current.Value);
			current = current.Next;
		}
	}

}


//element listy
@SuppressWarnings("serial")
class Node<T> implements Serializable
{
	public Node<T> Next;
    public T Value;
	
    
}


