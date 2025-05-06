package es.ubu.gii.edat.s04;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * Clase para la prueba de la complejidad algorítmica del ejercicio de listas.
 * 
 * @author bbaruque
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 */
public class TestRendimientoSeleccionListas {

	/**
	 * Tamaño máximo de la lista para las pruebas de rendimiento.
	 */
	private int maxSize = 100000;

	/**
	 * Lista de enteros utilizada para las pruebas de rendimiento.
	 */
	private List<Integer> l = null;

	/**
	 * Lista de listas de enteros utilizada para almacenar resultados de
	 * particiones.
	 */
	private List<List<Integer>> sel = new ArrayList<>();

	/**
	 * Instancia de la clase SeleccionListas para ejecutar los métodos de selección
	 * y partición.
	 */
	SeleccionListas<Integer> selector = new SeleccionListas();

	/**
	 * Inicializa una lista aleatoria con un número determinado de elementos.
	 * 
	 * @param Max Número de elementos a incluir en la lista.
	 */
	private void before(int Max) {
		l = listaAleatoria(Max);
	}

	/**
	 * Método para vaciar la lista. Se emplea para prepararla para la siguiente
	 * prueba.
	 */
	private void after() {
		l.clear();
	}

	/**
	 * Genera un array de números enteros aleatorios dentro del rango [0, Max-1].
	 * Los números pueden repetirse.
	 * 
	 * @param Max Cantidad de elementos del array y el límite superior (exclusivo).
	 * @return Un array de enteros aleatorios en el rango [0, Max-1].
	 */
	private int[] arrayAleatorio(int Max) {
		int[] array = new int[Max];

		for (int j = 0; j < Max; j++)
			array[j] = (int) Math.round(Math.random() * Max);

		return array;
	}

	/**
	 * Genera una lista de números enteros aleatorios con valores en el rango [0,
	 * Max].
	 * 
	 * @param Max Número de elementos a incluir en la lista.
	 * @return Lista de enteros generada aleatoriamente.
	 */
	private List<Integer> listaAleatoria(int Max) {
		List<Integer> lista = new LinkedList<Integer>();

		for (int j = 0; j < Max; j++)
			lista.add((int) Math.round(Math.random() * Max));

		return lista;
	}

	/**
	 * Mide el rendimiento del método de selección múltiple en una lista.
	 * 
	 * @param Max Número de elementos a incluir en la lista de prueba.
	 */
	private void testSeleccionMultiple(int Max) {
		int[] seleccionados = arrayAleatorio(Max / 2);

		long ini = System.currentTimeMillis();
		List<Integer> s = selector.seleccionMultiple(l, seleccionados);
		long fin = System.currentTimeMillis();

		long elapsed = fin - ini;

		System.out.println("SeleccionMultiple," + Max + "," + elapsed);
	}

	/**
	 * Mide el rendimiento del método de selección inversa múltiple en una lista con
	 * la primera implementación.
	 * 
	 * @param Max Número de elementos a incluir en la lista de prueba.
	 */
	private void testSeleccionInversaMultiple_1(int Max) {
		int[] eliminados = arrayAleatorio(Max / 2);

		long ini = System.currentTimeMillis();
		List<Integer> s = selector.seleccionInversaMultiple_1(l, eliminados);
		long fin = System.currentTimeMillis();

		long elapsed = fin - ini;

		System.out.println("SeleccionInversaMultiple en la primera implementación," + Max + "," + elapsed);
	}
	
	/**
	 * Mide el rendimiento del método de selección inversa múltiple en una lista con
	 * la segunda implementación.
	 * 
	 * @param Max Número de elementos a incluir en la lista de prueba.
	 */
	private void testSeleccionInversaMultiple_2(int Max) {
		int[] eliminados = arrayAleatorio(Max / 2);

		long ini = System.currentTimeMillis();
		List<Integer> s = selector.seleccionInversaMultiple_2(l, eliminados);
		long fin = System.currentTimeMillis();

		long elapsed = fin - ini;

		System.out.println("SeleccionInversaMultiple en la segunda implementación," + Max + "," + elapsed);
	}
	
	/**
	 * Mide el rendimiento del método de partición de una lista en múltiples
	 * subconjuntos.
	 * 
	 * @param Max Número de elementos a incluir en la lista de prueba.
	 */
	private void testParticion(int Max) {
		int[] destino = arrayAleatorio(Max);

		long ini = System.currentTimeMillis();
		List<List<Integer>> s = selector.particion(l, destino);
		long fin = System.currentTimeMillis();

		long elapsed = fin - ini;

		System.out.println("Particion," + Max + "," + elapsed);
	}

	/**
	 * Ejecuta una serie de pruebas de rendimiento con diferentes tamaños de lista.
	 */
	@Test
	public void ejecutaTests() {
		for (int i = 0; i <= 10; i++) {
			int Max = (maxSize / 10) * i;

			before(Max);
			testSeleccionMultiple(Max);
			after();

			before(Max);
			testSeleccionInversaMultiple_1(Max);
			after();
			
			before(Max);
			testSeleccionInversaMultiple_2(Max);
			after();
			
			before(Max);
			testParticion(Max);
			after();

			System.out.print("\n");
		}
	}
}