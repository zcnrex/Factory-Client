package czeng_CSCI201_assignment5b;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Factory extends JFrame {
	private JScrollPane jsp;
	private JLabel[] jl;
	
	private File[] f;
	
	private Tasks tasks = new Tasks(); // this stores every material and tool the factory possesses
	private TaskPool taskPool = new TaskPool();
	
	private JPanel outerPanel = new JPanel();
	private JPanel jspPanel;
	private MainPanel mainPanel = new MainPanel();
	private WorkPanel workPanel;
	private JPanel boardPanel;
	private JLabel boardLabel;
	
	private StorePanel storePanel;
	private FactoryOrderPanel factoryOrderPanel;
	
	private Vector<Worker> workers = new Vector<Worker>();
	private Position position = new Position();
	
	private Server server = new Server(12345);
	
		
	public Factory(){
		super("Factory");
		setSize(800, 630);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		outerPanel.setLayout(new CardLayout());
		
		Thread t = new Thread(mainPanel);
		t.start();
		
		
		boardPanel = new JPanel();
		boardPanel.setLayout(new BorderLayout());
		jspPanel = new JPanel();
		jspPanel.setLayout(new BoxLayout(jspPanel, BoxLayout.Y_AXIS));
		jsp = new JScrollPane(jspPanel);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setMaximumSize(new Dimension(200, 10000));
		boardLabel = new JLabel("Task Board");

		boardPanel.add(boardLabel, BorderLayout.NORTH);
		boardPanel.add(jsp, BorderLayout.CENTER);
		boardPanel.setBounds(600, 0, 200, 600);

		mainPanel.add(boardPanel);

		
		setupFactory();
		
		workPanel = new WorkPanel(tasks, this, workers);
		workPanel.setBounds(0, 0, 600, 600);
		Thread twp = new Thread(workPanel);
		twp.start();
		mainPanel.add(workPanel);
		outerPanel.add(mainPanel, "mainPanel");
		
		storePanel = new StorePanel(this);
		Thread storeThread = new Thread(storePanel);
		storeThread.start();
		outerPanel.add(storePanel, "storePanel");
		
		factoryOrderPanel = new FactoryOrderPanel(this);
		outerPanel.add(factoryOrderPanel, "factoryOrderPanel");
		Thread orderThread = new Thread(factoryOrderPanel);
		orderThread.start();
		
		
		
		
//		factoryOrderPanel = new FactoryOrderPanel(this);
//		outerPanel.add(storePanel, "storePanel");
//		outerPanel.add(factoryOrderPanel, "factoryOrderPanel");

		add(outerPanel);
		
		setVisible(true);
		

	}
	
	
	
	
	public void setTaskToJSP(){
		int len = taskPool.getSize();
		System.out.println("Set Task Board");
		jl = new JLabel[len];
		for (int j = 0; j < len; j++){
			jl[j] = new JLabel();
			jl[j].setText(taskPool.getTask(j).getName() + "..." + taskPool.getTask(j).getStatus());
			jspPanel.add(jl[j]);
		}
		
	}
	
	public void setJl(int i){
		jl[i].setText(taskPool.getTask(i).getName() + "..." + taskPool.getTask(i).getStatus());
	}
	
	public void removeJl(int i){
		jl[i].setVisible(false);
		jl[i] = null;
	}
	
	public void setupFactory(){
		int len = 0;
		tasks.setTool("Plier"+"s", len, 9, 11);
		tasks.setTool("Scissor"+"s", len, 7, 9);
		tasks.setTool("Paintbrush", len, 3, 5);
		tasks.setTool("Screwdriver", len, 7, 10);
		tasks.setTool("Hammer", len, 9, 12);
		tasks.setWorker(len);
		tasks.setMoney(100);
		tasks.setMaterial("Wood", len, 1, 1);
		tasks.setMaterial("Metal", len, 2, 3);
		tasks.setMaterial("Plastic", len, 1, 2);
	}
	
	public void setupFactory(StringTokenizer st, String s){
		
		int i = 0;
		String substring = "";
		while(true){
			int len = 0;
			
			//get number of tools the factory has
			for (int j = 0; j < s.length()-2-s.indexOf(":"); j++){
				len = (int) (Math.pow(10, j) * (Character.getNumericValue(s.charAt(s.length()-j-2))));
			}
			substring = s.substring(1, s.indexOf(":") - 1);
//			System.out.println("subString: " + substring);
			if (substring.equals("Plier")){
				tasks.setTool(substring+"s", len, 9, 11);
			}
			else if(substring.equals("Scissor")){
				tasks.setTool(substring+"s", len, 7, 9);
			}
			else if (substring.contains("Paintbrush")) {
				tasks.setTool("Paintbrush", len, 3, 5);
			}
			else if (substring.contains("Screwdriver")) {
				tasks.setTool("Screwdriver", len, 7, 10);
			}
			else if (substring.contains("Hammer")) {
				tasks.setTool("Hammer", len, 9, 12);
			}
			else if (substring.contains("Worker")){
				tasks.setWorker(len);
			}
			else if (substring.contains("Mone")){
				tasks.setMoney(len);
			}
			else if(substring.contains("Woo")){
				tasks.setMaterial("Wood", len, 1, 1);
				
			}
			else if(substring.contains("Meta")){
				tasks.setMaterial("Metal", len, 2, 3);
			}
			else if(substring.contains("Plasti")){
				tasks.setMaterial("Plastic", len, 1, 2);
			}
			
			if(st.hasMoreTokens()){
				s = st.nextToken();
			}
			else break;
		}
	}
	
	public TaskPool getTaskPool(){
		return taskPool;
	}
	
	public Tasks getTasks(){
		return tasks;
	}
	
	public JPanel getOuterPanel(){
		return outerPanel;
	}
	
	public Server getServer(){
		return server;
	}

	public void startOrder(){

		Parser parser = new Parser(f, this, taskPool);
	}
	
	public void startWorker(){
		Worker w;
		for (int i = 0; i < this.getTasks().getNumWorker(); i++){
			 w = new Worker(position, this);
			 w.setWorkerNum(i);
			 workers.add(w);
			 workers.get(i).start();
			} 
	}
	
	public StorePanel getStorePanel(){
		return storePanel;
	}
	
	public FactoryOrderPanel getFactoryOrderPanel(){
		return factoryOrderPanel;
	}
	

	
	
	public static void main(String[] args){
		Factory f = new Factory();
		
	}
}
