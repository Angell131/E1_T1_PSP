package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    //se crean los streams de entrada y salida
    private static ObjectInputStream entrada;
    private static ObjectOutputStream salida;

    public static void main(String[] args) {
        System.out.println(" APLICACIÓN CLIENTE");
        System.out.println("-----------------------------------");
        Scanner lector = new Scanner(System.in);
        try {
            //se crea el socket cliente
            Socket cliente = new Socket();
            //se crea la dirección del servidor y el puerto
            InetSocketAddress direccionServidor = new InetSocketAddress("192.168.1.20", 2000);
            System.out.println("Esperando a que el servidor acepte la conexión");
            cliente.connect(direccionServidor);
            // Conectamos con el servidor.
            System.out.println("Comunicación establecida con el servidor");
            //se crean los streams de entrada y salida
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());
            //se crea un bucle para que el cliente pueda enviar peticiones al servidor
            String peticion = "";
            while (!peticion.equals("FIN")) {
                System.out.println("1. Una sola canción");
                System.out.println("2. Canciones de un grupo");
                System.out.println("3. Canciones por título");
                System.out.println("4. Todas las canciones de la lista");
                System.out.println("5. Desconectar");
                System.out.println("Introduce una opción: 1, 2, 3, 4 o FIN");
                peticion= lector.nextLine();

                //se envía la petición al servidor
                switch (peticion) {
                    case "1":
                        // Una sola cancion al azar
                        Canciones cancionAleatoria = new Canciones();
                        System.out.println(cancionAleatoria.cancionAzar());
                        System.out.println(" ");
                        break;


                    case "2":
                        // Canciones de un grupo
                        Canciones cancionGrupo = new Canciones();
                        System.out.println("Introduce el nombre del grupo");
                        Scanner grupo = new Scanner(System.in);
                        for (Cancion cancion : cancionGrupo.getCancionesGrupo(grupo.nextLine())) {
                            System.out.println(cancion);
                        }
                        System.out.println(" ");
                        break;


                    case "3":
                        // Canciones por título
                        Canciones cancionTitulo = new Canciones();
                        System.out.println("Introduce el título de la canción");
                        Scanner titulo = new Scanner(System.in);
                        for (Cancion cancion : cancionTitulo.getCancionesTitulo(titulo.nextLine())) {
                            System.out.println(cancion);
                        }
                        System.out.println(" ");
                        break;


                    case "4":
                        // Todas las canciones de la lista
                        Canciones canciones = new Canciones();
                        for (Cancion c : canciones.getListaDistribucion()) {
                            System.out.println(c);
                        }
                        System.out.println("-- Fin de la lista --");
                        System.out.println(" ");
                        break;
                    case "FIN":
                        // Desconectar del servidor

                        System.out.println("Hasta pronto, gracias por establecer conexión");
                        peticion = "FIN";
                        break;
                    default:
                        System.out.println("Opción incorrecta");
                }
                //  para que el cliente se comunique con el servidor para obtener
                //  lo que necesita.

            }
            //cerramos los inputs
            entrada.close();
            salida.close();
            cliente.close();
            System.out.println("Comunicación cerrada");
        } catch (UnknownHostException e) {
            System.out.println("No se puede establecer comunicación con el servidor");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S");
            System.out.println(e.getMessage());
        }
        //cerramos el lector
        lector.close();
    }


}