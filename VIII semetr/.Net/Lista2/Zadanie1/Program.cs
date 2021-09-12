using System;
using System.Reflection;

namespace ListaPPW2
{
    class Program
    {
        static void Main(string[] args)
        {
            Person p1 = new Person();
            p1.GetType().GetMethod("Greeting", BindingFlags.NonPublic | BindingFlags.Instance).Invoke(p1, null);
            var age = p1.GetType().GetProperty("Age", BindingFlags.NonPublic | BindingFlags.Instance).GetValue(p1);
            Console.WriteLine(age);

            object name;
            int LiczbaProb = 1000;
            DateTime Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {

                name = p1.GetType().GetProperty("name").GetValue(p1);
            }
            DateTime End = DateTime.Now;
            TimeSpan Czas = Start - End;
            Console.WriteLine(Czas);

            Start = DateTime.Now;
            for (int proba = 0; proba < LiczbaProb; proba++)
            {
                name = p1.name;
            }
            End = DateTime.Now;
            Czas = Start - End;
            Console.WriteLine(Czas);

            // 46 raz szybczej

        }
    }
}
