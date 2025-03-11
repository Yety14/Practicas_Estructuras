package es.ubu.gii.edat.s04;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public final class SeleccionListas<E> {

	public List<E> seleccionMultiple(List<E> lista, int[] seleccionados) throws IndexOutOfBoundsException {

		if (lista == null) {
			throw new NoSuchElementException("La lista no puede ser null");

		}

		List<E> seleccionadosLista = new ArrayList<>();

		for (int elem : seleccionados) {
			if (elem >= 0 && elem < lista.size()) {
				seleccionadosLista.add(lista.get(elem));
			} else {
				throw new IndexOutOfBoundsException("Elemento pedido fuera del indice de la lista");
			}
		}

		return seleccionadosLista;
	}

	public List<E> seleccionInversaMultiple(List<E> lista, int[] eliminados)
			throws IndexOutOfBoundsException, NoSuchElementException {

		if (lista == null) {
			throw new NoSuchElementException("La lista no puede ser null");
		}

		List<E> seleccionadosLista = new ArrayList<>();
		int[] lista_nums = new int[lista.size()];

		for (int i = 0; i < lista.size(); i++) {
			lista_nums[i] = i;
		}
		for (int elem : eliminados) {
			if (elem >= 0 && elem < lista.size()) {
				lista_nums[elem] = -1;
			} else {
				throw new IndexOutOfBoundsException("Elemento pedido fuera del indice de la lista");
			}
		}
		for (int elem : lista_nums) {
			if (elem >= 0 && elem < lista.size()) {
				seleccionadosLista.add(lista.get(elem));
			}
		}
		return seleccionadosLista;
	}

	public List<List<E>> particion(List<E> lista, int[] destino) throws NoSuchElementException {

		if (lista == null) {
			throw new NoSuchElementException("La lista no puede ser null");
		}
		if (lista.size() != destino.length) {
			throw new NoSuchElementException("Tamaño de lista y destino distinta");
		}

		List<List<E>> partes = new ArrayList<>();

		for (int i = 0; i < destino.length; i++) {
			int sublistaIndex = destino[i];
			if (sublistaIndex >= 0) {
				while (sublistaIndex >= partes.size()) {
					partes.add(new ArrayList<E>());
				}
				partes.get(sublistaIndex).add(lista.get(i));
			}
		}
		return partes;
	}
}