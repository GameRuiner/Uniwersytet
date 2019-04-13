// Marko Golovko
// Pracownia PO, piątek, s. 137
// L3, z2, słownik 
// Dictionary
// class Dictionary
// 2018-03-16
using System;

namespace TestDict
{

    public class Dictionary
    {
        private Entry[] entries = new Entry[100];
        private int[] buckets = new int[100];
        int count = 0;

        // wstawenie elementu do słownika
        // otrzymujemy hash i zapisujemy w odpowiednią komórkę
        public void Insert(string key, string value)
        {
            if (count == 0)
            {
                int i = 0;
                while (i < buckets.Length)
                {
                    buckets[i] = -1;
                    i += 1;
                }
            }
            int hashCode = key.GetHashCode();
            int targetBucket = (hashCode & 0x7FFFFFFF) % buckets.Length;
            Entry NewEntry = new Entry();
            NewEntry.key = key;
            NewEntry.value = value;
            NewEntry.hash = hashCode;
            if (buckets[targetBucket] == -1)
            {
                buckets[targetBucket] = count;
                entries[count] = NewEntry;
            }
            else
            {
                Entry oldEntry = entries[count];
                while (oldEntry.next != null)
                {
                    oldEntry = oldEntry.next;
                }
                oldEntry.next = NewEntry;
            }
            count += 1;

        }


        // usuwanie elementu za pomocą klucza
        public void Delete(string key)
        {
            int hashCode = key.GetHashCode();
            int targetBucket = (hashCode & 0x7FFFFFFF) % buckets.Length;
            int pos = buckets[targetBucket];
            Entry Rentry = entries[pos];
            if (Rentry.next == null)
            {
                if (Rentry.hash == hashCode)
                {
                    entries[pos] = null;
                    count -= 1;
                }
                else
                {
                    Console.WriteLine("Wartość o podanym kluczem nie istnieje ");
                }
            }
            else
            {
                Entry prev = entries[pos];
                while (Rentry.hash != hashCode)
                {
                    if (Rentry.next != null)
                    {
                        Rentry = Rentry.next;
                    }
                    else
                    {
                        Console.WriteLine("Wartość o podanym kluczem nie istniej ");
                    }
                }
                if (Rentry.hash == hashCode)
                {
                    Rentry = default(Entry);
                    count -= 1;
                }
            }
        }


        // zwracamy wartość elementa za pomocą klucza
        public string ReturnValue(string key)
        {
            int hashCode = key.GetHashCode();
            int targetBucket = (hashCode & 0x7FFFFFFF) % buckets.Length;
            int pos = buckets[targetBucket];
            if (pos != -1)
            {
                Entry Rentry = entries[pos];
                if (Rentry != null)
                {

                    while (Rentry.hash != hashCode)
                    {
                        if (Rentry.next != null)
                        {
                            Rentry = Rentry.next;
                        }
                        else
                        {
                            Console.WriteLine("Wartość o podanym kluczem nie istniej ");
                            return null;
                        }
                    }

                    return Rentry.value;
                }
                return null;
            }
            else
            {
                Console.WriteLine("Wartość o podanym kluczem nie istniej ");
                return null;
            }

        }


    }

    //element słownika, zawiera klucz, wartość, hash kod i wskaznik 
    public class Entry
    {
        public string key;
        public string value;
        public int hash;
        public Entry next;
    }


}