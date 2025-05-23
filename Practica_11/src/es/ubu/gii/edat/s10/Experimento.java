package es.ubu.gii.edat.s10;

import es.ubu.gii.edat.datos.GeneradorEnteros;
import java.util.List;

/**
 * Clase Experimento. Ejecuta automáticamente una batería de pruebas con
 * diferentes tamaños de datos.
 * 
 * Compara los tiempos de ejecución de distintos métodos para encontrar el
 * elemento máximo.
 * 
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 */
public class Experimento {
	/**
	 * Array con los diferentes tamaños de colección que se probarán.
	 */
	private static final int[] tamanos = { 10, 100, 1000, 1_000_000, 2_000_000, 2_500_000, 5_000_000, 6_000_000,
			6_500_000, 7_000_000 };

	/**
	 * Método principal que ejecuta las pruebas para cada tamaño definido en
	 * {@link #tamanos}.
	 * 
	 * @param args argumentos de línea de comandos (no usados).
	 */
	public static void main(String[] args) {
		for (int n : tamanos) {
			System.out.println("\n===============================");
			System.out.println("Tamaño de la colección: " + n);
			System.out.println("===============================");

			List<Integer> lista = GeneradorEnteros.listaAleatoria(n);

			MaxElementCollection<Integer> coleccion = new MaxElementCollection<>();
			coleccion.addAll(lista);

			long t0_i = System.currentTimeMillis();
			coleccion.findMaxElementBySorting();
			long t0_f = System.currentTimeMillis();
			System.out.println("Tiempo Sorting (lista): " + (t0_f - t0_i) + " ms");

			long t1_i = System.currentTimeMillis();
			coleccion.findMaxElement();
			long t1_f = System.currentTimeMillis();
			System.out.println("Tiempo Iteración (lista): " + (t1_f - t1_i) + " ms");

			long t2_i = System.currentTimeMillis();
			coleccion.findMaxElementWithPriorityQueue();
			long t2_f = System.currentTimeMillis();
			System.out.println("Tiempo PriorityQueue (lista): " + (t2_f - t2_i) + " ms");

			MaxElementCollectionPQ<Integer> pqCollection = new MaxElementCollectionPQ<>();
			pqCollection.addAll(lista);

			long t3_i = System.currentTimeMillis();
			pqCollection.findMaxElement();
			long t3_f = System.currentTimeMillis();
			System.out.println("Tiempo Iteración (PQ): " + (t3_f - t3_i) + " ms");

			long t4_i = System.currentTimeMillis();
			pqCollection.findMaxElementInPriorityQueue();
			long t4_f = System.currentTimeMillis();
			System.out.println("Tiempo peek() (PQ): " + (t4_f - t4_i) + " ms");
		}
	}
}