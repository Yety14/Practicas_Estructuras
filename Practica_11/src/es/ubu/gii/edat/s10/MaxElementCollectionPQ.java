package es.ubu.gii.edat.s10;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Colección que almacena elementos en una PriorityQueue configurada como
 * max-heap. Proporciona métodos para añadir elementos y encontrar el máximo
 * elemento.
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
public class MaxElementCollectionPQ<E extends Comparable<E>> extends AbstractCollection<E> {
	/**
	 * PriorityQueue interna configurada como max-heap para almacenar los elementos.
	 */
	private PriorityQueue<E> elementosPQ;

	/**
	 * Constructor que inicializa la PriorityQueue con un comparador inverso para
	 * funcionar como max-heap.
	 */
	public MaxElementCollectionPQ() {
		elementosPQ = new PriorityQueue<>((a, b) -> b.compareTo(a)); // max-heap
	}

	/**
	 * Añade un elemento a la colección.
	 * 
	 * @param e el elemento a añadir.
	 * @return {@code true} si la colección cambia como resultado de esta llamada.
	 */
	@Override
	public boolean add(E e) {
		return elementosPQ.add(e);
	}

	/**
	 * Añade todos los elementos de la colección especificada a esta colección.
	 * 
	 * @param c la colección cuyos elementos se añadirán.
	 * @return {@code true} si la colección cambia como resultado de esta llamada.
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
//        boolean changed = false;
//        for (E e : c) {
//            changed |= add(e);
//        }
//        return changed;
		return elementosPQ.addAll(c);

	}

	/**
	 * Encuentra el mayor elemento recorriendo todos los elementos de la
	 * PriorityQueue.
	 * 
	 * <p>
	 * Aunque PriorityQueue tiene el máximo en la raíz, este método recorre todos
	 * los elementos para demostrar la búsqueda manual del máximo.
	 * </p>
	 * 
	 * @return el mayor elemento encontrado.
	 */
	public E findMaxElement() {
		if (elementosPQ.isEmpty()) {
			throw new IllegalArgumentException("La colección está vacía");
		}

		E max = null;
		for (E element : elementosPQ) {
			if (max == null || element.compareTo(max) > 0) {
				max = element;
			}
		}
		return max;
	}

	/*
	 * La q mas te guste
	 * 
	 * public E findMaxElement() { MaxElementCollection<E> auxiliar = new
	 * MaxElementCollection<>(); auxiliar.addAll(this.elementosPQ); // Copiamos los
	 * datos de la PQ return auxiliar.findMaxElement(); // Llamamos al método ya
	 * implementado
	 * 
	 */

	/**
	 * Obtiene el mayor elemento utilizando la propiedad del max-heap de la
	 * PriorityQueue.
	 * 
	 * @return el mayor elemento (el elemento en la raíz del heap).
	 */
	public E findMaxElementInPriorityQueue() {
		if (elementosPQ.isEmpty()) {
			throw new IllegalArgumentException("La colección está vacía");
		}
		return elementosPQ.peek();
	}

	/**
	 * Devuelve un iterador para recorrer los elementos de la colección.
	 * 
	 * @return un {@link Iterator} sobre los elementos.
	 */
	@Override
	public Iterator<E> iterator() {
		return elementosPQ.iterator();
	}

	/**
	 * Devuelve el número de elementos en la colección.
	 * 
	 * @return el tamaño de la colección.
	 */
	@Override
	public int size() {
		return elementosPQ.size();
	}
}
