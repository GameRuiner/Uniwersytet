/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author MarkG
 */
public class TCP2 {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        String data;
        System.out.println("\r\nRunning Server: ");
        long start = System.currentTimeMillis();
        long end = start + 30*1000;
        ServerSocket welcomeSocket = new ServerSocket(20192);
        Socket connectionSocket = welcomeSocket.accept();
        System.out.println("\r\nNew connection from " +
            connectionSocket.getInetAddress().getHostAddress());  
        BufferedReader inFromClient = 
               new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        outToClient.writeChars("Write your message." + "\r\n");
        
        while ( !(data = inFromClient.readLine()).equals(".") && System.currentTimeMillis() < end) {
        System.out.println("\r\nMessage is " + data);
       	outToClient.writeChars(data + "\r\n");
        //outToClient.writeChars((System.currentTimeMillis()-end) / 1000 + "\r\n");
        }
       
        inFromClient.close();
        welcomeSocket.close();
       
    }
    
}
