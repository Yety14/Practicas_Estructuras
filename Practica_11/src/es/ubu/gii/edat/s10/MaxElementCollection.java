package es.ubu.gii.edat.s10;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

/**
 * Implementación de un árbol tabulado utilizando un mapa para representar la
 * estructura jerárquica. Permite almacenar nodos junto con sus relaciones
 * padre-hijo y proporciona métodos para gestionar el árbol.
 *
 * @param <E> Tipo de elementos que almacena el árbol.
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 */
public class MaxElementCollection<E> extends AbstractMap<E, E> {

	/**
	 * Mapa que almacena la relación hijo-padre (clave-valor).
	 */
	private Map<E, E> mapa;

	/**
	 * Constructor por defecto que inicializa el mapa vacío.
	 */
	public MaxElementCollection() {
		mapa = new HashMap<E, E>();
	}

	/**
	 * Constructor que permite establecer el tamaño inicial del mapa.
	 *
	 * @param initSize Tamaño inicial del mapa.
	 */
	public MaxElementCollection(int initSize) {
		mapa = new HashMap<E, E>(initSize);
	}

	/**
	 * Inserta un hijo con su padre en el árbol.
	 *
	 * @param hijo  Nodo hijo a insertar.
	 * @param padre Nodo padre del hijo.
	 * @return El nodo padre anterior asociado al hijo o null si no existía.
	 * @throws IllegalArgumentException Si el padre no está previamente en el árbol
	 *                                  o ya existe una raíz.
	 */
	public E put(E hijo, E padre) {
		if (padre != null && !mapa.containsKey(padre)) {
			throw new IllegalArgumentException("El padre no está incluido previamente en el árbol.");
		}
		if (mapa.containsKey(hijo)) {
			return mapa.get(hijo);
		}
		if (padre == null && !mapa.values().isEmpty()) {
			throw new IllegalArgumentException("Ya existe una raíz en el árbol.");
		}
		return mapa.put(hijo, padre);
	}

	/**
	 * Elimina un nodo del árbol y reasigna sus hijos al padre del nodo eliminado.
	 *
	 * @param objeto Nodo a eliminar.
	 * @return El nodo padre del elemento eliminado o null si era la raíz.
	 * @throws IllegalArgumentException Si el nodo no existe en el árbol.
	 */
	@SuppressWarnings("unchecked")
	public E remove(Object objeto) {
		if (!mapa.containsKey(objeto)) {
			throw new IllegalArgumentException("No existe el objeto a eliminar.");
		}
		List<E> desc = descendants((E) objeto);
		E padre = mapa.get(objeto);
		List<E> hijosDirectos = new ArrayList<>();
		for (E elemento : desc) {
			if (mapa.get(elemento) == objeto) {
				hijosDirectos.add(elemento);
			}
		}
		if (mapa.get(objeto) == null) {
			E nuevoRaiz = hijosDirectos.get((int) (Math.random() * hijosDirectos.size()));
			mapa.put(nuevoRaiz, null);
			for (E hijo : hijosDirectos) {
				if (!hijo.equals(nuevoRaiz)) {
					mapa.put(hijo, nuevoRaiz);
				}
			}
			mapa.remove(objeto);
			return null;
		} else {
			if (desc.isEmpty()) {
				mapa.remove(objeto);
				return padre;
			}
			for (E elemento : hijosDirectos) {
				mapa.put((E) elemento, padre);
			}
			mapa.remove(objeto);
			return padre;
		}
	}

	/**
	 * Devuelve una lista con todos los descendientes de un nodo.
	 *
	 * @param elemento Nodo del que se quieren obtener los descendientes.
	 * @return Lista de nodos descendientes.
	 */
	public List<E> descendants(E elemento) {
		List<E> descendientes = new ArrayList<>();
		obtenerDescendientes(elemento, descendientes);
		return descendientes;
	}

	/**
	 * Método auxiliar para obtener recursivamente los descendientes de un nodo.
	 * 
	 * @param elemento      Nodo actual del que se quieren obtener los
	 *                      descendientes.
	 * @param descendientes Lista acumulada de descendientes.
	 */
	private void obtenerDescendientes(E elemento, List<E> descendientes) {
		for (Map.Entry<E, E> entrada : mapa.entrySet()) {
			E nodo = entrada.getKey();
			E padre = entrada.getValue();
			if (padre != null && padre.equals(elemento)) {
				descendientes.add(nodo);
				obtenerDescendientes(nodo, descendientes);
			}
		}
	}

	/**
	 * Devuelve una lista con todos los ancestros de un nodo.
	 *
	 * @param elemento Nodo del que se quieren obtener los ancestros.
	 * @return Lista de nodos ancestros.
	 */
	public List<E> ancestors(E elemento) {
		List<E> ancestros = new ArrayList<>();
		obtenerAncestros(elemento, ancestros);
		return ancestros;
	}

	/**
	 * Método auxiliar para obtener recursivamente los ancestros de un nodo.
	 *
	 * @param elemento  Nodo actual.
	 * @param ancestros Lista de ancestros acumulada.
	 */
	private void obtenerAncestros(E elemento, List<E> ancestros) {
		E padre = mapa.get(elemento);
		if (padre != null) {
			ancestros.add(padre);
			obtenerAncestros(padre, ancestros);
		}
	}

	/**
	 * Calcula la profundidad de un nodo en el árbol.
	 *
	 * @param elemento Nodo del que se quiere calcular la profundidad.
	 * @return Profundidad del nodo (número de ancestros).
	 */
	public int depth(E elemento) {
		List<E> ancestros = ancestors(elemento);
		return ancestros.size();
	}

	/**
	 * Calcula la altura de un nodo en el árbol.
	 *
	 * @param elemento Nodo del que se quiere calcular la altura.
	 * @return Altura del nodo (número máximo de niveles desde el nodo hasta una
	 *         hoja).
	 */
	public int height(E elemento) {
		boolean tieneHijos = false;
		int maxAltura = 0;

		for (Map.Entry<E, E> entry : mapa.entrySet()) {
			E valor = entry.getKey();
			E padre = entry.getValue();

			if (padre != null && padre.equals(elemento)) {
				tieneHijos = true;
				int alturaHijo = height(valor);
				if (alturaHijo > maxAltura) {
					maxAltura = alturaHijo;
				}
			}
		}
		return tieneHijos ? maxAltura + 1 : 0;
	}

	/**
	 * Realiza un recorrido en anchura del árbol.
	 *
	 * @return Lista de nodos en orden de recorrido por niveles.
	 */
	public List<E> breadthTraverse() {
		List<E> lista = new ArrayList<>();
		int altura = 0, actual = 0;
		for (E elem : mapa.keySet()) {
			if (height(elem) > altura) {
				altura = height(elem);
			}
		}
		while (actual <= altura) {
			for (E elem : mapa.keySet()) {
				if (actual == depth(elem)) {
					lista.add(elem);
				}
			}
			actual++;
		}
		return lista;
	}

	/**
	 * Devuelve el conjunto de entradas (pares clave-valor) del árbol.
	 *
	 * @return Conjunto de entradas del árbol
	 */
	@Override
	public Set<Map.Entry<E, E>> entrySet() {
		return mapa.entrySet();
	}
}
