package com.bb2004.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket ss;  
    private Socket socket;  
    private BufferedReader in;  
    private PrintWriter out;  
  
    public Server()   
    {  
        try   
        {  
            ss = new ServerSocket(10000);  
              
            System.out.println("The server is waiting your input...");  
              
            while(true)   
            {  
                socket = ss.accept();  
                  
//                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
//                out = new PrintWriter(socket.getOutputStream(), true);  
                String line = "3333";  
                  
//                System.out.println("you input is : " + line);  
                  
                //out.println("you input is :" + line);  
                
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
                out = new PrintWriter(socket.getOutputStream(), true);  
//                BufferedReader line = new BufferedReader(new InputStreamReader(System.in));  
      
                out.println(line); 
                  
                out.close();  
                in.close();  
                socket.close();  
                  
                if(line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit"))  
                    break;  
            }  
              
            ss.close();  
              
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public static void main(String[] args)   
    {  
        new Server();  
    }  
}
