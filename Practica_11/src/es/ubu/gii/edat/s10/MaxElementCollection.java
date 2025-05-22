package es.ubu.gii.edat.s10;

import java.util.AbstractCollection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 */
public class MaxElementCollection<E extends Comparable<E>> extends AbstractCollection<E> {

	public List<E> elementos;

	public MaxElementCollection() {
		elementos = new ArrayList<>();
	}

	public E findMaxElementWithPriorityQueue() throws IllegalArgumentException {
	    if (elementos.isEmpty()) {
	        throw new IllegalArgumentException("La colección está vacía");
	    }

	    PriorityQueue<E> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	    maxHeap.addAll(elementos); // O(n log n), por las inserciones
	    return maxHeap.peek();     // O(1)
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

	@Override
	public boolean add(E e) {
		return elementos.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> collection) {
		return super.addAll(collection);
	}

	@Override
	public Iterator<E> iterator() {
		return elementos.iterator();
	}

	@Override
	public int size() {
		return elementos.size();
	}

	public class MaxElementCollectionPQ extends AbstractCollection<E> {

		private PriorityQueue<E> elementosPQ;

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
		/* La q mas te guste
		
		public E findMaxElement() {
    		MaxElementCollection<E> auxiliar = new MaxElementCollection<>();
    		auxiliar.addAll(this.elementosPQ); // Copiamos los datos de la PQ
    		return auxiliar.findMaxElement();  // Llamamos al método ya implementado
	    
		 */

		public E findMaxElementInPriorityQueue() {
		    if (elementosPQ.isEmpty()) {
		        throw new IllegalArgumentException("La colección está vacía");
		    }
		    return elementosPQ.peek(); // O(1) – acceso al máximo gracias al max-heap
		}
		
		@Override
		public Iterator<E> iterator() {
			return elementosPQ.iterator();

		}

		@Override
		public int size() {
			return elementosPQ.size();

		}
	}

}