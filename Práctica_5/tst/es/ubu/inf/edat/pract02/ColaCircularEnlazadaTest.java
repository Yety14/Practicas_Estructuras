package es.ubu.inf.edat.pract02;

import static org.junit.Assert.*;

import java.util.Iterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ColaCircularEnlazadaTest {

	/**
	 * Cola sobre la que se realizan las pruebas
	 */
	ColaCircularEnlazada<Integer> cola;

	/**
	 * Antes de cada test, se crea una nueva instancia de la cola.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		cola = new ColaCircularEnlazada<Integer>(); 
	}

	/**
	 * Al finalizar cada test, se limpia el contenido de la cola
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		cola.clear();
	}

	/**
	 * Se comprueba la correccion al insertar elementos nuevos.
	 * TODO - Comprobar los casos excepcionales
	 */
	@Test
	public void testOffer() {

		Integer[] control = {3,5,6,9};
		Integer[] contenidoCola = new Integer[0];

		cola.offer(3);
		cola.offer(5);
		cola.offer(6);
		cola.offer(9);

		assertEquals("El tamano de la cola debe coincidir con el num. de elementos insertados",
				control.length, cola.size());
		
		assertArrayEquals("El contenido de la cola debe coincidir con los elementos insertados",
				control,cola.toArray(contenidoCola));

	}

	/**
	 * Se comprueba la correccion al consultar la cabeza de la lista.
	 * TODO - Comprobar los casos excepcionales 
	 */
	@Test
	public void testPeek() {

		testOffer();

		assertEquals("El consultar la cabeza de la cola devuelve el primer elemento insertado",
				(Integer) 3, cola.peek());
		assertEquals("El consultar la cabeza de la cola NO debe modificar el contenido de la misma",
				(Integer) 3, cola.peek());

	}

	/**
	 * Se comprueba la correccion al consultar la cabeza de la lista.
	 * TODO - Comprobar los casos excepcionales 
	 */
	@Test
	public void testElement() {

		testOffer();

		assertEquals("El consultar la cabeza de la cola devuelve el primer elemento insertado",
				(Integer) 3, cola.element());
		assertEquals("El consultar la cabeza de la cola NO debe modificar el contenido de la misma",
				(Integer) 3, cola.element());

	}

	/**
	 * Se comprueba que los elementos se extraen en el mismo 
	 * orden en el que se insertaron (FIFO).
	 * TODO - Comprobar los casos excepcionales
	 */
	@Test
	public void testPoll() {

		Integer[] control = {3,5,6,9};

		testOffer();

		for(Integer el : control){
			assertEquals("El elemento extraido no coincide con el que se inserto",
					el, cola.poll());
		}

	}

	/**
	 * Se comprueba que el método que permite eliminar en el
	 * iterador realiza la eliminación correcta de un elemento 
	 * interno de la cola.
	 * 
	 * Es posible por emplear el iterador. El método remove de la
	 * propia cola solo podría acceder a eliminar el elemento en
	 * la cabeza.
	 * 
	 * TODO - Comprobar los casos excepcionales
	 */

	@Test
	public void testRemove(){

		testOffer();

		int[] control = {3,5,6,9};

		Iterator<Integer> it = cola.iterator();
		assertTrue(it.hasNext());
		assertEquals( control[0], (int) it.next() );
		assertTrue(it.hasNext());
		assertEquals( control[1], (int) it.next() );
		it.remove();

		assertTrue(cola.contains(control[0]));
		assertFalse(cola.contains(control[1]));

	}

	/**
	 * Se comprueba que el contenido de la cola se recorre de forma correcta al solicitar
	 * el iterador lineal (standard).
	 * TODO - Comprobar los casos excepcionales
	 */
	@Test
	public void testIterator() {

		testOffer();
		Integer[] control = {3,5,6,9};

		Iterator<Integer> iter = cola.iterator();
		for(int i=0; i<4; i++){
			assertTrue("El iterador tiene que indicar que hay siguiente tantas veces como elementos haya",
					iter.hasNext());
			assertEquals("El iterador tiene que devolver los elementos en el orden que se insertaron",
					control[i],iter.next());
		}

	}

	/**
	 * Se comprueba que el contenido de la cola se recorre de forma correcta al solicitar
	 * el iterador circular.
	 * TODO - Comprobar los casos excepcionales
	 */
	@Test
	public void testCircularIterator(){

		testOffer();
		Integer[] control = {3,5,6,9};

		Iterator<Integer> circularIt = cola.circularIterator();
		for(int i=0; i<12; i++){
			assertTrue("El iterador circular tiene que indicar que siempre hay un siguiente",
					circularIt.hasNext());

			int mod = i%4;
			assertEquals("Debe devolver los elementos en el orden que se insertaron. "
					+ "Al llegar al final, comienza a devolverlos otra vez en ese mismo orden",
					control[mod],circularIt.next());
		}

	}

	/**
	 * Se comprueba que el tamano de la cola se actualiza con las diferentes operaciones. 
	 * TODO - Comprobar los casos excepcionales
	 */
	@Test
	public void testSize() {

		testOffer();

		assertFalse(cola.isEmpty());
		assertEquals("El tamano de la cola debe coincidir con el num. de elementos insertados",
				4, cola.size());

		cola.peek();
		assertEquals("El tamano de la cola no varía al consultar la cabeza",
				4, cola.size());

		cola.poll();
		assertEquals(3, cola.size());

	}
	/**
     * Se comprueba que al hacer poll() en una cola vacía se devuelve null.
     */
    @Test
    public void testPollEmpty() {
        assertNull("poll() en una cola vacía debe devolver null", cola.poll());
    }

    /**
     * Se comprueba que el método clear() vacíe correctamente la cola.
     */
    @Test
    public void testClear() {
        testOffer();
        cola.clear();
        assertTrue("Después de clear, la cola debe estar vacía", cola.isEmpty());
        assertEquals("Después de clear, el tamaño debe ser 0", 0, cola.size());
    }

    /**
     * Se comprueba que no se permita insertar elementos nulos en la cola
     * (si la implementación no lo permite).
     */
    @Test(expected = NullPointerException.class)
    public void testOfferNullException() {
        cola.offer(null); // Espera una excepción si no se permite nulos
    }

    /**
     * Se comprueba que el método remove() en el iterador elimina correctamente un elemento.
     */
    @Test
    public void testIteratorRemove() {
        testOffer();
        Iterator<Integer> iter = cola.iterator();
        iter.next(); // Avanzamos al primer elemento
        iter.remove(); // Removemos el primer elemento

        assertEquals("Después de eliminar un elemento, el tamaño debe decrecer en 1",
                     3, cola.size());
        assertFalse("El primer elemento debería haberse eliminado",
                    cola.contains(3));
    }

    /**
     * Se comprueba que el iterador circular recorra la cola correctamente con múltiples elementos.
     */
    @Test
    public void testCircularIteratorMultipleCycles() {
        testOffer();
        Integer[] control = {3, 5, 6, 9};

        Iterator<Integer> circularIt = cola.circularIterator();
        for (int i = 0; i < 16; i++) {
            assertTrue("El iterador circular debe indicar que siempre hay un siguiente", circularIt.hasNext());

            int mod = i % 4;
            assertEquals("Debe devolver los elementos en el orden que se insertaron. Al llegar al final, debe volver a empezar.",
                    control[mod], circularIt.next());
        }
    }

    /**
     * Se comprueba que contains() funcione correctamente.
     */
    @Test
    public void testContains() {
        testOffer();
        assertTrue("La cola debe contener el elemento 5", cola.contains(5));
        assertFalse("La cola no debe contener el elemento 10", cola.contains(10));
    }

    /**
     * Se comprueba que el iterador no permita la eliminación si no se llama a next() previamente.
     */
    @Test(expected = IllegalStateException.class)
    public void testIteratorRemoveWithoutNext() {
        Iterator<Integer> iter = cola.iterator();
        iter.remove(); // Esto debería lanzar IllegalStateException
    }

    /**
     * Se comprueba que el comportamiento de poll() en una cola vacía devuelve null.
     */
    @Test
    public void testPollOnEmpty() {
        assertNull("poll() en una cola vacía debe devolver null", cola.poll());
    }

    /**
     * Se comprueba que la cola puede ofrecer un solo elemento.
     */
    @Test
    public void testSingleElement() {
        cola.offer(100);
        assertEquals("La cola debe contener un solo elemento", 1, cola.size());
        assertEquals("El único elemento en la cola debe ser 100", (Integer) 100, cola.peek());
    }

    /**
     * Se comprueba que la cola sigue funcionando correctamente después de varios poll().
     */
    @Test
    public void testMultiplePolls() {
        testOffer();
        cola.poll(); // Remover 3
        cola.poll(); // Remover 5
        assertEquals("La cola debe contener 2 elementos después de dos poll()", 2, cola.size());
        assertEquals("El siguiente elemento en la cola debe ser 6", (Integer) 6, cola.peek());
    }

}