package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;



public class GisProject implements GIS_project {
	HashSet<GIS_layer> gp=new HashSet<GIS_layer>();
	String path;
	public GisProject(String a) {
		// TODO Auto-generated constructor stub
		path = a;
	} 
	public String getPath() {
		return path;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return gp.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return gp.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return gp.contains(o);
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		// TODO Auto-generated method stub
		return gp.iterator();
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
	public boolean add(GIS_layer e) {
		// TODO Auto-generated method stub
		return gp.add(e);
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return gp.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return gp.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		// TODO Auto-generated method stub
		return gp.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return gp.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return gp.removeAll(c);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		gp.clear();
	}

	@Override
	public Meta_data get_Meta_data() {
		// TODO Auto-generated method stub
		return null;
	}

}
