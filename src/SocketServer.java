import java.io.*;
import java.net.*;
import java.util.*;
 import java.time.LocalDate;
 import java.lang.*;

public class SocketServer {

 public static PrintStream p;
 public static String choice;

  public static void main(String[] args) throws IOException {
	int  temp;
	 String choice;
	 
	
	 
	  ServerSocket s1 = new ServerSocket(1236);
while(true) {
	try {
		Socket ss = s1.accept();
		System.out.println("New connection");
		Scanner sc = new Scanner(ss.getInputStream());
		p = new PrintStream(ss.getOutputStream());
		
		
		choice = sc.next();
			
			switch(choice) {
			case "1":
			  LocalDate myObj = LocalDate.now();
			  System.out.println(myObj);
			  p.println(myObj);
				
				p.flush();
				break;
			case "2":
				Process l = Runtime.getRuntime().exec("uptime");
				BufferedReader b = new BufferedReader(new InputStreamReader(l.getInputStream()));
				p.print(b.readLine());
				p.flush();
				break; 
			case "3":
				Process bb = Runtime.getRuntime().exec("free");
			
				BufferedReader v = new BufferedReader(new InputStreamReader(bb.getInputStream()));
			
				String el = null;
				while((el = v.readLine()) != null) {
					p.print(el);
					p.println();	
				}
				p.flush();
				break; 
			case "4":
			
				Process ee = Runtime.getRuntime().exec("netstat");
				BufferedReader g = new BufferedReader(new InputStreamReader(ee.getInputStream()));
				String pp = null;
				while((pp = g.readLine()) != null) {
					p.print(pp);
					p.println();
				}
				
			p.flush();
				break;
			
			case "5":
				Process wd = Runtime.getRuntime().exec("w");
				BufferedReader h = new BufferedReader(new InputStreamReader(wd.getInputStream()));
				String lm = null;
				while((lm = h.readLine()) != null) {
					p.println();
					p.print(lm);
					
				}
				p.flush();
				break;
		
			case "6":
				Process yy = Runtime.getRuntime().exec("ps -ef");
				BufferedReader y = new BufferedReader(new InputStreamReader(yy.getInputStream()));
				String hw = null;
				while((hw = y.readLine()) != null) {
					p.println();
					p.print(hw);
					
					}
				System.out.println("End");
				p.flush();
				
				break;
				
			case "manual":
				p.println("Please make a selection\n Press 1 for date and time\n");
				
				p.println("Press 2 for uptime ");
				p.println("Press 3 for memory use");
				p.println("Press 4 for NetStat");
				p.println("Press 5 for current users");
				p.println("press 6 for Running Processes");
				
				p.flush();
				break;
			
			default:
				p.println("no luck");

			p.flush();
			
			}
			System.out.println("End");
			ss.close();
			sc.close();
			p.close();
			
			
			
	}catch(IOException e) {
		p.flush();
		e.getStackTrace();
	}	
}
	}
  }
 