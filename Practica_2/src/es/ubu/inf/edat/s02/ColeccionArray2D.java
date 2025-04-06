package es.ubu.inf.edat.s02;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase que representa una colección de elementos genéricos almacenados en una
 * matriz bidimensional. Proporciona métodos para gestionar los elementos, como
 * establecer valores, obtener el tamaño y recorrerlos con un iterador.
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

	/** Matriz bidimensional genérica que almacena los elementos de la colección. */
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
	 * Establece un valor en una posición específica de la matriz bidimensional y
	 * devuelve el valor previamente almacenado en esa posición.
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
	 * Devuelve un iterador para recorrer los elementos de la matriz bidimensional.
	 *
	 * @return Un iterador sobre los elementos de la matriz.
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterator2D();
	}

	/**
	 * Devuelve el número total de elementos almacenados en la matriz bidimensional.
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

		/**
		 * Verifica si hay más elementos en la matriz bidimensional desde la posición
		 * actual del iterador.
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
		 * Devuelve el siguiente elemento de la matriz bidimensional en el recorrido del
		 * iterador.
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
			return elemento;
		}

		/**
		 * Elimina el último elemento devuelto por el iterador. Este método debe
		 * invocarse después de haber llamado a next().
		 *
		 * @throws IllegalStateException Si no se ha llamado antes a next().
		 */
		@Override
		public void remove() {
			int colAnterior = columnaActual - 1;
			int filaAnterior = filaActual;

			if (colAnterior < 0) {
				filaAnterior--;
				colAnterior = array[filaAnterior].length - 1;
			}

			array[filaAnterior][colAnterior] = null;
		}
	}
}
