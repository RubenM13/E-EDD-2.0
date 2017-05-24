package es.ucm.fdi.edd.core.erlang.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {

	public static void main(String args[]) {
		ServerSocket servidor;
		Socket conexion;
		DataOutputStream salida;
		DataInputStream entrada;
		int num = 0;
		try {
			// Creamos un ServerSocket en el puerto especificado
			servidor = new ServerSocket(6667); 
			System.out.println("Servidor arrancado correctamente...");
			while (true) {
				conexion = servidor.accept(); // Esperamos una conexión
				num++;
				System.out.println("Conexión número" + num + " desde: "	+ conexion.getInetAddress().getHostName());
				// Abrimos los canales de entrada y salida
				entrada = new DataInputStream(conexion.getInputStream()); 
				salida = new DataOutputStream(conexion.getOutputStream());
				// Leemos el mensaje del cliente
				String mensaje = entrada.readUTF(); 
				System.out.println("Conexión n." + num + " mensaje: " + mensaje);
				// Le respondemos
				salida.writeUTF("Mensaje: " + mensaje);
				// Y cerramos la conexión
				conexion.close(); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}