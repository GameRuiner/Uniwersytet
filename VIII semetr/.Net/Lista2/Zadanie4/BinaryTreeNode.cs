using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ListaPPW2
{
    class BinaryTreeNode<T> 
    {
        public BinaryTreeNode<T> Left { get; set; }
        public BinaryTreeNode<T> Right { get; set; }

        public T Value { get; set; }

        private Queue<BinaryTreeNode<T>> queue;

        public IEnumerable<T> DFS()
        {
            yield return this.Value;
            if ( this.Left != null )
            {
                foreach (T v in this.Left.DFS())
                {
                    yield return v;
                }
            }
            if (this.Right != null)
            {
                foreach (T v in this.Right.DFS())
                {
                    yield return v;
                }
            }
        }

        public IEnumerable<T> BFS()
        {
            queue = new Queue<BinaryTreeNode<T>>();
            queue.Enqueue(this);
            while (queue.Count > 0)
            {
                BinaryTreeNode < T > curr = queue.Dequeue();
                yield return curr.Value;
                if (curr.Right != null)
                {
                    queue.Enqueue(curr.Right);
                }
                if (curr.Left != null)
                {
                    queue.Enqueue(curr.Left);
                }
            }
        }

        public IEnumerator<T> GetBFS()
        {
            return new BFS2(this);
        }


        public IEnumerator<T> GetDFS()
        {
            return new DFS2(this);
        }

        public class BFS2 : IEnumerator<T>
        {
            private BinaryTreeNode<T> root;
            private BinaryTreeNode<T> curr;
            private Queue<BinaryTreeNode<T>> queue = new Queue<BinaryTreeNode<T>>();

            public BFS2(BinaryTreeNode<T> tree)
            {
                this.root = tree;
                this.Reset();
            }

            public T Current => curr.Value;

            object IEnumerator.Current => curr;

            public void Dispose()
            {
                
            }

            public bool MoveNext()
            {
                if (queue.Count > 0)
                {
                    curr = queue.Dequeue();
                    if (curr.Right != null) queue.Enqueue(curr.Right);
                    if (curr.Left != null) queue.Enqueue(curr.Left);
                    return true;
                } else
                {
                    return false;
                }
            }

            public void Reset()
            {
                queue.Clear();
                queue.Enqueue(this.root);
                this.curr = this.root;
            }
        }

        public class DFS2 : IEnumerator<T>
        {
            private BinaryTreeNode<T> root;
            private BinaryTreeNode<T> curr;
            private Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();

            public DFS2(BinaryTreeNode<T> tree)
            {
                this.root = tree;
                this.Reset();
            }

            public T Current => curr.Value;

            object IEnumerator.Current => curr;

            public void Dispose()
            {

            }

            public bool MoveNext()
            {
                if (stack.Count > 0)
                {
                    curr = stack.Pop();
                    if (curr.Right != null) stack.Push(curr.Right);
                    if (curr.Left != null) stack.Push(curr.Left);
                    return true;
                }
                else
                {
                    return false;
                }
            }

            public void Reset()
            {
                stack.Clear();
                stack.Push(this.root);
                this.curr = this.root;
            }
        }
    }
}
