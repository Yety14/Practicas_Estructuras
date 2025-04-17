package es.ubu.inf.edat.datos.coches;

import java.util.Comparator;

public class ComparadorCaballos implements Comparator<Coche> {

	@Override
	public int compare(Coche c1, Coche c2) {
		return Integer.compare(c1.getCaballos(), c2.getCaballos());
	}
}
