import java.net.*;
import java.util.*;
import java.io.*;
import java.util.logging.*;
import java.lang.*;

public class Client extends Thread {
	private static Socket sock;
//	private BufferedReader readIn;
	private PrintStream p;
	private static String address;
	private static int port;
	private static int command;
	private static long totalFinalTime = 0;
	private Scanner sc1;
	
	public Client(String address, int port, int o) throws UnknownHostException, IOException {
		sock = new Socket(address, port);
		sc1 = new Scanner(sock.getInputStream());
		p = new PrintStream(sock.getOutputStream());
		Client.address = address;
		Client.port = port;
		Client.command = o; 
	}
	
//	public Client(String address, int port) throws UnknownHostException, IOException {
//		sock = new Socket(address, port);
//		sc1 = new Scanner(sock.getInputStream());
//		p = new PrintStream(sock.getOutputStream());
//		Client.address = address; 
//		Client.port = port;
//	}
//	
	public void run() {
		this.p.print(command);
		
		while (sc1.hasNext()){
			System.out.println(sc1.nextLine());
		}
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter Hostname: ");
		address = input.next();
		
		System.out.println("Enter Port: ");
		port = Integer.parseInt(input.next());
		
		int threadCount;
		int check = -1; 
		
		Client threads[];
		threads = new Client[1002];
		

		try { 
			System.out.println("Connecting to Server");
			threads[1000] = new Client(address, port, 1);
			threads[1000].start();
			threads[1000].join();
			System.out.println("Now connected to Server"); 	
		}catch(UnknownHostException ex) {
			System.out.println("Server not found: " + ex.getMessage());
		}catch(Exception e) {
			System.out.print("connection failed");
			e.printStackTrace(); 
			return;
		}
		
		while(command != 7) {
			System.out.println("Please Enter the number of client threads to make 1-1000:");
			
			do {
				try {
					if(check != 0) {
						System.out.println("Please Enter a number that is 1 or greater");
					}
					threadCount = input.nextInt();
				}catch(InputMismatchException mm){
					System.out.println("Please enter a valid number");
					threadCount = -1; 
					input.next();
				}
			}while(threadCount<1);
			
			System.out.println("Please Enter command, or manual for options or quit");
			
			command = input.nextInt();
			if (command == 7){
				System.out.println("Ending Connection");
				System.exit(0);
			}
			
			for (int i = 0; i< threadCount; i++) {
				threads[i] = new Client(address, port, command);
			}
			System.out.println("Running " + threadCount + " threads for command: " + command);
			
			for(int i = 0; i< threadCount; i++) {
				try {
					threads[i].start();
					threads[i].join();
				}catch(InterruptedException interupt){
					Thread.currentThread().interrupt();
				}
			}
		}
	}

}

