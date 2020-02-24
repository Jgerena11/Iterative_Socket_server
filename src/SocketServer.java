import java.io.*;
import java.net.*;
import java.util.*;
 import java.time.LocalDate;
 import java.lang.*;

public class SocketServer {
 
  public static void main(String[] args) throws IOException {
	int number, temp;
	  
	  ServerSocket s1 = new ServerSocket(1342);
	
	Socket ss = s1.accept();
	Scanner sc = new Scanner(ss.getInputStream());
	number = sc.nextInt();
	PrintStream p = new PrintStream(ss.getOutputStream());
	
	switch(number) {
	
	case 1:
		  LocalDate myObj = LocalDate.now();
			p.println(myObj);
	break;
	case 2:
		Process l = Runtime.getRuntime().exec("uptime");
		BufferedReader b = new BufferedReader(new InputStreamReader(l.getInputStream()));
		
		p.print(b.readLine());
	break; 
	case 3:
		Process bb = Runtime.getRuntime().exec("free");
		
		BufferedReader v = new BufferedReader(new InputStreamReader(bb.getInputStream()));
		
		String el = null;
		while((el = v.readLine()) != null) {
			p.print(el);
			p.println();
			
			
		}
		
		
		break;
	default:
		
	p.println("no luck");

  
	
	}
	
	
	
  
}}