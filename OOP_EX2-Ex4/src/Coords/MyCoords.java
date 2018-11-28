package Coords;

import Geom.Point3D;

public class MyCoords implements coords_converter {
	
	final double earthRadios = 6371000;
	final double LonNorm = 0.847091174;

	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		// TODO Auto-generated method stub
		double radianXV = Math.asin(local_vector_in_meter.x()  / earthRadios) ;
		double radianYV = Math.asin(local_vector_in_meter.y() /( earthRadios * LonNorm));
		double radianX = (gps.x() * Math.PI) / 180;
		double radianY = (gps.y() * Math.PI) /180;
		double sumRX = (radianX + radianXV) * 180/Math.PI;
		double sumRY = (radianY + radianYV) * 180/Math.PI;
		double sumZ = gps.z() + local_vector_in_meter.z();
		Point3D newGps = new Point3D(sumRX, sumRY, sumZ);
		return newGps;
	}

	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		// TODO Auto-generated method stub
		double diffLat = (gps1.x() - gps0.x());
		double diffLon = (gps1.y() - gps0.y());
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
		double diffLat = gps1.x() - gps0.x();
		double diffLon = gps1.y() - gps0.y();
		double radDiffLat = (diffLat * Math.PI)/180;
		double radDiffLon = (diffLon * Math.PI)/180;
		double toMeterX = Math.sin(radDiffLat) * earthRadios;
		double toMeterY = Math.sin(radDiffLon) * earthRadios * LonNorm;
		Point3D vector = new Point3D(toMeterX,toMeterY, gps1.z()-gps0.z());
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
	    MyCoords c1 = new MyCoords();
	    double dis = c1.distance3d(gps0, gps1);
	    double high = gps1.z()-gps0.z();
	    double eleveation = Math.toDegrees(Math.asin(high/dis));
	    azi[0] = brng;
	    azi[1] = eleveation;
	    azi[2] = dis;
	    return azi;
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		// TODO Auto-generated method stub
		
		return ((p.x() > -180 && p.x() < 180) && (p.y() > -90 && p.y() < 90) && (p.z()>-450));
	}


}
