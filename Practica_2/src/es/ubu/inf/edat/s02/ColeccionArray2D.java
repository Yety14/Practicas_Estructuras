package es.ubu.inf.edat.s02;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase que representa una colección de elementos almacenados en una matriz
 * bidimensional.
 * 
 * También proporciona un iterador para recorrer los valores dentro del rango.
 * 
 * @param <E> el tipo de elementos que contiene la colección.
 * 
 * @author bbaruque
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 */

public class ColeccionArray2D<E> extends AbstractCollection<E> {

	/** Matriz bidimensional que almacena los elementos. */
	public E[][] array;

	/**
	 * Constructor que inicializa la matriz bidimensional con la dada.
	 *
	 * @param array La matriz bidimensional que se utilizará para almacenar los
	 *              elementos.
	 */
	public ColeccionArray2D(E[][] array) {
		this.array = array;
	}

	/**
	 * Establece un valor en una posición específica de la matriz y devuelve el
	 * valor que se encontraba en la posición anteriromente.
	 *
	 * @param posicion La posición lineal dentro de la matriz.
	 * @param dato     El dato a almacenar en la posición especificada.
	 * @return El valor anterior que estaba en la posición indicada.
	 */
	public E set(int posicion, E dato) {
		int filas = array.length;
		int columnas = array[0].length;

		if (posicion < 0 || posicion >= filas * columnas) {
			throw new IndexOutOfBoundsException("Fuera de rango");
		}

		int fila = posicion / columnas;
		int col = posicion % columnas;

		E valorAnterior = array[fila][col];
		array[fila][col] = dato;
		return valorAnterior;
	}

	/**
	 * Devuelve un iterador sobre los elementos de la matriz.
	 *
	 * @return Un iterador sobre los elementos de la matriz.
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterator2D();
	}

	/**
	 * Devuelve el número de elementos en la matriz.
	 *
	 * @return El número de elementos en la matriz.
	 */
	@Override
	public int size() {
		return (array.length * array[0].length);
	}

	/**
	 * Implementación de un iterador que recorre la matriz bidimensional.
	 */
	private class Iterator2D implements Iterator<E> {
		/** Fila actual. */
		private int filaActual = 0;
		/** Columna actual. */
		private int columnaActual = 0;
		/** Flag para controlar si se ha llamado a next(). */
		private boolean nextCalled = false;

		/**
		 * Verifica si hay más elementos en la matriz o no.
		 *
		 * @return si hay más elementos en la colección o no.
		 */
		@Override
		public boolean hasNext() {
			while (filaActual < array.length) {
				if (columnaActual < array[filaActual].length) {
					return true;
				}
				filaActual++;
				columnaActual = 0;
			}
			return false;
		}

		/**
		 * Devuelve el siguiente elemento de la matriz.
		 *
		 * @return El siguiente elemento en la matriz.
		 * @throws NoSuchElementException Si no hay más elementos.
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E elemento = array[filaActual][columnaActual];
			columnaActual++;
			nextCalled = true; // Marcar que se ha llamado a next()
			return elemento;
		}

		/**
		 * Elimina el último elemento devuelto por el iterador. Debe llamarse después de
		 * next().
		 *
		 * @throws IllegalStateException Si no se ha llamado antes a next().
		 */
		@Override
		public void remove() {
			if (!nextCalled) {
				throw new IllegalStateException("next() debe ser llamado antes de remove()");
			}
			int colAnterior = columnaActual - 1;
			int filaAnterior = filaActual;

			if (colAnterior < 0) {
				filaAnterior--;
				colAnterior = array[filaAnterior].length - 1;
			}

			array[filaAnterior][colAnterior] = null;
			nextCalled = false; // Reiniciar el flag
		}
	}
}