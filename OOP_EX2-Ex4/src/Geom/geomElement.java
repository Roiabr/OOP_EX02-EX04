package Geom;

public class geomElement implements Geom_element {
	String [] CSVfile;
	Point3D newpoint;
	public geomElement(String [] s) {
		CSVfile = s;
		double xP = Double.parseDouble(CSVfile[7]);
		double yP = Double.parseDouble(CSVfile[6]);
		double zP = Double.parseDouble(CSVfile[8]);
		newpoint = new Point3D(xP, yP, zP);
	}
	
	public Point3D getNewpoint() {
		return newpoint;
	}
	
	@Override
	public double distance3D(Point3D p) {
		
		
		return newpoint.distance3D(p);
	}

	@Override
	public double distance2D(Point3D p) {
	
		return newpoint.distance2D(p);
	}

	@Override
	public String toString() {
		return newpoint.x() + "," + newpoint.y();
	}

	@Override
	public void setNewpoint(Point3D p) {
		// TODO Auto-generated method stub
		newpoint = p;
		
	}

}
