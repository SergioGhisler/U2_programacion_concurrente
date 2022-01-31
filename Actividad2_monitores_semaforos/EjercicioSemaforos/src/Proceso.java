import java.util.ArrayList;
import java.util.Random;

public class Proceso extends Thread {


	private static int procesosCount = 0;
	private int procesoId;
	private  ArrayList<Integer> recursosUtilizados;
	public Proceso() {
		procesoId = ++procesosCount;
		recursosUtilizados=  new ArrayList<Integer>();
		start();
	}

	@Override
	public void run() {
		Random random = new Random();
		int tiempoInicializacionRecurso = random.nextInt(1000)+100;
		try {
			sleep(tiempoInicializacionRecurso);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {
			consumir();
			
			int tiempoHastaLiberar = random.nextInt(100)+100;
			try {
				sleep(tiempoHastaLiberar);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (recursosUtilizados.size()>0) {
				liberar();
			}
			
		}
	}

	private void consumir() {
		try {
			Program.consumirDisponible.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Random random = new Random();
		int recursosAConsumir = random.nextInt(Program.NUMERO_RECURSOS-1)+1;
		System.out.println("Hay "+Program.recursos_disponibles+ " recursos disponibles");
		System.out.println("Proceso " +procesoId+ ": Quiere consumir "+recursosAConsumir+ " recursos");
		if (recursosAConsumir > Program.recursos_disponibles) {
			System.out.println("Proceso " +procesoId+ ": No hay tantos recursos disponibles, pruebe más tarde");
		}else {
			Program.recursos_disponibles -= recursosAConsumir;
			int recursosConsumidos = 0;
			int count = 0;
			while (recursosConsumidos <recursosAConsumir) {
				
				if(Program.recursosCompartidos.get(count).consumirRecurso()) {
					recursosConsumidos+=1;
					recursosUtilizados.add(Program.recursosCompartidos.get(count).getId());
				}
				count +=1;
				
			}
			System.out.println("Proceso " +procesoId+ ": "+recursosConsumidos +" recursos consumidos");
			System.out.println("General: "+Program.recursos_disponibles +" recursos disponibles");
		}
		Program.consumirDisponible.release();
	}
	private void liberar() {
		Random random = new Random();
		int recursosALiberar;
		if (recursosUtilizados.size()==1) {
			recursosALiberar = 1;
		}else {
			recursosALiberar = random.nextInt(recursosUtilizados.size()-1)+1;
		}
		int count = 0;
		while (recursosALiberar> 0) {
			Program.recursosCompartidos.get(recursosUtilizados.get(0)-1).liberarRecurso();
			recursosUtilizados.remove(0);
			Program.recursos_disponibles += 1;
			recursosALiberar -=1;
			count+=1;
		}
		System.out.println("Proceso " +procesoId+ ": "+count +" recursos liberados");
		System.out.println("General: "+Program.recursos_disponibles +" recursos disponibles");
	}
}