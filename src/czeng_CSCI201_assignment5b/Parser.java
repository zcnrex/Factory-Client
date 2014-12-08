package czeng_CSCI201_assignment5b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JLabel;

public class Parser {
	private File[] files = null;

	private StringBuffer sb;
	private BufferedReader br;
	private ArrayList<String> sList;
	
	private Factory factory;
	
	private TaskPool taskPool;

	private StringTokenizer sToken;
	private String taskName, token;
	
	
	public Parser(File[] files, Factory factory, TaskPool taskPool){
		this.files = files;
		this.factory = factory;
		this.taskPool = taskPool;
		
		sb = new StringBuffer();
		sList = new ArrayList<String>();

		
		try {
			br = new BufferedReader(new FileReader("new.rcp"));
		
			String s = null;
			while ((s = br.readLine()) != null){
				sb.append(s);
				sb.append(" ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		sToken = new StringTokenizer(sb.toString());
		token = sToken.nextToken();
		setupTask(sToken, token);

		
	}
	
	public void setupTask(StringTokenizer st, String tk){
		
		taskName = tk.substring(1, tk.indexOf(":"));
		Material m;
		TaskPiece tp = new TaskPiece();
		int len = 0;
		for (int j =  tk.length()-2; j > tk.indexOf("$"); j--){
			len += (int) (Math.pow(10, tk.length()-2-j) * (Character.getNumericValue(tk.charAt(j))));
		}

		Task task = new Task(taskName);
		task.setCost(len);
		int num = 0;
		tk = st.nextToken();
		while (tk.contains(":")){
			num = 0;
			m = new Material();
			m.setName(tk.substring(1,tk.indexOf(":")));
			for (int i = 0; i < tk.length()-2-tk.indexOf(":"); i++){
				num += (int) (Math.pow(10, i) * (Character.getNumericValue(tk.charAt(tk.length()-i-2))));
			}
			m.setNum(num);
			task.addMaterial(m);
			task.addMaterial(m, tk.substring(1,tk.indexOf(":")));
//			System.out.println(tk.substring(1,tk.indexOf(":")));
			tk = st.nextToken();
		}
		TaskPiece taskpiece = new TaskPiece();
		int count = 0;
		while(st.hasMoreElements()){
			if (tk.contains("[Use")){
				taskpiece = new TaskPiece();
				task.addTaskPiece(taskpiece);
				tk = st.nextToken();
				if (tk.contains("x")){
					setTools(st, task.getLastTaskPiece(), tk);
					if(st.hasMoreElements()){
					tk = st.nextToken();
					}
					else break;
				}
				else{
					setStation(st, task.getLastTaskPiece(), tk);
					if(st.hasMoreElements()){
						tk = st.nextToken();
					}
				}
				continue;
			}
		}
		taskPool.addTask(Task.copy(task));
		
	}
	
	public void setStation(StringTokenizer st, TaskPiece taskpiece, String stk){
		String stationName = stk;
		if (stk.equals("Painting")){
			stk = st.nextToken();
			stk = st.nextToken();
			stk = st.nextToken();
		}
		else{
			stk = st.nextToken();
			stk = st.nextToken();
		}
		int num = 0;
		for (int i = 0; i < stk.length()-2; i++){
			num += (int) (Math.pow(10, i) * (Character.getNumericValue(stk.charAt(stk.length()-i-3))));
		}
		taskpiece.setStation(stationName, num);
	}
	
	public void setTools(StringTokenizer st, TaskPiece taskpiece, String stk){
		int num = 0;
		
		while(st.hasMoreElements()){
			if (stk.contains("x")){
				num = 0;
				for (int i = 0; i < stk.length()-1; i++){
					num += (int) (Math.pow(10, i) * (Character.getNumericValue(stk.charAt(stk.length()-i-2))));
				}
				stk = st.nextToken();
				if(stk.contains("Paintbrush")){ stk = "Paintbrush";}
				else if ((stk.charAt(stk.length()-1) == 's') && !stk.contains("Scissor") && !stk.contains("Plier") ){
					stk = stk.substring(0, stk.length()-1);
				}
				taskpiece.setTool(stk, num);
				stk = st.nextToken();
				if(stk.equals("and")){
					stk = st.nextToken();
					continue;
				}
				else if(stk.equals("at")){
					stk = st.nextToken();
					setStation(st, taskpiece, stk);
					break;
				}
			}
			
		}
	}
}
