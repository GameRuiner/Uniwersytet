using System;
using System.Reflection;
using System.Collections;
using System.Collections.Generic;

namespace ListaPPW2
{
    class Program
    {
        static void Main(string[] args)
        {
            //Testowanie wydajności nie dało sensownych wyników,
            //bo szybkość działania wyszła na tym samym poziome
            ArrayList list1 = new ArrayList();
            List<int> list2 = new List<int>();

            int testInt;

            int LiczbaProb = 1000;
            DateTime Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                list1.Add(421);
            }
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                testInt = (int)list1[proba];
            }
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                list1.RemoveAt(0);
            }
            DateTime End = DateTime.Now;
            TimeSpan Czas = Start - End;
            Console.WriteLine(Czas);

            Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                list2.Add(421);
            }
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                testInt = list2[proba];
            }
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                list2.RemoveAt(0);
            }
            End = DateTime.Now;
            Czas = Start - End;
            Console.WriteLine(Czas);

            Hashtable dict1 = new Hashtable();
            Dictionary<int, int> dict2 = new Dictionary<int, int>();

            Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                dict1.Add(proba, proba);
            }
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                testInt = (int)dict1[proba];
            }
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                dict1.Remove(proba);
            }
            End = DateTime.Now;
            Czas = Start - End;
            Console.WriteLine(Czas);

            Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                dict2.Add(proba, proba);
            }
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                testInt = dict2[proba];
            }
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                dict2.Remove(proba);
            }
            End = DateTime.Now;
            Czas = Start - End;
            Console.WriteLine(Czas);

        }
    }
}
