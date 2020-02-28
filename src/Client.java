import java.net.*;
import java.time.LocalDate;
import java.io.*;
import java.util.*;
import java.lang.*;

public class Client extends Thread{
	private Socket sock;
	private BufferedReader readIn;
	private DataOutputStream sendOut;

	private static String address;
	private static int port;
	private static int threadCount;
	private static String command = "manual";

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
			double start = System.currentTimeMillis();
	

			if (B.hasNext()) {
				double end = System.currentTimeMillis();
				double elapsedTime = end - start;
				totalFinalTime += elapsedTime;
			}
			if(command == "1") {
				System.out.println("command 1 inputed");
				System.out.println(B.next());
			}else {
				while(B.hasNext() && B.next() !=null) {	
					System.out.println(B.nextLine());
				}
			
				g.flush();
				B.close();
				m.close();
		
			}
		}catch(IOException ee) {}
	
	
	}
	
	public static void main(String args[]) throws UnknownHostException, IOException  {
		String obj;
		
		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter Address: ");
//		
//		address = sc.next();
//		System.out.println("Enter Port number: ");
//		port = sc.nextInt();
		
		address = "139.62.210.153";
		port = 1236;
		
		int check = -1;
		Client testConnection[];
		testConnection = new Client[1];
		Client threads[];
		threads = new Client[1002];
		try{
			System.out.println("Connecting to Server");
			threads[1000] = new Client(address, port, "manual");
			//threads[1000].start();
			threads[1000].run();
			threads[1000].join();
			System.out.println("Now connected to Server");}
		
		catch(InterruptedException interupt){
			System.out.println("Connection Failed");
			return;
		}
		
		while (!command.equals("quit")){
			
			System.out.println("How many Jawns do you wanna spawn?");
			threadCount = sc.nextInt();
	
			do {
				try {
					if(check!=0){
						System.out.println("please enter a number 1 or greater");
						check++;
					}
				}catch(InputMismatchException mm){
					System.out.println("Please enter a valid number");
					threadCount = -1;
					sc.next();
				}
			}
		
			while(threadCount<1); 

		
			System.out.println("Please Enter command, or manual for options, or quit:");
			command = sc.next();
			if(command.equals("quit")){
				System.out.println("Ending Connection");
				System.exit(0);
			}
			
			for(int i = 0; i<threadCount; i++) {
				threads[i] = new Client(address, port, command);
			}

			System.out.print("Running " + threadCount + " threads for command: " + command);

			for (int i = 0; i < threadCount; i++){
				threads[i].run();
			}
		

			for (int i = 0; i < threadCount; i++){
				try{
					threads[i].join();
				}catch (InterruptedException interupt){
				}
			}


			System.out.println("\n ***Thread: " + threadCount + ";command: " + command +";Average time in milliseconds: " + totalFinalTime/threadCount + "***\n\n");
			System.out.print("Total turn around time in milliseconds: " + totalFinalTime+"\n");
			totalFinalTime = 0;

			for(int i = 0; i < threadCount; i++) 
				threads[i] = null;
		}
		sc.close();
	
	}
	

}
