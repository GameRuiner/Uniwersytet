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
            // root 1
            // |   |
            // 2   3
            // /   /
            //5   4
            BinaryTreeNode<int> root = new BinaryTreeNode<int>()
            {
                Left = new BinaryTreeNode<int>()
                {
                    Left = new BinaryTreeNode<int>()
                    {
                        Value = 5
                    },
                    Value = 2
                },
                Right = new BinaryTreeNode<int>()
                {
                    Left = new BinaryTreeNode<int>()
                    {
                        Value = 4
                    },
                    Value = 3
                },
                Value = 1
            };

            foreach (int v in root.BFS())
             {
                Console.WriteLine(v);
            }
           // var enumer = root.GetBFS();
            //while (enumer.MoveNext())
           // {
            //    Console.WriteLine(enumer.Current);
            //}
        }
    }
}
