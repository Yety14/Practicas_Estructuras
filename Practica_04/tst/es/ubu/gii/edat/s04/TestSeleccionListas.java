package es.ubu.gii.edat.s04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Clase de pruebas unitarias para la clase SeleccionListas. Contiene métodos
 * para probar la funcionalidad de selección múltiple, selección inversa y
 * partición de listas.
 */
public class TestSeleccionListas {

	/**
	 * Instancia estática de la clase SeleccionListas para ejecutar los métodos de
	 * prueba.
	 */
	static SeleccionListas seleccionListas = new SeleccionListas();

	/**
	 * Prueba la selección múltiple de elementos en una lista. Verifica si los
	 * elementos seleccionados coinciden con los esperados.
	 */
	@Test
	public void pruebaSelMul() {
		Integer[] enteros = { 1, 2, 3, 4, 5, 6 };
		int[] seleccionados = { 0, 3, 5 }; // Índices a seleccionar

		List<Integer> lista = Arrays.asList(enteros);
		List<Integer> elegidos = seleccionListas.seleccionMultiple(lista, seleccionados);

		Integer[] respuesta = { 1, 4, 6 }; // Resultado esperado
		List<Integer> esperados = Arrays.asList(respuesta);

		System.out.println("esperados: " + esperados);
		System.out.println("elegidos: " + elegidos);

		assertEquals(3, elegidos.size());
		assertEquals(esperados, elegidos);
	}

	/**
	 * Prueba la selección múltiple con un índice fuera del rango inicial (-1). Se
	 * espera que se lance una excepción IndexOutOfBoundsException.
	 */
	@Test
	public void pruebaSelMul_outIni() {
		Integer[] enteros = { 1, 2, 3, 4, 5, 6 };
		int[] seleccionados = { -1, 3, 5 }; // Índice -1 no válido

		List<Integer> lista = Arrays.asList(enteros);

		try {
			List<Integer> elegidos = seleccionListas.seleccionMultiple(lista, seleccionados);
		} catch (IndexOutOfBoundsException iob) {
			assertTrue("IndexOutOfBoundsException", true);
			return;
		}

		fail("Se esperaba IndexOutOfBoundsException debido a índice negativo.");
	}

	/**
	 * Prueba la selección múltiple con un índice fuera del rango final. Se espera
	 * que se lance una excepción IndexOutOfBoundsException.
	 */
	@Test
	public void pruebaSelMul_outFin() {
		Integer[] enteros = { 1, 2, 3, 4, 5, 6 };
		int[] seleccionados = { 0, 3, 25 }; // Índice 25 fuera del tamaño de la lista

		List<Integer> lista = Arrays.asList(enteros);

		try {
			List<Integer> elegidos = seleccionListas.seleccionMultiple(lista, seleccionados);
		} catch (IndexOutOfBoundsException iob) {
			assertTrue("IndexOutOfBoundsException", true);
			return;
		}

		fail("Se esperaba IndexOutOfBoundsException debido a índice fuera de rango.");
	}

	/**
	 * Prueba la selección múltiple en una lista vacía. Se espera que se lance una
	 * excepción IndexOutOfBoundsException.
	 */
	@Test
	public void pruebaSelMul_vacia() {
		int[] seleccionados = { 0, 3, 5 };
		List<Integer> lista = new ArrayList<>(); // Lista vacía

		try {
			List<Integer> elegidos = seleccionListas.seleccionMultiple(lista, seleccionados);
		} catch (IndexOutOfBoundsException iob) {
			assertTrue("IndexOutOfBoundsException", true);
			return;
		}

		fail("Se esperaba IndexOutOfBoundsException para una lista vacía.");
	}

	/**
	 * Prueba la selección múltiple en una lista nula. Se espera que se lance una
	 * excepción NoSuchElementException.
	 */
	@Test
	public void pruebaSelMul_nula() {
		int[] seleccionados = { 0, 3, 5 };
		List<Integer> lista = null; // Lista nula

		try {
			List<Integer> elegidos = seleccionListas.seleccionMultiple(lista, seleccionados);
		} catch (NoSuchElementException nse) {
			assertTrue("NoSuchElementException", true);
			return;
		}

		fail("Se esperaba NoSuchElementException para una lista nula.");
	}

	/**
	 * Prueba la selección inversa eliminando ciertos elementos de una lista.
	 * Verifica si los elementos restantes coinciden con los esperados.
	 */
	@Test
	public void pruebaSelInv() {
		Integer[] enteros = { 1, 2, 3, 4, 5, 6 };
		int[] deseleccionados = { 0, 3, 5 }; // Índices a eliminar

		List<Integer> lista = Arrays.asList(enteros);
		List<Integer> elegidos_1 = seleccionListas.seleccionInversaMultiple_1(lista, deseleccionados);
		List<Integer> elegidos_2 = seleccionListas.seleccionInversaMultiple_1(lista, deseleccionados);

		Integer[] respuesta = { 2, 3, 5 }; // Lista esperada después de eliminar elementos
		List<Integer> esperados = Arrays.asList(respuesta);

		System.out.println("esperados: " + esperados);
		System.out.println("elegidos: " + elegidos_1);
		System.out.println("elegidos: " + elegidos_2);

		assertEquals(3, elegidos_1.size());
		assertEquals(3, elegidos_2.size());

		assertEquals(esperados, elegidos_1);
		assertEquals(esperados, elegidos_2);
}

	/**
	 * Prueba la partición de una lista en sublistas según un array de categorías.
	 * Verifica si las sublistas generadas coinciden con las esperadas.
	 */
	@Test
	public void pruebaParticion() {
		Integer[] enteros = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] particion = { 0, 0, 1, 1, 0, 2, 1, 2, 2 }; // Índices de partición

		List<Integer> lista = Arrays.asList(enteros);
		List<List<Integer>> elegidos = seleccionListas.particion(lista, particion);

		// Primera partición esperada
		Integer[] respuesta0 = { 1, 2, 5 };
		List<Integer> esperados0 = Arrays.asList(respuesta0);
		System.out.println("P0 - esperados: " + esperados0);
		System.out.println("P0 - elegidos: " + elegidos.get(0));
		assertEquals(3, elegidos.get(0).size());
		assertEquals(esperados0, elegidos.get(0));

		// Segunda partición esperada
		Integer[] respuesta1 = { 3, 4, 7 };
		List<Integer> esperados1 = Arrays.asList(respuesta1);
		System.out.println("P1 - esperados: " + esperados1);
		System.out.println("P1 - elegidos: " + elegidos.get(1));
		assertEquals(3, elegidos.get(1).size());
		assertEquals(esperados1, elegidos.get(1));

		// Tercera partición esperada
		Integer[] respuesta2 = { 6, 8, 9 };
		List<Integer> esperados2 = Arrays.asList(respuesta2);
		System.out.println("P2 - esperados: " + esperados2);
		System.out.println("P2 - elegidos: " + elegidos.get(2));
		assertEquals(3, elegidos.get(2).size());
		assertEquals(esperados2, elegidos.get(2));
	}
}