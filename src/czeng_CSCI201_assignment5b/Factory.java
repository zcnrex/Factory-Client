package czeng_CSCI201_assignment5b;

import java.awt.BorderLayout;
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
	private JMenuBar jmb;
	private JMenuItem openFolder;
	private JScrollPane jsp;
	private JLabel[] jl;
	
	private File[] f;
	private StringBuffer sb;
	private BufferedReader br;
	private ArrayList<String> sList;
	
	private Tasks tasks = new Tasks(); // this stores every material and tool the factory possesses
	private TaskPool taskPool = new TaskPool();
	
	private JPanel jspPanel;
	private MainPanel mainPanel = new MainPanel();
	private WorkPanel workPanel;
	private JPanel boardPanel;
	private JLabel boardLabel;
	private ArrayList<JLabel> taskList;
	
	private StringTokenizer sToken;
	private String taskName;
	
	private Vector<Worker> workers = new Vector<Worker>();
	private Position position = new Position();
	
		
	public Factory(){
		super("Factory");
		setSize(800, 630);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Thread t = new Thread(mainPanel);
		t.start();
		
		jmb = new JMenuBar();
		openFolder = new JMenuItem("Open Folder...");
		jmb.add(openFolder);
		setJMenuBar(jmb);
		
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

		add(mainPanel);		

		
		
		f = null;
		openFolder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Choose a file");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int choice = fc.showOpenDialog(openFolder);
				if(choice == JFileChooser.APPROVE_OPTION){
					f = fc.getSelectedFile().listFiles();
					getFile();
			
				}
			}
		});
		setVisible(true);
		while(true){
			if(taskPool.getTasks().size()>0)
			if(taskPool.getRecentTask().getStatus().equals("Complete!")){
				int selection = JOptionPane.showConfirmDialog(this, "Want to play again?", "Complete!", JOptionPane.YES_NO_OPTION);
				switch (selection) {
					case JOptionPane.YES_OPTION:
						for (int k = 0; k < workers.size(); k++){
							workers.get(k).interrupt();
							
						}
						taskPool = null;
						taskPool = new TaskPool();
						tasks = null;
						tasks = new Tasks();
						workers = null;
						workers = new Vector<Worker>();
						mainPanel.remove(workPanel);
						jl = null;
						jspPanel = null;
						jspPanel = new JPanel();
						jspPanel.setLayout(new BoxLayout(jspPanel, BoxLayout.Y_AXIS));
						jsp = null;
						jsp = new JScrollPane(jspPanel);
						jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
						jsp.setMaximumSize(new Dimension(200, 10000));
						boardPanel.remove(jsp);
						boardPanel.add(jsp, BorderLayout.CENTER);
						JFileChooser fc = new JFileChooser();
						fc.setDialogTitle("Choose a file");
						fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						int choice = fc.showOpenDialog(openFolder);
						if(choice == JFileChooser.APPROVE_OPTION){
							f = fc.getSelectedFile().listFiles();
		
							getFile();			
						}
						break;
					case JOptionPane.NO_OPTION: 
						System.exit(NORMAL);
						break;
				}
			}
		}
	}
	
	
	//Read and parse files 
	public void getFile(){
		
		Parser parser = new Parser(f, this, taskPool);
		Worker w;
		for (int i = 0; i < this.getTasks().getNumWorker(); i++){
			 w = new Worker(position, this);
			 w.setWorkerNum(i);
			 workers.add(w);
			} 
		
		workPanel = new WorkPanel(tasks, this, workers);
		workPanel.setBounds(0, 0, 600, 600);
		Thread twp = new Thread(workPanel);
		twp.start();

		mainPanel.add(workPanel);
		
			for (int k = 0; k < workers.size(); k++){
				workers.get(k).start();
			}

	}
	
	
	public void setTaskToJSP(){
		int len = taskPool.getSize();
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
	
	public void setupFactory(StringTokenizer st, String s){
		
		int i = 0;
		String substring = "";
		while(true){
			int len = 0;
			
			//get number of tools the factory has
			for (int j = 0; j < s.length()-2-s.indexOf(":"); j++){
				len = (int) (Math.pow(10, j) * (Character.getNumericValue(s.charAt(s.length()-j-2))));
			}
			substring = s.substring(1, s.indexOf(":")-1);
			if (substring.equals("Plier") || substring.equals("Scissor")){
				tasks.setTool(substring+"s", len);
			}
			else if (substring.contains("Paintbrush")) {
				tasks.setTool("Paintbrush", len);
			}
			else if (substring.contains("Worker")){
				tasks.setWorker(len);
			}
			else tasks.setTool(substring, len);
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

	
	public static void main(String[] args){
		Factory f = new Factory();
		
	}
}
