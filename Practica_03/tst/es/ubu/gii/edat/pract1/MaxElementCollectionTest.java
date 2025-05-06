package es.ubu.gii.edat.pract1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Clase de pruebas unitarias para la clase MaxElementCollection. Verifica el
 * correcto funcionamiento de los métodos para encontrar el elemento máximo en
 * una colección de enteros.
 * 
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 */
public class MaxElementCollectionTest {

	/**
	 * Prueba el método findMaxElement para encontrar el elemento máximo en una
	 * colección.
	 */
	@Test
	public void testFindMaxElement() {
		MaxElementCollection<Integer> collection = new MaxElementCollection<>();
		collection.addAll(List.of(3, 5, 7, 2, 8, 6, 4, 7, 0));

		int max = collection.findMaxElement();
		assertEquals(8, max, "El mayor elemento debería ser 8");
	}

	/**
	 * Prueba el método findMaxElementBySorting para encontrar el elemento máximo
	 * utilizando un enfoque basado en ordenación.
	 */
	@Test
	public void testFindMaxElementBySorting() {
		MaxElementCollection<Integer> collection = new MaxElementCollection<>();
		collection.addAll(List.of(3, 5, 7, 2, 8, 6, 4, 7, 0));

		int max = collection.findMaxElementBySorting();
		assertEquals(8, max, "El mayor elemento debería ser 8");
	}

	/**
	 * Prueba el comportamiento de los métodos findMaxElement y
	 * findMaxElementBySorting cuando la colección está vacía. Se espera que ambos
	 * métodos lancen una excepción.
	 */
	@Test
	public void testEmptyCollection() {
		MaxElementCollection<Integer> collection = new MaxElementCollection<>();

		assertThrows(IllegalArgumentException.class, collection::findMaxElement,
				"Debería lanzar una excepción si la lista está vacía");
		assertThrows(IllegalArgumentException.class, collection::findMaxElementBySorting,
				"Debería lanzar una excepción si la lista está vacía");
	}
}