import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Enlace extends Thread {
	private final Socket clientSocket;

	public Enlace(Socket socket) {
		this.clientSocket = socket;
	}
	
	@Override
	public void run() {
		PrintWriter out = null;
		BufferedReader in = null;

		String presentacion = "Se ha conectado al chatbot de la pizzeria papa luigi";
		String pregunta = "¿En qué podemos ayudarle?";

		List<String> opciones = new ArrayList<>();
		opciones.add("1 - ¿Cuál es el horario?");
		opciones.add("2 - ¿Cuál es el tiempo de entrega estimado?");
		opciones.add("3 - ¿Cuál es el precio de las pizzas?");
		opciones.add("4 - ¿Tienen opciones veganas?");
		opciones.add("5 - ¿Hay otros platos a pare de pizzas?");
		opciones.add("Salir");
		try {

			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String entrada;
			int clientAnswer = 0;
			while ((entrada = in.readLine()) != null) {

				if (entrada.equals("CON")) {

					out.println(presentacion);
					out.println(pregunta);
					out.println(opciones.get(0));
					out.println(opciones.get(1));
					out.println(opciones.get(2));
					out.println(opciones.get(3));
					out.println(opciones.get(4));
					out.println(opciones.get(5));

				}else if (entrada.equals("Salir")) {
					out.println("Hasta la proxima!");
					clientSocket.close();
					break;
				} else {

					if (isNumeric(entrada)) {
						clientAnswer = Integer.parseInt(entrada);
					}

					if (!isNumeric(entrada)) {
						out.println("Eso no es un número");

					} else if (clientAnswer < 1 || clientAnswer > 5) {
						out.println(
								"Ese número no esta en el rango");

					} else if (clientAnswer == 1) {
						out.println("Esta pizzería hace repartos a domicilio las 24 horas del día, así que no dudes en pedir si se ha alargado la noche!");

					} else if (clientAnswer == 2) {
						out.println("Nos comprometemos a hacer las entregas en menos de 45 minutos, si se supera este tiempo, la proxima vez que pidas se te aplicara un descuento del 30%");

					} else if (clientAnswer == 3) {
						out.println("Depende de la pizza, por ejemplo, la Margarita son 9.90€ y 'la suprema' son 17.90€");

					} else if (clientAnswer == 4) {
						out.println("Si! por ahora contamos con 3 opciones veganas, una Margarita, una con pollo vegetal y otra de bacon vegetal!");
					} else if (clientAnswer == 5) {
						out.println("Por ahora solo disponemos de pizzas, no descartamos en un futuro traer más opciones a la carta.");

					} else {
						out.println("Error inesperado, pruebe en otro momento");

					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
					clientSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
