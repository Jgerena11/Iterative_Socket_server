import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter Hostname: ");
		String hostname = scanner.next(args[0]);
		
		System.out.println("Enter Port: ");
		int port = Integer.parseInt(args[1]);
		
		try { 
			
			System.out.println("enter number of client requests: ");
			int clients = scanner.nextInt(); 
			
			for(int i = 0; i < clients; i++) {
				Socket socket = new Socket(hostname, port);
				DataInputStream dis = new DataInputStream(socket.getInputStream()); 
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				Thread t = new ClientThread(socket, dis, dos);
				t.start();
			}
			
			
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
		while(true) {
			try {
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
