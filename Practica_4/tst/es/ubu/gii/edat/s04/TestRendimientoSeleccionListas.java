package es.ubu.gii.edat.s04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * Clase para la prueba de la complejidad algorítmica del ejercicio de listas
 * 
 * @author bbaruque
 *
 */
public class TestRendimientoSeleccionListas {

	private int maxSize = 100000;
	private List<Integer> l = null;
	SeleccionListas selector = new SeleccionListas();

	/**
	 * Inicializa una lista aleatoria con un numero determinado de elementos
	 * @param Max numero de elementos a incluir en la lista
	 */
	private void before(int Max){
		l = listaAleatoria(Max); 
	}

	/**
	 * Metodo para vaciar la lista. Se emplea para prepararla para la siguiente prueba.
	 */
	private void after(){
		l.clear();
	}

	/**
	 * Permite generar un listado de numeros enteros aleatorios (repetidos)
	 * El rango es [0, num Elementos de la lista]
	 * 
	 * @param Max numero de elementos de la lista
	 * @return
	 */
	private int[] arrayAleatorio(int Max){

		int[] array = new int[Max];

		for (int j=0; j<Max; j++)
			array[j] = (int) Math.round(Math.random()*Max);

		return array;

	}

	/**
	 * 
	 * @param Max
	 * @return
	 */
	private List<Integer> listaAleatoria(int Max){

		// TODO. Intercambiar diferentes tipos de lista para comprobar la diferencia en la complejidad algorítmica al utilizar cada una.

		//		List<Integer> lista = new ArrayList<Integer>();
		List<Integer> lista = new LinkedList<Integer>();

		for (int j=0; j<Max; j++)
			lista.add( (int) Math.round(Math.random()*Max) );

		return lista;

	} // listaAleatoria

	/**
	 * Comprueba el rendimiento al ir insertando un mayor numero de elementos en la 
	 * ListaOrdenada 
	 */

	private void testSeleccionMultiple(int Max) {

		int [] seleccionados = arrayAleatorio(Max/2);

		long ini = System.currentTimeMillis(); 
		List<Integer> s = selector.seleccionMultiple(l, seleccionados);
		long fin = System.currentTimeMillis();

		long elapsed = fin-ini;

		System.out.println("SeleccionMultiple,"+Max+","+elapsed);

	}

	// TODO. Completar los tests con un test similar para 'seleccionInversa' y 'particion' 

	@Test 
	public void ejecutaTests(){

		for (int i=0; i<= 10; i++){

			int Max = (maxSize/10)*i;

			before(Max); testSeleccionMultiple(Max); after();
		}
	}

}
