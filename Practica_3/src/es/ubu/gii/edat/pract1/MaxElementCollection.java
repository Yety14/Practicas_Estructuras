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
 *
 * @param <E> el tipo de elementos que contiene la colección.
 */
public class MaxElementCollection<E> implements Collection<E> {

    private List<E> list;
    private Comparator<E> comparador;

    public MaxElementCollection() {
        this.list = new ArrayList<>(); // Inicializamos la lista vacía.
        this.comparador = null; // Sin comparador, asumimos que los elementos son comparables.
    }

    public MaxElementCollection(Comparator<E> comp) {
        this.list = new ArrayList<>(); // Inicializamos la lista vacía.
        this.comparador = comp; // Usamos el comparador proporcionado.
    }

    public E findMaxElement() {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("La lista está vacía");
        }

        E maxElement = list.get(0);

        for (E element : list) {
            if (comparador != null) {
                if (comparador.compare(element, maxElement) > 0) {
                    maxElement = element;
                }
            } else if (((Comparable<E>) element).compareTo(maxElement) > 0) {
                maxElement = element;
            }
        }
        return maxElement;
    }

    public E findMaxElementBySorting() {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("La lista está vacía");
        }

        if (comparador != null) {
            Collections.sort(list, comparador);
        } else {
            Collections.sort((List<Comparable>) list);
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
