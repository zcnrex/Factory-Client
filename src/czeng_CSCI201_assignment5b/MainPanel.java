package czeng_CSCI201_assignment5b;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class MainPanel extends JPanel implements Runnable{
	private JPanel boardPanel = new JPanel(), jspPanel = new JPanel();
	private JScrollPane jsp;
	private JLabel boardLabel = new JLabel("Task Board");
	public MainPanel(){
		this.setLayout(null);
	}
	
	public void run(){
		while(true){
//			repaint();
			revalidate();
		}
	}

}
