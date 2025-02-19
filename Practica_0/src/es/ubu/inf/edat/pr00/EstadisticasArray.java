package es.ubu.inf.edat.pr00;

import java.util.Arrays;

/**
 * 
 * Clase que permite el calculo de los momentos estadisticos sobre el contenido
 * de un array de enteros almacenado en la misma.
 * 
 * Otras medidas estadisticas: Moda, Varianza, desviacion tipica
 * 
 * @author bbaruque
 *
 */
public class EstadisticasArray {

	public int[] vector;

	/**
	 * En el bucle for la variable i se estaba inicializando a 1 en vez de a 0,
	 * haciendo que en vez de empezar a rellenar el vector en el primer hueco lo
	 * haga en el segundo, haciendo que se inicialice mal.
	 * 
	 * @param contenido
	 */
	public void rellenaVector(int[] contenido) {

		vector = new int[contenido.length];

		for (int i = 0; i < contenido.length; i++) {
			vector[i] = contenido[i];
		}

	}

	/**
	 * Metodo que calcula la media aritmetica del contenido de la lista.
	 * 
	 * La descripcion de ese calculo se puede encontrar en:
	 * https://es.wikipedia.org/wiki/Media_aritm%C3%A9tica
	 * 
	 * El sumatorio estaba inicializado a null en vez de a 0, porque si no luego
	 * suma a un valor nulo. El tamaño estaba inicializado a 1 en vez de a 0, lo
	 * queno está permitido en java.
	 * 
	 * El tamaño estaba inicializado a 1 en vez de a 0, haciendo que luego va
	 * recorriendo mal el array.
	 * 
	 * @return media aritmetica del contenido de la lista
	 */

	public float mediaAritmetica() {

		float media = 0;
		float sumatorio = 0;
		float tamano = 0;

		for (int num : vector) {
			sumatorio += num;
			tamano++;
		}

		media = sumatorio / tamano;

		return media;

	}

	/**
	 * Metodo que calcula la media geometrica del contenido de la lista.
	 * 
	 * La descripcion de ese calculo se puede encontrar en:
	 * https://es.wikipedia.org/wiki/Media_geom%C3%A9trica
	 * 
	 * 
	 * @return media geometrica del contenido de la lista
	 */

	public double mediaGeometrica() {

		double media = 0;
		double producto = 1;
		float tamano = 0;

		for (int num : vector) {
			producto *= num;
			tamano++;
		}
		media = (double) Math.pow(producto,1/tamano);
		return media;

	}

	/**
	 * Metodo que calcula la mediana del contenido de la lista.
	 * 
	 * La descripcion de ese calculo se puede encontrar en:
	 * https://es.wikipedia.org/wiki/Mediana_%28estad%C3%ADstica%29
	 * 
	 * @return mediana del contenido de la lista
	 */

	public float mediana() {

		float mediana = 0;
		int[] copiaVector = vector.clone();
		int tamano = copiaVector.length;
		Arrays.sort(copiaVector);

		if (tamano % 2 != 0) {	//impar
			mediana = copiaVector[tamano/2];
		}else {					//par
			int primer=(tamano-2);
			int segundo=(tamano-1);
			if(primer % 2!=0) 
				primer = (primer/2) +1;
			else
				primer = primer/2;
			if(segundo % 2!=0) 
				segundo = (segundo/2) +1;
			else
				segundo = segundo/2;
			mediana = (float)(copiaVector[primer] + copiaVector[segundo]) / 2;
			
		}return mediana;
	}

	/**
	 * 
	 * Metodo que calcula la varianza del contenido de la lista.
	 * 
	 * La descripcion de ese calculo se puede encontrar en:
	 * https://es.wikipedia.org/wiki/Varianza
	 * 
	 * @return
	 */
	public float varianza() {
		float sumatorio = 0;
		int tamano = 0;

		for (int num : vector) {
			tamano = tamano++;
			sumatorio = sumatorio + (num - mediaAritmetica());
		}
		float varianza = (float) (Math.pow(sumatorio, 2) / (tamano - 1));
		return varianza;
	}

}
