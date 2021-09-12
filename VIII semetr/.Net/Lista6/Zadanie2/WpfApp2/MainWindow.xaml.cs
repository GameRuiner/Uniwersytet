using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading;
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

namespace WpfApp2
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();

           

            List<User> items = new List<User>();
            items.Add(new User() { Name = "Marko Golvko", Age = 22, City = "Wrocław" });
            items.Add(new User() { Name = "Andriy Gordey", Age = 19, City = "Warszawa" });
            items.Add(new User() { Name = "Dima Pekun", Age = 7, City = "Żywiec" });
            users.ItemsSource = items;

            MenuItem root = new MenuItem() { Title = "Root" };
            MenuItem childItem1 = new MenuItem() { Title = "Child 1" };
            childItem1.Items.Add(new MenuItem() { Title = "Grandson 1" });
            childItem1.Items.Add(new MenuItem() { Title = "Grandson 2" });
            root.Items.Add(childItem1);
            root.Items.Add(new MenuItem() { Title = "Child 2" });
            trMenu.Items.Add(root);
        }
        private void Window_ContentRendered(object sender, EventArgs e)
        {
            for (int i = 0; i < 100; i++)
            {
                pb.Value++;
                Thread.Sleep(100);
            }
        }
    }
    public class User
    {
        public string Name { get; set; }

        public int Age { get; set; }

        public string City { get; set; }
    }

    public class MenuItem
    {
        public MenuItem()
        {
            this.Items = new ObservableCollection<MenuItem>();
        }

        public string Title { get; set; }

        public ObservableCollection<MenuItem> Items { get; set; }
    }
}
