package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import Geom.Geom_element;


public class GisLayer implements GIS_layer {
	HashSet<GIS_element> LayerSet=new HashSet<GIS_element>();
	String namePath;
	MetaDataLayer layerdata;

	/**
	 * a constructor for the class
	 * @param name - the name  for the layer(the path)
	 */
	public GisLayer(String name) {
		// TODO Auto-generated constructor stub
		namePath = name;
		layerdata = new MetaDataLayer(name);
	}


	@Override
	public int size() {
		// TODO Auto-generated method stub
		return LayerSet.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return LayerSet.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return LayerSet.contains(o);
	}

	@Override
	public Iterator<GIS_element> iterator() {
		// TODO Auto-generated method stub
		return LayerSet.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return LayerSet.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return LayerSet.toArray(a);
	}

	@Override
	public boolean add(GIS_element e) {
		// TODO Auto-generated method stub

		return LayerSet.add(e);
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub

		return LayerSet.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return LayerSet.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_element> c) {
		// TODO Auto-generated method stub
		return LayerSet.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return LayerSet.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return LayerSet.removeAll(c);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		LayerSet.clear();

	}

	/**
	 * the method return the metaDate of the layer like name
	 * @return layerdata - the metaData
	 */
	@Override
	public Meta_data get_Meta_data() {
		// TODO Auto-generated method stub
		return layerdata;
	}

}
