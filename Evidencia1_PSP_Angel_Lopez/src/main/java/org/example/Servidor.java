package org.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        Canciones canciones = new Canciones();
        System.out.println("APLICACIÓN DE SERVIDOR PROVEEDORA DE CANCIONES");
        System.out.println("-------------------------------------------------");
        try {
            //se crea el socket servidor
            ServerSocket servidor = new ServerSocket();
            //se crea la dirección del servidor y el puerto
            InetSocketAddress direccion = new InetSocketAddress("192.168.1.20", 2000);
            //se asocia la dirección al socket
            servidor.bind(direccion);
            System.out.println("Dirección IP: " + direccion.getAddress());
            //se acepta la conexión del cliente en un bucle que esta a la espera de un cliente
            while (true) {
                Socket enchufeAlCliente = servidor.accept();
                System.out.println("Comunicación entrante");
                new HiloEscuchador(enchufeAlCliente, canciones);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
