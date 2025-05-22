package es.ubu.gii.edat.datos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Clase GeneradorEnteros para generar listas y arrays de números enteros
 * aleatorios. Proporciona métodos para generar listas con diferentes
 * características, como números únicos, rangos específicos, repeticiones
 * controladas, etc.
 * 
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 */
public class GeneradorEnteros {

	/**
	 * Instancia estática de la clase Random para generar números aleatorios.
	 */
	static Random rand = new Random();

	/**
	 * Genera una lista de números aleatorios entre -tamano y tamano, incluyendo
	 * números negativos.
	 * 
	 * @param tamano Tamaño de la lista a generar.
	 * @return Lista de enteros aleatorios.
	 */
	public static List<Integer> listaAleatoria(int tamano) {
		Integer[] aleatoria = new Integer[tamano];

		for (int i = 0; i < tamano; i++) {
			int aleat = rand.nextInt(tamano);
			if (rand.nextBoolean())
				aleat = -1 * aleat;

			aleatoria[i] = aleat;
		}

		return Arrays.asList(aleatoria);
	}

	/**
	 * Genera una lista de números aleatorios dentro de un rango específico.
	 * 
	 * @param tamano   Tamaño de la lista a generar.
	 * @param inferior Límite inferior del rango.
	 * @param superior Límite superior del rango.
	 * @return Lista de enteros aleatorios dentro del rango especificado.
	 */
	public static List<Integer> listaAleatoria(int tamano, int inferior, int superior) {
		Integer[] aleatorio = new Integer[tamano];
		for (int i = 0; i < tamano; i++) {
			aleatorio[i] = (int) (Math.random() * (superior - inferior));
			aleatorio[i] = aleatorio[i] + inferior;
		}

		return Arrays.asList(aleatorio);
	}

	/**
	 * Genera una lista aleatoria seleccionando elementos de una lista
	 * proporcionada.
	 * 
	 * @param <E>            Tipo de los elementos en la lista.
	 * @param tamano         Tamaño de la lista a generar.
	 * @param seleccionables Lista de elementos disponibles para seleccionar.
	 * @return Lista de elementos seleccionados aleatoriamente.
	 */
	public static <E> List<E> listaAleatoria(int tamano, List<E> seleccionables) {
		List<E> seleccionados = new ArrayList<>(tamano);
		for (int i = 0; i < tamano; i++) {
			E elem = seleccionables.get(rand.nextInt(seleccionables.size()));
			seleccionados.add(elem);
		}
		return seleccionados;
	}

	/**
	 * Genera una lista de números aleatorios con un porcentaje de repeticiones
	 * controlado.
	 * 
	 * @param tamano    Tamaño de la lista a generar.
	 * @param repetidos Porcentaje de repeticiones (valor entre 0 y 1).
	 * @return Lista de enteros aleatorios con repeticiones controladas.
	 */
	public static List<Integer> listaAleatoria(int tamano, float repetidos) {
		Integer[] aleatorio = new Integer[tamano];

		aleatorio[0] = rand.nextInt();

		for (int i = 1; i < tamano; i++) {
			if (Math.random() < repetidos) {
				aleatorio[i] = aleatorio[i - 1];
				continue;
			}

			int aleat = rand.nextInt(tamano);
			if (rand.nextBoolean())
				aleat = -1 * aleat;

			aleatorio[i] = aleat;
		}

		return Arrays.asList(aleatorio);
	}

	/**
	 * Genera una lista de números aleatorios únicos (sin elementos repetidos).
	 * 
	 * @param tamano Tamaño de la lista a generar.
	 * @return Lista de enteros aleatorios únicos.
	 */
	public static List<Integer> listaAleatoriaUnicos(int tamano) {
		List<Integer> lista = new ArrayList<>(tamano);

		for (int i = 0; i < tamano * 1.5; i++) {
			if (Math.random() < 0.5)
				lista.add(-i);
			else
				lista.add(i);
		}

		Collections.shuffle(lista);
		return lista.subList(0, tamano);
	}

	/**
	 * Genera un array de números aleatorios entre -100 y 100, incluyendo números
	 * negativos.
	 * 
	 * @param tamano Tamaño del array a generar.
	 * @return Array de enteros aleatorios.
	 */
	public static int[] arrayAleatorio(int tamano) {
		List<Integer> lista = listaAleatoria(tamano);
		int[] aleatoria = new int[tamano];

		for (int i = 0; i < tamano; i++) {
			aleatoria[i] = lista.get(i);
		}

		return aleatoria;
	}

	/**
	 * Genera un array de números aleatorios dentro de un rango específico.
	 * 
	 * @param tamano Tamaño del array a generar.
	 * @param min    Límite inferior del rango.
	 * @param max    Límite superior del rango.
	 * @return Array de enteros aleatorios dentro del rango especificado.
	 */
	public static int[] arrayAleatorio(int tamano, int min, int max) {
		List<Integer> lista = listaAleatoria(tamano, min, max);
		int[] aleatoria = new int[tamano];

		for (int i = 0; i < tamano; i++) {
			aleatoria[i] = lista.get(i);
		}

		return aleatoria;
	}

	/**
	 * Genera un array de números aleatorios únicos (sin elementos repetidos).
	 * 
	 * @param tamano Tamaño del array a generar.
	 * @return Array de enteros aleatorios únicos.
	 */
	public static int[] arrayAleatorioUnicos(int tamano) {
		List<Integer> lista = listaAleatoriaUnicos(tamano);
		int[] aleatoria = new int[tamano];

		for (int i = 0; i < tamano; i++) {
			aleatoria[i] = lista.get(i);
		}

		return aleatoria;
	}

	/**
	 * Convierte un array de enteros en una cadena de texto.
	 * 
	 * @param <E>   Tipo de los elementos en el array (no utilizado en este método).
	 * @param array Array de enteros a convertir.
	 * @return Cadena de texto que representa el array.
	 */
	public static <E> String toString(int[] array) {
		String s = "[" + array[0];
		for (int i = 1; i < array.length; i++) {
			s = s + ", " + array[i];
		}
		s = s + "]";
		return s;
	}
}