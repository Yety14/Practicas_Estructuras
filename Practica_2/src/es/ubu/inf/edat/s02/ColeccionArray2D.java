package es.ubu.inf.edat.s02;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ColeccionArray2D<E> extends AbstractCollection<E> {

    public E[][] array;

    public ColeccionArray2D(E[][] array) {
        this.array = array;
    }

    public E set(int posicion, E dato) {
        int filas = array.length;
        int columnas = array[0].length;

        if (posicion < 0 || posicion >= filas * columnas) {
            throw new IndexOutOfBoundsException("Fuera de rango");
        }

        int fila = posicion / columnas;
        int col = posicion % columnas;

        E valorAnterior = array[fila][col];
        array[fila][col] = dato;
        return valorAnterior;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator2D();
    }

    @Override
    public int size() {
        return (array.length * array[0].length);
    }

    private class Iterator2D implements Iterator<E> {
        private int filaActual = 0;
        private int columnaActual = 0;
        private boolean nextCalled = false; // Para controlar si se ha llamado a next()

        @Override
        public boolean hasNext() {
            while (filaActual < array.length) {
                if (columnaActual < array[filaActual].length) {
                    return true;
                }
                filaActual++;
                columnaActual = 0;
            }
            return false;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E elemento = array[filaActual][columnaActual];
            columnaActual++;
            nextCalled = true; // Marcar que se ha llamado a next()
            return elemento;
        }

        @Override
        public void remove() {
            if (!nextCalled) {
                throw new IllegalStateException("next() debe ser llamado antes de remove()");
            }
            int colAnterior = columnaActual - 1;
            int filaAnterior = filaActual;

            if (colAnterior < 0) {
                filaAnterior--;
                colAnterior = array[filaAnterior].length - 1;
            }

            array[filaAnterior][colAnterior] = null;
            nextCalled = false; // Reiniciar el flag
        }
    }
}