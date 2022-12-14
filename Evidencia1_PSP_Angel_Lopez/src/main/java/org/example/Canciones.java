package org.example;

import java.io.Serializable;
import java.util.ArrayList;

public class Canciones implements Serializable {

    private ArrayList<Cancion> listaDistribucion;
//se crea el constructor
    public Canciones() {
        listaDistribucion = new ArrayList<Cancion>();

        listaDistribucion.add(new Cancion("Nothing compares to you", "Sinead O connor"));
        listaDistribucion.add(new Cancion("I will always love you", "Whitney Houston"));
        listaDistribucion.add(new Cancion("Still Loving You", "Scorpions"));
        listaDistribucion.add(new Cancion("The Scientist", "Coldplay"));
        listaDistribucion.add(new Cancion("A Sky Full Of Stars", "Coldplay"));
        listaDistribucion.add(new Cancion("scorpions lady", "Scorpions"));
        listaDistribucion.add(new Cancion("Nothing else matters", "Metalica"));
        listaDistribucion.add(new Cancion("Me paro cuando suena", "Orquesta de las nubes"));
        listaDistribucion.add(new Cancion("Bohemian rhapsody", "Queen"));
        listaDistribucion.add(new Cancion("Imagine", "John Lennon"));
        listaDistribucion.add(new Cancion("AnotherOne Bites the Dust", "Queen"));
    }

    public ArrayList<Cancion> getListaDistribucion() {
        return listaDistribucion;
    }
    public void setListaDistribucion(ArrayList<Cancion> listaDistribucion) {
        this.listaDistribucion = listaDistribucion;
    }

    public Cancion cancionAzar() {
        int i=(int) (Math.random()*10);
        return this.listaDistribucion.get(i);
    }

    public ArrayList<Cancion> getCancionesGrupo(String grupo) {
        ArrayList<Cancion> canciones = new ArrayList<Cancion>();
        for (Cancion c : this.listaDistribucion) {
            if (c.getGrupo().toUpperCase().equals(grupo.toUpperCase())) {
                canciones.add(c);
            }
        }
        return canciones;
    }

    public ArrayList<Cancion> getCancionesTitulo(String titulo) {
        ArrayList<Cancion> canciones = new ArrayList<Cancion>();
        for (Cancion c : this.listaDistribucion) {
            if (c.getTitulo().toUpperCase().contains(titulo.toUpperCase())) {
                canciones.add(c);
            }
        }
        return canciones;
    }
}
