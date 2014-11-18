package czeng_CSCI201_assignment5b;

import java.util.Vector;

public class TaskPool {
	private Vector<Task> taskPool = new Vector<Task>();
	
	public TaskPool(){
		
	}
	
	public void addTask(Task t){
		taskPool.add(t);
	}
	
	public Task getTask(int i){
		return taskPool.get(i);
	}
	
	public Vector<Task> getTasks(){
		return taskPool;
	}
	
	public Task getRecentTask(){
		return taskPool.lastElement();
	}
	
	public int getSize(){
		return taskPool.size();
	}

}
