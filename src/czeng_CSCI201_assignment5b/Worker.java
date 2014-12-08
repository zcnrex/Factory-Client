package czeng_CSCI201_assignment5b;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Worker extends Thread{
	private int[] pos;
	private Task task;
	private ImageIcon img;
	private Vector<int[]> posList = new Vector<int[]>();
	private Position position;
	private Factory factory;
	private int workerNum;
	
	
	Worker(){
		pos = new int[2];
		pos[0] = 0;
		pos[1] = 30;
		img = new ImageIcon("images/worker.png");
		
	}
	
	Worker(Position position, Factory factory){
		pos = new int[2];
		pos[0] = 0;
		pos[1] = 30;
		img = new ImageIcon("images/worker.png");
		this.position = position;
		this.task = new Task();
		this.factory = factory;
	}
	
	public int[] getPosition(){
		return pos;
	}
	
	public void setTask(Task t){
		this.task = t;
	}
	
	public void drawWorker(Graphics g){
		g.drawImage(img.getImage(), pos[0], pos[1], null);
	}
	
	public void setPosition(int x, int y){
		int[] p = new int[2];
		p[0] = x;
		p[2] = y;
		posList.add(p);
 	}
	
	public void setWorkerNum(int n){
		this.workerNum = n;
	}
	
	public void run(){
		try {
			sleep(workerNum*500+1000);
			while(pos[1] < 80){
				sleep(1);				
				pos[1]++;
			}
			while(pos[0] < 550){
				sleep(1);				
				pos[0]++;
			}
			sleep(200);
			int[] p = new int[2];
			for (int i = 0; i < factory.getTaskPool().getTasks().size(); i++){
				this.setTask(factory.getTaskPool().getTask(i));
				if (!task.getStatus().equals("Not Built")) continue;
				factory.getTaskPool().getTask(i).setStatus("In Progress");
				factory.setJl(i);
				for (Material m : task.getMeterials()){
					p = position.getPosition(m.getName());
					move(p, 50, 0);
					factory.getTasks().getMaterials().get(m.getName()).use(Integer.parseInt(m.getNum()));
					sleep(200);
				}
	
				for (TaskPiece tp : task.getTaskPieces()){
					if(!tp.getTools().isEmpty()){
						p = position.getPosition(tp.getTools().firstElement().getName());
						move(p, 50, 0);
						boolean b = true;
						while(true){
							b = false;
							for(Tool t : tp.getTools()){
								while(factory.getTasks().getToolNumber().get(t.getName()) < t.getNumber()){
									b = true;
								}
							}
							if(!b)
								break;
						}
						for(Tool t : tp.getTools()){
							factory.getTasks().useVirtualTool(t.getName(), t.getNumber());
						}
						for(Tool t : tp.getTools()){
							p = position.getPosition(t.getName());
							move(p, 50, 0);
							while(factory.getTasks().getTools().get(t.getName()).getNumber() < t.getNumber()){
								sleep(200);
							}
							factory.getTasks().getTools().get(t.getName()).takeTool(t.getNumber());
							sleep(200);
							
						}
					}
					Station s = tp.getStation();
					p = position.getPosition(s.getName()+"1");
					move(p, 0, -80);
					int a = 1;
					while(true){
						if (factory.getTasks().getStations().get(s.getName()+a).getVirtualStatus().equals("Open")){
							factory.getTasks().getStations().get(s.getName()+a).setVirtualStatus("Closed");
							p = position.getPosition(s.getName()+a);
							factory.getTasks().getStations().get(s.getName()+a).inUse(p, pos, s.getTime());

							factory.getTasks().getStations().get(s.getName()+a).setVirtualStatus("Open");
							break;
						}
						if (factory.getTasks().getStations().containsKey(s.getName()+(a+1))){
							a++;
						}
						else a = 1;
						sleep(50);
					}
					if (tp.hasTools()){
						for(Tool t : tp.getTools()){
							
							p = position.getPosition(t.getName());
							move(p, 50, 0);
							factory.getTasks().getTools().get(t.getName()).returnTool(t.getNumber());
							sleep(200);
							
						}
						for(Tool t : tp.getTools()){
							factory.getTasks().returnVirtualTool(t.getName(), t.getNumber());
						}
					}
				}
				while(pos[0] < 550){
					sleep(1);				
					pos[0]++;
				}
				while(pos[1] > 80){
					sleep(1);				
					pos[1]--;
				}
				
				factory.removeJl(i);
				factory.getTaskPool().getTasks().remove(i);
				factory.getServer().sendMessage("complete");
				ImgJSON ij = new ImgJSON(task.getName());
				factory.getServer().sendMessage(ij.getURL());
				sleep(200);
			}
			boolean complete = false;
			while(true){
				complete = true;
				for (int k = 0; k < factory.getTaskPool().getSize(); k++){
					if(!factory.getTaskPool().getTask(k).getStatus().equals("Complete!")) {
						complete = false;
						break;
					}
				}
				if (complete) break;
			}
			
			while(pos[0] < 1000){			
				pos[0]++;
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void move(int[] p, int x, int y){
		try {
			while(pos[1] < p[1] + y){
				sleep(1);				
				pos[1]++;
			}
			while(pos[1] > p[1] + y){
				sleep(1);				
				pos[1]--;
			}
			while(pos[0] < (p[0] + x)){
				sleep(1);				
				pos[0]++;
			}
			while(pos[0] > (p[0] + x)){
				sleep(1);				
				pos[0]--;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
