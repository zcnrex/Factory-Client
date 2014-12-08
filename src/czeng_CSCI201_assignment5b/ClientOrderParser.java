package czeng_CSCI201_assignment5b;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

public class ClientOrderParser {
	private Vector<String> order = new Vector<String>(); 
	private Vector<ClientOrderInstructionPiece> coip = new Vector<ClientOrderInstructionPiece>();
	private String item, cost, wood, plastic, metal, tool, workspace, time;
	
	private FileWriter fw;
	private PrintWriter pw;

	public ClientOrderParser(){
		try {
			fw = new FileWriter("new.rcp");
			pw = new PrintWriter(fw);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setText(){
		
	}
	
	public void setItem(String s){
		this.item = s;
	}
	
	public void setCost(String s){
		this.cost = s;
	}
	
	public void setWood(String s){
		this.wood = s;
	}
	
	public void setPlastic(String s){
		this.plastic = s;
	}
	
	public void setMetal(String s){
		this.metal = s;
	}
	
	public void setCOIP(Vector<ClientOrderInstructionPiece> c){
		this.coip = c;
	}
	
	public void writeFile(){
		pw.println("[" + item + ":$" + cost + "]");
		if(!wood.equals("")){
			pw.println("[Wood:" + wood + "]");
		}
		if(!plastic.equals("")){
			pw.println("[Plastic:" + plastic + "]");
		}
		if(!metal.equals("")){
			pw.println("[Metal:" + metal + "]");
		}
		
		for (ClientOrderInstructionPiece c : coip){
			pw.print("[Use ");
			if(!c.getJTF1().equals("")){
				pw.print(c.getJTF1() + "x " + c.getTool1());
			}
			if(!c.getJTF2().equals("")){
				pw.print(" and " + c.getJTF2() + "x " + c.getTool2());
			}
			
			if (!c.getJTF1().equals("") && !c.getJTF2().equals("")){
				pw.print(" at " + c.getWorkSpace() + " for " + c.getTime() + "s]");
			}
			else{
				pw.print(c.getWorkSpace() + " for " + c.getTime() + "s]");
			}
			pw.println("");
		}
		pw.flush();
		pw.close();
	}
}
