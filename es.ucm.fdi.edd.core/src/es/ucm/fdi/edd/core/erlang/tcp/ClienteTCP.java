package es.ucm.fdi.edd.core.erlang.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClienteTCP {

	public static void main(String args[]) {
		Socket cliente;
		DataInputStream entrada;
		DataOutputStream salida;
		String mensaje, respuesta;
		try {
			// Creamos el socket para conectarnos al puerto 6667 del servidor
			cliente = new Socket(InetAddress.getLocalHost(), 6667); 
			// Creamos los canales de entrada/salida
			entrada = new DataInputStream(cliente.getInputStream()); 
			salida = new DataOutputStream(cliente.getOutputStream());
			mensaje = "Mensaje Java To Erlang";
			salida.writeUTF(mensaje); // Enviamos un mensaje al servidor
			respuesta = entrada.readUTF(); // Leemos la respuesta
			System.out.println("Mi mensaje: " + mensaje);
			System.out.println("Respuesta del Servidor: " + respuesta);
			cliente.close(); // Cerramos la conexion
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}