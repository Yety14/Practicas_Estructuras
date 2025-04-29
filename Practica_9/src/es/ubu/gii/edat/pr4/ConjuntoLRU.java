package es.ubu.gii.edat.pr4;

import java.util.AbstractSet;
import java.util.SortedSet;
import java.util.Comparator;
import java.util.Iterator;

import es.ubu.gii.edat.utils.cacheLRUEnlazada;

public class ConjuntoLRU<E> extends AbstractSet<E> implements SortedSet<E> {

	private int capacidad;
	private Integer contador;
	private cacheLRUEnlazada<E, Integer> mapa;

	public ConjuntoLRU() {
		throw new UnsupportedOperationException("Constructor por defecto no permitido.");
	}

	public ConjuntoLRU(int maxSize) {
		this.capacidad = maxSize;
		this.mapa = new cacheLRUEnlazada<E, Integer>(capacidad);
		contador = 0;
	}

	@Override
	public boolean add(E e) {
		contador++;
		if (!mapa.containsValue(e)) {

			if (mapa.size() >= capacidad) {
				int min = 0;
				for (int i = 0; i < contador; i++) {
					if (mapa.containsKey(i)) {
						if (i < min) {
							min = i;
						}
					}
				}
				mapa.remove(min);
				mapa.put(e, contador);
			} else {
				mapa.put(e, contador);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object o) {
		if (mapa.containsKey(o)) {
			mapa.remove(o);
			contador--;
			return true;
		}

		return false;
	}

	@Override
	public int size() {
		return mapa.size();
	}

	private E encontrar(int i) {
		Iterator<E> it = iterator();
		int contador = 0;
		while (it.hasNext()) {
			E e = it.next();
			if (contador == i) {
				return e;
			}
			contador++;
		}
		return null;
	}

	@Override
	public E first() {
		Iterator<E> it = iterator();
		if (!it.hasNext())
			throw new IllegalStateException("Conjunto vacío.");
		return it.next(); // Primer accedido (menos reciente)
	}

	@Override
	public E last() {
		Iterator<E> it = iterator();
		E last = null;
		while (it.hasNext()) {
			last = it.next();
		}
		if (last == null)
			throw new IllegalStateException("Conjunto vacío.");
		return last; // Más recientemente accedido
	}

	/*
	 * obtener los elemento que se han accedido por última vez hace más tiempo que
	 * el que se facilita como parámetro
	 */
	@Override
	public SortedSet<E> headSet(E toElement) {
		if (!this.contains(toElement)) {
			throw new IllegalArgumentException("Elemento no presente en el conjunto.");
		}
		SortedSet<E> subConjunto = new ConjuntoLRU<>(this.capacidad);
		E actual = encontrar(inicial);
		Iterator<E> iterador = this.iterator();
		while (iterador.hasNext()) {
			if (actual == toElement) {
				break;
			}
			subConjunto.add(actual);
			actual = iterador.next();
		}
		return subConjunto;
	}

	/*
	 * obtener los elemento que se han accedido por última vez hace menos tiempo que
	 * el que se facilita como parámetro
	 */
	@Override
	public SortedSet<E> tailSet(E toElement) {
		if (!this.contains(toElement)) {
			throw new IllegalArgumentException("Elemento no presente en el conjunto.");
		}
		SortedSet<E> subConjunto = new ConjuntoLRU<>(this.capacidad);
		E actual = encontrar(fin);
		Iterator<E> iterador = this.iterator();
		while (iterador.hasNext()) {
			if (actual == toElement) {
				break;
			}
			subConjunto.add(actual);
			actual = iterador.next();
		}
		return subConjunto;
	}

	@Override
	public SortedSet<E> subSet(E a, E b) {
		if (!this.contains(a) || !this.contains(b)) {
			throw new IllegalArgumentException("Elemento no presente en el conjunto.");
		}
		SortedSet<E> subConjunto = new ConjuntoLRU<>(this.capacidad);
		Iterator<E> iterador = this.iterator();
		while (iterador.hasNext()) {
			E actual = iterador.next();
			if (actual.equals(a) && actual.equals(b)) {
				break;
			}
			subConjunto.add(actual);
		}
		return subConjunto;
	}

	@Override
	public Comparator<? super E> comparator() {
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		return mapa.values().iterator();
	}

}
