/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp4server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author MarkG
 */
public class GUIController implements Initializable {

    @FXML
    private Button startBtn;
    
    @FXML
    private Button stopBtn;
    
    @FXML
    private Label label;
    
    private startThread t;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    private void btnStart(ActionEvent event) throws Exception {
        String data;
        System.out.println("\r\nRunning Server: ");
        
        
        label.setText("Started");
        t = new startThread();
        t.start();
        //inFromClient.close();
        
    }
    
    @FXML
    private void btnStop(ActionEvent event) throws Exception {
        t.stopThread();
        label.setText("Stopped");
    }
    
}
