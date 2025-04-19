package es.ubu.inf.edat.datos.coches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase que genera listas de objetos {@link Coche} de forma secuencial o
 * mezclada.
 * 
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 */
public class GeneradorCoches {

	/**
	 * Genera una lista de {@code n} coches con marcas, modelos y caballos generados
	 * de forma secuencial.
	 * 
	 * @param n el número de coches a generar
	 * @return una lista con {@code n} coches en orden secuencial
	 */
	public static List<Coche> listaSecuencial(int n) {
		List<Coche> lista = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			lista.add(new Coche("Marca" + i, "Modelo" + i, 100 + i * 10));
		}
		return lista;
	}

	/**
	 * Genera una lista mezclada aleatoriamente de {@code n} coches.
	 *
	 * @param n el número de coches a generar
	 * @return una lista con {@code n} coches en orden aleatorio
	 */
	public static List<Coche> listaMezclada(int n) {
		List<Coche> lista = listaSecuencial(n);
		Collections.shuffle(lista);
		return lista;
	}
}
