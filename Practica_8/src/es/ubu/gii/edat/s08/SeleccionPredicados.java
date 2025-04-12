package es.ubu.gii.edat.s08;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SeleccionPredicados {
	public enum Condicion {
		MAYOR, MAYORIGUAL, IGUAL, MENORIGUAL, MENOR
	}

	public <E extends Comparable<E>> Set<E> seleccionaPredicado(Collection<E> coleccion, Condicion condicion,
			E referencia) {
		Set<E> resultado = crearSetDesde(coleccion);

		for (E elemento : coleccion) {
			if (cumpleCondicion(elemento.compareTo(referencia), condicion)) {
				resultado.add(elemento);
			}
		}

		return resultado;
	}

	public <E extends Comparable<E>> Set<E> eliminaPredicado(Collection<E> coleccion, Condicion condicion,
			E referencia) {
		Set<E> resultado = crearSetDesde(coleccion);

		for (E elemento : coleccion) {
			if (!cumpleCondicion(elemento.compareTo(referencia), condicion)) {
				resultado.add(elemento);
			}
		}

		return resultado;
	}

	// Para elementos con Comparator externo
	public <E> Set<E> seleccionaPredicado(Collection<E> coleccion, Condicion condicion, E referencia,
			Comparator<E> comparador) {
		Set<E> resultado = crearSetDesde(coleccion);

		for (E elemento : coleccion) {
			if (cumpleCondicion(comparador.compare(elemento, referencia), condicion)) {
				resultado.add(elemento);
			}
		}

		return resultado;
	}

	public <E> Set<E> eliminaPredicado(Collection<E> coleccion, Condicion condicion, E referencia,
			Comparator<E> comparador) {
		Set<E> resultado = crearSetDesde(coleccion);

		for (E elemento : coleccion) {
			if (!cumpleCondicion(comparador.compare(elemento, referencia), condicion)) {
				resultado.add(elemento);
			}
		}

		return resultado;
	}

	// Método auxiliar para comparar según la condición
	private boolean cumpleCondicion(int comparacion, Condicion condicion) {
		return switch (condicion) {
		case MAYOR -> comparacion > 0;
		case MAYORIGUAL -> comparacion >= 0;
		case IGUAL -> comparacion == 0;
		case MENORIGUAL -> comparacion <= 0;
		case MENOR -> comparacion < 0;
		};
	}

	// Crea el Set apropiado según el tipo de colección original
	private <E> Set<E> crearSetDesde(Collection<E> coleccion) {
		if (coleccion instanceof SortedSet<E> sortedSet) {
			Comparator<? super E> comp = sortedSet.comparator();
			return (comp != null) ? new TreeSet<>(comp) : new TreeSet<>();
		} else {
			return new HashSet<>();
		}
	}
}
