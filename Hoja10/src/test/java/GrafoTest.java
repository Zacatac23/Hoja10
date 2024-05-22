import org.example.FloydWarshall;
import org.example.Grafo;
import org.junit.Test;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class GrafoTest {
    private Grafo grafo;

    @BeforeEach
    public void setUp() {
        grafo = new Grafo(3);
        grafo.agregarCiudad("Ciudad1", 0);
        grafo.agregarCiudad("Ciudad2", 1);
        grafo.agregarCiudad("Ciudad3", 2);
        grafo.agregarArco("Ciudad1", "Ciudad2", 10);
        grafo.agregarArco("Ciudad2", "Ciudad3", 20);
    }

    @Test
    public void testAgregarArco() {
        grafo.agregarArco("Ciudad3", "Ciudad1", 30);
        assertEquals(30, grafo.getMatrizAdyacencia()[2][0]);
    }

    @Test
    public void testEliminarArco() {
        grafo.eliminarArco("Ciudad1", "Ciudad2");
        assertEquals(Integer.MAX_VALUE, grafo.getMatrizAdyacencia()[0][1]);
    }

    @Test
    public void testFloydWarshall() {
        FloydWarshall floydWarshall = new FloydWarshall(grafo);
        floydWarshall.calcularCaminos();

        assertEquals(30, floydWarshall.obtenerDistancia("Ciudad1", "Ciudad3", grafo));
        assertEquals(10, floydWarshall.obtenerDistancia("Ciudad1", "Ciudad2", grafo));
    }
}

