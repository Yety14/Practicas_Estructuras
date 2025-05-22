package es.ubu.gii.edat.s10;

import es.ubu.gii.edat.datos.GeneradorEnteros;
import java.util.Scanner;

/**
 * Clase Experimento. Permite introducir un número de valores y medir el tiempo
 * de ejecución de dos métodos para encontrar el máximo.
 * 
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 */
public class Experimento {
    /**
     * Método principal que ejecuta un experimento para comparar el tiempo de
     * ejecución de dos métodos de búsqueda del máximo:
     * <ul>
     * <li>{@code findMaxElementBySorting()}: ordena la colección antes de encontrar el máximo.</li>
     * <li>{@code findMaxElement()}: encuentra el máximo iterando sobre la colección.</li>
     * </ul>
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
	public static void main(String[] args) {
	    try (Scanner escaner = new Scanner(System.in)) {
	        while (true) {
	            System.out.println("Dame el número de elementos a comparar:");
	            int datos = escaner.nextInt();

	            var lista = GeneradorEnteros.listaAleatoria(datos);

	            // MaxElementCollection
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

	            // MaxElementCollectionPQ
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

	            System.out.println("¿Quieres seguir evaluando? Y/N");
	            String letra = escaner.next();
	            if (!letra.equalsIgnoreCase("Y")) {
	                break;
	            }
	        }
	    } catch (Exception e) {
	        System.err.println("Error: Entrada no válida.");
	        e.printStackTrace();
	    }
	}

}
