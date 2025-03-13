package es.ubu.gii.edat.pract1;

import es.ubu.gii.edat.datos.GeneradorEnteros;
import java.util.Scanner;

/**
 * Clase Experimento. Permite introducir el número de valores y comprobar cuánto
 * tiempo dura en ordenadorlos con ambos métodos de ordenación.
 * 
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 */
public class Experimento {
	/**
	 * Método principal que ejecuta el experimento de comparación de tiempos de
	 * búsqueda del máximo utilizando dos métodos.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Try-with-resources para cerrar automáticamente el Scanner
		try (Scanner escaner = new Scanner(System.in)) {
			while (true) {
				System.out.println("Dame el número de elementos a comparar:");
				int datos = escaner.nextInt();

				MaxElementCollection<Integer> coleccion = new MaxElementCollection<>();
				coleccion.rellenarLista(GeneradorEnteros.listaAleatoria(datos));
				long t0_i = System.currentTimeMillis();
				coleccion.findMaxElementBySorting();
				long t0_f = System.currentTimeMillis();
				System.out.println("Tiempo de buscar mayor por Sorting en milisegundos: " + (t0_f - t0_i));

				long t1_i = System.currentTimeMillis();
				coleccion.findMaxElement();
				long t1_f = System.currentTimeMillis();
				System.out.println("Tiempo de buscar mayor en milisegundos: " + (t1_f - t1_i));

				System.out.println("Quieres seguir evaluando  Y/N");
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
