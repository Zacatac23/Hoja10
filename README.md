# Algoritmo de Floyd-Warshall para Rutas más Cortas en un Grafo

## Descripción

Este proyecto implementa un grafo dirigido y utiliza el algoritmo de Floyd-Warshall para calcular la ruta más corta entre cualquier par de ciudades. También calcula el centro del grafo, que es la ciudad con la menor distancia máxima a todas las demás ciudades.

El programa lee los datos del grafo desde un archivo de texto (`guategrafo.txt`) y permite interactuar con el grafo para consultar rutas, modificarlo y obtener el centro del grafo.

## Archivos

- `Principal.java`: Contiene el método `main` y la lógica principal del programa.
- `Grafo.java`: Define la clase `Grafo` y los métodos para manipular el grafo.
- `FloydWarshall.java`: Implementa el algoritmo de Floyd-Warshall.
- `CentroGrafo.java`: Calcula el centro del grafo.
- `guategrafo.txt`: Contiene los datos del grafo.

## Requisitos

- Java Development Kit (JDK) 8 o superior.
- Un editor de texto o un IDE como IntelliJ IDEA, Eclipse o Visual Studio Code.

## Instalación y Ejecución

1. **Clona el repositorio o descarga los archivos:**
   ```sh
   git clone <URL_DEL_REPOSITORIO>
   cd <NOMBRE_DEL_REPOSITORIO>
