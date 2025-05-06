package es.ubu.gii.edat.pr05;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.HashMap;

public class ArbolTabulado<E> extends AbstractMap<E, E> {
	
    private Map<E,E> mapa; // nodo, padre
        
	public ArbolTabulado() {
		mapa = new HashMap<E,E>();
	}

	public ArbolTabulado(int initSize) {
		mapa = new HashMap<E,E>(initSize);
	}

	public E put(E hijo, E padre) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public E remove(Object objeto) {
		List<E> desc = descendants((E) objeto);
		List<E> anc = ancestors((E) objeto);

		if (desc.isEmpty()) {
			return mapa.remove(objeto);
		}
		for (E elemento : desc) {
			if (depth(elemento)==1) {
				mapa.put(elemento, anc.get(0));
				mapa.remove(elemento);
			}

		}
		return null;
	}

	public List<E> descendants(E elemento) {
		List<E> descendientes = new ArrayList<>();
		for (E clave : mapa.keySet()) {
			if (clave.equals(elemento)) {
				descendientes.add(elemento);
			}
		}
		return descendientes;
	}

	public List<E> ancestors(E elemento) {
		List<E> ancestros = new ArrayList<>();
		for (E valor : mapa) {
			if (valor.equals(elemento)) {
				ancestros.add(elemento);
			}
		}
		return ancestros;
	}
	
	public int depth(E elemento) {		//mal
		List<E> lista = descendants(elemento);
		int i = 0;
		for (E elem : lista) {
			for (E comp : lista) {
				if (!ancestry(elem).equals(ancestry(comp))) {
					i++;
				}
			}
		}
		return i;
	}

	private E ancestry(E elemento) {
		//q busque en el mapa el elemento y devuelva su padre
		return null;
	}
	public int height(E elemento) {		//mal
		List<E> lista = ancestors(elemento);
		return lista.size();
	}

	public List<E> breadthTraverse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Entry<E, E>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
