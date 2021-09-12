using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WinFormsApp3
{
    public partial class Form1 : Form
    {
        Graphics g1;
        public Form1()
        {
            InitializeComponent();
            g1 = CreateGraphics();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
        }

        private void button1_Click(object sender, EventArgs e)
        {
            g1.Clear(Color.White);
            Pen ph = new(Color.Blue, 5);
            Pen pm = new(Color.Blue, 3);
            Pen ps = new(Color.Black, 2);
            double hours = DateTime.Now.Hour;
            double minutes = DateTime.Now.Minute;
            double seconds = DateTime.Now.Second;

            double degrees = 90-(hours%12)*30;
            double radians = Math.PI * degrees / 180;
            double xHours = Math.Cos(radians) * 120;
            double yHours = Math.Sin(radians) * 120;
            g1.DrawEllipse(ph, 100, 100, 300, 300);
            g1.DrawLine(ph, 250, 250, 250 + (int)xHours, 250 - (int)yHours);

            degrees = 90 - (minutes / 5) * 30;
            radians = Math.PI * degrees / 180;
            double xMinutes = Math.Cos(radians) * 140;
            double yMinutes = Math.Sin(radians) * 140;
            g1.DrawLine(pm, 250, 250, 250 + (int)xMinutes, 250 - (int)yMinutes);

            degrees = 90 - (seconds / 5) * 30;
            radians = Math.PI * degrees / 180;
            double xSeconds = Math.Cos(radians) * 130;
            double ySeconds = Math.Sin(radians) * 130;
            g1.DrawLine(ps, 250, 250, 250 + (int)xSeconds, 250 - (int)ySeconds);
        }
    }
}
