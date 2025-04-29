package es.ubu.gii.edat.pr4;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

public class ConjuntoLRU<a> extends AbstractSet<a> implements SortedSet<a> {

	private static final long serialVersionUID = 1L;

	protected int MAXCAPACITY;
	protected int MAXELEMENT;

	protected cacheLRUEnlazada<a, a> control;

	public ConjuntoLRU(int maxSize) {
		super();
		this.MAXCAPACITY = maxSize;
		this.control = new cacheLRUEnlazada<a, a>(MAXCAPACITY);
	}

	@Override
	public boolean add(a e) {
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
	public SortedSet<a> subSet(a a, a b) {
		// TODO Auto-generated method stub
        return null;	
	}
	
	@Override
	 public a first(){
		return null; 
	 }
	
	@Override
	 public a last(){
		return null; 
	 }
	
	@Override
    public SortedSet<a> tailSet(a fromElement){
		return null;
	}
    
    @Override
    public Comparator<? super a> comparator(){
    	return null;
    }

	@Override
	public Iterator<a> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<a> headSet(a toElement) {
		// TODO Auto-generated method stub
		return null;
	}

}
