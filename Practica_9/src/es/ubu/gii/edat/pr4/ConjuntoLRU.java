package es.ubu.gii.edat.pr4;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

import es.ubu.gii.edat.utils.cacheLRUEnlazada;

public class ConjuntoLRU<E> extends AbstractSet<E> implements SortedSet<E> {

	private static final long serialVersionUID = 1L;

	protected int MAXCAPACITY;
	protected int MAXELEMENT;

	protected cacheLRUEnlazada<E, E> control;

	public ConjuntoLRU(int maxSize) {
		super();
		this.MAXCAPACITY = maxSize;
		this.control = new cacheLRUEnlazada<E, E>(MAXCAPACITY);
	}

	@Override
	public boolean add(E e) {
		if (control.containsKey(e)) {
			return false;
		}

		control.put(e, e);

		return true;
	}

	@Override
	public boolean remove(Object o) {
		if (control.containsKey(o)) {
			control.remove(o);
			return true;
		}

		return false;
	}

	@Override
	public int size() {
		return control.size();
	}
	
	@Override
	public SortedSet<E> subSet(E a, E b) {
		// TODO Auto-generated method stub
        return null;	
	}
	
	@Override
	 public E first(){
		return null; 
	 }
	
	@Override
	 public E last(){
		return null; 
	 }
	
	@Override
    public SortedSet<E> tailSet(E fromElement){
		return null;
	}
    
    @Override
    public Comparator<? super E> comparator(){
    	return null;
    }

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<E> headSet(E toElement) {
		// TODO Auto-generated method stub
		return null;
	}

}
