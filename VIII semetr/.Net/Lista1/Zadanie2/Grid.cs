using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
/// <summary>
/// Lista 1
/// </summary>
namespace PPWLista1
{
    /// <summary>
    /// Grid class.
    /// Contains contains constructor and indexers.
    /// </summary>
    public class Grid
    {
        /// <value>Grid container</value>
        int[,] container;

        /// <summary>
        /// Create new grid
        /// </summary>
        /// <remarks>
        /// This could create two dimensional grid
        /// </remarks>
        public Grid(int y, int x)
        {
            container = new int[y, x];
        }

        /// <returns>
        /// I-th row.
        /// </returns>
        public int[] this[int i]
        {
            get 
            {
                int rows = container.GetLength(0);
                int cols = container.GetLength(1);
                int size = sizeof(int);
                int[] result = new int[cols];
                Buffer.BlockCopy(container, i * cols * size, result, 0, cols * size);
                return result;
            }
        }

        /// <returns>
        /// Element with indexes y and y
        /// </returns>
        public int this[int y, int x]
        {
            get { return container[y, x]; }
            set { container[y, x] = value; }
        }

        static void Main(string[] args)
        {
            Grid grid = new Grid(4, 4);
            grid[2, 2] = 5;
            grid[2, 3] = 9;
            int elem = grid[2, 3];
            Console.WriteLine($"elem[2,3] = {elem}");

            int i = 2;
            int[] rowdata = grid[i];
            Array.ForEach(rowdata, Console.WriteLine);
            Console.ReadLine();
        }
    }
}
