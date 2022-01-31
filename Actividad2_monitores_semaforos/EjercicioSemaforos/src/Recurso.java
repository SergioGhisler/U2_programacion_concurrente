import java.util.concurrent.Semaphore;

public class Recurso {
	private Semaphore estado;
	public int id;

	public Recurso(int id) {
		this.id = id;
		this.estado = new Semaphore(1);
	}

	public int getId() {
		return id;
	}

	public boolean consumirRecurso() {
		return estado.tryAcquire();
	}

	public void liberarRecurso() {
		estado.release();
	}

}