package es.ubu.gii.edat.pr4;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map.Entry;

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

		if (mapa.containsKey(e)) {
			mapa.put(e, contador);
			return false; 
		} else {
			if (mapa.size() >= capacidad) {
				eliminarMenosUsado();
			}
			mapa.put(e, contador);
			return true;
		}
	}

	private void eliminarMenosUsado() {
		E menosUsado = null;
		int minAcceso = Integer.MAX_VALUE;

		for (Entry<E, Integer> entry : mapa.entrySet()) {
			if (entry.getValue() < minAcceso) {
				minAcceso = entry.getValue();
				menosUsado = entry.getKey();
			}
		}

		if (menosUsado != null) {
			mapa.remove(menosUsado);
		}
	}

	@Override
	public boolean remove(Object o) {
		return mapa.remove(o) != null;
	}

	@Override
	public int size() {
		return mapa.size();
	}

	@Override
	public E first() {
		if (mapa.isEmpty())
			throw new IllegalStateException("Conjunto vacío.");

		E menosUsado = null;
		int minAcceso = Integer.MAX_VALUE;

		for (Entry<E, Integer> entry : mapa.entrySet()) {
			if (entry.getValue() < minAcceso) {
				minAcceso = entry.getValue();
				menosUsado = entry.getKey();
			}
		}
		return menosUsado;
	}

	@Override
	public E last() {
		if (mapa.isEmpty())
			throw new IllegalStateException("Conjunto vacío.");

		E masUsado = null;
		int maxAcceso = Integer.MIN_VALUE;

		for (Entry<E, Integer> entry : mapa.entrySet()) {
			if (entry.getValue() > maxAcceso) {
				maxAcceso = entry.getValue();
				masUsado = entry.getKey();
			}
		}
		return masUsado;
	}

	/*
	 * obtener los elemento que se han accedido por última vez hace más tiempo que
	 * el que se facilita como parámetro
	 */
	@Override
	public SortedSet<E> headSet(E toElement) {
		if (!mapa.containsKey(toElement))
			throw new IllegalArgumentException("Elemento no presente en el conjunto.");

		int toAccess = mapa.get(toElement);
		SortedSet<E> subconjunto = new ConjuntoLRU<>(this.capacidad);

		for (Entry<E, Integer> entry : mapa.entrySet()) {
			if (entry.getValue() < toAccess) {
				subconjunto.add(entry.getKey());
			}
		}
		return subconjunto;
	}

	/*
	 * obtener los elemento que se han accedido por última vez hace menos tiempo que
	 * el que se facilita como parámetro
	 */
	@Override
	public SortedSet<E> tailSet(E fromElement) {
		if (!mapa.containsKey(fromElement))
			throw new IllegalArgumentException("Elemento no presente en el conjunto.");

		int fromAccess = mapa.get(fromElement);
		SortedSet<E> subconjunto = new ConjuntoLRU<>(this.capacidad);

		for (Entry<E, Integer> entry : mapa.entrySet()) {
			if (entry.getValue() >= fromAccess) {
				subconjunto.add(entry.getKey());
			}
		}
		return subconjunto;
	}

	@Override
	public SortedSet<E> subSet(E desde, E hasta) {
		if (!mapa.containsKey(desde) || !mapa.containsKey(hasta))
			throw new IllegalArgumentException("Alguno de los elementos no está en el conjunto.");

		int accesoDesde = mapa.get(desde);
		int accesoHasta = mapa.get(hasta);

		if (accesoDesde > accesoHasta) {
			throw new IllegalArgumentException("El acceso de 'desde' debe ser anterior o igual al de 'hasta'.");
		}

		SortedSet<E> subconjunto = new ConjuntoLRU<>(this.capacidad);

		for (Entry<E, Integer> entry : mapa.entrySet()) {
			int acceso = entry.getValue();
			if (acceso >= accesoDesde && acceso < accesoHasta) {
				subconjunto.add(entry.getKey());
			}
		}
		return subconjunto;
	}

	@Override
	public Comparator<? super E> comparator() {
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		Iterator<E> baseIterator = new ArrayList<>(mapa.keySet()).iterator();

		return new Iterator<E>() {
			@Override
			public boolean hasNext() {
				return baseIterator.hasNext();
			}

			@Override
			public E next() {
				E elemento = baseIterator.next();
				contador++;
				mapa.put(elemento, contador);
				return elemento;
			}

			@Override
			public void remove() {
				baseIterator.remove();
			}
		};
	}

}
