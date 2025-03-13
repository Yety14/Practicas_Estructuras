package es.ubu.gii.edat.s04;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * Clase que proporciona métodos para realizar selecciones y particiones sobre
 * listas genéricas.
 *
 * @param <E> el tipo de elementos contenidos en la lista
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 */
public final class SeleccionListas<E> {

	/**
	 * Devuelve una nueva lista con los elementos seleccionados según los índices
	 * proporcionados.
	 *
	 * @param lista         la lista de la cual se seleccionarán los elementos
	 * @param seleccionados un array de índices que indican qué elementos se
	 *                      seleccionarán
	 * @return una lista con los elementos seleccionados
	 * @throws IndexOutOfBoundsException si algún índice está fuera del rango de la
	 *                                   lista
	 */
	public List<E> seleccionMultiple(List<E> lista, int[] seleccionados) throws IndexOutOfBoundsException {

		if (lista == null) {
			throw new NoSuchElementException("La lista no puede ser null");
		}

		List<E> seleccionadosLista = new ArrayList<>();

		for (int elem : seleccionados) {
			if (elem >= 0 && elem < lista.size()) {
				seleccionadosLista.add(lista.get(elem));
			} else {
				throw new IndexOutOfBoundsException("Elemento pedido fuera del índice de la lista");
			}
		}

		return seleccionadosLista;
	}

	/**
	 * Devuelve una nueva lista excluyendo los elementos cuyos índices están en el
	 * array de eliminados.
	 *
	 * @param lista      la lista original
	 * @param eliminados un array de índices que indican qué elementos serán
	 *                   eliminados
	 * @return una lista sin los elementos eliminados
	 * @throws IndexOutOfBoundsException si algún índice está fuera del rango de la
	 *                                   lista
	 * @throws NoSuchElementException    si la lista es {@code null}
	 */
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
				throw new IndexOutOfBoundsException("Elemento pedido fuera del índice de la lista");
			}
		}
		for (int elem : lista_nums) {
			if (elem >= 0 && elem < lista.size()) {
				seleccionadosLista.add(lista.get(elem));
			}
		}
		return seleccionadosLista;
	}

	/**
	 * Divide una lista en sublistas según el array de destino. Cada elemento de la
	 * lista original se coloca en una sublista determinada por el índice en el
	 * array de destino.
	 *
	 * @param lista   la lista original a ser particionada
	 * @param destino un array que indica en qué sublista debe ubicarse cada
	 *                elemento de la lista original
	 * @return una lista de sublistas con los elementos particionados
	 * @throws NoSuchElementException si la lista es {@code null} o si su tamaño no
	 *                                coincide con el del array destino
	 */
	public List<List<E>> particion(List<E> lista, int[] destino) throws NoSuchElementException {

		if (lista == null) {
			throw new NoSuchElementException("La lista no puede ser null");
		}
		if (lista.size() != destino.length) {
			throw new NoSuchElementException("Tamaño de lista y destino distinto");
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
