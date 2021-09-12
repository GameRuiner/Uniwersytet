using System;
using System.Reflection;

namespace ListaPPW2
{
    class Program
    {
        static void Main(string[] args)
        {
            Foo f = new Foo();
            GetFun(f);

        }

        static public void GetFun(object obj)
        {
            MethodInfo[] methods = obj.GetType().GetMethods(BindingFlags.Public | BindingFlags.Instance);
            foreach (MethodInfo fun in methods)
            {
                if (fun.ReturnType == typeof(int) && fun.GetParameters().Length == 0)
                {
                    if (fun.GetCustomAttribute(typeof(OznakowaneAttribute)) != null) 
                    {
                        Console.WriteLine(fun.Invoke(obj, null));
                    }                    
                }
            }
        }
    }
}
