package czeng_CSCI201_assignment5b;

import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

	class ClientThread extends Thread {
		private BufferedReader br;
		private PrintWriter pw;
		private String line = "";
		private ClientOuterPanel cop;
		public ClientThread(BufferedReader br, PrintWriter pw, ClientOuterPanel cop) {
			this.br = br;
			this.pw = pw;
			this.cop = cop;
		}
		public void run() {
			while(true){
				try {
					line = br.readLine();
//					System.out.println(line);
					if (line.equals("accept")){
						CardLayout c1 = (CardLayout)cop.getLayout();
						c1.show(cop, "accept");
					}
					else if(line.equals("denied")){
						CardLayout c1 = (CardLayout)cop.getLayout();
						c1.show(cop, "denied");
					}
					else if(line.equals("complete")){
						line = br.readLine();
						cop.setImage(line);
						CardLayout c1 = (CardLayout)cop.getLayout();
						c1.show(cop, "complete");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

