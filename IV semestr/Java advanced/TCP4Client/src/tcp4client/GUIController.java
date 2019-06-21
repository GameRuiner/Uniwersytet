/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp4client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


/**
 *
 * @author MarkG
 */
public class GUIController implements Initializable {

    @FXML
    TextField txtField;

    @FXML
    Button btnGo;

    @FXML
    ListView lstView;
    
    DataOutputStream outToServer;
    //BufferedReader inFromServer;
    DataInputStream inFromServer;
    
    ObservableList<String> list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Socket clientSocket = new Socket("localhost", 20194);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            //inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //System.out.println(inFromServer.readLine());
            inFromServer = new DataInputStream(clientSocket.getInputStream()); 
        } catch (Exception ex) {
            //System.out.println(ex);
            //Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void actionGo (ActionEvent event) throws Exception {
        try {
        String data = txtField.getText();
        //int n = Integer.parseInt(data);
        outToServer.writeUTF(data);
        outToServer.flush();
        String displayBytes;
        //String recieved = inFromServer.readUTF();
        displayBytes = inFromServer.readUTF();
        //while ((displayBytes = inFromServer.readUTF()) != null)
        //{
            //System.out.println(data + " " + displayBytes);
            list.add(data + " " + displayBytes);
        //}
        lstView.setItems(list);
        } catch (Exception n) {
            
        }
    }
    
}
