package es.ubu.inf.edat.pract02;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ColaCircularEnlazada<T> extends AbstractQueue<T> {

	private Nodo inicial = null;
	private Nodo ultimo = null;
	private int size = 0;

	@Override
	public void clear() {
		inicial = null;
		ultimo = null;
		size = 0;
	}

	@Override
	public T element() {
		if (isEmpty()) {
			throw new NoSuchElementException("La cola está vacía");
		}
		return inicial.objeto;
	}

	@Override
	public boolean contains(Object e) {
	    if (inicial == null) {
	        return false;
	    }
	    Nodo actual = inicial;
	    do {
	        if (actual.objeto.equals(e)) {
	            return true;
	        }
	        actual = actual.siguiente;
	    } while (actual != inicial);
	    return false;
	}


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


	private class Nodo {
		private T objeto;
		private Nodo siguiente;
		private Nodo anterior;

		public Nodo(T objeto) {
			this.objeto = objeto;
			this.siguiente = null;
			this.anterior = null;
		}
	}

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

	@Override
	public T peek() {
		return (inicial == null) ? null : inicial.objeto;
	}

	@Override
	public Iterator<T> iterator() {
		return new CircularIterator();
	}

	@Override
	public int size() {
		return size;
	}

	private class CircularIterator implements Iterator<T> {
		private Nodo actual = inicial;
		private boolean firstPass = true;
		private Nodo lastReturned = null;

		@Override
		public boolean hasNext() {
			return actual != null && (firstPass || actual != inicial);
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			lastReturned = actual;
			T data = actual.objeto;
			actual = actual.siguiente;
			firstPass = false;
			return data;
		}

		@Override
		public void remove() {
			if (lastReturned == null) {
				throw new IllegalStateException("Debe llamar a next() antes de remove()");
			}

			if (lastReturned == inicial) {
				poll(); 
			} else {
				lastReturned.anterior.siguiente = lastReturned.siguiente;
				lastReturned.siguiente.anterior = lastReturned.anterior;
				if (lastReturned == ultimo) {
					ultimo = lastReturned.anterior;
				}
				size--;
			}

			lastReturned = null;
		}
	}
}
