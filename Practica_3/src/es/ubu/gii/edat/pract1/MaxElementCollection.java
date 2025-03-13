package es.ubu.gii.edat.pract1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Clase contenedor. Implementa la interfaz {@code Collection<E>}, almacenando
 * los elementos en una lista interna.
 * 
 * @author bbaruque
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 *
 * @param <E> el tipo de elementos que contiene la colección. Debe ser
 *            comparable o proporcionar un {@code Comparator<E>}.
 */
public class MaxElementCollection<E extends Comparable<E>> implements Collection<E> {

	  /**
     * Lista interna que almacena los elementos de la colección.
     */
    private List<E> list;

    /**
     * Comparador opcional para determinar el orden de los elementos en la colección.
     * Si es {@code null}, se utilizará el orden natural de los elementos.
     */
    private Comparator<E> comparador;

    /**
     * Crea una nueva colección vacía sin un comparador específico.
     */
    public MaxElementCollection() {
        this.list = new ArrayList<>(); // Inicializamos la lista vacía.
        this.comparador = null;
    }

    /**
     * Rellena la colección con los elementos de una lista dada.
     * 
     * @param lista la lista de elementos con la que se rellenará la colección.
     * @throws IllegalArgumentException si la lista es {@code null}.
     */
    public void rellenarLista(List<E> lista) throws IllegalArgumentException{
        if (lista == null) {
            throw new IllegalArgumentException("La lista no puede ser nula");
        }
        this.list = lista;
    }

    /**
     * Encuentra el mayor elemento recorriendo la lista sin necesidad de ordenarla previamente.
     * 
     * <p>Este método itera sobre todos los elementos de la colección y 
     * selecciona el máximo comparándolos entre sí. Si se proporciona un 
     * {@code Comparator<E>}, se usará para determinar el mayor elemento; 
     * de lo contrario, se utilizará el orden natural de los elementos.</p>
     * 
     * @return el mayor elemento de la colección.
     * @throws IllegalArgumentException si la colección no contiene elementos.
     */
    public E findMaxElement() throws IllegalArgumentException{
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
     * Encuentra el mayor elemento ordenando primero la colección y seleccionando el último elemento.
     * 
     * <p>Este método ordena la colección en orden ascendente y luego 
     * devuelve el último elemento, que será el mayor. Si se proporciona 
     * un {@code Comparator<E>}, se utilizará para la ordenación; de lo contrario, 
     * se usará el orden natural de los elementos.</p>
     * 
     * @return el mayor elemento de la colección después de ordenarla.
     * @throws IllegalArgumentException si la colección no contiene elementos.
     */
    public E findMaxElementBySorting() throws IllegalArgumentException{
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
