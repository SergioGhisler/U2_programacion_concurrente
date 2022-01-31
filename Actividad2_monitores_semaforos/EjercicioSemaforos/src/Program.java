import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;


public class Program {
	
	
	
	public static int NUMERO_PROCESOS;
	public static int NUMERO_RECURSOS;
	public static int recursos_disponibles;
	public static ArrayList<Recurso> recursosCompartidos=  new ArrayList<Recurso>();
	public static Semaphore consumirDisponible = new Semaphore(1);
	public static void main(String[] args) throws IOException {
		pedirValores();
		 recursos_disponibles = NUMERO_RECURSOS;
		 for(int i = 0; i < NUMERO_RECURSOS; i++) {
			 recursosCompartidos.add(new Recurso(i+1));
		 }
		
		for(int i = 0; i < NUMERO_PROCESOS; i++) {
			new Proceso();
		}
		
		
		
	}
	
	public static void pedirValores() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.print("Número de procesos:  ");
		        try {
		        	NUMERO_PROCESOS = Integer.parseInt(br.readLine());
		        } catch(NumberFormatException nfe) {
		            System.err.println("Este valor no es un numero");
		            continue;
		        }
			    break;
		}
		while(true) {
			System.out.print("Número de recursos: ");
			try{
				NUMERO_RECURSOS = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Este valor no es un numero");
				continue;
			}
			break;
		}
		
	}

}


