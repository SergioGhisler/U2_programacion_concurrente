import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Program {
	
	public static void main(String[] args) throws InterruptedException {
		Puente puente = new Puente();
		String dir;
		Random random = new Random();
		while(true) {
			if (random.nextBoolean()) {
				dir =  "Norte";
			}else {
				dir = "Sur";
			}
			
			new Coche(dir,puente);
			
			System.out.println(Puente.NList.size()+" coches esperando a cruzar al norte");
			System.out.println(Puente.SList.size()+" coches esperando a cruzar al sur");

		}
	}
}
