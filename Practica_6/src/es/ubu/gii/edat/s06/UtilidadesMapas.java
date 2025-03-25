package es.ubu.gii.edat.s06;

import java.util.*;

public class UtilidadesMapas {

	// Método para crear un mapa a partir de dos colecciones
	public static <K, V> Map<K, V> creaMapa(Collection<K> keys, Collection<V> values) {
		if (keys.size() != values.size()) {
			throw new IllegalArgumentException("No coincide el tamaño de las claves con el de los valores");
		}

		Map<K, V> mapa = new HashMap<>();

		List<K> listaClaves = new ArrayList<>(keys);
		List<V> listaValores = new ArrayList<>(values);

		for (int i = 0; i < listaClaves.size(); i++) {
			mapa.put(listaClaves.get(i), listaValores.get(i));
		}
		return mapa;
	}

	// Método para crear un mapa a partir de dos iteradores
	public static <K, V> Map<K, V> creaMapa(Iterator<K> keyIterator, Iterator<V> valueIterator) {
		Map<K, V> mapa = new HashMap<>();

		while (keyIterator.hasNext() && valueIterator.hasNext()) {
			mapa.put(keyIterator.next(), valueIterator.next());
		}

		return mapa;
	}

	// Método para invertir un mapa
	public static <K, V> Map<V, K> mapaInverso(Map<K, V> map) {
		Map<V, K> mapaInvertido = new HashMap<>();

		for (Map.Entry<K, V> entrada : map.entrySet()) {
			mapaInvertido.put(entrada.getValue(), entrada.getKey());
		}

		return mapaInvertido;
	}

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