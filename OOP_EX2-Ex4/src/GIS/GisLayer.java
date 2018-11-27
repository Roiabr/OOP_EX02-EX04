package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GisLayer implements GIS_layer {
	HashSet<GIS_element> gte=new HashSet<GIS_element>();


	@Override
	public int size() {
		// TODO Auto-generated method stub
		return gte.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return gte.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return gte.contains(o);
	}

	@Override
	public Iterator<GIS_element> iterator() {
		// TODO Auto-generated method stub
		return gte.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(GIS_element e) {
		// TODO Auto-generated method stub
		
		return gte.add(e);
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub

		return gte.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return gte.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_element> c) {
		// TODO Auto-generated method stub
		return gte.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return gte.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return gte.removeAll(c);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Meta_data get_Meta_data() {
		// TODO Auto-generated method stub
		return null;
	}

}
