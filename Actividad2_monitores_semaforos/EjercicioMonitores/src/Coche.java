public class Coche extends Thread {
	
	private enum STATE { 
		ESPERANDO, CRUZANDO, FINAL
	}
	
	private STATE state;
	private String dir;
	private int id;
	private Puente puente;
	private static int cocheCount;
	
	public Coche(String dir, Puente p) {
		id = cocheCount++;
		state = STATE.ESPERANDO;
		this.dir = dir;
		puente =p;
		start();
	}
	
	public void run()  {
		while (state != STATE.FINAL) {
			
			switch(state) {
			case ESPERANDO:
				empezarCruzar();
				break;
			case CRUZANDO:
				terminarCruzar();
				break;
			default:
				break;
			}
		}
	}
	
	public void empezarCruzar() {
		System.out.println("Coche "+ id + ": esperando para cruzar hacia el "+dir);
		try {
			puente.wait(id,dir);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		state = STATE.CRUZANDO;
		System.out.println("Coche "+id+": Esta cruzando hasta el "+dir);
	
	}
	public void terminarCruzar(){
		try {
			sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		puente.puenteCrudado(id, dir);
		state = STATE.FINAL;
		System.out.println("Coche "+id+": terminado de cruzar");
	}
	
}
