// Marko Golovko
// Pracownia PO, piątek, s. 137
// L 3, z 1, lista wiązana 
// Linked list
// class Linked list
// 2018-03-16

// Marko Golovko
// Pracownia PO, piątek, s. 137
// L 4, z 1, lista wiązana 
// Kolekcja IEnumerable
// class Linked list
// 2018-03-23

using System;
using System.Collections;
namespace Lista3Zadanie1
{
    //element listy 
    public class Node<T>
    {
        public Node<T> Next;
        public Node<T> Prev;
        public T Value;
    }


    //enumerator 
    class ListEnum<T> : IEnumerator
    {
        Node<T> lista,current;

        public ListEnum(Node<T> lista) {
            this.lista = lista;
        }


        public bool MoveNext() {
            if (this.current == null) this.current = this.lista;
            else this.current = this.current.Next;
            return this.current != null;
        }


        public object Current {
            get
            {
                return current.Value;
            }
        }


        public void Reset() {
            this.current = this.lista;
        }
        public void Dispose() { }
}

    // class listy
    public class LinkedList<T> : IEnumerable
    {
        private Node<T> head;
        private Node<T> tail;

        public int Length;


        //konstructor listy
        public LinkedList()
        {
            head = new Node<T>();
            tail = head;
            tail.Next = null;
            Length = 0;
        }


        // tworzy napis który zawiera elementy listy
        public override string ToString(){
            string res = "Linked list";
            Node<T> current = head;
            while (current != null)
            {
                res += " " + current.Value;
                current = current.Next;
            }
            return res; 
            }


        // dostęp indeksowany
        public T this[int indeks]
        {
            get
            {
                Node<T> current = head;
                while (indeks != 0)
                {
                    current = current.Next;
                    indeks -= 1;
                }
                return current.Value;
            }
        }


            // implementacja interfejsu IEnumerable
            IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }
        public IEnumerator GetEnumerator()
        {
            return new ListEnum<T>(head);
        }


        //dodaje element na koniec listy
        public void Append(T val)
        {
            Node<T> newNode = new Node<T>();
            newNode.Value=val;
            if (IsEmpty())
            {
                head = tail = newNode;
            }
            else
            {
                tail.Next = newNode;
                newNode.Prev = tail;
                tail = newNode;    
            }
            tail.Next = null;
            Length += 1;
        }


        //dodaje element na początek listy
        public void AddAtStart (T val)
        {
            Node<T> newNode = new Node<T>();
            newNode.Value = val;
            if (IsEmpty())
            {
                head = tail = newNode;
            }
            else
            {
                newNode.Next = head;
                head.Prev = newNode;
                head = newNode;
            }
            Length += 1;
        }


        // sprawdzanie zawartośći listy
        public bool IsEmpty ()
        {
            if (Length == 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }


        // usuwa element z początku listy
        public T RemoveFirst()
        {
            if ( IsEmpty() ){
                Console.WriteLine("No element exist in this linked list.");
                return default(T);
            }
            else
            {
                Node<T> first = head;
                head = first.Next;
                head.Prev = null;
                Length -= 1;
                return first.Value;
            }
        }
        // usuwa ostatni element listy
        public T RemoveLast()
        {
            if (IsEmpty())
            {
                Console.WriteLine("No element exist in this linked list.");
                return default(T);
            }
            else
            {
                Node<T> last = tail;
                tail = last.Prev;
                tail.Next = null;
                Length -= 1;
                return last.Value;
            }
        }
        //wypisuje elementy listy
        public void PrintList()
        {
            
            if (IsEmpty())
            {
                Console.WriteLine("List is empty");
            }
            else
            {
                
                Node<T> current = head;
                while (current != null) 
                {
                    Console.WriteLine(current.Value);
                    current = current.Next;
               }
                

            }
        }
    }

}
