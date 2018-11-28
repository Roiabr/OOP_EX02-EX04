package Geom;

public interface Geom_element {
	
	public double distance3D(Point3D p) ;
	public double distance2D(Point3D p);
	public Point3D getNewpoint();
	
	public void setNewpoint(Point3D p1);
	
}
