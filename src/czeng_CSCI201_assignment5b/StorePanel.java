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

public class StorePanel extends JPanel implements Runnable{
	private JButton backButton = new JButton();
	private Factory factory;
	private StorePosition position = new StorePosition();
	private HashMap<String, Material> material = new HashMap<String, Material>();
	private HashMap<String, Tool> tool = new HashMap<String, Tool>();
	private HashMap<String, int[]> positionMap = position.getAllPositions();
	private HashMap<String, Integer> id = new HashMap<String, Integer>();
	private Vector<ImageIcon> imgIcon = new Vector<ImageIcon>();
	
	private Tasks tasks;	
	private Worker w;
	private int numWorker;
	
	private Iterator it;
	private Map.Entry pairs;
	private int i = 0;
	
//	private JLabel nameLabel, iconLabel, buyLabel, sellLabel;
//	private JButton sellButton, buyButton;
	private Vector<JLabel> nameLabel = new Vector<JLabel>(),  
			iconLabel = new Vector<JLabel>(),  
			buyLabel = new Vector<JLabel>(),  
			sellLabel = new Vector<JLabel>();
	private Vector<JButton> sellButton = new Vector<JButton>(), buyButton = new Vector<JButton>();
 	
	public StorePanel(Factory factory){
		this.factory = factory;
		this.setLayout(null);
		this.tasks = factory.getTasks();
		this.numWorker = tasks.getNumWorker();
		
		material = tasks.getMaterials();
		tool = tasks.getTools();
		
		backButton.setBounds(70, 30, 50, 50);
		ImageIcon icon = new ImageIcon("images/Back.png");
		backButton.setIcon(icon);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				factory.getOuterPanel().revalidate();
				CardLayout c1 = (CardLayout)factory.getOuterPanel().getLayout();
				c1.show(factory.getOuterPanel(), "mainPanel");
			}
		});
		add(backButton);
		
		it = positionMap.entrySet().iterator();
		i = 0;

//		it = positionMap.entrySet().iterator();
		while(it.hasNext()){
			pairs = (Map.Entry)it.next();
			String name = (String)pairs.getKey();
			id.put(name, i);
			if(name.equals("Money")){
				iconLabel.add(new JLabel("Money"));
				iconLabel.get(i).setBounds(((int[])pairs.getValue())[0], ((int[])pairs.getValue())[1], 50, 20);
				add(iconLabel.get(i));
				nameLabel.add(new JLabel("$"+tasks.getMoney()));
				nameLabel.get(i).setBounds(((int[])pairs.getValue())[0], ((int[])pairs.getValue())[1]+20, 50, 20);
				add(nameLabel.get(i));
				buyButton.add(new JButton());
				sellButton.add(new JButton());
				
				buyLabel.add(new JLabel());
				sellLabel.add(new JLabel());
				imgIcon.add(new ImageIcon());
			}
			else{
				imgIcon.add(new ImageIcon("images/"+name+".png"));
				
				if (((int[])pairs.getValue())[0] == 440 && !name.contains("Worker")){
					iconLabel.add(new JLabel(material.get(((String)pairs.getKey())).getNum(), imgIcon.get(i), SwingConstants.CENTER));
					iconLabel.get(i).setHorizontalTextPosition(SwingConstants.CENTER);
					iconLabel.get(i).setVerticalTextPosition(SwingConstants.CENTER);
					iconLabel.get(i).setBounds(((int[])pairs.getValue())[0], ((int[])pairs.getValue())[1]-15, 100, 50);
					nameLabel.add(new JLabel(name));
					nameLabel.get(i).setBounds(((int[])pairs.getValue())[0]+30, ((int[])pairs.getValue())[1]-45, 100, 30);
					add(iconLabel.get(i));
					add(nameLabel.get(i));
					
					buyLabel.add(new JLabel("$" + material.get(((String)pairs.getKey())).getBuyValue()));
					buyLabel.get(i).setBounds(((int[])pairs.getValue())[0]+125, ((int[])pairs.getValue())[1]-15, 65, 20);
					sellLabel.add(new JLabel("$" + material.get(((String)pairs.getKey())).getSellValue()));
					sellLabel.get(i).setBounds(((int[])pairs.getValue())[0]+125, ((int[])pairs.getValue())[1]+15, 65, 20);
					add(buyLabel.get(i));
					add(sellLabel.get(i));
					
					buyButton.add(new JButton("Buy"));
					buyButton.get(i).setBounds(((int[])pairs.getValue())[0]+200, ((int[])pairs.getValue())[1]-15, 40, 20);					
					buyButton.get(i).addActionListener(new ActionListener() {						
						@Override
						public void actionPerformed(ActionEvent e) {
							material.get(name).setNum(Integer.parseInt(material.get(name).getNum())+1);
							iconLabel.get(id.get(name)).setText(""+material.get(name).getNum());
							tasks.takeMoney(material.get(name).getBuyValue());
							if(Integer.parseInt(material.get(name).getNum()) > 0){
								sellButton.get(id.get(name)).setEnabled(true);
							}
							if(tasks.getMoney() < material.get(name).getBuyValue()){
								buyButton.get(id.get(name)).setEnabled(false);
							}
							nameLabel.get(id.get("Money")).setText("$" + tasks.getMoney());
						}
					});
					sellButton.add(new JButton("Sell"));
					sellButton.get(i).setBounds(((int[])pairs.getValue())[0]+200, ((int[])pairs.getValue())[1]+15, 40, 20);
					sellButton.get(i).addActionListener(new ActionListener() {						
						@Override
						public void actionPerformed(ActionEvent e) {
							
							if(Integer.parseInt(material.get(name).getNum()) <= 0){
								sellButton.get(id.get(name)).setEnabled(false);
							}
							else{
								material.get(name).setNum(Integer.parseInt(material.get(name).getNum())-1);
								iconLabel.get(id.get(name)).setText(""+material.get(name).getNum());
								tasks.addMoney(material.get(name).getSellValue());
							}
							if(tasks.getMoney() >= material.get(name).getBuyValue()){
								buyButton.get(id.get(name)).setEnabled(true);
							}
							nameLabel.get(id.get("Money")).setText("$" + tasks.getMoney());
						}
					});
					add(buyButton.get(i));
					add(sellButton.get(i));
				}
				else if(((int[])pairs.getValue())[0] == 40){
					iconLabel.add(new JLabel(tool.get(((String)pairs.getKey())).getNumTool() + "", imgIcon.get(i), SwingConstants.CENTER));
					iconLabel.get(i).setHorizontalTextPosition(SwingConstants.CENTER);
					iconLabel.get(i).setVerticalTextPosition(SwingConstants.CENTER);
					iconLabel.get(i).setBounds(((int[])pairs.getValue())[0], ((int[])pairs.getValue())[1]-15, 100, 50);
					nameLabel.add(new JLabel(name));
					nameLabel.get(i).setBounds(((int[])pairs.getValue())[0]+30, ((int[])pairs.getValue())[1]-45, 100, 30);
					add(iconLabel.get(i));
					add(nameLabel.get(i));
					
					buyLabel.add(new JLabel("$" + tool.get(((String)pairs.getKey())).getBuyValue()));
					buyLabel.get(i).setBounds(((int[])pairs.getValue())[0]+125, ((int[])pairs.getValue())[1]-15, 65, 20);
					sellLabel.add(new JLabel("$" + tool.get(((String)pairs.getKey())).getSellValue()));
					sellLabel.get(i).setBounds(((int[])pairs.getValue())[0]+125, ((int[])pairs.getValue())[1]+15, 65, 20);
					add(buyLabel.get(i));
					add(sellLabel.get(i));
					
					buyButton.add(new JButton("Buy"));
					buyButton.get(i).setBounds(((int[])pairs.getValue())[0]+200, ((int[])pairs.getValue())[1]-15, 40, 20);					
					buyButton.get(i).addActionListener(new ActionListener() {						
						@Override
						public void actionPerformed(ActionEvent e) {
							tool.get(name).setNum(tool.get(name).getNumTool()+1);
							factory.getTasks().returnVirtualTool(name, 1);
							iconLabel.get(id.get(name)).setText(""+tool.get(name).getNumTool());
							tasks.takeMoney(tool.get(name).getBuyValue());
							if(tool.get(name).getNumTool() > 0){
								sellButton.get(id.get(name)).setEnabled(true);
							}
							else{
								
							}
							if(tasks.getMoney() < tool.get(name).getBuyValue()){
								buyButton.get(id.get(name)).setEnabled(false);
							}
							nameLabel.get(id.get("Money")).setText("$" + tasks.getMoney());
						}
					});
					sellButton.add(new JButton("Sell"));
					sellButton.get(i).setBounds(((int[])pairs.getValue())[0]+200, ((int[])pairs.getValue())[1]+15, 40, 20);
					sellButton.get(i).addActionListener(new ActionListener() {						
						@Override
						public void actionPerformed(ActionEvent e) {
							if(tool.get(name).getNumTool() <= 0){
								sellButton.get(id.get(name)).setEnabled(false);
							}
							else{
								tool.get(name).setNum(tool.get(name).getNumTool()-1);
								factory.getTasks().useVirtualTool(name, 1);
								iconLabel.get(id.get(name)).setText(""+tool.get(name).getNumTool());
								tasks.addMoney(tool.get(name).getSellValue());
							}
							if(tasks.getMoney() >= tool.get(name).getBuyValue()){
								buyButton.get(id.get(name)).setEnabled(true);
							}
							nameLabel.get(id.get("Money")).setText("$" + tasks.getMoney());
						}
					});
					add(buyButton.get(i));
					add(sellButton.get(i));
				}
				else {
					iconLabel.add(new JLabel("0", imgIcon.get(i), SwingConstants.CENTER));
					iconLabel.get(i).setHorizontalTextPosition(SwingConstants.CENTER);
					iconLabel.get(i).setVerticalTextPosition(SwingConstants.CENTER);
					iconLabel.get(i).setBounds(((int[])pairs.getValue())[0], ((int[])pairs.getValue())[1]-65, 100, 50);
					nameLabel.add(new JLabel(name));
					nameLabel.get(i).setBounds(((int[])pairs.getValue())[0]+30, ((int[])pairs.getValue())[1]-95, 100, 30);
					JLabel workerNum = new JLabel(tasks.getNumWorker()+ "");
					workerNum.setBounds(((int[])pairs.getValue())[0]+40, ((int[])pairs.getValue())[1]-15, 100, 30);
					add(iconLabel.get(i));
					add(nameLabel.get(i));
					add(workerNum);
					
					buyLabel.add(new JLabel("$15"));
					buyLabel.get(i).setBounds(((int[])pairs.getValue())[0]+125, ((int[])pairs.getValue())[1]-65, 65, 20);
					sellLabel.add(new JLabel("$15"));
					sellLabel.get(i).setBounds(((int[])pairs.getValue())[0]+125, ((int[])pairs.getValue())[1]-35, 65, 20);
					add(buyLabel.get(i));
					add(sellLabel.get(i));					

					buyButton.add(new JButton("Hire"));
					buyButton.get(i).setBounds(((int[])pairs.getValue())[0]+200, ((int[])pairs.getValue())[1]-65, 40, 20);
					buyButton.get(i).addActionListener(new ActionListener() {						
						@Override
						public void actionPerformed(ActionEvent e) {
							tasks.addNumWorker(1);
							workerNum.setText(tasks.getNumWorker()+ "");
							tasks.takeMoney(15);
							if(tasks.getNumWorker() > 0){
								sellButton.get(id.get(name)).setEnabled(true);
							}
							if(tasks.getMoney() < 15){
								buyButton.get(id.get(name)).setEnabled(false);
							}
							nameLabel.get(id.get("Money")).setText("$" + tasks.getMoney());
						}
					});
					sellButton.add(new JButton("Fire"));
					sellButton.get(i).setBounds(((int[])pairs.getValue())[0]+200, ((int[])pairs.getValue())[1]-35, 40, 20);
					sellButton.get(i).addActionListener(new ActionListener() {						
						@Override
						public void actionPerformed(ActionEvent e) {
							if(tasks.getNumWorker() <= 0){
								sellButton.get(id.get(name)).setEnabled(false);
							}
							else{
								tasks.takeNumWorker(1);;
								workerNum.setText(tasks.getNumWorker()+ "");
								tasks.addMoney(15);
								
							}
							if(tasks.getMoney() >= 15){
								buyButton.get(id.get(name)).setEnabled(true);
							}
							nameLabel.get(id.get("Money")).setText("$" + tasks.getMoney());
						}
					});
					add(buyButton.get(i));
					add(sellButton.get(i));
				}
			}
			i++;
		}
		
	}
	

	public void paintComponent(Graphics g){
		
	}
	
	public void run(){
		while(true){
			revalidate();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			
			
			
		}
	}

}
