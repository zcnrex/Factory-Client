package czeng_CSCI201_assignment5b;

import java.util.HashMap;

public class StorePosition {
	private int[][] pos;
	private HashMap<String, int[]> hashmap;
	
	public StorePosition(){
		pos = new int[10][2];
		hashmap = new HashMap<String, int[]>();
		setPosition();
		
	}
	
	public void setPosition(){
		String s;
		
		
		
		s = "Wood";
		pos[0][0] = 440;
		pos[0][1] = 140;
		hashmap.put(s, pos[0]);
		
		s = "Metal";		
		pos[1][0] = 440;
		pos[1][1] = 240;
		hashmap.put(s, pos[1]);
		
		s = "Plastic";
		pos[2][0] = 440;
		pos[2][1] = 340;
		hashmap.put(s, pos[2]);
		
		s = "Screwdriver";
		pos[3][0] = 40;
		pos[3][1] = 140;
		hashmap.put(s, pos[3]);
		
		s = "Hammer";
		pos[4][0] = 40;
		pos[4][1] = 240;
		hashmap.put(s, pos[4]);
		
		s = "Paintbrush";
		pos[5][0] = 40;
		pos[5][1] = 340;
		hashmap.put(s, pos[5]);
		
		s = "Pliers";
		pos[6][0] = 40;
		pos[6][1] = 440;
		hashmap.put(s, pos[6]);
		
		s = "Scissors";
		pos[7][0] = 40;
		pos[7][1] = 540;
		hashmap.put(s, pos[7]);
		
		s = "Worker";
		pos[8][0] = 440;
		pos[8][1] = 540;
		hashmap.put(s, pos[8]);
		
		s = "Money";
		pos[9][0] = 15;
		pos[9][1] = 30;
		hashmap.put(s, pos[9]);
		
	}	
	
	public int[] getPosition(String name){
		return hashmap.get(name);
	}
	
	public HashMap<String, int[]> getAllPositions(){
		return hashmap;
	}

}
