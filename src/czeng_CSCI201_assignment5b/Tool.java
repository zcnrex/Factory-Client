package czeng_CSCI201_assignment5b;

import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tool implements Serializable{
	private String name;
	private int sum, num, buyValue, sellValue;
	private Lock lock = new ReentrantLock();
	
	public Tool(){
		super();
		this.name = "";
		this.num = 0;
		this.sum = 0;
		this.buyValue = 0;
		this.sellValue = 0;
	}
	
	public Tool(String name){
		super();
		this.name = name;
		this.num = 0;
		this.sum = 0;
		this.buyValue = 0;
		this.sellValue = 0;
	}
	
	public Tool(String name, int sum){
		super();
		this.name = name;
		this.sum = sum;
		this.num = sum;
		this.buyValue = 0;
		this.sellValue = 0;
	}
	
	public void setName(String s){
		this.name = s;
	}
	
	public void setNum(int n){
		this.num = n;
		this.sum = n;
	}
	
	public void setBuyValue(int n){
		this.buyValue = n;
	}
	
	public void setSellValue(int n){
		this.sellValue = n;
	}
	
	public void takeTool(int n){
		lock.lock();
		this.num -= n;
		lock.unlock();
	}
		
	public void returnTool(int n){
		this.num += n;
	}
	
	public String getNum(){
		return (num + "/" + sum);
	}
	
	public int getNumTool(){
		return num;
	}
	
	public int getNumber(){
		return num;
	}
	
	public String getName(){
		return name;
	}
	
	public int getBuyValue(){
		return buyValue;
	}
	
	public int getSellValue(){
		return sellValue;
	}
}
