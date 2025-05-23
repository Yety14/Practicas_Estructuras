package es.ubu.gii.edat.pr4;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map.Entry;

import es.ubu.gii.edat.utils.cacheLRUEnlazada;

/**
 * Implementación de un conjunto con política de reemplazo LRU (Least Recently
 * Used). Almacena los elementos junto con un contador que representa el orden
 * de acceso.
 *
 * @param <E> Tipo de elementos que almacena el conjunto.
 * 
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 */
public class ConjuntoLRU<E> extends AbstractSet<E> implements SortedSet<E> {

	/**
	 * Capacidad máxima del conjunto.
	 */
	private int capacidad;

	/**
	 * Contador para registrar el orden de acceso de los elementos.
	 */
	private Integer contador;

	/**
	 * Estructura de almacenamiento basada en cache LRU enlazada con los elementos y
	 * su orden de acceso.
	 */
	private cacheLRUEnlazada<E, Integer> mapa;

	/**
	 * Constructor por defecto no permitido.
	 *
	 * @throws UnsupportedOperationException siempre que se invoque.
	 */
	public ConjuntoLRU() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Constructor por defecto no permitido.");
	}

	/**
	 * Constructor que permite establecer la capacidad máxima del conjunto.
	 *
	 * @param maxSize Capacidad máxima del conjunto.
	 */
	public ConjuntoLRU(int maxSize) {
		this.capacidad = maxSize;
		this.mapa = new cacheLRUEnlazada<E, Integer>(capacidad);
		contador = 0;
	}

	/**
	 * Añade un elemento al conjunto. Si ya existe, solo actualiza su acceso. Si el
	 * conjunto ha alcanzado su capacidad, elimina el menos recientemente usado.
	 *
	 * @param e Elemento a añadir.
	 * @return true si se añadió, false si ya existía.
	 */
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

	/**
	 * Elimina el elemento menos recientemente usado del conjunto.
	 */
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

	/**
	 * Elimina un elemento del conjunto.
	 *
	 * @param o Elemento a eliminar.
	 * @return true si se eliminó, false si no existía.
	 */
	@Override
	public boolean remove(Object o) {
		return mapa.remove(o) != null;
	}

	/**
	 * Devuelve el número de elementos del conjunto.
	 *
	 * @return Tamaño actual del conjunto.
	 */
	@Override
	public int size() {
		return mapa.size();
	}

	/**
	 * Devuelve el elemento menos recientemente accedido.
	 *
	 * @return Elemento con menor contador de acceso.
	 * @throws IllegalStateException si el conjunto está vacío.
	 */
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

	/**
	 * Devuelve el elemento más recientemente accedido.
	 *
	 * @return Elemento con mayor contador de acceso.
	 * @throws IllegalStateException si el conjunto está vacío.
	 */
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

	/**
	 * Devuelve un subconjunto con los elementos menos accedidos que el
	 * especificado.
	 *
	 * @param toElement Elemento de referencia.
	 * @return Subconjunto con elementos menos accedidos.
	 * @throws IllegalArgumentException si el elemento no está en el conjunto.
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

	/**
	 * Devuelve un subconjunto con los elementos más accedidos que el especificado.
	 *
	 * @param fromElement Elemento de referencia.
	 * @return Subconjunto con elementos más accedidos.
	 * @throws IllegalArgumentException si el elemento no está en el conjunto.
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

	/**
	 * Devuelve un subconjunto con los elementos accedidos entre dos elementos
	 * (inclusive el primero, exclusivo el segundo).
	 *
	 * @param desde Elemento de inicio.
	 * @param hasta Elemento de fin.
	 * @return Subconjunto entre los accesos de ambos elementos.
	 * @throws IllegalArgumentException si alguno de los elementos no está en el
	 *                                  conjunto o el orden es incorrecto.
	 */
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

	/**
	 * Devuelve el comparador utilizado por el conjunto.
	 * 
	 * @return Siempre null, ya que no se utiliza un comparador explícito.
	 */
	@Override
	public Comparator<? super E> comparator() {
		return null;
	}

	/**
	 * Devuelve un iterador que actualiza el contador de acceso al recorrer los
	 * elementos.
	 *
	 * @return Iterador de los elementos del conjunto.
	 */
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
