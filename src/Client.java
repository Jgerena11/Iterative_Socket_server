import java.net.*;
import java.util.*;
import java.io.*;
import java.util.logging.*;
import java.lang.*;

public class Client extends Thread{
	private static Socket sock;
	private BufferedReader readIn;
	private DataOutputStream sendOut;
	private static String address;
	private static int port;
	private static String command = "manual";
	private static long totalFinalTime = 0;
	
	public Client(String address, int port, String s) {
		Client.address = address;
		Client.port = port;
		Client.command = s; 
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner sc1 = new Scanner(sock.getInputStream());
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter Hostname: ");
		address = scanner.next(args[0]);
		
		System.out.println("Enter Port: ");
		port = Integer.parseInt(args[1]);
		
		int threadCount;
		int check = -1; 
		
		Client thread; 

		try { 
			System.out.println("Connecting to Server");
			thread = new Client(address, port, "manual");
			thread.start();
			thread.join();
			System.out.println("Now connected to Server"); 	
		}catch(Exception e) {
			System.out.print("connection failed");
			e.printStackTrace(); 
			return;
		}
		
		while(!command.equals("quit")) {
			
			System.out.println("Please Enter command, or manual for options or quit");
			command = scanner.next();
			if (command.contentEquals("quit")){
				System.out.println("Ending Connection");
				System.exit(0);
			}
		}
	}

}

