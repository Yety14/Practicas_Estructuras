package es.ubu.gii.edat.pr4;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

import es.ubu.gii.edat.utils.cacheLRUEnlazada;

public class ConjuntoLRU<E> extends AbstractSet<E> implements SortedSet<E> {

	private static final long serialVersionUID = 1L;

	protected int capacidad;
	protected int contador;

	protected cacheLRUEnlazada<E, E> mapa;

	public ConjuntoLRU(int maxSize) {
		super();
		this.capacidad = maxSize;
		this.mapa = new cacheLRUEnlazada<E, E>(capacidad);
	}

	@Override
	public boolean add(E e) {
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
			}
		}
		if (mapa.containsKey(e)) {
			return false;
		}

		mapa.put(e, e);

		return true;
	}

	@Override
	public boolean remove(Object o) {
		if (mapa.containsKey(o)) {
			mapa.remove(o);
			return true;
		}

		return false;
	}

	@Override
	public int size() {
		return mapa.size();
	}

	@Override
	public SortedSet<E> subSet(E a, E b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E first() {
		return null;
	}

	@Override
	public E last() {
		return null;
	}

	@Override
	public SortedSet<E> tailSet(E fromElement) {
		return null;
	}

	@Override
	public Comparator<? super E> comparator() {
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<E> headSet(E toElement) {
		// TODO Auto-generated method stub
		return null;
	}

}
