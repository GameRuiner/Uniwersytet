using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace WpfApp1
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            comboBox.SelectedIndex = 0;
            checkBox1.IsChecked = true;
        }

        private void CheckBox_Checked(object sender, RoutedEventArgs e)
        {
            checkBox2.IsChecked = false;
        }

        private void CheckBox_Checked_1(object sender, RoutedEventArgs e)
        {
            checkBox1.IsChecked = false;
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Popup popup = new Popup();
            popup.label1.Content = textBox1.Text;
            popup.label2.Content = textBox2.Text;
            popup.label3.Content = comboBox.Text;
            popup.label4.Content = (checkBox1.IsChecked ?? false) ? checkBox1.Content : checkBox2.Content;
            popup.ShowDialog();
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
    }
}
