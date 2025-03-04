package es.ubu.gii.edat.pract1;

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
        assertEquals(8, max, "El mayor elemento debería ser 8");
    }

    @Test
    public void testFindMaxElementBySorting() {
        MaxElementCollection<Integer> collection = new MaxElementCollection<>();
        collection.addAll(List.of(3, 5, 7, 2, 8, 6, 4, 7, 0));

        int max = collection.findMaxElementBySorting();
        assertEquals(8, max, "El mayor elemento debería ser 8");
    }

    @Test
    public void testEmptyCollection() {
        MaxElementCollection<Integer> collection = new MaxElementCollection<>();

        assertThrows(IllegalArgumentException.class, collection::findMaxElement, "Debería lanzar una excepción si la lista está vacía");
        assertThrows(IllegalArgumentException.class, collection::findMaxElementBySorting, "Debería lanzar una excepción si la lista está vacía");
    }
}