package es.ubu.gii.edat.pract1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Clase contenedor. Implementa una Colección. Internamente almacenará los datos
 * dentro de una lista.
 * 
 * @author bbaruque
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 *
 * @param <E> el tipo de elementos que contiene la colección.
 */
public class MaxElementCollection<E extends Comparable<E>> implements Collection<E> {

	private List<E> list;
	private Comparator<E> comparador;

	public MaxElementCollection() {
		this.list = new ArrayList<>(); // Inicializamos la lista vacía.
		this.comparador = null;
	}

	public void rellenarLista(List<E> lista) {
		this.list = lista;
	}

	/**
	 * Método para encontrar el mayor elemento iterando por la lista. Este método no
	 * requiere ordenar la lista, solo iterar y comparar los elementos.
	 * 
	 * @return el mayor elemento de la colección.
	 * @throws IllegalArgumentException si la lista está vacía.
	 */
	public E findMaxElement() {
		if (list.isEmpty()) {
			throw new IllegalArgumentException("La colección está vacía");
		}

		E maxElement = list.get(0); // Asumimos que el primer elemento es el mayor inicialmente.
		for (E element : list) {
			if (comparador != null) {
				if (comparador.compare(element, maxElement) > 0) {
					maxElement = element;
				}
			} else {
				if (element.compareTo(maxElement) > 0) {
					maxElement = element;
				}
			}
		}
		return maxElement;
	}

	/**
	 * Método para encontrar el mayor elemento, ordenando primero la lista.
	 * 
	 * @return el mayor elemento de la colección, después de ordenar la lista.
	 * @throws IllegalArgumentException si la lista está vacía.
	 */
	public E findMaxElementBySorting() {
		if (list.isEmpty()) {
			throw new IllegalArgumentException("La colección está vacía");
		}

		if (comparador != null) {
			Collections.sort(list, comparador);
		} else {
			Collections.sort(list);
		}
		return list.get(list.size() - 1);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	@Override
	public boolean add(E e) {
		return list.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return list.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	@Override
	public void clear() {
		list.clear();
	}
}
