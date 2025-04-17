package es.ubu.gii.edat.s08;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.*;

public class SeleccionPredicados {

	public enum Condicion {
		MAYOR, MAYORIGUAL, IGUAL, MENORIGUAL, MENOR;

		public <E> boolean comparar(E elemento, E referencia, Comparator<E> comparador) {
			int cmp = comparador.compare(elemento, referencia);
			return switch (this) {
			case MAYOR -> cmp > 0;
			case MAYORIGUAL -> cmp >= 0;
			case IGUAL -> cmp == 0;
			case MENORIGUAL -> cmp <= 0;
			case MENOR -> cmp < 0;
			};
		}

		public <E extends Comparable<E>> boolean comparar(E elemento, E referencia) {
			int cmp = elemento.compareTo(referencia);
			return switch (this) {
			case MAYOR -> cmp > 0;
			case MAYORIGUAL -> cmp >= 0;
			case IGUAL -> cmp == 0;
			case MENORIGUAL -> cmp <= 0;
			case MENOR -> cmp < 0;
			};
		}
	}

	// ==== COMPARABLE ====

	public <E extends Comparable<E>> Set<E> seleccionaPredicado(Collection<E> coleccion, Condicion condicion,
			E referencia) {
		Set<E> resultado = crearSetMismoTipo(coleccion);
		if (coleccion instanceof SortedSet) {
			SortedSet<E> sorted = (SortedSet<E>) coleccion;
			for (E elemento : sorted) {
				if (condicion.comparar(elemento, referencia)) {
					resultado.add(elemento);
				}
			}
		} else {
			for (E elemento : coleccion) {
				if (condicion.comparar(elemento, referencia)) {
					resultado.add(elemento);
				}
			}
		}
		return resultado;
	}

	public <E extends Comparable<E>> Set<E> eliminaPredicado(Collection<E> coleccion, Condicion condicion,
			E referencia) {
		Set<E> resultado = crearSetMismoTipo(coleccion);
		for (E elemento : coleccion) {
			if (!condicion.comparar(elemento, referencia)) {
				resultado.add(elemento);
			}
		}
		return resultado;
	}

	// ==== COMPARATOR ====

	public <E> Set<E> seleccionaPredicado(Collection<E> coleccion, Condicion condicion, E referencia,
			Comparator<E> comparador) {
		System.out.println("referencia: " + referencia);
		System.out.println("condicion: " + condicion);

		Set<E> resultado = crearSetMismoTipo(coleccion);
		if (coleccion instanceof SortedSet) {
			SortedSet<E> sorted = (SortedSet<E>) coleccion;
			for (E elemento : sorted) {
				System.out.println(elemento.toString());
				if (condicion.comparar(elemento, referencia, comparador)) {
					resultado.add(elemento);
				}
			}
		} else {
			for (E elemento : coleccion) {
				System.out.println(elemento.toString());
				if (condicion.comparar(elemento, referencia, comparador)) {
					resultado.add(elemento);
				}
			}
		}
		System.out.println(resultado.size());

		return resultado;
	}

	public <E> Set<E> eliminaPredicado(Collection<E> coleccion, Condicion condicion, E referencia,
			Comparator<E> comparador) {
		Set<E> resultado = crearSetMismoTipo(coleccion);
		for (E elemento : coleccion) {
			if (!condicion.comparar(elemento, referencia, comparador)) {
				resultado.add(elemento);
			}
		}
		return resultado;
	}

	// ==== MÉTODO AUXILIAR ====

	private <E> Set<E> crearSetMismoTipo(Collection<E> coleccion) {
		if (coleccion instanceof SortedSet) {
			return new TreeSet<>(((SortedSet<E>) coleccion).comparator());
		} else if (coleccion instanceof LinkedHashSet) {
			return new LinkedHashSet<>();
		} else {
			return new HashSet<>();
		}
	}
}
