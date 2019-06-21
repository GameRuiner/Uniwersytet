/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tcp1;
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author MarkG
 */
public class TCP1 {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception{
        System.out.println("\r\nRunning Server: ");
        while (true) {
        ServerSocket welcomeSocket = new ServerSocket(20191);
        Socket connectionSocket = welcomeSocket.accept();
        System.out.println("\r\nNew connection from " +
        connectionSocket.getInetAddress().getHostAddress());
          
        BufferedReader inFromClient = 
               new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
       	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
	Date date = new Date();
	System.out.println(dateFormat.format(date)); 
        outToClient.writeChars(dateFormat.format(date) + "\r\n");   
        //data = inFromClient.readLine();
        inFromClient.close();
        welcomeSocket.close();
        }
    }
    
}
