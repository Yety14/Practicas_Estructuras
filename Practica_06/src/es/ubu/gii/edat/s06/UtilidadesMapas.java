package es.ubu.gii.edat.s06;

import java.util.*;

/**
 * Clase de utilidades para trabajar con mapas en Java. Proporciona métodos para
 * crear mapas a partir de colecciones o iteradores, invertir un mapa y generar
 * un multi-mapa inverso.
 * 
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 */

public class UtilidadesMapas {

	/**
	 * Crea un mapa a partir de dos colecciones: una de claves y otra de valores.
	 *
	 * @param <K>    el tipo de las claves
	 * @param <V>    el tipo de los valores
	 * @param keys   colección de claves
	 * @param values colección de valores
	 * @return un mapa que asocia cada clave con su valor correspondiente
	 * @throws IllegalArgumentException si las colecciones no tienen el mismo tamaño
	 */
	public static <K, V> Map<K, V> creaMapa(Collection<K> keys, Collection<V> values) throws IllegalArgumentException {
		if (keys.size() != values.size()) {
			throw new IllegalArgumentException("No coincide el tamaño de las claves con el de los valores");
		}

		Map<K, V> mapa = new HashMap<>();

		List<K> listaClaves = new ArrayList<>(keys);
		List<V> listaValores = new ArrayList<>(values);

		for (int i = 0; i < listaClaves.size(); i++) {
			mapa.put(listaClaves.get(i), listaValores.get(i));
		}

/*	    Iterator<K> keyIterator = keys.iterator();
	    Iterator<V> valueIterator = values.iterator();

	    while (keyIterator.hasNext() && valueIterator.hasNext()) {
	        mapa.put(keyIterator.next(), valueIterator.next());
	    }
	*/	return mapa;
	}

	/**
	 * Crea un mapa a partir de dos iteradores: uno de claves y otro de valores. El
	 * mapa se construye hasta que uno de los iteradores se quede sin elementos.
	 *
	 * @param <K>           el tipo de las claves
	 * @param <V>           el tipo de los valores
	 * @param keyIterator   iterador de claves
	 * @param valueIterator iterador de valores
	 * @return un mapa que asocia cada clave con su valor correspondiente
	 */
	public static <K, V> Map<K, V> creaMapa(Iterator<K> keyIterator, Iterator<V> valueIterator) {
		Map<K, V> mapa = new HashMap<>();

		while (keyIterator.hasNext() && valueIterator.hasNext()) {
			mapa.put(keyIterator.next(), valueIterator.next());
		}

		return mapa;
	}

	/**
	 * Invierte un mapa, intercambiando las claves por los valores. Si hay valores
	 * duplicados, se sobrescriben en el mapa resultante.
	 *
	 * @param <K> el tipo de las claves del mapa original
	 * @param <V> el tipo de los valores del mapa original
	 * @param map el mapa original a invertir
	 * @return un mapa con claves y valores invertidos
	 */
	public static <K, V> Map<V, K> mapaInverso(Map<K, V> map) {
		Map<V, K> mapaInvertido = new HashMap<>();

		for (Map.Entry<K, V> entrada : map.entrySet()) {
			mapaInvertido.put(entrada.getValue(), entrada.getKey());
		}

		return mapaInvertido;
	}
	
	/**
	 * Crea un multi-mapa inverso, donde cada valor del mapa original se asocia a
	 * una colección de claves que lo contenían.
	 *
	 * @param <K> el tipo de las claves del mapa original
	 * @param <V> el tipo de los valores del mapa original
	 * @param map el mapa original
	 * @return un mapa que asocia a cada valor una colección de claves
	 */
	// Método para crear un multi-mapa inverso
	public static <K, V> Map<V, Collection<K>> multiMapaInverso(Map<K, V> map) {
		Map<V, Collection<K>> multiMapa = new HashMap<>();

		for (Map.Entry<K, V> entrada : map.entrySet()) {
			multiMapa.putIfAbsent(entrada.getValue(), new ArrayList<>()); // Si no existe la clave, crea una nueva lista
			multiMapa.get(entrada.getValue()).add(entrada.getKey()); // Añade la clave a la lista
		}

		return multiMapa;
	}
}