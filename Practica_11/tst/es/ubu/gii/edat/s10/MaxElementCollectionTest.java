package es.ubu.gii.edat.s10;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

public class MaxElementCollectionTest {

    @Test
    public void testFindMaxElement() {
        MaxElementCollection<Integer> collection = new MaxElementCollection<>();
        collection.addAll(List.of(3, 5, 7, 2, 8, 6, 4, 7, 0));

        int max = collection.findMaxElement();
        assertEquals(8, max, "El mayor elemento deber�a ser 8");
    }

    @Test
    public void testFindMaxElementBySorting() {
        MaxElementCollection<Integer> collection = new MaxElementCollection<>();
        collection.addAll(List.of(3, 5, 7, 2, 8, 6, 4, 7, 0));

        int max = collection.findMaxElementBySorting();
        assertEquals(8, max, "El mayor elemento deber�a ser 8");
    }

    @Test
    public void testFindMaxElementWithPriorityQueue() {
        MaxElementCollection<Integer> collection = new MaxElementCollection<>();
        collection.addAll(List.of(3, 5, 7, 2, 8, 6, 4, 7, 0));

        int max = collection.findMaxElementWithPriorityQueue();
        assertEquals(8, max, "El mayor elemento deber�a ser 8");
    }

    @Test
    public void testEmptyCollection() {
        MaxElementCollection<Integer> collection = new MaxElementCollection<>();

        assertThrows(IllegalArgumentException.class, collection::findMaxElement, "Deber�a lanzar una excepci�n si la lista est� vac�a");
        assertThrows(IllegalArgumentException.class, collection::findMaxElementBySorting, "Deber�a lanzar una excepci�n si la lista est� vac�a");
    }
}