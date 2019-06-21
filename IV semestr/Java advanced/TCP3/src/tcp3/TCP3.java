/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author MarkG
 */
public class TCP3 {
    static boolean stop = false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        String data = null;
        
        System.out.println("\r\nRunning Server: ");
        ServerSocket welcomeSocket = new ServerSocket(20193);
        Socket connectionSocket = null;
        try {
        while(!welcomeSocket.isClosed()) {
                    
                connectionSocket = welcomeSocket.accept();
                
                System.out.println("\r\nNew connection from " +
                connectionSocket.getInetAddress().getHostAddress());
                DataInputStream inStream = new DataInputStream(connectionSocket.getInputStream());
                BufferedReader inFromClient = 
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
              
                Thread t = new Exit(connectionSocket, inStream, outToClient, welcomeSocket);
                                //inFromClient.close();
                t.start();
            }
            
        //PrintStream out = new PrintStream(connectionSocket.getOutputStream(), true);   
        }
        catch (Exception e){
               // connectionSocket.close();
                //e.printStackTrace();
            }
}
    
        static class Exit extends Thread
        {
            final DataInputStream dis;
            final DataOutputStream dos;
            final Socket s;
            final ServerSocket ss;
            
            public Exit(Socket s, DataInputStream dis, DataOutputStream dos, ServerSocket ss) {
                this.dis = dis;
                this.s = s;
                this.dos = dos;
                this.ss = ss;
            }
            
            @Override
            public void run ()
            {
                String received;
                try {
                dos.writeChars("Write your number." + "\r\n");
                } catch (Exception e) {
                    
                }
                while (true) {
                    
                        try {
                    
                        received = dis.readLine();
                        //System.out.println(received);
                        switch (received) {
                            case "quit":
                                System.out.println("Client " + this.s + " sends exit...");
                                System.out.println("Closing this connection");
                                this.dis.close();
                                this.dos.close();
                                this.s.close();
                                this.ss.close();
                                TCP3.stop = true;
                                System.out.println("Connection closed");
                                break;
                            case ".":
                                this.dis.close();
                                this.dos.close();
                                break;
                            default:
                                //dos.writeChars(received + "\r\n");
                                int n =  Integer.parseInt(received);
                                ArrayList<Integer> result = arrPhi(n);
                                dos.writeChars(result.toString() + "\r\n");
                                break;
                        }
                                        }catch (Exception e) {
                    
                    }
                    
                }

            }
            
            int phi (int n) {
                int result = n;
                for (int i=2; i*i<=n; ++i)
                    if (n % i == 0) {
			while (n % i == 0)
				n /= i;
			result -= result / i;
		}
                if (n > 1)
                    result -= result / n;
                return result;
            }
            
            ArrayList<Integer> arrPhi (int n) {
                ArrayList<Integer> dividers = new ArrayList<>();
                //dividers.add(1);
                int cn = n;
                for (int i = 2; i <= n; i++ ) {
                    if (n % i == 0) {
                        //System.out.println(i);
                        dividers.add(i);
                        n/=i;
                        while (n % i == 0) {
                            n/=i;
                        }
                    }
                }
                
                ArrayList<Integer> result = new ArrayList<>();
                result.add(1);
                for (int i = 2; i < cn; i++) {
                    boolean prime = true;
                    for (int j = 0; j < dividers.size(); j++) {
                        if (i % dividers.get(j) == 0) {
                            prime = false;
                            break;

                    }}
                    
                    if (prime) {
                        result.add(i);
                    }
                        
                }
                return result;
            }
        }
    }
    
    

