package czeng_CSCI201_assignment5b;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FactoryOrderPiece extends JPanel{
	private Task task = new Task();
	
	private JLabel nameLabel, costLabel, 
			woodLabel = new JLabel("Wood"), woodIcon, 
			metalLabel = new JLabel("Metal"), metalIcon, 
			plasticLabel = new JLabel("Plastic"), plasticIcon, timeLabel;
	private JButton accecptButton = new JButton("Accept"),
			declineButton = new JButton("Decline");
	
	private String order = "";

	public FactoryOrderPiece(Task task) {
		setLayout(null);
		setSize(800, 100);
		
		this.task = task;
		
		nameLabel = new JLabel(task.getName() + " - $" + task.getCost());
		nameLabel.setBounds(20, 40, 100, 30);
		add(nameLabel);

		woodIcon = new JLabel("0", new ImageIcon("images/Wood.png"), SwingConstants.CENTER);
		metalIcon = new JLabel("0", new ImageIcon("images/Metal.png"), SwingConstants.CENTER);
		plasticIcon = new JLabel("0", new ImageIcon("images/Plastic.png"), SwingConstants.CENTER);
		for(Material m : task.getMeterials()){
			if(m.getName().equals("Wood")){
			woodIcon.setText(task.getMaterial("Wood").getNum());
			}
			else if(m.getName().equals("Metal")){
			metalIcon.setText(task.getMaterial("Metal").getNum());
			}
			else if(m.getName().equals("Plastic")){
			plasticIcon.setText(task.getMaterial("Plastic").getNum());
			}
			
		}
		

		woodIcon.setHorizontalTextPosition(SwingConstants.CENTER);
		woodIcon.setVerticalTextPosition(SwingConstants.CENTER);
		woodIcon.setBounds(200, 40, 50, 50);
		woodLabel.setBounds(200, 10, 70, 30);
		add(woodIcon);
		add(woodLabel);
		
		metalIcon.setHorizontalTextPosition(SwingConstants.CENTER);
		metalIcon.setVerticalTextPosition(SwingConstants.CENTER);
		metalIcon.setBounds(270, 40, 50, 50);
		metalLabel.setBounds(270, 10, 70, 30);
		add(metalIcon);
		add(metalLabel);
		
		plasticIcon.setHorizontalTextPosition(SwingConstants.CENTER);
		plasticIcon.setVerticalTextPosition(SwingConstants.CENTER);
		plasticIcon.setBounds(340, 40, 50, 50);
		plasticLabel.setBounds(340, 10, 70, 30);
		add(plasticIcon);
		add(plasticLabel);
		
		timeLabel = new JLabel(task.getTime() + "s");
		timeLabel.setBounds(420, 40, 50, 30);
		add(timeLabel);
		
		accecptButton.setBounds(470, 30, 70, 30);
		accecptButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {				
				order = "accept";
				System.out.println("Accept");
			}
		});
		declineButton.setBounds(540, 30, 100, 30);
		declineButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {				
				order = "decline";
			}
		});
		add(accecptButton);
		add(declineButton);
	}
	
	public String getOrder(){
		return order;
	}
	
	public void setOrder(String s){
		order = s;
	}
	
	public int getCost(){
		return task.getCost();
	}
}
