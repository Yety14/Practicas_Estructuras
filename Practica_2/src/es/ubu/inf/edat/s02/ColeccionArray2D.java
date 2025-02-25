package es.ubu.inf.edat.s02;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ColeccionArray2D<E> extends AbstractCollection<E> {

	public E[][] array;// TODO completar con los atributos que se necesiten para almacenar los datos
						// como se pide en el enunciado.

	public ColeccionArray2D(E[][] array) {
		for (int i = 0; i <= array.length; i++) {
			this.array = array;
		}
	}

	public E set(int posicion, E dato) {
		int fila = 0;
		int col = 0;// hacer como si fuese todo una línea
		int columnas = array[0].length;// El número de columnas

		fila = posicion / columnas;
		col = posicion % columnas;

		if (posicion >= 0 && posicion <= array.length) {
			array[fila][col] = dato;
			return array[fila][col];
		} else {
			throw new IndexOutOfBoundsException("Fuera de rango");
		}
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
