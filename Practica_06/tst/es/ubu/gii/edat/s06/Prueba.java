/**
 * Programa que prueba los métodos de la clase {@code Mapas}.
 * 
 * Al ejecutarse con las aserciones habilitadas (opción -ea o -enableassertions
 * de la máquina virtual), no debería saltar ninguna.
 */

package es.ubu.gii.edat.s06;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * Clase que prueba los métodos de la clase {@code Mapas}.
 * 
 * @author <a href="mailto:jjrodriguez@ubu.es">Juan José Rodríguez Diez</a>
 * 
 */
public class Prueba {

	/**
	 * Función que prueba {@code Mapas.creaMapa}.
	 */
	public static void pruebaCreaMapa() {

		System.out.println("pruebaCreaMapa");

		// Creamos un mapa que asocia cadenas a cadenas, comprobamos
		Map<String, String> mapStrStr = UtilidadesMapas.creaMapa(
				Arrays.asList("uno", "dos", "tres"),
				Arrays.asList("one", "two", "three"));
		System.out.println("mapStrStr:" + mapStrStr);
		assert mapStrStr.size() == 3;
		assert mapStrStr.get("uno").equals("one");
		assert mapStrStr.get("dos").equals("two");
		assert mapStrStr.get("tres").equals("three");
		assert mapStrStr.get("one") == null;

		// Creamos un mapa que asocia dobles a enteros, comprobamos
		Map<Double, Integer> mapDblInt = UtilidadesMapas.creaMapa(
				Arrays.asList(1.1, 2.2, 3.3, 4.4).iterator(),
				Arrays.asList(1, 2, 3, 4).iterator());
		System.out.println("mapDblInt:" + mapDblInt);
		assert mapDblInt.size() == 4;
		assert mapDblInt.get(1.1) == 1;
		assert mapDblInt.get(2.2) == 2;
		assert mapDblInt.get(3.3) == 3;
		assert mapDblInt.get(4.4) == 4;
		assert mapDblInt.get(5.5) == null;
	}

	/**
	 * Función que prueba {@code Mapas.mapaInverso}.
	 */
	public static void pruebaMapaInverso() {

		System.out.println("\npruebaMapaInverso");

		// Creamos un mapa de cadenas a cadenas, invertimos, comprobamos
		Map<String, String> mapStrStr = UtilidadesMapas.creaMapa(
				Arrays.asList("uno", "dos", "tres"),
				Arrays.asList("one", "two", "three"));
		System.out.println("mapStrStr:" + mapStrStr);
		mapStrStr = UtilidadesMapas.mapaInverso(mapStrStr);
		System.out.println("mapStrStr:" + mapStrStr);
		assert mapStrStr.equals(UtilidadesMapas.creaMapa(
				Arrays.asList("one", "two", "three"),
				Arrays.asList("uno", "dos", "tres")));

		// Creamos un mapa de cadenas a enteros, invertimos, comprobamos
		Map<String, Integer> mapStrInt = UtilidadesMapas.creaMapa(
				Arrays.asList("uno", "dos", "tres"), Arrays.asList(1, 2, 3));
		System.out.println("mapStrInt:" + mapStrInt);
		Map<Integer, String> mapIntStr = UtilidadesMapas.mapaInverso(mapStrInt);
		System.out.println("mapIntStr:" + mapIntStr);
		assert mapIntStr.equals(UtilidadesMapas.creaMapa(Arrays.asList(1, 2, 3),
				Arrays.asList("uno", "dos", "tres")));

		// Añadimos elementos al mapa de enteros a cadenas, invertimos,
		// comprobamos
		mapIntStr.put(4, "cuatro");
		mapIntStr.put(5, "cinco");
		System.out.println("mapIntStr:" + mapIntStr);
		mapStrInt = UtilidadesMapas.mapaInverso(mapIntStr);
		System.out.println("mapStrInt:" + mapStrInt);
		assert mapStrInt.equals(UtilidadesMapas.creaMapa(
				Arrays.asList("uno", "dos", "tres", "cuatro", "cinco"),
				Arrays.asList(1, 2, 3, 4, 5)));
	}

	/**
	 * Función que prueba {@code Mapas.multiMapaInverso}.
	 */
	public static void pruebaMultiMapaInverso() {

		System.out.println("\npruebaMultiMapaInverso");

		// Creamos un mapa de dobles a enteros
		Map<Double, Integer> mapDblInt = UtilidadesMapas.creaMapa(
				Arrays.asList(1.1, 2.2, 3.3, 4.4, 1.7, 3.8, 3.9),
				Arrays.asList(1, 2, 3, 4, 1, 3, 3));
		System.out.println("mapDblInt:" + mapDblInt);
		
		// Invertimos el mapa
		Map<Integer,Collection<Double>> mapIntColDbl = UtilidadesMapas.multiMapaInverso(mapDblInt);
		System.out.println("mapIntColDbl:" + mapIntColDbl);
		
		// Comprobamos
		assert mapIntColDbl.size() == 4;
		assert mapIntColDbl.get(1).size() == 2;
		assert mapIntColDbl.get(2).size() == 1;
		assert mapIntColDbl.get(3).size() == 3;
		assert mapIntColDbl.get(4).size() == 1;
		assert mapIntColDbl.get(1).contains(1.1);
		assert mapIntColDbl.get(1).contains(1.7);
		assert mapIntColDbl.get(3).contains(3.3);
		assert mapIntColDbl.get(3).contains(3.8);
		assert mapIntColDbl.get(3).contains(3.9);
	}
	

	/**
	 * Llama a las funciones de prueba, debería ejecutarse sin que salten
	 * aserciones.
	 * 
	 * @param args
	 *            parámetro de entrada en línea de comandos, se ignora.
	 */
	public static void main(String[] args) {
		pruebaCreaMapa();
		pruebaMapaInverso();
		pruebaMultiMapaInverso();
	}
}
