package Map;

import Geom.Point3D;

/**
 * 
 * @author Roi Abramovitch 
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

	public static Point3D gpsToPix(double longitude, double latitude,double pyHeighty,double pxwidth){
		double x=  longitude - mapLongitudeStart;
		double y = mapLatitudeStart-latitude;
		double x1 = (pxwidth*(x/mapLongitude));
		double y1 =  (pyHeighty*(y/mapLatitude));
		return new Point3D(x1, y1);
	}

	public static  Point3D pixToGps(double x1, double y1,double pyHeighty,double pxwidth)
	{
		double y=(y1/pyHeighty)*mapLatitude;
		double x=(x1/pxwidth)*mapLongitude;
		double longitude=x+mapLongitudeStart;
		double latitude=mapLatitudeStart-y;
		return new Point3D (latitude,longitude);
	}


}