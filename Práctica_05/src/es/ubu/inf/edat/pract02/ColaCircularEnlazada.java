package es.ubu.inf.edat.pract02;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase que implementa una cola circular doblemente enlazada. Permite almacenar
 * elementos en un orden FIFO (First In, First Out), con la particularidad de
 * que la cola es circular y los elementos pueden ser recorridos de manera
 * continua.
 *
 * @param <T> el tipo de elementos almacenados en la cola.
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 */
public class ColaCircularEnlazada<T> extends AbstractQueue<T> {

	/**
	 * Referencia al primer nodo de la cola.
	 */
	private Nodo inicial = null;

	/**
	 * Referencia al último nodo de la cola.
	 */
	private Nodo ultimo = null;

	/**
	 * Tamaño actual de la cola.
	 */
	private int size = 0;

	/**
	 * Elimina todos los elementos de la cola, dejándola vacía.
	 */
	@Override
	public void clear() {
		inicial = null;
		ultimo = null;
		size = 0;
	}

	/**
	 * Devuelve el elemento al frente de la cola sin eliminarlo.
	 *
	 * @return el elemento al frente de la cola.
	 * @throws NoSuchElementException si la cola está vacía.
	 */
	@Override
	public T element() {
		if (this.size == 0) {
			throw new NoSuchElementException("La cola está vacía");
		}
		return inicial.objeto;
	}

	/**
	 * Verifica si la cola contiene un elemento específico.
	 *
	 * @param e el elemento a buscar en la cola.
	 * @return {@code true} si el elemento está en la cola, {@code false} en caso
	 *         contrario.
	 * @throws NoSuchElementException si el elemento a buscar es nulo o la cola está
	 *                                vacía.
	 */
	@Override
	public boolean contains(Object e) {
		if (inicial == null || e == null) {
			throw new NoSuchElementException("Objeto a buscar nulos o cola vacía");
		}
		Nodo actual = inicial;
		do {
			if (e.equals(actual.objeto)) {
				return true;
			}
			actual = actual.siguiente;
		} while (actual != inicial);
		return false;
	}

	/**
	 * Devuelve un iterador circular que recorre la cola de manera continua.
	 *
	 * @return un iterador circular sobre los elementos de la cola.
	 */
	public Iterator<T> circularIterator() {
		return new Iterator<T>() {
			private Nodo actual = inicial;

			@Override
			public boolean hasNext() {
				return true;
			}

			@Override
			public T next() {
				if (actual == null) {
					throw new NoSuchElementException();
				}
				T data = actual.objeto;
				actual = actual.siguiente;
				return data;
			}
		};
	}

	/**
	 * Clase interna que representa un nodo de la cola circular doblemente enlazada.
	 */
	private class Nodo {
		/**
		 * El elemento almacenado en el nodo.
		 */
		private T objeto;

		/**
		 * Referencia al siguiente nodo.
		 */
		private Nodo siguiente;

		/**
		 * Referencia al nodo anterior.
		 */
		private Nodo anterior;

		/**
		 * Constructor que inicializa un nodo con un elemento específico.
		 *
		 * @param objeto el elemento a almacenar en el nodo.
		 */
		public Nodo(T objeto) {
			this.objeto = objeto;
			this.siguiente = null;
			this.anterior = null;
		}
	}

	/**
	 * Inserta un elemento en la cola.
	 *
	 * @param e el elemento a insertar.
	 * @return {@code true} si el elemento se insertó correctamente.
	 * @throws NullPointerException si el elemento a insertar es nulo.
	 */
	@Override
	public boolean offer(T e) {
		if (e == null) {
			throw new NullPointerException("No se permiten elementos nulos");
		}

		Nodo nuevo = new Nodo(e);
		if (inicial == null) {
			inicial = nuevo;
			ultimo = nuevo;
			inicial.siguiente = inicial;
			inicial.anterior = inicial;
		} else {
			nuevo.anterior = ultimo;
			nuevo.siguiente = inicial;
			ultimo.siguiente = nuevo;
			inicial.anterior = nuevo;
			ultimo = nuevo;
		}
		size++;
		return true;
	}

	/**
	 * Elimina y devuelve el elemento al frente de la cola.
	 *
	 * @return el elemento al frente de la cola, o {@code null} si la cola está
	 *         vacía.
	 */
	@Override
	public T poll() {
		if (isEmpty()) {
			return null;
		}
		T data = inicial.objeto;
		if (inicial == ultimo) {
			inicial = null;
			ultimo = null;
		} else {
			inicial = inicial.siguiente;
			inicial.anterior = ultimo;
			ultimo.siguiente = inicial;
		}
		size--;
		return data;
	}

	/**
	 * Devuelve el elemento al frente de la cola sin eliminarlo.
	 *
	 * @return el elemento al frente de la cola, o {@code null} si la cola está
	 *         vacía.
	 */
	@Override
	public T peek() {
		return (inicial == null) ? null : inicial.objeto;
	}

	/**
	 * Devuelve un iterador sobre los elementos de la cola.
	 *
	 * @return un iterador sobre los elementos de la cola.
	 */
	@Override
	public Iterator<T> iterator() {
		return new CircularIterator();
	}

	/**
	 * Devuelve el número de elementos en la cola.
	 *
	 * @return el tamaño de la cola.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Clase interna que implementa un iterador para la cola circular.
	 */
	private class CircularIterator implements Iterator<T> {
		/**
		 * Nodo actual en la iteración.
		 */
		private Nodo actual = inicial;

		/**
		 * Indica si es la primera pasada.
		 */
		private boolean primeraVuelta = true;

		/**
		 * Último nodo devuelto por next().
		 */
		private Nodo ultimoDevuelto = null;

		/**
		 * Verifica si hay más elementos en la iteración.
		 *
		 * @return {@code true} si hay más elementos, {@code false} en caso contrario.
		 */
		@Override
		public boolean hasNext() {
			return actual != null && (primeraVuelta || actual != inicial);
		}

		/**
		 * Devuelve el siguiente elemento en la iteración.
		 *
		 * @return el siguiente elemento en la iteración.
		 * @throws NoSuchElementException si no hay más elementos.
		 */
		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			ultimoDevuelto = actual;
			T data = actual.objeto;
			actual = actual.siguiente;
			primeraVuelta = false;
			return data;
		}

		/**
		 * Elimina el último elemento devuelto por el iterador.
		 *
		 * @throws IllegalStateException si no se ha llamado a {@code next()} antes de
		 *                               {@code remove()}.
		 */
		@Override
		public void remove() {
			if (ultimoDevuelto == null) {
				throw new IllegalStateException("Debe llamar a next() antes de remove()");
			}

			if (ultimoDevuelto == inicial) {
				poll(); // Reutiliza el método poll() para eliminar el primer elemento.
			} else {
				ultimoDevuelto.anterior.siguiente = ultimoDevuelto.siguiente;
				ultimoDevuelto.siguiente.anterior = ultimoDevuelto.anterior;
				if (ultimoDevuelto == ultimo) {
					ultimo = ultimoDevuelto.anterior;
				}
				size--;
			}

			ultimoDevuelto = null;
		}
	}
}