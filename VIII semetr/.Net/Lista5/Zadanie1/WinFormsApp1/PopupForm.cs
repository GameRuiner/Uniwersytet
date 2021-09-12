using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WinFormsApp1
{
    public partial class PopupForm : Form
    {
        public PopupForm()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        public string Label1
        {
            set
            {
                this.label1.Text = value;
            }
        }
        public string Label2
        {
            set
            {
                this.label2.Text = value;
            }
        }
        public string Label3
        {
            set
            {
                this.label3.Text = value;
            }
        }
        public string Label4
        {
            set
            {
                this.label4.Text = value;
            }
        }
    }
}
