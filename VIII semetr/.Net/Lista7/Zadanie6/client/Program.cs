using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace ConsoleApp2
{
    class Program
    {
        static void Main(string[] args)
        {
            Int32 port = 13000;
            String server = "127.0.0.1";
            TcpClient client = new TcpClient(server, port);

            PurchaseOrder po = new PurchaseOrder() { MyAddress = "Wroclaw" };
            XmlSerializer ser = new XmlSerializer(typeof(PurchaseOrder));
            Byte[] data;
            using (var ms = new MemoryStream())
            {
                ser.Serialize(ms, po);
                data = ms.ToArray();
            }

            
            NetworkStream stream = client.GetStream();
            stream.Write(data, 0, data.Length);
            Console.WriteLine("Data sent");
            Console.ReadLine();
            stream.Close();
            client.Close();
        }
    }
    public class PurchaseOrder
    {
        public string MyAddress;
    }
}
