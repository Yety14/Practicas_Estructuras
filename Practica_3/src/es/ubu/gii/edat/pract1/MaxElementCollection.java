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
        this.comparador = null; // No se necesita comparador, se asume que los elementos son comparables.
    }

    /**
     * En caso de que los elementos a almacenar no sean comparables, habrá que pasarle un comparador.
     * 
     * @param comp el comparador que se utilizará para comparar los elementos.
     */
    public MaxElementCollection(Comparator<E> comp) {
        this.list = new ArrayList<>(); // Inicializamos la lista vacía.
        this.comparador = comp; // Usamos el comparador proporcionado.
    }

    /**
     * Método para encontrar el mayor elemento iterando por la lista.
     * Este método no requiere ordenar la lista, solo iterar y comparar los elementos.
     * 
     * @return el mayor elemento de la colección.
     */
    public E findMaxElement() {
        if (list.isEmpty()) {
            return null; // Si la lista está vacía, retornamos null.
        }
        
        E maxElement = list.get(0); // Asumimos que el primer elemento es el mayor inicialmente.
        
        for (E element : list) {
            if (comparador != null) {
                // Si existe un comparador, lo usamos para comparar los elementos.
                if (comparador.compare(element, maxElement) > 0) {
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
     */
    public E findMaxElementBySorting() {
        if (list.isEmpty()) {
            return null; // Si la lista está vacía, retornamos null.
        }
        
        // Si se proporcionó un comparador, usamos Collections.sort para ordenar con el comparador.
        if (comparador != null) {
            Collections.sort(list, comparador);
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
