package es.ubu.gii.edat.pr4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import es.ubu.gii.edat.utils.cacheLRUEnlazada;


public class TestConjuntoLRU {

	protected int MAXCAPACITY = 20;
	protected int MAXELEMENT = 30;
	
	protected cacheLRUEnlazada<Integer, Integer> control;
	protected ConjuntoLRU<Integer> cache;  

	@org.junit.Before
	public void SetUp(){
		control = new cacheLRUEnlazada<Integer, Integer>(MAXCAPACITY);
		cache = new ConjuntoLRU<Integer>(MAXCAPACITY);
	}

	@org.junit.After
	public void TearDown(){
		control.clear();
		cache.clear();
	}

	// TODO - hacer que en lugar de números se inserten caracteres, 
	// por distinguir luego entre el entero de acceso y el dato que se almacena
	
	// TODO - Lo lógico sería cambiar el entero por un objeto Date y al ir insertando,
	// si hace falta sumar 1 ms al menos para que el tiempo de acceso sea único

	// TODO - hacer unos tests con valores predefinidos para dar a los alumnos y guardar estos,
	// que se general aleatoriamente, para los adicionales.
	
	@Test
	public void insercionEspacio(){

		for (int i=0; i<MAXCAPACITY; i++){
			
			int clave = (int) (Math.random() * MAXELEMENT);

			if(control.containsKey(clave)){
				assertFalse("Incluyendo elemento ya incluido. Se esperaba false.",cache.add(clave));
			}else{
				assertTrue("Incluyendo elemento NO incluido. Se esperaba true.",cache.add(clave));
			}
			
			control.put(clave, i);
			
			assertEquals(control.size(), cache.size());
			assertTrue(control.keySet().containsAll(cache));
			
		}
		
	}

	@SuppressWarnings("unchecked")
	@Test
	public void insercionLlenado(){

		insercionEspacio();
		boolean llena = false, nueva = false;;
		
		for( int i = cache.size(); i<MAXCAPACITY*2; i++){

			if(cache.size() == MAXCAPACITY)
				llena = true;

			Map.Entry<Integer,Integer>[] controlArray = 
					control.entrySet().toArray(new Map.Entry[0]);
			int masAntiguo = controlArray[0].getKey();
			
			int clave = (int) (Math.random() * MAXELEMENT);
			
			if(control.containsKey(clave)){

				nueva = false;
				assertFalse("Incluyendo elemento ya incluido. Se esperaba false.",cache.add(clave));
				
			}else{
				
				nueva = true;
				assertTrue("Incluyendo elemento NO incluido. Se esperaba true.",cache.add(clave));

			}

			control.put(clave, i);
			
			assertEquals(control.size(), cache.size());
			
System.out.println(control.keySet());
System.out.println(cache);
			
			assertTrue(control.keySet().containsAll(cache));
			
			assertTrue(cache.size()<=MAXCAPACITY);
			if(llena && nueva)
				assertFalse(cache.contains(masAntiguo));			
			
		}
		
	}

	@SuppressWarnings("unchecked")
	@Test
	public void pruebaExtremos(){
		
		Map.Entry<Integer,Integer>[] controlArray;
		
		insertaCache(MAXELEMENT/3);
		
		controlArray = control.entrySet().toArray(new Map.Entry[0]);
		
		assertEquals(controlArray[0].getKey(), cache.first());
		assertEquals(controlArray[control.size()-1].getKey(), cache.last());

		// 20 inserciones (algunas repetidas)
		insertaCache(MAXELEMENT/3);

		controlArray = control.entrySet().toArray(new Map.Entry[0]);
		
		assertEquals(controlArray[0].getKey(), cache.first());
		assertEquals(controlArray[control.size()-1].getKey(), cache.last());
		
		// 30 inserciones (algunas repetidas)
		insertaCache(MAXELEMENT/3);

		controlArray = control.entrySet().toArray(new Map.Entry[0]);
		
		assertEquals(controlArray[0].getKey(), cache.first());
		assertEquals(controlArray[control.size()-1].getKey(), cache.last());
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void pruebaAccesos(){
		
		Map.Entry<Integer,Integer>[] controlArray;
		
		insertaCache(MAXELEMENT);
		
		Iterator<Integer> iter = cache.iterator();
		for(int i=0; i<MAXCAPACITY/2;i++){
			int clave = iter.next();
			control.get(clave);
		}
		
		controlArray = control.entrySet().toArray(new Map.Entry[0]);
		
		assertEquals(cache.first(),	controlArray[0].getKey());
		assertEquals(cache.last(),	controlArray[control.size()-1].getKey());
		
	}

	@Test
	public void pruebaAccesosAnteriores(){
		
		List<Integer> controlList;
		
		insertaCache(MAXELEMENT);
		
		controlList = new ArrayList<Integer>(control.keySet());
		
		int pos = (int) (Math.random() * Math.floor(controlList.size()));
		Set<Integer> anteriores = cache.headSet(controlList.get(pos));
		
		List<Integer> anterioresControl = controlList.subList(0,pos);
		
		assertEquals(anterioresControl.size(),anteriores.size());
		assertTrue(anterioresControl.containsAll(anteriores));
		
	}

	@Test
	public void pruebaAccesosPosteriores(){
		
		List<Integer> controlList;
		
		insertaCache(MAXELEMENT);
		
		controlList = new ArrayList<Integer>(control.keySet());
		
		int pos = (int) (Math.random() * Math.floor(controlList.size()));
		Set<Integer> posteriores = cache.tailSet(controlList.get(pos));
		
		List<Integer> posterioresControl = controlList.subList(pos,controlList.size());
		
		assertEquals(posterioresControl.size(),posteriores.size());
		assertTrue(posterioresControl.containsAll(posteriores));
		
	}

	/**
	 * Método auxiliar para 
	 * @param numInserciones
	 */
	protected void insertaCache(int numInserciones){

		for(int i=0; i<numInserciones; i++){

			int clave = (int) (Math.random() * MAXELEMENT);

			for(int j=10000; j < (int)(Math.random() * 1000000); j++ );
			
			control.put(clave,i);
			cache.add(clave);

		}

	} // insertaCache

}
