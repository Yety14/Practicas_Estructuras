package es.ubu.inf.edat.datos.coches;

import java.util.Comparator;

/**
 * Comparador que compara objetos {@link Coche} según su número de caballos.
 * 
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 */

public class ComparadorCaballos implements Comparator<Coche> {

	/**
	 * Compara dos coches según su número de caballos.
	 *
	 * @param c1 el primer coche a comparar
	 * @param c2 el segundo coche a comparar
	 * @return un valor negativo si {@code c1} tiene menos caballos que {@code c2},
	 *         cero si tienen los mismos caballos, o un valor positivo si {@code c1}
	 *         tiene más caballos que {@code c2}
	 */
	@Override
	public int compare(Coche c1, Coche c2) {
		return Integer.compare(c1.getCaballos(), c2.getCaballos());
	}
}
