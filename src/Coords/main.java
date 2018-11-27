package Coords;
import Geom.Point3D;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			 Point3D p1 = new Point3D(32.103315, 35.209039, 670);
			 Point3D p2 = new Point3D(32.106352, 35.205225, 650);
			 Coord  C1  = new Coord();
			 double dis = C1.distance3d(p1, p2);
			 System.out.println(dis);
			 Point3D vector = C1.vector3D(p1, p2);
			 System.out.println(C1.add(p1, vector));
			 double [] azi = (C1.azimuth_elevation_dist(p1, p2));
			 System.out.println(azi[1]);
	}

}
