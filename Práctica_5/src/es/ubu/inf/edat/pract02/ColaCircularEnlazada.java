package es.ubu.inf.edat.pract02;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ColaCircularEnlazada<T> extends AbstractQueue<T> {

	private Nodo cabeza = null;
	private Nodo ultimo = null;
	private int size = 0;

	@Override
	public void clear() {
		cabeza = null;
		ultimo = null;
		size = 0;
	}

	@Override
	public T element() {
		if (isEmpty()) {
			throw new NoSuchElementException("La cola está vacía");
		}
		return cabeza.objeto;
	}

	@Override
	public boolean contains(Object e) {
	    if (cabeza == null) {
	        return false;
	    }
	    Nodo actual = cabeza;
	    do {
	        if (actual.objeto.equals(e)) {
	            return true;
	        }
	        actual = actual.siguiente;
	    } while (actual != cabeza);
	    return false;
	}


	public Iterator<T> circularIterator() {
	    return new Iterator<T>() {
	        private Nodo actual = cabeza;

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
		if (cabeza == null) {
			cabeza = nuevo;
			ultimo = nuevo;
			cabeza.siguiente = cabeza;
			cabeza.anterior = cabeza;
		} else {
			nuevo.anterior = ultimo;
			nuevo.siguiente = cabeza;
			ultimo.siguiente = nuevo;
			cabeza.anterior = nuevo;
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
		T data = cabeza.objeto;
		if (cabeza == ultimo) {
			cabeza = null;
			ultimo = null;
		} else {
			cabeza = cabeza.siguiente;
			cabeza.anterior = ultimo;
			ultimo.siguiente = cabeza;
		}
		size--;
		return data;
	}

	@Override
	public T peek() {
		return (cabeza == null) ? null : cabeza.objeto;
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
		private Nodo actual = cabeza;
		private boolean firstPass = true;
		private Nodo lastReturned = null;

		@Override
		public boolean hasNext() {
			return actual != null && (firstPass || actual != cabeza);
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

			if (lastReturned == cabeza) {
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
