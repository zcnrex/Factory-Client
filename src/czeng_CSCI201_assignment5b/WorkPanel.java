package czeng_CSCI201_assignment5b;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class WorkPanel extends JPanel implements Runnable{
	private Vector<ImageIcon> imgIcon = new Vector<ImageIcon>();
	private Vector<Worker> workers = new Vector<Worker>();
	private Position position = new Position();
	private Factory factory;
	
	private HashMap<String, int[]> positionMap = position.getAllPositions();
	private HashMap<String, Material> material = new HashMap<String, Material>();
	private HashMap<String, Tool> tool = new HashMap<String, Tool>();
	private HashMap<String, Station> station = new HashMap<String, Station>();
	
	private Tasks tasks = new Tasks();	
	private Worker w;
	private Iterator it;
	private Map.Entry pairs;
	private int i = 0;
	
	private JButton storeButton = new JButton(), orderButton = new JButton();
	
	WorkPanel(Tasks tasks, Factory factory, Vector<Worker> workers){
		this.setLayout(null);	
		this.tasks = tasks;
		this.factory = factory;
		this.workers = workers;
		
		storeButton.setBounds(70, 30, 50, 50);
		ImageIcon icon = new ImageIcon("images/Store.png");
		storeButton.setIcon(icon);
		storeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				factory.getOuterPanel().repaint();
				CardLayout c1 = (CardLayout)factory.getOuterPanel().getLayout();
				c1.show(factory.getOuterPanel(), "storePanel");
			}
		});
		add(storeButton);
		orderButton.setBounds(540, 30, 50, 50);
		icon = new ImageIcon("images/Order.png");
		orderButton.setIcon(icon);
		orderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout)factory.getOuterPanel().getLayout();
				c1.show(factory.getOuterPanel(), "factoryOrderPanel");
			}
		});
		orderButton.setVisible(false);
		add(orderButton);
		material = tasks.getMaterials();
		tool = tasks.getTools();
		station = tasks.getStations();
	}
	
	public void paintComponent(Graphics g){
		
		it = positionMap.entrySet().iterator();
		i = 0;
		

		//set Store Button
		
		
		g.setFont(new Font("Courier", Font.PLAIN, 15));
		while(it.hasNext()){
			pairs = (Map.Entry)it.next();
			String name = (String)pairs.getKey();
			if (name.equals("Anvils") || name.equals("Work benches") || name.equals("Furnaces") ||
					name.equals("Table Saws") || name.equals("Painting Stations") || name.equals("Press")){
				g.setColor(Color.BLACK);
				g.drawString(name, ((int[])pairs.getValue())[0], ((int[])pairs.getValue())[1] + 20);
			}
			else if(name.equals("Money")){
				g.setColor(Color.BLACK);
				g.drawString(name, 15, 40);
				g.drawString("$"+tasks.getMoney(), 15, 60);
			}
			else{
				if ((name.charAt(name.length()-1)-'1') >= 0 && (name.charAt(name.length()-1)-'1') <= 4)
					name = name.substring(0, name.length()-1);
				if(name.equals("Painting")){
					imgIcon.add(new ImageIcon("images/Paintingstation.png"));
				}
				else{
					imgIcon.add(new ImageIcon("images/"+name+".png"));
				}
				
				g.drawImage(imgIcon.get(i).getImage(), ((int[])pairs.getValue())[0], ((int[])pairs.getValue())[1], null);
				i++;
				if (((int[])pairs.getValue())[1] == 30){
					g.setColor(Color.BLACK);
					g.drawString((material.get((String)pairs.getKey())).getNum(), ((int[])pairs.getValue())[0] + 12, ((int[])pairs.getValue())[1] + 25);
					g.drawString((material.get((String)pairs.getKey())).getName(), ((int[])pairs.getValue())[0], ((int[])pairs.getValue())[1] - 5);
					
				}
				else if(((int[])pairs.getValue())[0] == 0){
					g.setColor(Color.BLACK);
					g.drawString((tool.get((String)pairs.getKey())).getName(), ((int[])pairs.getValue())[0], ((int[])pairs.getValue())[1] - 5);
					g.drawString((tool.get((String)pairs.getKey())).getNum(), ((int[])pairs.getValue())[0] + 12, ((int[])pairs.getValue())[1] + 25);
				}
				else {
					g.setColor((station.get((String)pairs.getKey())).getColor());
					g.drawString((station.get((String)pairs.getKey())).getStatus(), ((int[])pairs.getValue())[0] + 3, ((int[])pairs.getValue())[1] - 5);
				}
			}
			for (int k = 0; k < workers.size(); k++){
				workers.get(k).drawWorker(g);
			}
		}
		
	}
	
	public void run(){
		while(true){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
			if (factory.getServer().getMessage().equals("request")){
				factory.startOrder();
				factory.getFactoryOrderPanel().newOrder();
				orderButton.setVisible(true);
			}
		}
	}
	
	public Vector<Worker> getWorkers(){
		return workers;
	}
	
}