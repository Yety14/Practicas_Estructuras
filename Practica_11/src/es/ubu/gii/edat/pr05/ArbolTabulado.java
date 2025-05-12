package es.ubu.gii.edat.pr05;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class ArbolTabulado<E> extends AbstractMap<E, E> {

	private Map<E, E> mapa; // nodo(K), padre(V)

	public ArbolTabulado() {
		mapa = new HashMap<E, E>();
	}

	public ArbolTabulado(int initSize) {
		mapa = new HashMap<E, E>(initSize);
	}

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

	@SuppressWarnings("unchecked")
	public E remove(Object objeto) {
		if (!mapa.containsKey(objeto)) {
			throw new IllegalArgumentException("No existe el objeto a eliminar.");
		}
		List<E> desc = descendants((E) objeto);
		E padre = mapa.get(objeto);
		List<E> hijosDirectos = new ArrayList<E>();
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

	public List<E> descendants(E elemento) {
		List<E> descendientes = new ArrayList<>();
		obtenerDescendientes(elemento, descendientes);
		return descendientes;
	}

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

	public List<E> ancestors(E elemento) {
		List<E> ancestros = new ArrayList<>();
		obtenerAncestros(elemento, ancestros);
		return ancestros;
	}

	private void obtenerAncestros(E elemento, List<E> ancestros) {
		E padre = mapa.get(elemento);
		if (padre != null) {
			ancestros.add(padre);
			obtenerAncestros(padre, ancestros);
		}
	}

	public int depth(E elemento) {
		List<E> ancestros = ancestors(elemento);
		return ancestros.size();
	}

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
					// por q si tiene 2 hijos miramos el q mas altura tenga
					maxAltura = alturaHijo;
				}
			}
		}
		return tieneHijos ? maxAltura + 1 : 0;
	}

	public List<E> breadthTraverse() {
		List<E> lista = new ArrayList<>();
		int altura = 0, actual = 0;
		for (E elem : mapa.keySet()) { // obtener la altura del arbol
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

	@Override
	public Set<Map.Entry<E, E>> entrySet() {
		return mapa.entrySet();
	}

}
