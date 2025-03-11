package es.ubu.gii.edat.s04;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

public class TestSeleccionListas {

	static SeleccionListas seleccionListas = new SeleccionListas();

	/**
	 * 
	 */
	@Test
	public void pruebaSelMul(){

		Integer[] enteros = {1, 2, 3, 4, 5, 6};
		int[] seleccionados = {0,3,5};

		List<Integer> lista = Arrays.asList(enteros);

		List<Integer> elegidos = seleccionListas.seleccionMultiple(lista, seleccionados);

		Integer[] respuesta = {1,4,6};
		List<Integer> esperados	= Arrays.asList(respuesta);

		System.out.println("esperados: "+esperados);
		System.out.println("elegidos: "+elegidos);

		assertEquals (3, elegidos.size());
		assertEquals (esperados, elegidos);

	}

	/**
	 * 
	 */
	@Test
	public void pruebaSelMul_outIni(){

		Integer[] enteros = {1, 2, 3, 4, 5, 6};
		int[] seleccionados = {-1,3,5};

		List<Integer> lista = Arrays.asList(enteros);

		try{
			List<Integer> elegidos = seleccionListas.seleccionMultiple(lista, seleccionados);
		}catch (IndexOutOfBoundsException iob){
			assertTrue("IndexOutOfBoundsException", true);
			return;
		}

		fail("Seleccion de indices no definidos por el inicio. Se esperaba IndexOutOfBoundsException");

	}
	
	@Test
	public void pruebaSelMul_outFin(){

		Integer[] enteros = {1, 2, 3, 4, 5, 6};
		int[] seleccionados = {0,3,25};

		List<Integer> lista = Arrays.asList(enteros);

		try{
			List<Integer> elegidos = seleccionListas.seleccionMultiple(lista, seleccionados);
		}catch (IndexOutOfBoundsException iob){
			assertTrue("IndexOutOfBoundsException", true);
			return;
		}

		fail("Seleccion de indices no definidos por el final. Se esperaba IndexOutOfBoundsException");

	}
	
	/**
	 * 
	 */
	@Test
	public void pruebaSelMul_dup(){

		Integer[] enteros = {1, 2, 3, 4, 5, 6};
		int[] seleccionados = {0,3,5,3};

		List<Integer> lista = Arrays.asList(enteros);

		List<Integer> elegidos = seleccionListas.seleccionMultiple(lista, seleccionados);

		Integer[] respuesta = {1,4,6,4};
		List<Integer> esperados	= Arrays.asList(respuesta);

		System.out.println("esperados: "+esperados);
		System.out.println("elegidos: "+elegidos);

		assertEquals (4, elegidos.size());
		assertEquals (esperados, elegidos);

	}
	
	/**
	 * 
	 */
	@Test
	public void pruebaSelMul_vacia(){

		int[] seleccionados = {0,3,5};
		List<Integer> lista = new ArrayList<Integer>();

		try{
			List<Integer> elegidos = seleccionListas.seleccionMultiple(lista, seleccionados);
		}catch (IndexOutOfBoundsException iob){
			assertTrue("IndexOutOfBoundsException", true);
			return;
		}

		fail("Lista vacía. Se esperaba IndexOutOfBoundsException");

	}

	/**
	 * 
	 */
	@Test
	public void pruebaSelMul_nula(){

		int[] seleccionados = {0,3,5};
		List<Integer> lista = null;

		try{
			List<Integer> elegidos = seleccionListas.seleccionMultiple(lista, seleccionados);
		}catch (NoSuchElementException nse){
			assertTrue("NoSuchElementException", true);
			return;
		}

		fail("Lista vacía. Se esperaba NoSuchElementException");

	}
	
	/**
	 * 
	 */
	@Test
	public void pruebaSelMul_nulos(){

		Integer[] enteros = {1, 2, 3, null, 5, 6};
		int[] seleccionados = {0,3,5};

		List<Integer> lista = Arrays.asList(enteros);

		List<Integer> elegidos = seleccionListas.seleccionMultiple(lista, seleccionados);

		Integer[] respuesta = {1,null,6};
		List<Integer> esperados	= Arrays.asList(respuesta);

		System.out.println("esperados: "+esperados);
		System.out.println("elegidos: "+elegidos);

		assertEquals (3, elegidos.size());
		assertEquals (esperados, elegidos);

	}
	
	/**
	 * 
	 */
	@Test
	public void pruebaSelMul_heterogenea(){

		int[] seleccionados = {0,2,5};

		List lista = new ArrayList();
		lista.add(1); lista.add(2);
		lista.add(new Character('b'));
		lista.add(4); lista.add(5); lista.add(6);
		// {1,2,b,4,5,6}
		
		List<Integer> elegidos = seleccionListas.seleccionMultiple(lista, seleccionados);

		List esperados = new ArrayList();
		esperados.add(1); 
		esperados.add(new Character('b'));
		esperados.add(6);
		//{1,b,6}

		System.out.println("esperados: "+esperados);
		System.out.println("elegidos: "+elegidos);

		assertEquals (3, elegidos.size());
		assertEquals (esperados, elegidos);

	}
	
	/**
	 * 
	 */
	@Test
	public void pruebaSelInv(){

		Integer[] enteros = {1, 2, 3, 4, 5, 6};
		int[] deseleccionados = {0,3,5};

		List<Integer> lista = Arrays.asList(enteros);

		List<Integer> elegidos = seleccionListas.seleccionInversaMultiple(lista, deseleccionados);

		Integer[] respuesta = {2,3,5};
		List<Integer> esperados	= Arrays.asList(respuesta);

		System.out.println("esperados: "+esperados);
		System.out.println("elegidos: "+elegidos);

		assertEquals (3, elegidos.size());
		assertEquals (esperados, elegidos);

	}

	/**
	 * 
	 */
	@Test
	public void pruebaSelInv_outIni(){

		Integer[] enteros = {1, 2, 3, 4, 5, 6};
		int[] seleccionados = {-1,3,5};

		List<Integer> lista = Arrays.asList(enteros);

		try{
			List<Integer> elegidos = seleccionListas.seleccionInversaMultiple(lista, seleccionados);
		}catch (IndexOutOfBoundsException iob){
			assertTrue("IndexOutOfBoundsException", true);
			return;
		}

		fail("Seleccion de indices no definidos por el inicio. Se esperaba IndexOutOfBoundsException");

	}
	
	/**
	 * 
	 */
	@Test
	public void pruebaSelInv_outFin(){

		Integer[] enteros = {1, 2, 3, 4, 5, 6};
		int[] seleccionados = {0,3,25};

		List<Integer> lista = Arrays.asList(enteros);

		try{
			List<Integer> elegidos = seleccionListas.seleccionInversaMultiple(lista, seleccionados);
		}catch (IndexOutOfBoundsException iob){
			assertTrue("IndexOutOfBoundsException", true);
			return;
		}

		fail("Seleccion de indices no definidos por el final. Se esperaba IndexOutOfBoundsException");

	}
	
	/**
	 * 
	 */
	@Test
	public void pruebaSelInv_dup(){

		Integer[] enteros = {1, 2, 3, 4, 5, 6};
		int[] seleccionados = {0,3,5,0};

		List<Integer> lista = Arrays.asList(enteros);

		List<Integer> elegidos = seleccionListas.seleccionInversaMultiple(lista, seleccionados);

		Integer[] respuesta = {2, 3, 5};
		List<Integer> esperados	= Arrays.asList(respuesta);

		System.out.println("esperados: "+esperados);
		System.out.println("elegidos: "+elegidos);

		assertEquals (3, elegidos.size());
		assertEquals (esperados, elegidos);

	}
	
	/**
	 * 
	 */
	@Test
	public void pruebaSelInv_vacia(){

		int[] seleccionados = {0,3,5};
		List<Integer> lista = new ArrayList<Integer>();

		try{
			List<Integer> elegidos = seleccionListas.seleccionInversaMultiple(lista, seleccionados);
		}catch (IndexOutOfBoundsException iob){
			assertTrue("IndexOutOfBoundsException", true);
			return;
		}

		fail("Lista vacía. Se esperaba IndexOutOfBoundsException");

	}

	/**
	 * 
	 */
	@Test
	public void pruebaSelInv_nula(){

		int[] seleccionados = {0,3,5};
		List<Integer> lista = null;

		try{
			List<Integer> elegidos = seleccionListas.seleccionInversaMultiple(lista, seleccionados);
		}catch (NoSuchElementException nse){
			assertTrue("NoSuchElementException", true);
			return;
		}

		fail("Lista vacía. Se esperaba NoSuchElementException");

	}
	
	/**
	 * 
	 */
	@Test
	public void pruebaSelInv_nulos(){

		Integer[] enteros = {1, 2, 3, null, 5, 6};
		int[] deseleccionados = {0,2,5};

		List<Integer> lista = Arrays.asList(enteros);

		List<Integer> elegidos = seleccionListas.seleccionInversaMultiple(lista, deseleccionados);

		Integer[] respuesta = {2,null,5};
		List<Integer> esperados	= Arrays.asList(respuesta);

		System.out.println("esperados: "+esperados);
		System.out.println("elegidos: "+elegidos);

		assertEquals (3, elegidos.size());
		assertEquals (esperados, elegidos);

	}
	
	/**
	 * 
	 */
	@Test
	public void pruebaSelInv_heterogenea(){

		int[] seleccionados = {0,1,5};

		List lista = new ArrayList();
		lista.add(1); lista.add(2);
		lista.add(new Character('b'));
		lista.add(4); lista.add(5); lista.add(6);
		// {1,2,b,4,5,6}
		
		List<Integer> elegidos = seleccionListas.seleccionInversaMultiple(lista, seleccionados);

		List esperados = new ArrayList();
		esperados.add(new Character('b'));
		esperados.add(4); esperados.add(5);

		System.out.println("esperados: "+esperados);
		System.out.println("elegidos: "+elegidos);

		assertEquals (3, elegidos.size());
		assertEquals (esperados, elegidos);

	}
	

	/**
	 * 
	 */
	@Test
	public void pruebaParticion(){

		Integer[] enteros = {1,2,3,4,5,6,7,8,9};
		int[] particion =   {0,0,1,1,0,2,1,2,2};

		List<Integer> lista = Arrays.asList(enteros);

		List<List<Integer>> elegidos = seleccionListas.particion(lista, particion);

		// Primera Particion
		Integer[] respuesta0 = {1,2,5};
		List<Integer> esperados0 = Arrays.asList(respuesta0);

		System.out.println("P0 - esperados: "+esperados0);
		System.out.println("P0 - elegidos: "+elegidos.get(0));

		assertEquals (3, elegidos.get(0).size());
		assertEquals (esperados0, elegidos.get(0));

		// Segunda Particion
		Integer[] respuesta1 = {3,4,7};
		List<Integer> esperados1 = Arrays.asList(respuesta1);

		System.out.println("P1 - esperados: "+esperados1);
		System.out.println("P1 - elegidos: "+elegidos.get(1));

		assertEquals (3, elegidos.get(1).size());
		assertEquals (esperados1, elegidos.get(1));

		// Tercera Particion
		Integer[] respuesta2 = {6,8,9};
		List<Integer> esperados2 = Arrays.asList(respuesta2);

		System.out.println("P2 - esperados: "+esperados2);
		System.out.println("P2 - elegidos: "+elegidos.get(2));

		assertEquals (3, elegidos.get(2).size());
		assertEquals (esperados2, elegidos.get(2));

	}

	//	@Test
	//	public void boobyTrap(){
	//		assertEquals("Esto falla siempre para probar.",true,false);
	//	}
	
} // Prueba
