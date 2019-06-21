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
import java.util.Random;

/**
 *
 * @author MarkG
 */
public class startThread extends Thread{
    //final DataInputStream dis;
    //final DataOutputStream dos;
    
    ServerSocket welcomeSocket;
    Socket connectionSocket;
    DataInputStream inStream;
    //BufferedReader inFromClient;
    DataInputStream inFromClient;
    DataOutputStream outToClient;
    public startThread() {
        try {
            
        } catch (Exception ex) {
            //System.out.println(ex);
        }    
        }
    
    @Override
    public void run ()
    {
        while (true) {
        try {
        if (welcomeSocket == null) {
        welcomeSocket = new ServerSocket(20194);
        }
        connectionSocket = welcomeSocket.accept();
        System.out.println("\r\nNew connection from " +
            connectionSocket.getInetAddress().getHostAddress());  
        inStream = new DataInputStream(connectionSocket.getInputStream());
        //inFromClient = 
        //       new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        inFromClient =
                new DataInputStream(connectionSocket.getInputStream());
        outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        //outToClient.writeChars("Write your message." + "\r\n");
        String data;
        Random rand = new Random();
        int n = rand.nextInt(9000);
        n+=1000;
        //int n = 3522;
        int attempts = 13;
        int userN;
        boolean equal = false;
        
        //while (attempts > 0 && !(data = inFromClient.readLine()).equals(".")) {
        while (attempts > 0) {
        data = inFromClient.readUTF();
        System.out.println(data);
        userN = -1;
        try {
            userN = Integer.parseInt(data);
        }
        catch (Exception e){
            attempts++;
        }
        //System.out.println(userN);
        if (userN != -1) {
            if (userN > n)
                outToClient.writeUTF(">");
            else if(userN < n)
                outToClient.writeUTF("<");
            else {
                outToClient.writeUTF("=");
                equal = true;
                break;
            }
            //outToClient.close();
        }
       	//outToClient.writeChars(data + "\r\n");
        attempts--;
        //outToClient.writeChars((System.currentTimeMillis()-end) / 1000 + "\r\n");
        }
        if (attempts == 0 && equal == false ) {
            outToClient.writeUTF("May be next time");
        }
        outToClient.close();
        }
        
        catch (Exception e) {
            //System.out.println(e.toString());
        }
        }
    }
    
    public void stopThread () {
        try {
        welcomeSocket.close();
        } catch(Exception e) {
            
        }
    }
    
}
