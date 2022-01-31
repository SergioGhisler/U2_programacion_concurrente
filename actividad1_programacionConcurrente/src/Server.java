
import java.io.*;
import java.net.*;

// Server class
class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket server = null;

		try {
			server = new ServerSocket(9999);
			System.out.println("Servidor iniciado");
			while (true) {

				Socket cliente = server.accept();
				System.out.println("Nuevo cliente conectado");
				Enlace enlaceCliente = new Enlace(cliente);

				enlaceCliente.start();
			}
		} catch (IOException e) {
			System.out.println("El servidor no ha podido conectarse");
			e.printStackTrace();
		} finally {
			if (server != null) {

				server.close();
			}
		}
	}
}


