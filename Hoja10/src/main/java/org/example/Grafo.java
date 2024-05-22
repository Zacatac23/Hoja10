package org.example;

import java.util.*;

public class Grafo {
    private final int numVertices;
    private final int[][] matrizAdyacencia;
    final Map<String, Integer> ciudadIndice;
    private final Map<Integer, String> indiceCiudad;

    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        this.matrizAdyacencia = new int[numVertices][numVertices];
        this.ciudadIndice = new HashMap<>();
        this.indiceCiudad = new HashMap<>();

        for (int i = 0; i < numVertices; i++) {
            Arrays.fill(matrizAdyacencia[i], Integer.MAX_VALUE);
            matrizAdyacencia[i][i] = 0;
        }
    }

    public void agregarCiudad(String ciudad, int indice) {
        ciudadIndice.put(ciudad, indice);
        indiceCiudad.put(indice, ciudad);
    }

    public void agregarArco(String ciudad1, String ciudad2, int peso) {
        int indice1 = ciudadIndice.get(ciudad1);
        int indice2 = ciudadIndice.get(ciudad2);
        matrizAdyacencia[indice1][indice2] = peso;
    }

    public void eliminarArco(String ciudad1, String ciudad2) {
        int indice1 = ciudadIndice.get(ciudad1);
        int indice2 = ciudadIndice.get(ciudad2);
        matrizAdyacencia[indice1][indice2] = Integer.MAX_VALUE;
    }

    public int[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public String getCiudad(int indice) {
        return indiceCiudad.get(indice);
    }

    public int getIndice(String ciudad) {
        return ciudadIndice.get(ciudad);
    }

    public int getNumVertices() {
        return numVertices;
    }
}

