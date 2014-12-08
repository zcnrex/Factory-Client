package czeng_CSCI201_assignment5b;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ClientOuterPanel extends JPanel{

	private ClientOrderPanel cop;
	private JPanel waitPanel = new JPanel(), 
			completePanel = new JPanel(),
			acceptPanel = new JPanel(),
			deniedPanel = new JPanel();
	private JLabel waitjl = new JLabel("Waiting for response..."), 
			completejl = new JLabel("Order Complete!"),
			acceptjl = new JLabel("Request Accepted!"),
			deniedjl = new JLabel("Request Denied!");
	private JButton backButton = new JButton("Back"),
			doneButton = new JButton("Done!"),
			exitButton = new JButton("Exit");
	private ImageIcon img;
	
	public ClientOuterPanel(PrintWriter pw){
		this.setLayout(new CardLayout());
		cop = new ClientOrderPanel(this, pw);
		waitPanel.setLayout(null);
		waitjl.setBounds(20, 20, 200, 50);
		waitPanel.add(waitjl);
//		waitPanel.add(backButton);
		add(cop, "order");
		add(waitPanel, "wait");
		
		acceptPanel.setLayout(null);
		acceptjl.setBounds(20, 20, 200, 50);
		acceptPanel.add(acceptjl);
		add(acceptPanel, "accept");
		
		completePanel.setLayout(new BorderLayout());
//		completePanel.setLayout(null);
		doneButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
//		doneButton.setBounds(240, 500, 120, 50);
//		completePanel.add(doneButton);
		completePanel.add(doneButton, BorderLayout.SOUTH);
//		completejl.setBounds(20, 20, 200, 50);
		completePanel.add(completejl, BorderLayout.NORTH);
//		completePanel.add(completejl);
		add(completePanel, "complete");
		
		deniedPanel.setLayout(null);
		deniedjl.setBounds(20, 20, 200, 50);
		deniedPanel.add(deniedjl);
		backButton.setBounds(240, 500, 120, 50);
//		backButton.addActionListener(new ButtonClicked(this));
//		deniedPanel.add(backButton);
		exitButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
		exitButton.setBounds(240, 500, 120, 50);
		deniedPanel.add(exitButton);
		add(deniedPanel, "denied");
	}
	
	public void setImage(String s){
		Image image = null;
		try {
			URL url = new URL(s);
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		Image resizedImage = image.getScaledInstance(300, 300, null);
		JLabel jl = new JLabel(new ImageIcon(image));
//		jl.setBounds(200, 200, 300, 300);
		completePanel.add(jl, BorderLayout.CENTER);
		
		
	}
	
	class ButtonClicked implements ActionListener{
		private ClientOuterPanel cout;
		public ButtonClicked(ClientOuterPanel cout) {
			this.cout = cout;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout c1 =(CardLayout)cout.getLayout();
			c1.show(cout, "order");
			
		}
		
	}

}
