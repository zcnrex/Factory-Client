package czeng_CSCI201_assignment5b;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ClientOrderPanel extends JPanel{
	private Vector<ClientOrderInstructionPiece> coip = new Vector<ClientOrderInstructionPiece>();
	private ClientOrderParser parser = new ClientOrderParser();
	
	private JLabel itemLabel = new JLabel("Item: "),
			costLabel = new JLabel("Cost: "),
			materialLabel = new JLabel("Materials"),
			woodLabel = new JLabel("Wood:"),
			plasticLabel = new JLabel("Plastic: "),
			metalLabel = new JLabel("Metall: "),
			instructionLabel = new JLabel("Instructions");
	private JTextField itemJTF = new JTextField(""),
			costJTF = new JTextField(""),
			woodJTF = new JTextField(""),
			plasticJTF = new JTextField(""),
			metalJTF = new JTextField("");
	private JButton pButton = new JButton("+"),
			mButton = new JButton("-"),
			requestButton = new JButton("Send Request");
	private int i = 0;
	
	private JPanel jspPanel = new JPanel(), borderPanel = new JPanel();
	private JScrollPane jsp;
	
//	private ClientOuterPanel cop = new ClientOuterPanel();
	
	public ClientOrderPanel(ClientOuterPanel cop, PrintWriter pw){
		this.setLayout(null);
//		this.cop = cop;
		
		itemLabel.setBounds(20, 20, 40, 30);
		costLabel.setBounds(20, 50, 40, 30);
		itemJTF.setBounds(70, 20, 100, 30);		
		costJTF.setBounds(70, 50, 100, 30);
		add(itemJTF);
		add(itemLabel);
		add(costJTF);
		add(costLabel);
		
		materialLabel.setBounds(340, 20, 70, 30);
		woodLabel.setBounds(320, 60, 70, 30);
		woodJTF.setBounds(400, 60, 50, 30);
		plasticLabel.setBounds(320, 100, 70, 30);
		plasticJTF.setBounds(400, 100, 50, 30);
		metalLabel.setBounds(320, 140, 70, 30);
		metalJTF.setBounds(400, 140, 50, 30);
		add(materialLabel);
		add(woodLabel);
		add(woodJTF);
		add(plasticLabel);
		add(plasticJTF);
		add(metalLabel);
		add(metalJTF);
		
		instructionLabel.setBounds(100, 170, 100, 30);
		add(instructionLabel);
				
		ClientOrderInstructionPiece clientoip = new ClientOrderInstructionPiece();
		clientoip.setBounds(5, 0, 500, 50);
		coip.add(clientoip);
		
		
		
		
		
//		jspPanel.setSize(500, 200);
		jspPanel.setLayout(new BoxLayout(jspPanel, BoxLayout.Y_AXIS));
//		jspPanel.setBounds(0, 200, 500, 60);
//		jspPanel.setLayout(null);
		jspPanel.add(coip.get(i));
//		jspPanel.setBackground(Color.BLACK);
		i++;
		
		jsp = new JScrollPane(jspPanel);
//		jsp.setBounds(0, 200, 500, 300);
		jsp.setBackground(null);
		jsp.setBorder(null);
//		add(jsp);
//		jsp.setMaximumSize(new Dimension(500, 10000));
//		jsp.setM
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		JTextArea jta = new JTextArea();
//		jsp.setViewportView(jspPanel);
		
//		borderPanel.setLayout(null);
		borderPanel.setLayout(new BorderLayout());
//		borderPanel.setPreferredSize(new Dimension(500, 300));
		borderPanel.add(new JLabel(), BorderLayout.NORTH);
		borderPanel.add(jsp, BorderLayout.CENTER);
//		borderPanel.add(jsp);
		borderPanel.setBounds(0, 200, 500, 300);
		add(borderPanel);
//		add(jspPanel);
		
		
		
		
		pButton.setBounds(510, 210, 30, 30);
		pButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientOrderInstructionPiece clientoip = new ClientOrderInstructionPiece();
				clientoip.setBounds(5, i*60, 500, 50);
				coip.add(clientoip);
				jspPanel.setSize(500, 60 + i*60);
				jspPanel.add(coip.get(i));
				jspPanel.repaint();
				repaint();
				i++;
			}
		});
		
		mButton.setBounds(550, 210, 30, 30);
		mButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
//				coip = new ClientOrderInstructionPiece();
//				coip.setBounds(0, 200 + i*60, 500, 50);
				if(i>1){
					jspPanel.remove(coip.get(i-1));
					jspPanel.setSize(500, (i-1)*60);
					coip.remove(i-1);
					repaint();
					i--;
				}
			}
		});
		add(pButton);
		add(mButton);
		
		requestButton.setBounds(240, 520, 120, 50);
		requestButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean valid = true;
				if(!(itemJTF.getText().equals("") || costJTF.getText().equals(""))
						&& !(woodJTF.getText().equals("") && metalJTF.getText().equals("") && plasticJTF.getText().equals(""))){
					for (ClientOrderInstructionPiece c : coip){
						if(c.getWorkSpace().equals("") || c.getTime().equals("")) {
							valid = false;
							break;
						}
						
						if(!c.getJTF1().equals("")){
							if (c.getTool1().equals("")){
								valid = false;
								break;
							}
						}
						if(!c.getJTF2().equals("")){
							if (c.getTool2().equals("")){
								valid = false;
								break;
							}
						} 
						
					}
			
					if(valid){
						CardLayout c1 =(CardLayout)cop.getLayout();
						c1.show(cop, "wait");
						
						parser.setItem(itemJTF.getText());
						parser.setCost(costJTF.getText());
						parser.setWood(woodJTF.getText());
						parser.setMetal(metalJTF.getText());
						parser.setPlastic(plasticJTF.getText());
						parser.setCOIP(coip);
						pw.println("request");
						pw.flush();
						pw.println("");
						pw.flush();
						parser.writeFile();
					}
				}
//				for (ClientOrderInstructionPiece p : coip){
//					p.getJTF1();
//				}
			}
		});
		add(requestButton);
	}
	

}
