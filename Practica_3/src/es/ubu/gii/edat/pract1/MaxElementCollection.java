package es.ubu.gii.edat.pract1;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Clase contenedor. Implementa una Colecci�n. Internamente almacenar� los datos dentro de una lista. 
 * 
 * @author bbaruque
 *
 * @param <E>
 */
public class MaxElementCollection<E> implements Collection<E> {

    private List<E> list;
    private Comparator<E> comparador;

    /**
     * Constructor de la colecci�n. Instancia la colecci�n vac�a.
     */
    public MaxElementCollection() {
    	// TODO Completar el metodo
    }

    /**
     * En caso de que los elementos a almacenar no sean comparables, habr� que pasarle un comparador.
     * 
     * @param comp
     */
    public MaxElementCollection(Comparator<E> comp) {
    	// TODO Completar el metodo
    }

    /**
     * M�todo para encontrar el mayor elemento iterando por la lista
     * 
     * @return
     */
    public E findMaxElement() {
    	// TODO Completar el metodo. En este caso, basta con realizar una iteración simple por la lista.
    }

    // 
    
    /**
     * M�todo para encontrar el mayor elemento, ordenando primero la lista
     * 
     * @return mayor elemento de la coleccion
     */
    public E findMaxElementBySorting() {
    	// TODO Completar el metodo. En este caso, se siguen dos pasos:
        // 1. ordenar la lista. Se pueden emplear los métodos de la clase Collections. Ver documentación de API Java.
        // 2. devuelvolver siempre el último elemento.
    }

}