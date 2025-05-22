package es.ubu.gii.edat.s10;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.PriorityQueue;

public class MaxElementCollectionPQ<E extends Comparable<E>> extends AbstractCollection<E> {

    private PriorityQueue<E> elementosPQ;

    public MaxElementCollectionPQ() {
        elementosPQ = new PriorityQueue<>((a, b) -> b.compareTo(a)); // max-heap
    }

    @Override
    public boolean add(E e) {
        return elementosPQ.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;
        for (E e : c) {
            changed |= add(e);
        }
        return changed;
    }

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
        return elementosPQ.peek();
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
