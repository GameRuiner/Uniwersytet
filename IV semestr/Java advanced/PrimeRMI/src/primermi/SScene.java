/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primermi;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import server.*;
/**
 *
 * @author MarkG
 */
public class SScene extends JFrame implements ActionListener{
    static JLabel label;
    JTextField tf;
    JButton button;
    static long num;
    static String result;
    static Context context;
    static IPrime iPrime;
    static JList list;
    
    public SScene() {
        setLayout(new FlowLayout());
        
        label = new JLabel("Prime: ");
    
        tf = new JTextField("Number");
        tf.setSize(300,400);
        add(tf);

        button = new JButton("Start");
        button.addActionListener(this);
        add(button);
        num = -1;
        add(label);
        list = new JList();
        add(list);
        JScrollPane scrollingList = new JScrollPane(list);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
        num = Long.parseLong(tf.getText());
        result = iPrime.isPrime(num);
        } catch (Exception ex) {
            
        }
        label.setText(result);
        ArrayList arList = new ArrayList();
        try {
            arList = iPrime.Dividers(num);
        } catch (RemoteException ex) {
            Logger.getLogger(SScene.class.getName()).log(Level.SEVERE, null, ex);
        }
        list.setListData(new ArrayList().toArray());
        list.setListData(arList.toArray());
        add(list);
    }
    
    
        public static void main(String[] args) throws Exception {
        SScene gui = new SScene();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(300,150);
        gui.setTitle("Prime");
        gui.setVisible(true);
        
        context = new InitialContext();
        iPrime = (IPrime)context.lookup("rmi://localhost/iprime");
    }
    
}
