package czeng_CSCI201_assignment5b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;

public class Client extends JFrame{
	private Socket s;
	private BufferedReader br;
	private PrintWriter pw;
	
	private ClientOuterPanel cop;

	public Client(){
		super("Order Form");
		setSize(600, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try{
			s = new Socket ("localhost", 12345 );
			pw = new PrintWriter( s.getOutputStream() );
			br= new BufferedReader( new InputStreamReader(s.getInputStream() ) );
			 cop = new ClientOuterPanel(pw);
	//		cp = new FightPanel(pw);
			ClientThread ct = new ClientThread(br, pw, cop);
			ct.start();
		} catch (IOException ioe) {
			System.out.println( "IOExceptionin Client constructor: " + ioe.getMessage() );
		}
//		
//		cop.setBounds(0, 400, 600, 200);
		add(cop);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Client c = new Client();

	}

}
