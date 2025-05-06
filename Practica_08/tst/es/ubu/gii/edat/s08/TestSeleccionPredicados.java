package es.ubu.gii.edat.s08;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.ubu.gii.edat.s08.SeleccionPredicados.Condicion;
import es.ubu.inf.edat.datos.coches.Coche;
import es.ubu.inf.edat.datos.coches.ComparadorCaballos;
import es.ubu.inf.edat.datos.coches.GeneradorCoches;

public class TestSeleccionPredicados {

	private SeleccionPredicados selector;

	@Before
	public void setUp() throws Exception {
		selector = new SeleccionPredicados();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 
	 */
	@Test
	public void testSeleccionOrdenadosComparables() {

		Integer[] enteros = {1,2,3,4,5,6};
		List<Integer> lista = Arrays.asList(enteros);

		SortedSet<Integer> arbol = new TreeSet<Integer>();
		arbol.addAll(lista);

		compruebaSeleccionComparable(arbol);

	}


	/**
	 * 
	 */
	@Test
	public void testSeleccionDesordenadosComparables() {

		Integer[] enteros = {1,2,3,4,5,6};
		List<Integer> lista = Arrays.asList(enteros);

		Set<Integer> tabla = new HashSet<Integer>();
		tabla.addAll(lista);

		compruebaSeleccionComparable(tabla);

	}


	/**
	 * 
	 */
	@Test
	public void testSeleccionListaComparables() {

		Integer[] enteros = {1,2,3,4,5,6};
		List<Integer> lista = Arrays.asList(enteros);

		compruebaSeleccionComparable(lista);

	}


	/**
	 * 
	 */
	@Test
	public void testSeleccionListadoRepetComparables() {

		Integer[] enteros = {1,2,3,3,4,5,6,6,6};
		List<Integer> lista = Arrays.asList(enteros);

		compruebaSeleccionComparable(lista);

	}

	///////////////

	@Test
	public void testDeseleccionOrdenadosComparables() {

		Integer[] enteros = {1,2,3,4,5,6};
		List<Integer> lista = Arrays.asList(enteros);

		SortedSet<Integer> arbol = new TreeSet<Integer>();
		arbol.addAll(lista);

		compruebaDeseleccionComparable(arbol);

	}


	@Test
	public void testDeseleccionDesordenadosComparables() {

		Integer[] enteros = {1,2,3,4,5,6};
		List<Integer> lista = Arrays.asList(enteros);

		Set<Integer> tabla = new HashSet<Integer>();
		tabla.addAll(lista);

		compruebaDeseleccionComparable(tabla);

	}

	/**
	 * 
	 */
	@Test
	public void testDeseleccionListaComparables() {

		Integer[] enteros = {1,2,3,4,5,6};
		List<Integer> lista = Arrays.asList(enteros);

		compruebaDeseleccionComparable(lista);

	}


	/**
	 * 
	 */
	@Test
	public void testDeseleccionListadoRepetComparables() {

		Integer[] enteros = {1,2,3,3,4,5,6,6,6};
		List<Integer> lista = Arrays.asList(enteros);

		compruebaDeseleccionComparable(lista);

	}

	///////////


	/**
	 * 
	 */
	@Test
	public void testSeleccionOrdenadosComparador() {

		List<Coche> lista = GeneradorCoches.listaMezclada(6);
		ComparadorCaballos comp = new ComparadorCaballos();

		SortedSet<Coche> arbol = new TreeSet<Coche>(comp);
		arbol.addAll(lista);

		compruebaSeleccionNoComparable(arbol, comp);
	}


	/**
	 * 
	 */
	@Test
	public void testSeleccionDesordenadosComparador() {

		List<Coche> lista = GeneradorCoches.listaMezclada(6);
		ComparadorCaballos comp = new ComparadorCaballos();

		Set<Coche> tabla = new HashSet<Coche>();
		tabla.addAll(lista);

		compruebaSeleccionNoComparable(tabla, comp);

	}

	/**
	 * 
	 * @param coleccion
	 */
	private void compruebaSeleccionComparable(Collection<Integer> coleccion){

		Set<Integer> s = selector.seleccionaPredicado(coleccion, Condicion.MAYOR, Integer.valueOf(4));
		assertEquals("No se ha obtenido el numero de elementos esperado",2,s.size());

		for(Integer elem: s){
			assertTrue("El elemento"+elem+"no es mayor que el de referencia: "+4, elem > 4);
		}

		s = selector.seleccionaPredicado(coleccion, Condicion.MAYORIGUAL, Integer.valueOf(4));
		assertEquals("No se ha obtenido el numero de elementos esperado", 3, s.size());

		for(Integer elem: s){
			assertTrue("El elemento"+elem+"no es mayor o igual que el de referencia: "+4, elem >= 4);
		}

		s = selector.seleccionaPredicado(coleccion, Condicion.IGUAL, Integer.valueOf(4));
		assertEquals("No se ha obtenido el numero de elementos esperado", 1, s.size());

		for(Integer elem: s){
			assertTrue("El elemento"+elem+"no es igual que el de referencia: "+4, elem == 4);
		}

		s = selector.seleccionaPredicado(coleccion, Condicion.MENORIGUAL, Integer.valueOf(4));
		assertEquals("No se ha obtenido el numero de elementos esperado", 4, s.size());

		for(Integer elem: s){
			assertTrue("El elemento"+elem+"no es menor o igual que el de referencia: "+4, elem <= 4);
		}

		s = selector.seleccionaPredicado(coleccion, Condicion.MENOR, Integer.valueOf(4));
		assertEquals("No se ha obtenido el numero de elementos esperado", 3, s.size());

		for(Integer elem: s){
			assertTrue("El elemento"+elem+"no es menor que el de referencia: "+4, elem < 4);
		}
	}

	/**
	 * 
	 * @param coleccion
	 */
	private void compruebaSeleccionNoComparable(Collection<Coche> coleccion, Comparator<Coche> comparador){

		List<Coche> lista = GeneradorCoches.listaSecuencial(6);
		Set<Coche> s = null; 

		s = selector.seleccionaPredicado(coleccion, Condicion.MAYOR, lista.get(1), comparador);
		assertEquals("No se ha obtenido el numero de elementos esperado", 3, s.size());
		for(Coche elem: s){
			assertTrue("El elemento "+elem+" no es mayor que el de referencia: "+lista.get(1), comparador.compare(lista.get(1), elem) <0 );
		}

		s = selector.seleccionaPredicado(coleccion, Condicion.MAYORIGUAL, lista.get(1), comparador);
		assertEquals("No se ha obtenido el numero de elementos esperado", 4, s.size());
		for(Coche elem: s){
			assertTrue("El elemento"+elem+"no es mayor o igual que el de referencia: "+lista.get(1), comparador.compare(lista.get(1), elem) <= 0);
		}

		s = selector.seleccionaPredicado(coleccion, Condicion.IGUAL, lista.get(1), comparador);
		assertEquals("No se ha obtenido el numero de elementos esperado", 1, s.size());
		for(Coche elem: s){
			assertTrue("El elemento"+elem+"no es mayor o igual que el de referencia: "+lista.get(1), comparador.compare(lista.get(1), elem) == 0);
		}

		s = selector.seleccionaPredicado(coleccion, Condicion.MENORIGUAL, lista.get(1), comparador);
		assertEquals("No se ha obtenido el numero de elementos esperado", 3, s.size());
		for(Coche elem: s){
			assertTrue("El elemento"+elem+"no es menor o igual que el de referencia: "+lista.get(1), comparador.compare(lista.get(1), elem) >= 0);
		}

		s = selector.seleccionaPredicado(coleccion, Condicion.MENOR, lista.get(1), comparador);
		assertEquals("No se ha obtenido el numero de elementos esperado", 2, s.size());

		for(Coche elem: s){
			assertTrue("El elemento"+elem+"no es menor que el de referencia: "+lista.get(1),  comparador.compare(lista.get(1), elem) > 0);
		}
	}

	/**
	 * 
	 * @param coleccion
	 */
	private void compruebaDeseleccionComparable(Collection<Integer> coleccion){		

		Set<Integer> s = null;

		s = selector.eliminaPredicado(coleccion, Condicion.MAYOR, Integer.valueOf(4));
		assertEquals("No se ha obtenido el numero de elementos esperado",4,s.size());

		for(Integer elem: s){
			assertTrue("El elemento"+elem+"es mayor que el de referencia: "+4, elem <= 4);
		}

		s = selector.eliminaPredicado(coleccion, Condicion.MAYORIGUAL, Integer.valueOf(4));
		assertEquals("No se ha obtenido el numero de elementos esperado", 3, s.size());

		for(Integer elem: s){
			assertTrue("El elemento "+elem+" mayor o igual que el de referencia: "+4, elem < 4);
		}

		s = selector.eliminaPredicado(coleccion, Condicion.IGUAL, Integer.valueOf(4));
		assertEquals("No se ha obtenido el numero de elementos esperado", 5, s.size());

		for(Integer elem: s){
			assertTrue("El elemento "+elem+" es igual que el de referencia: "+4, elem != 4);
		}

		s = selector.eliminaPredicado(coleccion, Condicion.MENORIGUAL, Integer.valueOf(4));
		assertEquals("No se ha obtenido el numero de elementos esperado", 2, s.size());

		for(Integer elem: s){
			assertTrue("El elemento "+elem+" es menor o igual que el de referencia: "+4, elem > 4);
		}

		s = selector.eliminaPredicado(coleccion, Condicion.MENOR, Integer.valueOf(4));
		assertEquals("No se ha obtenido el numero de elementos esperado", 3, s.size());

		for(Integer elem: s){
			assertTrue("El elemento "+elem+" es menor que el de referencia: "+4, elem >= 4);
		}

	}


}
