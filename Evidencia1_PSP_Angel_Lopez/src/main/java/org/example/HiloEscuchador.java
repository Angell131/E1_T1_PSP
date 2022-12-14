package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloEscuchador implements Runnable {
    //se inicializan las variables
    private Thread hilo;
    private static int numCliente = 0;
    private Socket enchufeAlCliente;
    private Canciones canciones;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    //se crea el constructor
    public HiloEscuchador(Socket cliente, Canciones canciones) throws IOException {
        numCliente++;
        hilo = new Thread(this, "Cliente" + numCliente);
        this.enchufeAlCliente = cliente;
        this.canciones = canciones;
        hilo.start();
    }

    //se crea el método run
    @Override
    public void run() {
        System.out.println("Estableciendo comunicación con " + hilo.getName());
        try {
            //se crea el flujo de entrada y salida
            salida = new ObjectOutputStream(enchufeAlCliente.getOutputStream());
            entrada = new ObjectInputStream(enchufeAlCliente.getInputStream());
            String peticion="";
            do {
                // Recoge la petición del cliente como un objeto String.
                salida.writeObject(peticion);
                Object respuesta = entrada.readObject();
                // La comunicación con el cliente se mantendrá hasta que
                //  el cliente envíe la palabra "FIN".

                peticion = (String) entrada.readObject();
                if (peticion.trim().equals("FIN")) {
                    salida.writeObject("Hasta pronto, gracias por establecer conexión");
                    System.out.println(hilo.getName() + " ha cerrado la comunicación");
                } else {
                    // Atiende aquí la petición del cliente
                    switch (peticion) {
                        case "1":
                            // Una sola cancion azar
                            salida.writeObject(canciones.cancionAzar().toString());
                            break;
                        case "2":
                            // Canciones de un grupo
                            System.out.println("Introduce el nombre del grupo");
                            String grupo = (String) entrada.readObject();
                            salida.writeObject(canciones.getCancionesGrupo(grupo));
                            break;
                        case "3":
                            // Canciones por título
                            System.out.println("Introduce el título de la canción");
                            String titulo = (String) entrada.readObject();
                            salida.writeObject(canciones.getCancionesTitulo(titulo));
                            break;
                        case "4":
                            // Todas las canciones de la lista

                            salida.writeObject(canciones.getListaDistribucion());
                            break;
                        case "FIN":
                            // Desconectar del servidor

                            salida.writeObject("Hasta pronto, gracias por establecer conexión");
                            peticion = "FIN";
                            break;
                        default:
                            salida.writeObject("Opción no válida");
                            break;
                    }
                    //Debes enviar al cliente una canción o un ArrayList de canciones lo que envíes irá en función de la petición recibida.

                }
            } while ((!peticion.trim().equals("FIN")));
            entrada.close();
            salida.close();
            enchufeAlCliente.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
