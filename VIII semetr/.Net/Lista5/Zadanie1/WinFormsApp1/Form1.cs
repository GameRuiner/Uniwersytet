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
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void groupBox1_Enter(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void checkBox1_Click(object sender, EventArgs e)
        {
            this.checkBox2.Checked = false;
            //this.checkBox1.Checked = true;
        }

        private void checkBox2_Click(object sender, EventArgs e)
        {
            this.checkBox1.Checked = false;
           // this.checkBox2.Checked = true;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            PopupForm popup = new PopupForm();
            popup.Label1 = this.textBox1.Text;
            popup.Label2 = this.textBox2.Text;
            popup.Label3 = this.comboBox1.Text;
            popup.Label4 = this.checkBox1.Checked ? this.checkBox1.Text : this.checkBox2.Text;
            popup.ShowDialog();
            popup.Dispose();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
