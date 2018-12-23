package Coords;

import Geom.Point3D;

public class Coord implements coords_converter {
	
	final double earthRadios = 6371000;
	final double LonNorm = 0.847091174;

	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		// TODO Auto-generated method stub
		double radianXV = (local_vector_in_meter.x() *Math.PI) / 180;
		double radianYV =(local_vector_in_meter.y() * Math.PI) / 180;
		double radianX = (gps.x() *Math.PI) / 180;
		double radianY = (gps.y() * Math.PI) /180;
		double sumRX = (radianX + radianXV) *180/Math.PI;
		double sumRY = (radianY + radianYV) *180/Math.PI;
		Point3D newGps = new Point3D(sumRX, sumRY, gps.z());
		return newGps;
	}

	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		// TODO Auto-generated method stub
		double diffLat = (gps1.x() - gps0.x());
		double diffLon = (gps1.y() - gps0.y());
		double diffAlt = gps1.z() - gps0.z();
		double diff_radianX = ((diffLat * Math.PI)/180);
		double diff_radianY = ((diffLon * Math.PI)/180);
		double toMeterX = Math.sin(diff_radianX) * earthRadios;
		double toMeterY = Math.sin(diff_radianY) * LonNorm * earthRadios;
		double distance = Math.sqrt((toMeterX * toMeterX) +(toMeterY * toMeterY));
		return distance;
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		// TODO Auto-generated method stub
	
		double toMeterX1 = Math.sin(gps1.x()) * earthRadios;
		double toMeterY1 = Math.sin(gps1.y()) * LonNorm * earthRadios;
		double toMeterX0 = Math.sin(gps0.x()) * earthRadios;
		double toMeterY0 = Math.sin(gps0.y()) * LonNorm * earthRadios;
		Point3D vector = new Point3D(toMeterX1 - toMeterX0, toMeterY1-toMeterY0, gps1.z()-gps0.z());
		return vector;
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		// TODO Auto-generated method stub
		double[] azi = new double[3];
		double teta1 = (gps0.x()*Math.PI)/180;
	    double teta2 = (gps1.x()*Math.PI)/180;
	    
	    double delta2 = ((gps1.y()- gps0.y())*Math.PI)/180;

	    double y = Math.sin(delta2) * Math.cos(teta2);
	    double x = Math.cos(teta1) * Math.sin(teta2) - Math.sin(teta1)*Math.cos(teta2)*Math.cos(delta2);
	    double brng = Math.atan2(y,x);
	    brng = (brng*180)/ Math.PI ;// radians to degrees
	    brng = (((double)brng + 360) % 360 );
	    Coord c1 = new Coord();
	    double dis = c1.distance3d(gps0, gps1);
	    double ev = Math.atan2((gps1.z()-gps0.z()),dis);
	    azi[1] = ev;
	    return azi;
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		// TODO Auto-generated method stub
		return false;
	}


//	
}
