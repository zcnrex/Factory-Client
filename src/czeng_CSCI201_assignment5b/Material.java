package czeng_CSCI201_assignment5b;

import java.io.Serializable;

public class Material implements Serializable{
	private String name;
	private int num;
	private int buyValue = 0, sellValue = 0;
	
	public Material(){
		super();
		this.name = "";
		this.num = 0;
		this.buyValue = 0;
		this.sellValue = 0;
	}
	public Material(String name){
		super();
		this.name = name;
		this.num = 0;
		this.buyValue = 0;
		this.sellValue = 0;
	}
	
	public void setName(String s){
		this.name = s;
	}
	
	public void setNum(int i){
		this.num = i;
	}
	
	public void setBuyValue(int n){
		this.buyValue = n;
	}
	
	public void setSellValue(int n){
		this.sellValue = n;
	}
	
	public void use(int n){
		this.num -= n;
	}
	
	public String getNum(){
		return (num + "");
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
