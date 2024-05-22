package org.example;

public class CentroGrafo {
    public static String calcularCentro(Grafo grafo, FloydWarshall floydWarshall) {
        int[][] distancias = floydWarshall.getDistancias();
        int numVertices = grafo.getNumVertices();
        int minDistanciaMaxima = Integer.MAX_VALUE;
        int centro = -1;

        for (int i = 0; i < numVertices; i++) {
            int distanciaMaxima = 0;
            for (int j = 0; j < numVertices; j++) {
                if (i != j) {
                    distanciaMaxima = Math.max(distanciaMaxima, distancias[i][j]);
                }
            }

            if (distanciaMaxima < minDistanciaMaxima) {
                minDistanciaMaxima = distanciaMaxima;
                centro = i;
            }
        }

        return grafo.getCiudad(centro);
    }
}

