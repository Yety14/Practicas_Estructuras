package es.ubu.inf.edat.s02;

import java.util.AbstractCollection;
import java.util.Iterator;

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

	@Override
	public Iterator<E> iterator() {
		return new Iterator2D();
	}

	@Override
	public int size() {
		return (array.length * array[0].length);
	}

	private class Iterator2D implements Iterator<E> {
		private int filaActual = 0;
		private int columnaActual = 0;

		@Override
		public boolean hasNext() {
			while (filaActual < array.length) {
				if (columnaActual < array[filaActual].length) {
					return true;
				}
			}
			return false;

		}

		@Override
		public E next() {
			if (columnaActual >= array[filaActual].length) {
				filaActual++;
				columnaActual = 0;
			}
			return array[filaActual][columnaActual++];
		}

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
