package es.ubu.inf.edat.pr00;

import java.util.Arrays;

/**
 * 
 * Clase que permite el calculo de los momentos estadisticos sobre el 
 * contenido de un array de enteros almacenado en la misma.
 * 
 * Otras medidas estadisticas: Moda, Varianza, desviacion tipica
 * 
 * @author bbaruque
 *
 */
public class EstadisticasArray {

	public int [] vector;

	/**
	 * 
	 * @param contenido
	 */
	public void rellenaVector(int[] contenido){

		vector = new int[contenido.length];

		for (int i=0; i<contenido.length; i++){
			vector[i] = contenido[i];
		}

	}

	/**
	 * Metodo que calcula la media aritmetica del contenido de la lista.
	 * 
	 * La descripcion de ese calculo se puede encontrar en:
	 * https://es.wikipedia.org/wiki/Media_aritm%C3%A9tica
	 * 
	 * @return media aritmetica del contenido de la lista
	 */

	public float mediaAritmetica(){

		float media = 0;
		float sumatorio = 0; 
		float tamano = 0; 

		for ( int num : vector ){
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
	 * @return media geometrica del contenido de la lista
	 */
	
	public double mediaGeometrica(){
		
		double media = 0;
		double producto = 1; 		
		float tamano = 0;
		
		for ( int num : vector ){
			producto *= num;
			tamano++;
		}
		media = (double) Math.pow(producto,1/tamano);
		System.out.print(media);
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

	public float mediana(){

		float mediana = 0;
		int [] copiaVector = vector.clone();
		int tamano = copiaVector.length;

		Arrays.sort(copiaVector);

		if (copiaVector.length%2 == 0)
			mediana = copiaVector[tamano/2];
		else
			mediana = ( copiaVector[tamano/2] + copiaVector[tamano+1/2] ) / 2;
		
		return mediana;
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
	public float varianza(){
		// TODO A completar por el alumno
		return 0;
	}
	
}
