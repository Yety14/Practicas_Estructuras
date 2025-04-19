package es.ubu.inf.edat.datos.coches;

/**
 * Representa un coche con marca, modelo y número de caballos.
 * 
 * @author <a href="mgv1029@alu.ubu.es">María Guzmán Valdezate</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @since 1.0
 * @version 1.0.1
 */
public class Coche {

	/**
	 * Marca del coche.
	 */
	private String marca;

	/**
	 * Modelo del coche.
	 */
	private String modelo;

	/**
	 * Número de caballos de potencia que tiene el coche.
	 */
	private int caballos;

	/**
	 * Crea un nuevo objeto {@code Coche} con la marca, modelo y caballos
	 * especificados.
	 *
	 * @param marca    la marca del coche
	 * @param modelo   el modelo del coche
	 * @param caballos el número de caballos del coche
	 */
	public Coche(String marca, String modelo, int caballos) {
		this.marca = marca;
		this.modelo = modelo;
		this.caballos = caballos;
	}

	/**
	 * Obtiene la marca del coche.
	 *
	 * @return la marca del coche
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Obtiene el modelo del coche.
	 *
	 * @return el modelo del coche
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * Obtiene el número de caballos del coche.
	 *
	 * @return el número de caballos
	 */
	public int getCaballos() {
		return caballos;
	}

	/**
	 * Devuelve una representación en forma de cadena del coche.
	 *
	 * @return una cadena que representa el coche
	 */
	@Override
	public String toString() {
		return marca + " " + modelo + " (" + caballos + " CV)";
	}

	/**
	 * Compara este coche con otro objeto para determinar si son iguales. Para que
	 * dos coches se consideren iguales tienen que tener la misma marca, modelo y
	 * número de caballos.
	 *
	 * @param obj el objeto con el que se va a comparar
	 * @return {@code true} si los objetos son iguales; {@code false} en caso
	 *         contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Coche coche = (Coche) obj;
		return caballos == coche.caballos && marca.equals(coche.marca) && modelo.equals(coche.modelo);
	}

	/**
	 * Devuelve un código hash para este coche.
	 *
	 * @return el código hash del coche
	 */
	@Override
	public int hashCode() {
		return marca.hashCode() * 31 + modelo.hashCode() * 17 + caballos;
	}
}
