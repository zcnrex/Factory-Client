package czeng_CSCI201_assignment5b;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;
import java.io.*;

	public class Server {
		
		private String line = "", filename = "";
		private ServerThread st;
		private ServerSocket ss;
		private Socket s;
		
		public Server(int port) {
				st = new ServerThread(this);
				st.start();
			
		}
//		public static void main(String [] args) {
//			/*if (args.length != 1) {
//				System.out.println("USAGE: java Server [port]");
//				return;
//			}*/
//			Server server = new Server(2232);
//		}
		public void sendMessage(String line) {
			st.send(line);
			
		}
		
		public void setLine(String s){
			line = s;
		}
		

		public String getMessage(){
			return line;
		}
		
	
	class ServerThread extends Thread {
		private Socket s;
		private Server cs;
		private BufferedReader br ;
		private PrintWriter pw;
		
		
		public ServerThread(Server cs) {
			this.s = s;
			this.cs = cs;
		}
		public void run() {
			try {
				ss = new ServerSocket(12345);
				s = ss.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				pw = new PrintWriter(s.getOutputStream());
				while (true)
				{
//					System.out.println("Line received from client ");
					line = br.readLine();
//					System.out.println("Line received from client " + s.getInetAddress() + ": " + line);
					//pw.println("Line going to client");
					cs.sendMessage(line);
					pw.println(line);
					pw.flush();
					//pw.println(line);
					//pw.flush();
				}
				
			} catch (IOException ioe) {
				System.out.println("IOException in ServerThread constructor: " + ioe.getMessage());
			}
		}
		

		public PrintWriter getPW(){
			return pw;
		}
		
		public void send(String msg)
		{
			pw.println(msg);
			pw.flush();
		}
		
	}
 }