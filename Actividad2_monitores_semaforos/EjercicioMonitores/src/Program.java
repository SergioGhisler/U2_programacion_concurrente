import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Program {
	
	public static void main(String[] args) throws InterruptedException {
		Puente puente = new Puente();
		String dir;
		Random random = new Random();
		while(true) {
			TimeUnit.SECONDS.sleep(1);
			if (random.nextBoolean()) {
				dir =  "Norte";
			}else {
				dir = "Sur";
			}
			
			new Coche(dir,puente);
		}
	}
}
