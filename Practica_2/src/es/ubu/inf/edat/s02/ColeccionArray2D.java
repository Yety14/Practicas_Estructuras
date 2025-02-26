package es.ubu.inf.edat.s02;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ColeccionArray2D<E> extends AbstractCollection<E> {

	public E[][] array;

	public ColeccionArray2D(E[][] array) {
		this.array = array;
	}

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

	private class Iterator2D implements Iterator<E> {
		Iterator2D iterator = new Iterator2D();
		private int filaActual = 0;
		private int columnaActual = 0;

		@Override
		public boolean hasNext() {
			if (array[filaActual][columnaActual] != null) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public E next() {
			return array[filaActual][columnaActual++];
		}

		@Override
		public void remove() {
			array[filaActual][columnaActual] = null;
		}

	}
}
