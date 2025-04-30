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
		contador++; // Cada operación cuenta como un acceso

		if (mapa.containsKey(e)) {
			// Si ya está, solo actualizamos su valor de acceso
			mapa.put(e, contador);
			return false; // no se añadió, ya existía
		} else {
			// Si ya está lleno, eliminar el menos usado
			if (mapa.size() >= capacidad) {
				eliminarMenosUsado();
			}
			mapa.put(e, contador); // añadir nuevo con valor de acceso
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
		// No tocar el contador aquí, solo eliminar si existe
		return mapa.remove(o) != null;
	}

	@Override
	public int size() {
		return mapa.size();
	}

//	private E encontrar(int i) {
//		Iterator<E> it = iterator();
//		int contador = 0;
//		while (it.hasNext()) {
//			E e = it.next();
//			if (contador == i) {
//				return e;
//			}
//			contador++;
//		}
//		return null;
//	}

//	@Override
//	public E first() {
//		Iterator<E> it = iterator();
//		if (!it.hasNext())
//			throw new IllegalStateException("Conjunto vacío.");
//		return it.next(); // Primer accedido (menos reciente)
//	}
//
//	@Override
//	public E last() {
//		Iterator<E> it = iterator();
//		E last = null;
//		while (it.hasNext()) {
//			last = it.next();
//		}
//		if (last == null)
//			throw new IllegalStateException("Conjunto vacío.");
//		return last; // Más recientemente accedido
//	}
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
//	@Override
//	public SortedSet<E> headSet(E toElement) {
//		if (!this.contains(toElement)) {
//			throw new IllegalArgumentException("Elemento no presente en el conjunto.");
//		}
//		SortedSet<E> subConjunto = new ConjuntoLRU<>(this.capacidad);
//		E actual = encontrar(inicial);
//		Iterator<E> iterador = this.iterator();
//		while (iterador.hasNext()) {
//			if (actual == toElement) {
//				break;
//			}
//			subConjunto.add(actual);
//			actual = iterador.next();
//		}
//		return subConjunto;
//	}

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
//	@Override
//	public SortedSet<E> tailSet(E toElement) {
//		if (!this.contains(toElement)) {
//			throw new IllegalArgumentException("Elemento no presente en el conjunto.");
//		}
//		SortedSet<E> subConjunto = new ConjuntoLRU<>(this.capacidad);
//		E actual = encontrar(fin);
//		Iterator<E> iterador = this.iterator();
//		while (iterador.hasNext()) {
//			if (actual == toElement) {
//				break;
//			}
//			subConjunto.add(actual);
//			actual = iterador.next();
//		}
//		return subConjunto;
//	}
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

//	@Override
//	public SortedSet<E> subSet(E a, E b) {
//		if (!this.contains(a) || !this.contains(b)) {
//			throw new IllegalArgumentException("Elemento no presente en el conjunto.");
//		}
//		SortedSet<E> subConjunto = new ConjuntoLRU<>(this.capacidad);
//		Iterator<E> iterador = this.iterator();
//		while (iterador.hasNext()) {
//			E actual = iterador.next();
//			if (actual.equals(a) && actual.equals(b)) {
//				break;
//			}
//			subConjunto.add(actual);
//		}
//		return subConjunto;
//	}

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

//	@Override
//	public Iterator<E> iterator() {
//		return mapa.values().iterator();
//	}
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
				contador++; // cada acceso al elemento cuenta
				mapa.put(elemento, contador); // actualiza momento de acceso
				return elemento;
			}

			@Override
			public void remove() {
				baseIterator.remove(); // opcional, según quieras soportarlo
			}
		};
	}

}
