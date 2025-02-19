package es.ubu.gii.edat.s01;

public class Prueba {

	   public static void main(String[] args) {
		   
	        EnteroEnRango entero1 = new EnteroEnRango(5, 0, 10);
	        EnteroEnRango entero2 = new EnteroEnRango(3, 0, 8);

	        System.out.println("Valor inicial de entero1: " + entero1.getValor());
	        System.out.println("Valor inicial de entero2: " + entero2.getValor());

	        entero1.sumar(entero2);

	        System.out.println("Después de sumar entero1 con entero2: " + entero1.getValor());
	    }
	   
}
