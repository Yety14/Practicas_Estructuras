package es.ubu.gii.edat.s08;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.LinkedHashSet;

/**
 * Clase que proporciona métodos genéricos para seleccionar o eliminar elementos
 * de una colección según una condición de comparación.
 * 
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 */
public class SeleccionPredicados {

	/**
	 * Enum que representa las condiciones posibles para comparar elementos.
	 */
	public enum Condicion {

		/** Mayor que (&gt;). */ // hemos puesto las etiquetas HTML para que se vea el simbolo correcto.
		MAYOR,

		/** Mayor o igual que (&ge;). */
		MAYORIGUAL,

		/** Igual a (=). */
		IGUAL,

		/** Menor o igual que (&le;). */
		MENORIGUAL,

		/** Menor que (&lt;). */
		MENOR;

		/**
		 * Compara dos elementos usando un {@link Comparator} y esta condición.
		 *
		 * @param <E>        tipo de los elementos a comparar
		 * @param elemento   el elemento que se evalúa
		 * @param referencia el valor de referencia
		 * @param comparador el comparador que define el orden
		 * @return true si la condición se cumple, false en caso contrario
		 */
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

		/**
		 * Compara dos elementos que implementan {@link Comparable} según esta
		 * condición.
		 *
		 * @param <E>        tipo de los elementos comparables
		 * @param elemento   el elemento que se evalúa
		 * @param referencia el valor de referencia
		 * @return true si la condición se cumple, false en caso contrario
		 */
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

	/**
	 * Selecciona los elementos de la colección que cumplen una condición con
	 * respecto a un valor de referencia, usando {@link Comparable}.
	 *
	 * @param <E>        tipo de elementos comparables
	 * @param coleccion  colección de entrada
	 * @param condicion  condición a aplicar
	 * @param referencia valor con el que se comparan los elementos
	 * @return conjunto con los elementos que cumplen la condición
	 */
	public <E extends Comparable<E>> Set<E> seleccionaPredicado(Collection<E> coleccion, Condicion condicion,
			E referencia) {
		Set<E> resultado = crearSetMismoTipo(coleccion);
		for (E elemento : coleccion) {
			if (condicion.comparar(elemento, referencia)) {
				resultado.add(elemento);
			}
		}
		return resultado;
	}

	/**
	 * Elimina de la colección los elementos que cumplen una condición con respecto
	 * a un valor de referencia, usando {@link Comparable}.
	 *
	 * @param <E>        tipo de elementos comparables
	 * @param coleccion  colección de entrada
	 * @param condicion  condición a aplicar
	 * @param referencia valor con el que se comparan los elementos
	 * @return conjunto con los elementos que no cumplen la condición
	 */
	public <E extends Comparable<E>> Set<E> eliminaPredicado(Collection<E> coleccion, Condicion condicion,
			E referencia) {
		Set<E> resultado = crearSetMismoTipo(coleccion);
		for (E elemento : coleccion) {
			if (!condicion.comparar(elemento, referencia)) {
				resultado.add(elemento);
				coleccion.remove(elemento);
			}
		}
		return resultado;
	}

	// ==== COMPARATOR ====

	/**
	 * Selecciona los elementos de la colección que cumplen una condición con
	 * respecto a un valor de referencia, usando un {@link Comparator}.
	 *
	 * @param <E>        tipo de elementos
	 * @param coleccion  colección de entrada
	 * @param condicion  condición a aplicar
	 * @param referencia valor con el que se comparan los elementos
	 * @param comparador comparador que define el criterio de comparación
	 * @return conjunto con los elementos que cumplen la condición
	 */
	public <E> Set<E> seleccionaPredicado(Collection<E> coleccion, Condicion condicion, E referencia,
			Comparator<E> comparador) {

		Set<E> resultado = crearSetMismoTipo(coleccion);

		for (E elemento : coleccion) {
			if (condicion.comparar(elemento, referencia, comparador)) {
				resultado.add(elemento);
			}
		}
		return resultado;
	}

	/**
	 * Elimina de la colección los elementos que cumplen una condición con respecto
	 * a un valor de referencia, usando un {@link Comparator}.
	 *
	 * @param <E>        tipo de elementos
	 * @param coleccion  colección de entrada
	 * @param condicion  condición a aplicar
	 * @param referencia valor con el que se comparan los elementos
	 * @param comparador comparador que define el criterio de comparación
	 * @return conjunto con los elementos que no cumplen la condición
	 */
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

	/**
	 * Crea un nuevo conjunto del mismo tipo que la colección original. Si es un
	 * {@link SortedSet}, se mantiene el mismo comparador.
	 *
	 * @param <E>       tipo de los elementos
	 * @param coleccion colección original
	 * @return un nuevo conjunto del mismo tipo
	 */
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
