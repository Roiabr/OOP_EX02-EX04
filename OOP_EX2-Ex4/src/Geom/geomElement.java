package Geom;

public class geomElement implements Geom_element {
	String [] CSVfile;
	Point3D newpoint;
	
	/**
	 * a constrctur for geomElement
	 * @param s - an array of string that has the information for the element
	 */
	public geomElement(String [] s) {
		CSVfile = s;
		double xP = Double.parseDouble(CSVfile[7]);
		double yP = Double.parseDouble(CSVfile[6]);
		double zP = Double.parseDouble(CSVfile[8]);
		newpoint = new Point3D(xP, yP, zP);
	}
	/**
	 * @return newpoint - return the coordinats of the geomElement
	 */
	public Point3D getNewpoint() {
		return newpoint;
	}
	
	/**
	 * set the the points valus
	 */
	@Override
	public void setNewpoint(Point3D p) {
		// TODO Auto-generated method stub
		newpoint = p;
		
	}
	/**
	 * this method compute the distance betwneen the element coordinates and a diffrent coordinates
	 * @param p - a coordinats 
	 * @return - the distance between the two coordinates
	 */
	@Override
	public double distance3D(Point3D p) {
		
		return newpoint.distance3D(p);
	}
	/**
	 * this method compute the distance in 2D betwneen the element coordinates and a diffrent coordinates
	 * @param p - a coordinats 
	 * @return - the distance between the two coordinates
	 */
	@Override
	public double distance2D(Point3D p) {
	
		return newpoint.distance2D(p);
	}
	/**
	 * print the x and y valus of the coordinates
	 */
	@Override
	public String toString() {
		return newpoint.x() + "," + newpoint.y();
	}



}
