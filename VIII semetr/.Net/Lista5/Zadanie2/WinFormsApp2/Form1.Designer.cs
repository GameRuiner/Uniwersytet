
using System.Windows.Forms;

namespace WinFormsApp2
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.Windows.Forms.ListViewItem listViewItem1 = new System.Windows.Forms.ListViewItem(new string[] {
            "I Group",
            "Marko",
            "Anna",
            "Lena"}, 0);
            System.Windows.Forms.ListViewItem listViewItem2 = new System.Windows.Forms.ListViewItem(new string[] {
            "II Group",
            "Nikola",
            "Rose",
            "Coco"}, 0);
            System.Windows.Forms.ListViewItem listViewItem3 = new System.Windows.Forms.ListViewItem(new string[] {
            "III Group",
            "Pedro",
            "Lee",
            "Chan"}, 0);
            System.Windows.Forms.TreeNode treeNode1 = new System.Windows.Forms.TreeNode("King");
            this.listView1 = new System.Windows.Forms.ListView();
            this.treeView1 = new System.Windows.Forms.TreeView();
            this.progressBar1 = new System.Windows.Forms.ProgressBar();
            this.SuspendLayout();
            // 
            // listView1
            // 
            this.listView1.AllowColumnReorder = true;
            this.listView1.CheckBoxes = true;
            this.listView1.GridLines = true;
            this.listView1.HideSelection = false;
            listViewItem1.StateImageIndex = 0;
            listViewItem2.StateImageIndex = 0;
            listViewItem3.StateImageIndex = 0;
            this.listView1.Items.AddRange(new System.Windows.Forms.ListViewItem[] {
            listViewItem1,
            listViewItem2,
            listViewItem3});
            this.listView1.Location = new System.Drawing.Point(12, 12);
            this.listView1.Name = "listView1";
            this.listView1.Size = new System.Drawing.Size(356, 107);
            this.listView1.Sorting = System.Windows.Forms.SortOrder.Ascending;
            this.listView1.TabIndex = 0;
            this.listView1.UseCompatibleStateImageBehavior = false;
            this.listView1.View = System.Windows.Forms.View.Details;
            this.listView1.Columns.Add("Group name", -2, HorizontalAlignment.Left);
            this.listView1.Columns.Add("Team Lead", -2, HorizontalAlignment.Left);
            this.listView1.Columns.Add("Member", -2, HorizontalAlignment.Left);
            this.listView1.Columns.Add("Member", -2, HorizontalAlignment.Center);
            this.listView1.SelectedIndexChanged += new System.EventHandler(this.listView1_SelectedIndexChanged);
            // 
            // treeView1
            // 
            this.treeView1.Location = new System.Drawing.Point(13, 126);
            this.treeView1.Name = "treeView1";
            this.treeView1.Size = new System.Drawing.Size(355, 95);
            this.treeView1.TabIndex = 1;
            this.treeView1.BeginUpdate();
            this.treeView1.Nodes.Add("King");
            this.treeView1.Nodes[0].Nodes.Add("Lord 1");
            this.treeView1.Nodes[0].Nodes.Add("Lord 2");
            this.treeView1.Nodes[0].Nodes[1].Nodes.Add("Сenturion");
            this.treeView1.Nodes[0].Nodes[1].Nodes[0].Nodes.Add("Soldier");
            this.treeView1.EndUpdate();
            // 
            // progressBar1
            // 
            this.progressBar1.Location = new System.Drawing.Point(13, 228);
            this.progressBar1.Name = "progressBar1";
            this.progressBar1.Size = new System.Drawing.Size(355, 23);
            this.progressBar1.TabIndex = 2;
            this.progressBar1.Minimum = 1;
            this.progressBar1.Maximum = 1000;
            this.progressBar1.Value = 1;
            this.progressBar1.Step = 1;
            for (int x = 1; x <= 1000; x++)
            {
                this.progressBar1.PerformStep();
            }
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.progressBar1);
            this.Controls.Add(this.treeView1);
            this.Controls.Add(this.listView1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ListView listView1;
        private TreeView treeView1;
        private ProgressBar progressBar1;
    }
}

