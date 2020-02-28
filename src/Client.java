import java.net.*;
import java.io.*;
import java.util.*;

public class Client extends Thread{
	private Socket sock;
	private static String address;
	private static int port;
	private static int threadCount;
	private static String command = "manual";
	private static double elapsedTime = 0;
	private static double totalFinalTime = 0;
	
	public Client(String add, int pot, String comm) throws UnknownHostException, IOException {
		address = add;
		port = pot;
		command = comm;
	}
	public void run() {
		
		try {
			Socket m = new Socket(address, port);
			Scanner B = new Scanner(m.getInputStream());
			PrintStream g = new PrintStream(m.getOutputStream());
			g.println(command);
			double start = System.currentTimeMillis(); //start timer
	
			//if there is text in input stream, end timer and add to total time.
			if (B.hasNext()) {
				double end = System.currentTimeMillis();
				elapsedTime = end - start;
				totalFinalTime += elapsedTime;
			}
			
			if(command == "1") {
				System.out.println(B.next());
			}else {
				while(B.hasNext() && B.next() !=null) {	
					System.out.println(B.nextLine());
				}
				g.flush();
				B.close();
				m.close();
			}
			if(command != "manual") {
				System.out.println(" ***Turn Around Time: " + elapsedTime+"***\n");
			}
		}catch(IOException ee) {}
	
	}
	
	public static void main(String args[]) throws UnknownHostException, IOException  {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Address: ");
		
		address = sc.next();
		System.out.print("Enter Port number: ");
		port = sc.nextInt();
		
		Client threads[];
		threads = new Client[1002];
		try{
			System.out.println("\n Connecting to Server\n");
			threads[1000] = new Client(address, port, "manual");
			threads[1000].run();
			threads[1000].join();
			System.out.println("\n Now connected to Server");
		}catch(InterruptedException interupt){
			System.out.println(" Connection Failed");
			return;
		}
		
		while (!command.equals("quit")){
			totalFinalTime = 0;
			
			System.out.print("\n Please Enter command, or manual for options, or quit: ");
			command = sc.next();
			
			int command_check;
			if(command.equals("quit")){
				System.out.println("Ending Connection");
				System.exit(0);
			}else if(command.equals("manual")) {
				try{
					threads[1000] = new Client(address, port, "manual");
					threads[1000].run();
					threads[1000].join();
				
				}catch(InterruptedException interupt){
					System.out.println(" Connection Failed");
					return;
				}
				continue;
			}else{
				//checking to make sure commands are not strings.
				//and are between 1 and 6
				try {
					command_check = Integer.parseInt(command);
				}catch(NumberFormatException fe){
					System.out.println(" Command not found\n");
					continue;
				}
				if(command_check < 1 || command_check > 6) {
					System.out.print(" command not found \n");
					continue;
				}
			}
				
			System.out.print("\n How many Clients would you like to spawn? ");
	
			do {
				//checking for integers only and greater than 1
				try { 
					threadCount = Integer.parseInt(sc.next());
				}catch(NumberFormatException nfe){
					System.out.print(" Please enter integers only: ");
					threadCount = -1;
					continue;
				}
				if(threadCount <= 0){
					System.out.println(" please enter a number 1 or more\n ");
				}
			}
			while(threadCount<1);
			
			for(int i = 0; i<threadCount; i++) {
				threads[i] = new Client(address, port, command);
			}
			
			System.out.println("\n--------------------------------------------------------");
			System.out.println("--------------------------------------------------------\n");
			System.out.print(" Running " + threadCount + " threads for command: " + command + "\n\n");
			
			for (int i = 0; i < threadCount; i++){
				threads[i].run();
			}
		
			for (int i = 0; i < threadCount; i++){
				try{
					threads[i].join();
				}catch (InterruptedException interupt){
				}
			}
			
			System.out.println(" -------------------------\n");
			System.out.println(" Average time in milliseconds: " + totalFinalTime/threadCount + "\n");
			System.out.print(" Total turn around time in milliseconds: " + totalFinalTime+"\n");
			System.out.println("--------------------------------------------------------");
			System.out.println("--------------------------------------------------------\n");

			for(int i = 0; i < threadCount; i++) 
				threads[i] = null;
		}
		sc.close();
	}
}
