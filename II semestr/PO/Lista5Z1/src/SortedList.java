import java.io.Serializable;

// Marko Golovko
// Pracownia PO, piątek, s. 137
// L6, z1, lista sortowana
// Lista sortowana
// klas listy
// 2018-04-05
public class SortedList<T extends Comparable<T>> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
class Node<T>
{
	public Node<T> Next;
    public T Value;
	
    
}


