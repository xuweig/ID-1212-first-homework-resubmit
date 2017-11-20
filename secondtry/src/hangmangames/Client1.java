package hangmangames;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client1
{
	private static InetAddress host; 
	public static void main(String[] args) throws Exception{
		host = InetAddress.getLocalHost();//Get client's IP address
		Socket socket= new Socket(host,1234) ;
		new Threadin(socket).start();  //Input thread
		new Threadout(socket).start(); //Output thread

	}

}
class Threadin extends Thread{
	
	
    Socket socket;
    public Threadin(Socket socket){
        this.socket=socket;
    }
    
    public void run(){
    try{
        BufferedReader socketin=
                new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str=null;
        while((str=socketin.readLine())!=null){
            System.out.println(str);  //Output the message received from server.
        }
        
    }   catch (IOException e) {    
           
        }    
    
}
    
            
    
}
class Threadout extends Thread{
	Socket socket;
	public Threadout(Socket socket){
		this.socket=socket;
	}
	public void run(){
		try{
			while(true){
				BufferedReader tapin=
						new BufferedReader(new InputStreamReader(System.in ));
				BufferedWriter socketout=
						new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				String line;
				line=tapin.readLine(); //Get the message received from keyboard.
				if((line)!=null){
					if((line).equals("QUIT")){ //Check if client wants to quit the game.
						break;
					}

					socketout.write(line); 
					socketout.newLine();
					socketout.flush();

				}
			}
			socket.close();//Disconnect with server


		} catch (IOException e) {

		}
	}

}
