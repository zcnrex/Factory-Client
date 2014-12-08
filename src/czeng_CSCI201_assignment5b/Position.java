package czeng_CSCI201_assignment5b;

import java.util.HashMap;

public class Position {
	private int[][] pos;
	private HashMap<String, int[]> hashmap;
	
	public Position(){
		pos = new int[30][2];
		hashmap = new HashMap<String, int[]>();
		setPosition();
		
	}
	
	public boolean getViable(int[] d){
		if(d[0] == 0 && d[1] != 20) return false;
		else if (d[1] == 0 && (d[0] == 140 || d[0] == 280 || d[0] == 420)) return false;
		else if((d[1] == 140 || d[1] == 280 || d[1] == 420) && d[0] != 70 && d[0] != 490) return false;
		else return true;
	}
	
	public void setPosition(){
		String s;
		
		s = "Money";
		pos[29][0] = 20;
		pos[29][1] = 20;
		hashmap.put(s, pos[29]);
		
		s = "Wood";
		pos[0][0] = 140;
		pos[0][1] = 30;
		hashmap.put(s, pos[0]);
		
		s = "Metal";		
		pos[1][0] = 280;
		pos[1][1] = 30;
		hashmap.put(s, pos[1]);
		
		s = "Plastic";
		pos[2][0] = 420;
		pos[2][1] = 30;
		hashmap.put(s, pos[2]);
		
		s = "Screwdriver";
		pos[3][0] = 0;
		pos[3][1] = 190;
		hashmap.put(s, pos[3]);
		
		s = "Hammer";
		pos[4][0] = 0;
		pos[4][1] = 270;
		hashmap.put(s, pos[4]);
		
		s = "Paintbrush";
		pos[5][0] = 0;
		pos[5][1] = 350;
		hashmap.put(s, pos[5]);
		
		s = "Pliers";
		pos[6][0] = 0;
		pos[6][1] = 430;
		hashmap.put(s, pos[6]);
		
		s = "Scissors";
		pos[7][0] = 0;
		pos[7][1] = 510;
		hashmap.put(s, pos[7]);
		
		s = "Anvil1";
		pos[8][0] = 140;
		pos[8][1] = 190;
		hashmap.put(s, pos[8]);
		
		s = "Anvil2";
		pos[9][0] = 210;
		pos[9][1] = 190;
		hashmap.put(s, pos[9]);
		
		s = "Workbench1";
		pos[10][0] = 280;
		pos[10][1] = 190;
		hashmap.put(s, pos[10]);
		
		s = "Workbench2";
		pos[11][0] = 350;
		pos[11][1] = 190;
		hashmap.put(s, pos[11]);
		
		s = "Workbench3";
		pos[12][0] = 420;
		pos[12][1] = 190;
		hashmap.put(s, pos[12]);
		
		s = "Furnace1";
		pos[13][0] = 140;
		pos[13][1] = 350;
		hashmap.put(s, pos[13]);
		
		s = "Furnace2";
		pos[14][0] = 210;
		pos[14][1] = 350;
		hashmap.put(s, pos[14]);
		
		s = "Saw1";
		pos[15][0] = 280;
		pos[15][1] = 350;
		hashmap.put(s, pos[15]);
		
		s = "Saw2";
		pos[16][0] = 350;
		pos[16][1] = 350;
		hashmap.put(s, pos[16]);
		
		s = "Saw3";
		pos[17][0] = 420;
		pos[17][1] = 350;
		hashmap.put(s, pos[17]);
		
		s = "Painting1";
		pos[18][0] = 140;
		pos[18][1] = 510;
		hashmap.put(s, pos[18]);
		
		s = "Painting2";
		pos[19][0] = 210;
		pos[19][1] = 510;
		hashmap.put(s, pos[19]);
		
		s = "Painting3";
		pos[20][0] = 280;
		pos[20][1] = 510;
		hashmap.put(s, pos[20]);
		
		s = "Painting4";
		pos[21][0] = 350;
		pos[21][1] = 510;
		hashmap.put(s, pos[21]);
		
		s = "Press1";
		pos[22][0] = 420;
		pos[22][1] = 510;
		hashmap.put(s, pos[22]);
		
		s = "Anvils";
		pos[23][0] = 170;
		pos[23][1] = 240;
		hashmap.put(s, pos[23]);
		
		s = "Work benches";
		pos[24][0] = 310;
		pos[24][1] = 240;
		hashmap.put(s, pos[24]);
		
		s = "Furnaces";
		pos[25][0] = 150;
		pos[25][1] = 400;
		hashmap.put(s, pos[25]);
		
		s = "Table Saws";
		pos[26][0] = 330;
		pos[26][1] = 400;
		hashmap.put(s, pos[26]);
		
		s = "Painting Stations";
		pos[27][0] = 200;
		pos[27][1] = 560;
		hashmap.put(s, pos[27]);
		
		s = "Press";
		pos[28][0] = 430;
		pos[28][1] = 560;
		hashmap.put(s, pos[28]);
		
	}	
	
	public int[] getPosition(String name){
		return hashmap.get(name);
	}
	
	public HashMap<String, int[]> getAllPositions(){
		return hashmap;
	}
}
