package es.ubu.gii.edat.pract03;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Estructura de datos que permite asociar una clave con varios valores.
 *
 * @param <K> Tipo de las claves.
 * @param <V> Tipo de los valores.
 */
public class MultiMapa<K, V> extends AbstractMap<K, V> {

	/**
	 * Mapa principal que guarda listas de valores por cada clave.
	 */
	private final Map<K, List<V>> mapa;

	/**
	 * Número total de valores almacenados (no de claves).
	 */
	private int tamano = 0;

	/**
	 * Guarda el último índice accedido por clave, para realizar un acceso rotativo.
	 */
	public final Map<K, Integer> ultimoAcceso;

	/**
	 * Constructor por defecto. Inicializa las estructuras internas.
	 */
	public MultiMapa() {
		this.mapa = new HashMap<>();
		this.ultimoAcceso = new HashMap<>();
	}

	/**
	 * Devuelve todas las asociaciones clave-valor del multimap.
	 *
	 * @return Conjunto con todas las entradas.
	 */
	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> entradas = new LinkedHashSet<>();
		for (Map.Entry<K, List<V>> entry : mapa.entrySet()) {
			for (V valor : entry.getValue()) {
				entradas.add(new AbstractMap.SimpleEntry<>(entry.getKey(), valor));
			}
		}
		return entradas;
	}

	/**
	 * Añade un valor nuevo asociado a una clave.
	 *
	 * @param clave Clave con la que se asociará el valor.
	 * @param valor Valor que se quiere añadir.
	 * @return El valor que se ha insertado.
	 */
	public V put(K clave, V valor) {
		if (!mapa.containsKey(clave)) {
			mapa.put(clave, new ArrayList<>());
		}
		mapa.get(clave).add(valor);
		tamano++;
		return valor;
	}

	/**
	 * Inserta varios valores para una misma clave.
	 *
	 * @param clave   Clave a la que se asociarán los valores.
	 * @param valores Lista de valores a añadir.
	 */
	public void putAllMappings(K clave, List<V> valores) {
		if (!mapa.containsKey(clave)) {
			mapa.put(clave, new ArrayList<>());
		}
		mapa.get(clave).addAll(valores);
		tamano += valores.size();
	}

	/**
	 * Recupera todos los valores asociados a una clave.
	 *
	 * @param clave Clave para la que se quieren obtener los valores.
	 * @return Lista de valores asociados. Si no hay, devuelve una lista vacía.
	 */
	public List<V> getAllMappings(Object clave) {
		List<V> valores = mapa.get(clave);
		return valores;
	}

	/**
	 * Elimina todos los valores de una clave dada.
	 *
	 * @param clave Clave cuyos valores se quieren borrar.
	 * @return Lista con los valores eliminados, o vacía si no había ninguno.
	 */
	public List<V> removeAllMappings(Object clave) {
		List<V> eliminados = mapa.remove(clave);
		if (eliminados != null) {
			tamano -= eliminados.size();
		}
		return eliminados != null ? eliminados : new ArrayList<>();
	}

	/**
	 * Borra todo el contenido del multimap.
	 */
	@Override
	public void clear() {
		mapa.clear();
		tamano = 0;
	}

	/**
	 * Devuelve un valor de la clave indicada. Si hay varios, los devuelve de forma
	 * rotativa.
	 *
	 * @param clave Clave cuyo valor se quiere recuperar.
	 * @return Un valor asociado, o null si no existe ninguno.
	 */
	public V get(Object clave) {
		List<V> valores = mapa.get(clave);
		if (valores == null || valores.isEmpty()) {
			return null;
		}

		int indice = ultimoAcceso.getOrDefault(clave, 0);
		if (indice >= valores.size()) {
			indice = 0;
		}

		ultimoAcceso.put((K) clave, indice + 1);
		return valores.get(indice);
	}

	/**
	 * Elimina y devuelve el primer valor asociado a la clave (FIFO).
	 *
	 * @param clave Clave cuyo primer valor se quiere eliminar.
	 * @return Valor eliminado, o null si no había ninguno.
	 */
	public V remove(Object clave) {
		List<V> valores = mapa.get(clave);
		if (valores == null || valores.isEmpty()) {
			return null;
		}

		V eliminado = valores.remove(0);
		tamano--;

		if (valores.isEmpty()) {
			mapa.remove(clave);
			ultimoAcceso.remove(clave);
		} else {
			ultimoAcceso.put((K) clave, 0);
		}

		return eliminado;
	}

	/**
	 * Comprueba si existe al menos una ocurrencia del valor dado.
	 *
	 * @param valor Valor a buscar.
	 * @return true si está presente en algún lugar, false en caso contrario.
	 */
	@Override
	public boolean containsValue(Object valor) {
		return mapa.containsValue(valor);
	}

	/**
	 * Indica si existe alguna clave igual a la especificada.
	 *
	 * @param clave Clave a comprobar.
	 * @return true si está contenida en el mapa, false si no.
	 */
	@Override
	public boolean containsKey(Object clave) {
		return mapa.containsKey(clave);
	}

	/**
	 * Devuelve todos los valores del multimap, sin distinción de clave.
	 *
	 * @return Colección con todos los valores.
	 */
	@Override
	public Collection<V> values() {
		List<V> resultado = new ArrayList<>();
		for (List<V> lista : mapa.values()) {
			resultado.addAll(lista);
		}
		return resultado;
	}

	/**
	 * Número total de valores almacenados (no de claves).
	 *
	 * @return Tamaño total del multimap.
	 */
	@Override
	public int size() {
		return tamano;
	}

	/**
	 * Indica si no hay ningún valor almacenado.
	 *
	 * @return true si el multimap está vacío, false en caso contrario.
	 */
	@Override
	public boolean isEmpty() {
		return tamano == 0;
	}
}
