import java.util.ArrayList;

public class Puente {
	
	public static ArrayList<Integer> NList = new ArrayList<Integer>();
	public static ArrayList<Integer> SList = new ArrayList<Integer>();
	
	public synchronized void wait(int id, String dir) throws InterruptedException {
		if(dir == "Norte") {
			while(SList.size() != 0) {
				wait();
			}
			
			NList.add(id);
		}
		if(dir == "Sur") {
			while(NList.size() != 0) {
				wait();
			}
			
			SList.add(id);
		}
	}
	
	@SuppressWarnings("removal")
	public synchronized void puenteCrudado(int id, String dir) {
		if (dir == "Norte"){
			NList.remove(new Integer(id));
		} else {
			SList.remove(new Integer(id));
		}
		notify();
	}

}
