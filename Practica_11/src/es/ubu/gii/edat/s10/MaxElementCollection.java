package es.ubu.gii.edat.s10;

import java.util.AbstractCollection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Clase contenedor que extiende {@link AbstractCollection} y almacena elementos
 * en una lista interna. Permite encontrar el máximo elemento de distintas
 * formas.
 * 
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 * 
 * @param <E> el tipo de elementos que contiene la colección. Debe ser
 *            comparable o proporcionar un {@code Comparator<E>}.
 */
public class MaxElementCollection<E extends Comparable<E>> extends AbstractCollection<E> {
	/**
	 * Lista interna que almacena los elementos de la colección.
	 */
	public List<E> elementos;

	/**
	 * Constructor por defecto que inicializa la lista interna vacía.
	 */
	public MaxElementCollection() {
		elementos = new ArrayList<>();
	}

	/**
	 * Encuentra el mayor elemento utilizando una PriorityQueue (max-heap).
	 * 
	 * <p>
	 * Crea una cola de prioridad con un comparador que invierte el orden natural,
	 * añade todos los elementos, y devuelve el elemento máximo en tiempo constante.
	 * </p>
	 * 
	 * @return el mayor elemento encontrado.
	 * @throws IllegalArgumentException si la colección está vacía.
	 */
	public E findMaxElementWithPriorityQueue() throws IllegalArgumentException {
		if (elementos.isEmpty()) {
			throw new IllegalArgumentException("La colección está vacía");
		}

		PriorityQueue<E> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		maxHeap.addAll(elementos);
		return maxHeap.peek();
	}

	/**
	 * Encuentra el mayor elemento recorriendo la lista sin necesidad de ordenarla
	 * previamente.
	 * 
	 * <p>
	 * Este método itera sobre todos los elementos de la colección y selecciona el
	 * máximo comparándolos entre sí. Si se proporciona un {@code Comparator<E>}, se
	 * usará para determinar el mayor elemento; de lo contrario, se utilizará el
	 * orden natural de los elementos.
	 * </p>
	 * 
	 * @return el mayor elemento de la colección.
	 * @throws IllegalArgumentException si la colección no contiene elementos.
	 */
	public E findMaxElement() throws IllegalArgumentException {
		if (elementos.isEmpty()) {
			throw new IllegalArgumentException("La colección está vacía");
		}

		E maxElement = elementos.get(0);
		for (E element : elementos) {
			if (element.compareTo(maxElement) > 0) {
				maxElement = element;
			}
		}
		return maxElement;
	}

	/**
	 * Encuentra el mayor elemento ordenando primero la colección y seleccionando el
	 * último elemento.
	 * 
	 * <p>
	 * Este método ordena la colección en orden ascendente y luego devuelve el
	 * último elemento, que será el mayor. Si se proporciona un
	 * {@code Comparator<E>}, se utilizará para la ordenación; de lo contrario, se
	 * usará el orden natural de los elementos.
	 * </p>
	 * 
	 * @return el mayor elemento de la colección después de ordenarla.
	 * @throws IllegalArgumentException si la colección no contiene elementos.
	 */
	public E findMaxElementBySorting() throws IllegalArgumentException {
		if (elementos.isEmpty()) {
			throw new IllegalArgumentException("La colección está vacía");
		}
		Collections.sort(elementos);
		return elementos.get(elementos.size() - 1);
	}

	/**
	 * Añade un elemento a la colección.
	 * 
	 * @param e el elemento a añadir.
	 * @return {@code true} si la colección cambió como resultado de la llamada.
	 */
	@Override
	public boolean add(E e) {
		return elementos.add(e);
	}

	/**
	 * Añade todos los elementos de una colección dada a esta colección.
	 * 
	 * @param collection la colección cuyos elementos se añadirán.
	 * @return {@code true} si la colección cambió como resultado de la llamada.
	 */
	@Override
	public boolean addAll(Collection<? extends E> collection) {
		boolean changed = false;
		for (E element : collection) {
			if (elementos.add(element)) {
				changed = true;
			}
		}
		return changed;
	}

	/**
	 * Devuelve un iterador sobre los elementos en esta colección.
	 * 
	 * @return un {@link Iterator} para recorrer los elementos.
	 */
	@Override
	public Iterator<E> iterator() {
		return elementos.iterator();
	}

	/**
	 * Devuelve el número de elementos en esta colección.
	 * 
	 * @return el tamaño de la colección.
	 */
	@Override
	public int size() {
		return elementos.size();
	}

}