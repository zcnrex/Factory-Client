package czeng_CSCI201_assignment5b;

import java.util.HashMap;

//Actually it stores every materials and tools in the factory
public class Tasks {
	private HashMap<String, Material> mList = new HashMap<String, Material>();
	private HashMap<String, Tool> tList = new HashMap<String, Tool>();
	private HashMap<String, Station> sList = new HashMap<String, Station>();
	private HashMap<String, Integer> materialNumList = new HashMap<String, Integer>();
	private HashMap<String, Integer> toolNumList = new HashMap<String, Integer>();
	private int numWorker = 0, money = 100;
	
	public Tasks(){
		Material[] m = new Material[3];
		m[0] = new Material("Wood");
		mList.put("Wood", m[0]);
		materialNumList.put(m[0].getName(), 0);
		m[1] = new Material("Metal");
		mList.put("Metal", m[1]);
		materialNumList.put(m[1].getName(), 0);
		m[2] = new Material("Plastic");
		mList.put("Plastic", m[2]);
		materialNumList.put(m[2].getName(), 0);
		
		Tool[] t = new Tool[5];
		t[0] = new Tool("Screwdriver");
		tList.put(t[0].getName(), t[0]);
		toolNumList.put(t[0].getName(), 0);
		t[1] = new Tool("Hammer");
		tList.put(t[1].getName(), t[1]);
		toolNumList.put(t[1].getName(), 0);
		t[2] = new Tool("Paintbrush");
		tList.put(t[2].getName(), t[2]);
		toolNumList.put(t[2].getName(), 0);
		t[3] = new Tool("Pliers");
		tList.put(t[3].getName(), t[3]);
		toolNumList.put(t[3].getName(), 0);
		t[4] = new Tool("Scissors");
		tList.put(t[4].getName(), t[4]);
		toolNumList.put(t[4].getName(), 0);
		
		Station[] s = new Station[15];
		s[0] = new Station("Anvil");
		sList.put("Anvil1", s[0]);
		s[1] = new Station("Anvil");
		sList.put("Anvil2", s[1]);
		s[2] = new Station("Workbench");
		sList.put("Workbench1", s[2]);
		s[3] = new Station("Workbench");
		sList.put("Workbench2", s[3]);
		s[4] = new Station("Workbench");
		sList.put("Workbench3", s[4]);
		s[5] = new Station("Furnace");
		sList.put("Furnace1", s[5]);
		s[6] = new Station("Furnace");
		sList.put("Furnace2", s[6]);
		s[7] = new Station("Saw");
		sList.put("Saw1", s[7]);
		s[8] = new Station("Saw");
		sList.put("Saw2", s[8]);
		s[9] = new Station("Saw");
		sList.put("Saw3", s[9]);
		s[10] = new Station("Painting");
		sList.put("Painting1", s[10]);
		s[11] = new Station("Painting");
		sList.put("Painting2", s[11]);
		s[12] = new Station("Painting");
		sList.put("Painting3", s[12]);
		s[13] = new Station("Painting");
		sList.put("Painting4", s[13]);
		s[14] = new Station("Press");
		sList.put("Press1", s[14]);
	}
	
	public void setWorker(int n){
		this.numWorker = n;
	}

	public void addNumWorker(int n){
		this.numWorker += n;
	}
	
	public void takeNumWorker(int n){
		this.numWorker -= n;
	}
	public int getNumWorker(){
		return this.numWorker;
	}
	
	public void setMaterial(String s, int n, int sellVal, int buyVal){
//		Material m = new Material(s);
//		
//		mList.put(s, m);
		mList.get(s).setNum(n);
		mList.get(s).setBuyValue(buyVal);
		mList.get(s).setSellValue(sellVal);
		materialNumList.replace(s, n);
	}
	
	public void setTool(String s, int n, int sellVal, int buyVal){
		tList.get(s).setNum(n);
		tList.get(s).setBuyValue(buyVal);
		tList.get(s).setSellValue(sellVal);
		toolNumList.replace(s, n);
	}
	
	public void setMoney(int n){
		this.money = n;
	}
	
	public void addMoney(int n){
		this.money += n;
	}
	
	public void takeMoney(int n){
		this.money -= n;
	}
	
	public void useVirtualTool(String s, int n){
		toolNumList.replace(s, toolNumList.get(s)-n);
	}
	
	public void returnVirtualTool(String s, int n){
		toolNumList.replace(s, toolNumList.get(s)+n);
	}
	
	public HashMap<String, Tool> getTools(){
		return tList;
	}
	public HashMap<String, Material> getMaterials(){
		return mList;
	}
	public HashMap<String, Station> getStations(){
		return sList;
	}
	
	public HashMap<String, Integer> getToolNumber(){
		return toolNumList;
	}
	
//	public void addTool(String s, int n){
//		
//	}
	
	public int getMoney(){
		return money;
	}
	
}
