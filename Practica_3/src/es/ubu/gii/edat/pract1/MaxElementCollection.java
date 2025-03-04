package es.ubu.gii.edat.pract1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Clase contenedor. Implementa una Colección. Internamente almacenará los datos dentro de una lista. 
 * 
 * @author bbaruque
 *
 * @param <E> el tipo de elementos que contiene la colección.
 */
public class MaxElementCollection<E> implements Collection<E> {

    private List<E> list;
    private Comparator<E> comparador;

    /**
     * Constructor de la colección. Instancia la colección vacía.
     */
    public MaxElementCollection() {
        this.list = new ArrayList<>(); // Inicializamos la lista vacía.
        this.comparador = null;
    }

    /**
     * Método para encontrar el mayor elemento iterando por la lista.
     * Este método no requiere ordenar la lista, solo iterar y comparar los elementos.
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
            } else if (element instanceof Comparable) {
                Comparable<E> comparableElement = (Comparable<E>) element;
                if (comparableElement.compareTo(maxElement) > 0) {
                    maxElement = element;
                }
            } else {
                throw new IllegalStateException("Los elementos no son comparables y no se proporcionó un comparador");
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
            // Usar un comparador por defecto que funcione con Comparable
            Collections.sort(list, new Comparator<E>() {
                @Override
                public int compare(E o1, E o2) {
                    if (o1 instanceof Comparable && o2 instanceof Comparable) {
                        Comparable<E> c1 = (Comparable<E>) o1;
                        return c1.compareTo(o2);
                    } else {
                        throw new IllegalStateException("Los elementos no son comparables y no se proporcionó un comparador");
                    }
                }
            });
        }

        // Una vez ordenada la lista, el último elemento es el mayor.
        return list.get(list.size() - 1);
    }

    // Métodos requeridos por la interfaz Collection<E>

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