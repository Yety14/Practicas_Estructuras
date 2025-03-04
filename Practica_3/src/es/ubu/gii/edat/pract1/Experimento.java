package es.ubu.gii.edat.pract1;

import es.ubu.gii.edat.datos.GeneradorEnteros;
import java.util.List;
import java.util.Scanner;

public class Experimento {

    public static void main(String[] args) {
        // Try-with-resources para cerrar automáticamente el Scanner
        try (Scanner escaner = new Scanner(System.in)) {
            System.out.println("Dame el número de elementos a comparar:");
            int datos = escaner.nextInt();

            List<Integer> lista = GeneradorEnteros.listaAleatoria(datos);
            System.out.println("Lista generada: " + lista);

            long t0 = System.currentTimeMillis();
            System.out.println("Tiempo actual en milisegundos: " + t0);
        } catch (Exception e) {
            System.err.println("Error: Entrada no válida.");
            e.printStackTrace();
        }
    }
}
