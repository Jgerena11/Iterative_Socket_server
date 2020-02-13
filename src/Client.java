import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter Hostname: ");
		String hostname = scanner.next(args[0]);
		
		System.out.println("Enter Port: ");
		int port = Integer.parseInt(args[1]);
		
		try { 
			
			Socket socket = new Socket(hostname, port);
			
			System.out.println("enter number of client requests: ");
			int clients = scanner.nextInt(); 
			
			
			DataInputStream dis = new DataInputStream(socket.getInputStream()); 
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
		}catch(Exception e) {
			e.printStackTrace(); 
		}
	}

}

class ClientThread extends Thread {
	
	final DataInputStream dis; 
	final DataOutputStream dos; 
	final Socket socket;
	
	public ClientThread(Socket socket, DataInputStream dis, DataOutputStream dos) {
		this.socket = socket;
		this.dis = dis; 
		this.dos = dos; 
	}
	
	@Override
	public void run(){
		
		
	}
}
