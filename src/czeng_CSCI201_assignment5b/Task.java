package czeng_CSCI201_assignment5b;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

public class Task implements Serializable{
	private String status = "", name = "";
	private int cost;
	
	private Vector<TaskPiece> taskPieces = new Vector<TaskPiece>();
	private Vector<Material> materials = new Vector<Material>();
	private HashMap<String, Material> material = new HashMap<String, Material>();
	
	public Task(){
		super();
		status = "Not Built";
	}
	
	public Task(String name){
		super();
		status = "Not Built";
		this.name = name;
	}
	
	public void addTaskPiece(TaskPiece tp){
		taskPieces.add(tp);
	}
	
	public TaskPiece getLastTaskPiece(){
		return taskPieces.lastElement();
	}
	
	public Vector<TaskPiece> getTaskPieces(){
		return taskPieces;
	}
	
	public void addMaterial(Material m){
		materials.add(m);
	}
	
	public void addMaterial(Material m, String name){
		material.put(name, m);
	}
	
	public void addMaterials(Vector<Material> a){
		materials = a;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setStatus(String s){
		status = s;
	}
	
	public String getName(){
		return name;
	}
	
	public Vector<Material> getMeterials(){
		return materials;
	}
	
	public Material getMaterial(int i){
		return materials.get(i);
	}
	
	public Material getMaterial(String s){
		return material.get(s);
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setCost(int n){
		this.cost = n;
	}
	
	public int getCost(){
		return cost;
	}
	
	public int getTime(){
		int time = 0;
		for (TaskPiece t : taskPieces){
			time += t.getTime();
		}
		return time;
	}
	
	public static Task copy(Task orig) {
        Task obj = null;
        try {
            // Write the object out to a byte array
        	ByteArrayOutputStream baos = new ByteArrayOutputStream();
        	ObjectOutputStream out = new ObjectOutputStream(baos);
            out.writeObject(orig);
            out.flush();
            out.close();

            // Retrieve an input stream from the byte array and read
            // a copy of the object back in. 
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bais);
            obj = (Task) in.readObject();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return obj;
    }
	
}
