package es.ubu.inf.edat.datos.coches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneradorCoches {

	public static List<Coche> listaSecuencial(int n) {
		List<Coche> lista = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			lista.add(new Coche("Marca" + i, "Modelo" + i, 100 + i * 10));
		}
		return lista;
	}

	public static List<Coche> listaMezclada(int n) {
		List<Coche> lista = listaSecuencial(n);
		Collections.shuffle(lista);
		return lista;
	}
}
