package es.ubu.inf.edat.pract02;

import static org.junit.Assert.*;

import java.util.Iterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.ubu.inf.edat.pract02.ColaCircularEnlazada;

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
}
