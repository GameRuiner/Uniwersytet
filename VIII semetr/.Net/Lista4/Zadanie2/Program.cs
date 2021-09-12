using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Lista4
{
    class Program
    {
        static void Main(string[] args)
        {
            Type wordType = Type.GetTypeFromProgID("Word.Application");
            dynamic w = Activator.CreateInstance(wordType);
            w.Visible = true;
            dynamic doc = w.Documents.Add();
            dynamic para = doc.Content.Paragraphs.Add();
            para.Range.Text = "Programowanie pod Windows";
            doc.SaveAs("ppw.doc");
            Console.ReadLine();
        }
    }
}
