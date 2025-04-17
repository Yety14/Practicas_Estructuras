package es.ubu.inf.edat.datos.coches;

public class Coche {
	private String marca;
	private String modelo;
	private int caballos;

	public Coche(String marca, String modelo, int caballos) {
		this.marca = marca;
		this.modelo = modelo;
		this.caballos = caballos;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public int getCaballos() {
		return caballos;
	}

	@Override
	public String toString() {
		return marca + " " + modelo + " (" + caballos + " CV)";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Coche coche = (Coche) obj;
		return caballos == coche.caballos && marca.equals(coche.marca) && modelo.equals(coche.modelo);
	}

	@Override
	public int hashCode() {
		return marca.hashCode() * 31 + modelo.hashCode() * 17 + caballos;
	}
}
