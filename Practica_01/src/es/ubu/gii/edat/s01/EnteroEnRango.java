package es.ubu.gii.edat.s01;

import java.util.Iterator;
import java.lang.IllegalArgumentException;

/**
 * Clase que representa un número entero dentro de un rango definido por un mínimo y un máximo.
 * Permite realizar operaciones como suma y resta dentro del rango permitido.
 * 
 * También proporciona un iterador para recorrer los valores dentro del rango.
 * 
 * @author bbaruque
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 */
public class EnteroEnRango implements Iterable<Integer> {

    /** Valor actual del entero. */
    private int valor;

    /** Valor mínimo permitido. */
    private int minimo;

    /** Valor máximo permitido. */
    private int maximo;
    
    /**
     * Constructor de la clase EnteroEnRango.
     * 
     * @param valor El valor inicial del entero.
     * @param minimo El valor mínimo permitido para el entero.
     * @param maximo El valor máximo permitido para el entero.
     * @throws IllegalArgumentException Si el valor no está dentro del rango [minimo, maximo] o si minimo > maximo.
     */
    public EnteroEnRango(int valor, int minimo, int maximo) throws IllegalArgumentException {
        if (minimo <= valor && maximo >= valor && minimo <= maximo) {
            this.minimo = minimo;
            this.maximo = maximo;
            setValor(valor);
        } else {
            throw new IllegalArgumentException("Número fuera de rango");
        }
    }
    
    /**
     * Obtiene el valor actual del entero.
     * 
     * @return El valor actual del entero.
     */
    public int getValor() {
        return this.valor;
    }
    
    /**
     * Establece un nuevo valor para el entero.
     * 
     * @param nuevoValor El nuevo valor a establecer.
     */
    public void setValor(int nuevoValor) {
        this.valor = nuevoValor;
    }

    /**
     * Obtiene el valor mínimo permitido para el entero.
     * 
     * @return El valor mínimo permitido.
     */
    public int getValorMinimo() {
        return this.minimo;
    }
    
    /**
     * Obtiene el valor máximo permitido para el entero.
     * 
     * @return El valor máximo permitido.
     */
    public int getValorMaximo() {
        return this.maximo;
    }

    /**
     * Suma el valor de otro objeto EnteroEnRango al valor actual.
     * 
     * @param otro El objeto EnteroEnRango cuyo valor se sumará al valor actual.
     * @throws IllegalArgumentException Si la suma excede el valor máximo permitido.
     */
    public void sumar(EnteroEnRango otro) throws IllegalArgumentException {
        if (otro.valor + this.valor > maximo) {
            throw new IllegalArgumentException("Número fuera de rango");
        } else {
            setValor(otro.valor + this.valor);
        }
    }

    /**
     * Resta el valor de otro objeto EnteroEnRango al valor actual.
     * 
     * @param otro El objeto EnteroEnRango cuyo valor se restará al valor actual.
     * @throws IllegalArgumentException Si la resta resulta en un valor menor al mínimo permitido.
     */
    public void restar(EnteroEnRango otro) throws IllegalArgumentException {
        if (this.valor - otro.valor < minimo) {
            throw new IllegalArgumentException("Número fuera de rango");
        } else {
            setValor(this.valor - otro.valor);
            this.maximo = otro.maximo; // Actualiza el valor máximo
        }
    }

    /**
     * Devuelve una representación en cadena del objeto EnteroEnRango.
     * 
     * @return Una cadena que representa el objeto EnteroEnRango.
     */
    @Override
    public String toString() {
        return "EnteroEnRango [valor=" + valor + ", minimo=" + minimo + ", maximo=" + maximo + "]";
    }
    
    /**
     * Devuelve un iterador para recorrer los valores dentro del rango [minimo, maximo].
     * 
     * @return Un iterador de tipo {@code Iterator<Integer>}.
     */
    @Override
    public Iterator<Integer> iterator() {
        return new EnteroEnRangoIterator();
    }

    /**
     * Iterador que recorre los valores dentro del rango de EnteroEnRango.
     */
    private class EnteroEnRangoIterator implements Iterator<Integer> {
        
        /** Valor actual del iterador. */
        private int valorActual = EnteroEnRango.this.minimo;

        /** Valor máximo del iterador. */
        private int maximoActual = EnteroEnRango.this.maximo;

        @Override
        public boolean hasNext() {
            return this.valorActual <= this.maximoActual;
        }

        @Override
        public Integer next() {
            return this.valorActual++;
        }
    }

}
