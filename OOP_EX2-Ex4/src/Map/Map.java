package Map;

import Coords.MyCoords;
import Geom.Point3D;

/**
 * This class represents a Calculations on the map of the gui in the game.
 * @author Roi Abramovitch & Gal Hadida
 *
 */
public class Map {

	static final int mapWidth = 1433, mapHeight = 642;
	// offsets
	static final double mapLongitudeStart = 35.202574, mapLatitudeStart = 32.106046;
	// length of map in long/lat
	static final double mapLongitude = 35.212405-mapLongitudeStart, 
			// invert because it decreases as you go down
			mapLatitude = mapLatitudeStart-32.101858;
	final static  Point3D leftup = new Point3D(32.10537328713624, 35.20236396350326);
	final static Point3D leftdown = new Point3D(32.10232073464449, 35.20236396350326);
	final static Point3D rightup = new Point3D(32.10537328713624, 35.212430);
	static final Point3D rightdown = new Point3D(32.10232073464449, 35.212430);
	static final double Latitudedistance = rightup.y()-leftup.y();
	static double Longitudedistance = leftup.x()-leftdown.x();

	public Map()
	{

	}
	/**
	 * the method get points in Coordinates and change it to pixel on the screen
	 * @param longitude - the longtude of the map
	 * @param latitude 
	 * @param pyHeighty
	 * @param pxwidth
	 * @return Point3D - a new point in pixel
	 */
	public static Point3D gpsToPix(double longitude, double latitude,double pyHeighty,double pxwidth){
		double x=  longitude - mapLongitudeStart;
		double y = mapLatitudeStart-latitude;
		double x1 = (pxwidth*(x/mapLongitude));
		double y1 =  (pyHeighty*(y/mapLatitude));
		return new Point3D(x1, y1);
	}
	/**
	 * the method get points in pixel and change it to Coordinates on the screen
	 * @param x1
	 * @param y1
	 * @param pyHeighty
	 * @param pxwidth
	 * @return Point3D - point in Coordinates
	 */
	public static  Point3D pixToGps(double x1, double y1,double pyHeighty,double pxwidth)
	{
		double y=(y1/pyHeighty)*mapLatitude;
		double x=(x1/pxwidth)*mapLongitude;
		double longitude=x+mapLongitudeStart;
		double latitude=mapLatitudeStart-y;
		return new Point3D (latitude,longitude);

	}
	/**
	 * the method get the distence between 2 objects in the game
	 * @param pixel1 - a point 3d in pixel
	 * @param pixel2 - a point 3d in pixel
	 * @param pyHeighty
	 * @param pxwidth
	 * @return answer - the distance
	 */
	public static double pixelDistance (Point3D pixel1, Point3D pixel2,double pyHeighty,double pxwidth) {

		MyCoords m = new MyCoords();

		Point3D gps1 = pixToGps(pixel1.x(),pixel1.y(),pyHeighty,pxwidth);
		Point3D gps2 = pixToGps(pixel2.x(),pixel2.y(),pyHeighty,pxwidth);

		double answer=m.distance3d(gps1,gps2);

		return answer;
	}
	/**
	 * the method get the azimuth between 2 object in the game
	 * @param pixel1 - a point 3d in pixel
	 * @param pixel2 - a point 3d in pixel
	 * @param pyHeighty 
	 * @param pxwidth
	 * @return answer - the azimuth
	 */
	public static double pixelangel (Point3D pixel1, Point3D pixel2,double pyHeighty,double pxwidth) {

		MyCoords m = new MyCoords();

		Point3D gps1 = pixToGps(pixel1.x(),pixel1.y(),pyHeighty,pxwidth);
		Point3D gps2 = pixToGps(pixel2.x(),pixel2.y(),pyHeighty,pxwidth);

		double ans[]=m.azimuth_elevation_dist(gps1,gps2);
		double answer=ans[1];

		return answer;
	}

}