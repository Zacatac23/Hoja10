package org.example;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            Grafo grafo = leerGrafoDesdeArchivo();

            FloydWarshall floydWarshall = new FloydWarshall(grafo);
            floydWarshall.calcularCaminos();

            while (true) {
                System.out.println("Opciones:");
                System.out.println("1. Consultar ruta más corta entre dos ciudades.");
                System.out.println("2. Consultar centro del grafo.");
                System.out.println("3. Modificar grafo.");
                System.out.println("4. Finalizar.");

                int opcion = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                if (opcion == 1) {
                    System.out.print("Ingrese la ciudad origen: ");
                    String origen = scanner.nextLine();
                    System.out.print("Ingrese la ciudad destino: ");
                    String destino = scanner.nextLine();

                    if (!grafo.ciudadIndice.containsKey(origen) || !grafo.ciudadIndice.containsKey(destino)) {
                        System.out.println("Una o ambas ciudades no existen en el grafo. Inténtalo de nuevo.");
                        continue;
                    }

                    int distancia = floydWarshall.obtenerDistancia(origen, destino, grafo);
                    List<String> camino = floydWarshall.obtenerCamino(origen, destino, grafo);

                    if (camino != null) {
                        System.out.println("Distancia: " + distancia);
                        System.out.println("Camino: " + String.join(" -> ", camino));
                    } else {
                        System.out.println("No hay camino entre las ciudades especificadas.");
                    }
                } else if (opcion == 2) {
                    String centro = CentroGrafo.calcularCentro(grafo, floydWarshall);
                    System.out.println("Centro del grafo: " + centro);
                } else if (opcion == 3) {
                    System.out.println("1. Interrumpir tráfico entre ciudades.");
                    System.out.println("2. Establecer conexión entre ciudades.");
                    int subOpcion = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    if (subOpcion == 1) {
                        System.out.print("Ingrese la ciudad1: ");
                        String ciudad1 = scanner.nextLine();
                        System.out.print("Ingrese la ciudad2: ");
                        String ciudad2 = scanner.nextLine();

                        if (!grafo.ciudadIndice.containsKey(ciudad1) || !grafo.ciudadIndice.containsKey(ciudad2)) {
                            System.out.println("Una o ambas ciudades no existen en el grafo. Inténtalo de nuevo.");
                            continue;
                        }

                        grafo.eliminarArco(ciudad1, ciudad2);
                    } else if (subOpcion == 2) {
                        System.out.print("Ingrese la ciudad1: ");
                        String ciudad1 = scanner.nextLine();
                        System.out.print("Ingrese la ciudad2: ");
                        String ciudad2 = scanner.nextLine();
                        System.out.print("Ingrese la distancia en KM: ");
                        int distancia = scanner.nextInt();

                        if (!grafo.ciudadIndice.containsKey(ciudad1) || !grafo.ciudadIndice.containsKey(ciudad2)) {
                            System.out.println("Una o ambas ciudades no existen en el grafo. Inténtalo de nuevo.");
                            continue;
                        }

                        grafo.agregarArco(ciudad1, ciudad2, distancia);
                    }

                    floydWarshall = new FloydWarshall(grafo);
                    floydWarshall.calcularCaminos();
                } else if (opcion == 4) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Grafo leerGrafoDesdeArchivo() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("guategrafo.txt"));
        List<String[]> conexiones = new ArrayList<>();
        Set<String> ciudades = new HashSet<>();

        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] partes = linea.split(" ");
            conexiones.add(partes);
            ciudades.add(partes[0]);
            ciudades.add(partes[1]);
        }

        int numVertices = ciudades.size();
        Grafo grafo = new Grafo(numVertices);

        int indice = 0;
        for (String ciudad : ciudades) {
            grafo.agregarCiudad(ciudad, indice++);
        }

        for (String[] conexion : conexiones) {
            String ciudad1 = conexion[0];
            String ciudad2 = conexion[1];
            int distancia = Integer.parseInt(conexion[2]);
            grafo.agregarArco(ciudad1, ciudad2, distancia);
        }

        reader.close();
        return grafo;
    }
}