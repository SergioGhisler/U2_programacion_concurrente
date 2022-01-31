
import java.io.*;
import java.net.*;

class Cliente {

	public static void main(String[] args) throws IOException {

		try (Socket socket = new Socket("127.0.0.1", 9999)) {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String line = null;
			System.out.println("Enviado CON");
			out.println("CON");
			System.out.println(in.readLine() + "\n");
			System.out.println(in.readLine() + "\n");
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println("\n");

			while (!"Salir".equalsIgnoreCase(line)) {

				line = System.console().readLine();
				out.println(line);
				System.out.println(in.readLine());
				System.out.println("\n");

			}
		}

	}
}
