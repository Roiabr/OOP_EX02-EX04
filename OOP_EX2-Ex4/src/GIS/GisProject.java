package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;



public class GisProject implements GIS_project {
	HashSet<GIS_layer> ProjectSet=new HashSet<GIS_layer>();
	String path;
	MetaDataProject projectData;
	/**
	 * a constructor for the project
	 * @param Name - the name of the project
	 */
	public GisProject(String Name) {
		// TODO Auto-generated constructor stub
		path = Name;
		projectData = new MetaDataProject(Name);
	} 
	
	public String getPath() {
		return path;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return ProjectSet.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return ProjectSet.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return ProjectSet.contains(o);
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		// TODO Auto-generated method stub
		return ProjectSet.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return ProjectSet.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return ProjectSet.toArray(a);
	}

	@Override
	public boolean add(GIS_layer e) {
		// TODO Auto-generated method stub
		return ProjectSet.add(e);
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return ProjectSet.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return ProjectSet.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		// TODO Auto-generated method stub
		return ProjectSet.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return ProjectSet.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return ProjectSet.removeAll(c);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		ProjectSet.clear();
	}
	
	/**
	 * the mathod return all the metadata of the project(name)
	 * @return projectData - the matadata of the project
	 */
	@Override
	public Meta_data get_Meta_data() {
		// TODO Auto-generated method stub
		return projectData;
	}

}
