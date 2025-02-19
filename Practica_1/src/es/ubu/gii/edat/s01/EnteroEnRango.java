package es.ubu.gii.edat.s01;

import java.util.Iterator;
import java.lang.IllegalArgumentException ;

public class EnteroEnRango implements Iterable<Integer> {

	private int valor;
	private int minimo;
	private int maximo;
	
	public EnteroEnRango(int valor, int minimo, int maximo) throws IllegalArgumentException {
		if(minimo<=valor && maximo>=valor && minimo<=maximo) {
			this.minimo=minimo;
			this.maximo=maximo;
			setValor(valor);
		}else {
			throw new IllegalArgumentException ("Numero fuera de rango");
		}
	}
	
	public int getValor() {
		return this.valor;
	}
	
	public void setValor(int nuevoValor) {
		this.valor=nuevoValor;
	}

    public int getValorMinimo() {
		return this.minimo;
	}
	
	public int getValorMaximo() {
		return this.maximo;
	}

    public void sumar(EnteroEnRango otro) {
    	if(otro.valor+this.valor>maximo){
			throw new IllegalArgumentException ("Numero fuera de rango");
    	}else {
    		setValor(otro.valor+this.valor);
    	}
    }

    public void restar(EnteroEnRango otro) {
    	if(this.valor-otro.valor<minimo){
			throw new IllegalArgumentException ("Numero fuera de rango");
    	}else {
    		setValor(this.valor-otro.valor);
    		this.maximo=otro.maximo;/////////////////////////////////////////
    	}
    }

    @Override
	public String toString() {
		return "EnteroEnRango [valor=" + valor + ", minimo=" + minimo + ", maximo=" + maximo + "]";
	}
    
    @Override
    public Iterator<Integer> iterator() {
    	return new EnteroEnRangoIterator();
    }

    private class EnteroEnRangoIterator implements Iterator<Integer> {
    	
    	private int valorActual=minimo;////////////////////////////////
    	private int maximoActual=maximo;
    	
        @Override
        public boolean hasNext() {
        	return this.valorActual<=this.maximoActual;
        }

        @Override
        public Integer next() {
        	return this.valorActual++;
        }
    }
}