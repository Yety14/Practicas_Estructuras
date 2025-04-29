package es.ubu.gii.edat.pr4;

import java.util.AbstractSet;
import java.util.SortedSet;
import java.util.Comparator;
import java.util.Iterator;

import es.ubu.gii.edat.utils.cacheLRUEnlazada;

public class ConjuntoLRU<E> extends AbstractSet<E> implements SortedSet<E> {


	private int capacidad;
	private int contador;
	private cacheLRUEnlazada<E, E> mapa;
	private int inicial = 0;
	private int fin = 0;
    
	public ConjuntoLRU() {
        throw new UnsupportedOperationException("Constructor por defecto no permitido.");
    }
	
	public ConjuntoLRU(int maxSize) {
		super();
		this.capacidad = maxSize;
		this.mapa = new cacheLRUEnlazada<E, E>(capacidad);
		contador = 0;
	}

	@Override
	public boolean add(E e) {
		if (!mapa.containsValue(e)) {
			if (mapa.size() >= capacidad) {
				int min = 0;
				for (int i = 0; i < contador; i++) {
					if (mapa.containsKey(i)) {
						if (i < min) {
							min = i;
						}
					}
				}
				mapa.remove();
			}
		}
		if (mapa.containsKey(e)) {
			return false;
		}

		mapa.put(e, e);
		contador++;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		if (mapa.containsKey(o)) {
			mapa.remove(o);
			contador--;
			return true;
		}

		return false;
	}

	@Override
	public int size() {
		return mapa.size();
	}

	@Override
	public SortedSet<E> subSet(E a, E b) {
		// TODO Auto-generated method stub
        return null;	
	}
	
	private E encontrar(int i) {
        Iterator<E> it = iterator();
        int contador = 0;
        while (it.hasNext()) {
            E e = it.next();
            if (contador == i) {
                return e;
            }
            contador++;
        }
        return null;
	}
	
	@Override
	 public E first(){
		return encontrar(inicial); 
	 }
	
	@Override
	 public E last(){
		return encontrar(fin); 
	 }
	
	@Override
    public SortedSet<E> tailSet(E fromElement){
		return null;
	}

	@Override
	public E first() {
		return null;
	}

	@Override
	public E last() {
		return null;
	}

	@Override
	public SortedSet<E> tailSet(E fromElement) {
		return null;
	}

	@Override
	public Comparator<? super E> comparator() {
		return null;
	}


	@Override
	public SortedSet<E> headSet(E toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
