package czeng_CSCI201_assignment5b;

import java.io.Serializable;

public class Material implements Serializable{
	private String name;
	private int num;
	
	public Material(){
		super();
		this.name = "";
		this.num = 999;
	}
	public Material(String name){
		super();
		this.name = name;
		this.num = 999;
	}
	
	public void setName(String s){
		this.name = s;
	}
	
	public void setNum(int i){
		this.num = i;
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
}
