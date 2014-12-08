package czeng_CSCI201_assignment5b;

import java.awt.Color;
import java.io.Serializable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Station implements Serializable{
	private String name, status, virtualStatus;
	private int time;
	private Lock lock = new ReentrantLock();
	private Condition stationAvailable = lock.newCondition();
	
	public Station(){
		super();
		this.name = "";
		this.time = 0;
		this.status = "Open";
		this.virtualStatus = "Open";
	}

	public Station(String name){
		super();
		this.name = name;
		this.time = 0;
		this.status = "Open";
		this.virtualStatus = "Open";
	}
	
	public Station(String name, int time){
		super();
		this.name = name;
		this.time = time;
		this.status = "Open";
	}
	
	public void setName(String s){
		this.name = s;
	}
	
	public void setStatus(String s){
		this.status = s;
	}
	
	public void setVirtualStatus(String s){
		this.virtualStatus = s;
	}
	
	public String getVirtualStatus(){
		return virtualStatus;
	}
	
	public void setTime(int n){
		this.time = n;
	}
	
	public int getTime(){
		return this.time;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Color getColor(){
		if (this.status.equals("Open"))
			return Color.GREEN;
		else 
			return Color.RED;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public void setNum(int n){}
	public String getNum(){return "";}
	
	public Lock getLock(){
		return lock;
	}
	
	public Condition getCondition(){
		return stationAvailable;
	}
	
	public void inUse(int[]p, int[] pos, int time){
		lock.lock();
		try {
			this.time = time;

			while(pos[0] < (p[0] + 0)){
				Thread.sleep(1);				
				pos[0]++;
			}
			while(pos[0] > (p[0] + 0)){
				
					Thread.sleep(1);
				pos[0]--;
			}
			while(pos[1] < p[1] + 0){
				Thread.sleep(1);				
				pos[1]++;
			}
			while(pos[1] > p[1] + 0){
				Thread.sleep(1);				
				pos[1]--;
			}
			
		
			for(int i = 0; i < time; i ++){

				setStatus((time-i) + "s");
				Thread.sleep(1000);
			}
			setStatus("Open");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void finished(){
		lock.lock();
		stationAvailable.signal();
		lock.unlock();
	}
}
