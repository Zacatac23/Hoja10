package org.example;

import java.util.ArrayList;
import java.util.List;

public class FloydWarshall {
    private final int[][] distancias;
    private final int[][] siguiente;
    private final int numVertices;

    public FloydWarshall(Grafo grafo) {
        this.numVertices = grafo.getNumVertices();
        this.distancias = new int[numVertices][numVertices];
        this.siguiente = new int[numVertices][numVertices];

        inicializar(grafo);
    }

    private void inicializar(Grafo grafo) {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                distancias[i][j] = grafo.getMatrizAdyacencia()[i][j];
                if (i != j && grafo.getMatrizAdyacencia()[i][j] != Integer.MAX_VALUE) {
                    siguiente[i][j] = j;
                } else {
                    siguiente[i][j] = -1;
                }
            }
        }
    }

    public void calcularCaminos() {
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (distancias[i][k] != Integer.MAX_VALUE && distancias[k][j] != Integer.MAX_VALUE
                            && distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                        siguiente[i][j] = siguiente[i][k];
                    }
                }
            }
        }
    }

    public List<String> obtenerCamino(String origen, String destino, Grafo grafo) {
        List<String> camino = new ArrayList<>();
        int u = grafo.getIndice(origen);
        int v = grafo.getIndice(destino);

        if (siguiente[u][v] == -1) {
            return null;
        }

        camino.add(grafo.getCiudad(u));
        while (u != v) {
            u = siguiente[u][v];
            camino.add(grafo.getCiudad(u));
        }

        return camino;
    }

    public int obtenerDistancia(String origen, String destino, Grafo grafo) {
        return distancias[grafo.getIndice(origen)][grafo.getIndice(destino)];
    }

    public int[][] getDistancias() {
        return distancias;
    }
}
