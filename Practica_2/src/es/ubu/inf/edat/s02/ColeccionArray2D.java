package es.ubu.inf.edat.pr02;


import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ColeccionArray2D<E> extends AbstractCollection<E> {

	// TODO completar con los atributos que se necesiten para almacenar los datos como se pide en el enunciado. 
	
	public ColeccionArray2D(E[][] contenido) {
		// TODO Instanciar la clase e inicializar contenedores de datos
	}

	public E set (int posiciÛn, E dato) {
		// TODO Completar el metodo para modificar el contenido de una posiciÛn del array 2D
	}

	private class Iterator2D implements Iterator<E> {

		// TODO completar con los atributos que se necesiten para almacenar los datos como se pide en el enunciado. 

		@Override
		public boolean hasNext() {
			// TODO Completar el c√≥digo del iterador
		}

		@Override
		public E next() {
			// TODO Completar el c√≥digo del iterador
		}

		@Override
		public void remove() {
			// TODO Completar el c√≥digo del iterador
		}

	}
}
