package es.ubu.inf.edat.pr00;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import es.ubu.inf.edat.pr00.EstadisticasArray;

public class EstadisticasArrayTest {

	/**
	 * Permite probar la clase de EstaditicasArray cuando el vector contiene un
	 * numero impar de elementos
	 */
	@Test
	public void pruebaPares() {

		int arrayPrueba[] = { 3, 6, 2, 8, 12, 57, 9, 25 };

		EstadisticasArray estadisticas = new EstadisticasArray();

		estadisticas.rellenaVector(arrayPrueba);

		System.out.println("-- Prueba Pares --");
		System.out.println("Media Aritmetica: " + estadisticas.mediaAritmetica());
		assertEquals(15.25, estadisticas.mediaAritmetica(), 0);

		System.out.println("Media Geometica: " + estadisticas.mediaGeometrica());
		assertEquals(9.03, estadisticas.mediaGeometrica(), 0.01);

		System.out.println("Mediana: " + estadisticas.mediana());
		assertEquals(8.5, estadisticas.mediana(), 0.01);

		System.out.println("Varianza: " + estadisticas.varianza());
		assertEquals(335.93, estadisticas.varianza(), 0.01);
	}

	/**
	 * Permite probar la clase de EstaditicasArray cuando el vector contiene un
	 * numero impar de elementos
	 */
	@Test
	public void pruebaImpares() {

		int arrayPrueba[] = { 5, 9, 43, 78, 21, 14, 28 };

		EstadisticasArray estadisticas = new EstadisticasArray();

		estadisticas.rellenaVector(arrayPrueba);

		System.out.println("-- Prueba Impares --");
		System.out.println("Media Aritmetica: " + estadisticas.mediaAritmetica());
		assertEquals(28.28, estadisticas.mediaAritmetica(), 0.01);

		System.out.println("Media Geometica: " + estadisticas.mediaGeometrica());
		assertEquals(19.91, estadisticas.mediaGeometrica(), 0.01);

		System.out.println("Mediana: " + estadisticas.mediana());
		assertEquals(21, estadisticas.mediana(), 0.01);

		System.out.println("Varianza: " + estadisticas.varianza());
		assertEquals(643.24, estadisticas.varianza(), 0.01);

	}

}
