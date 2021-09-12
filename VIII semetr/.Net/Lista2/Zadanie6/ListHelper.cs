using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ListaPPW2
{
    class ListHelper
    {
        public static List<TOutput> ConvertAll<T, TOutput>( List<T> list, Converter<T, TOutput> converter)
        {
            List<TOutput> res = new List<TOutput>();
            foreach (T elem in list)
            {
                res.Add(converter(elem));
            }
            return res;
        }

        public static List<T> FindAll<T>(List<T> list, Predicate<T> match)
        {
            List<T> res = new List<T>();
            foreach (T elem in list)
            {
                if (match(elem))
                {
                    res.Add(elem);
                }
            }
            return res;
        }

        public static void ForEach<T>(List<T> list, Action<T> action)
        {
            foreach (T elem in list)
            {
                action(elem);
            }
        }
        public static int RemoveAll<T>(List<T> list, Predicate<T> match)
        {
            int count = 0;
            for (int i = 0; i < list.Count; i++)
            {
                if (match(list[i])) {
                    count += 1;
                    list.RemoveAt(i);
                    i--;
                }
            }
            return count;
        }

        public static void Sort<T>(List<T> list, Comparison<T> comparison)
        {
            T temp;
            for (int j = 0; j <= list.Count - 2; j++)
            {
                for (int i = 0; i <= list.Count - 2; i++)
                {
                    if (comparison(list[i],list[i + 1]) > 0)
                    {
                        temp = list[i + 1];
                        list[i + 1] = list[i];
                        list[i] = temp;
                    }
                }
            }
        }
    }
}
