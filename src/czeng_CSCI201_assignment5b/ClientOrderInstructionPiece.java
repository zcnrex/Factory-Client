package czeng_CSCI201_assignment5b;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientOrderInstructionPiece extends JPanel{
	private JLabel useLabel = new JLabel("Use"), atLabel = new JLabel("At"), 
			forLabel = new JLabel("For"), sLabel = new JLabel("s");
	private JTextField jtf1 = new JTextField(""), 
			jtf2 = new JTextField(""), 
			timejtf = new JTextField("");
	private Object toolOptions[] = {"","Screwdriver", "Hammer", "Paintbrush", "Pliers", "Scissors"},
			workSpaceOptions[] = {"","Anvil", "Workbench", "Furnace", "Saw", "Painting Station", "Press"};
	private JComboBox toolBox1 = new JComboBox(toolOptions), 
			toolBox2 = new JComboBox(toolOptions), 
			workSpaceBox = new JComboBox(workSpaceOptions);

	public ClientOrderInstructionPiece(){
		this.setSize(500, 60);
		this.setLayout(null);
		
		useLabel.setBounds(0, 20, 30, 20);		
		add(useLabel);
		
		jtf1.setBounds(30, 5, 30, 20);
		jtf2.setBounds(30, 30, 30, 20);
		add(jtf1);
		add(jtf2);
		
		toolBox1.setBounds(70, 10, 140, 20);
		toolBox2.setBounds(70, 30, 140, 20);
		add(toolBox1);
		add(toolBox2);
		
		atLabel.setBounds(215, 20, 30, 20);
		add(atLabel);
		
		workSpaceBox.setBounds(240, 20, 150, 20);
		add(workSpaceBox);
		
		forLabel.setBounds(400, 20, 30, 20);
		add(forLabel);
		
		timejtf.setBounds(430, 20, 30, 20);
		add(timejtf);
		
		sLabel.setBounds(470, 20, 20, 20);
		add(sLabel);
	}
	
	public String getJTF1(){
		return jtf1.getText();
	}
	
	public String getJTF2(){
		return jtf2.getText();
	}
	
	public String getTime(){
		return timejtf.getText();
	}
	
	public String getTool1(){
		return toolBox1.getSelectedItem().toString();
	}
	
	public String getTool2(){
		return toolBox2.getSelectedItem().toString();
	}
	
	public String getWorkSpace(){
		return workSpaceBox.getSelectedItem().toString();
	}
}
