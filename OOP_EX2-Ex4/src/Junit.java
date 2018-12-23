import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



import Coords.MyCoords;
import Geom.Point3D;

class Junit {

	@Test
	void testAdd() {
		Point3D p1 = new Point3D(32.103315, 35.209039, 670);
		Point3D p2 = new Point3D(32.2352, 35.20225, 650);
		Point3D p3 = new Point3D(0, 0, 0);
		MyCoords  C1  = new MyCoords();
		Point3D vector = C1.vector3D(p1, p2);
		System.out.println(vector);
		p3 = C1.add(p1, vector);
		assertEquals(p2.toString(), p3.toString());
	}

	@Test
	void testDistance3d() {
		Point3D p1 = new Point3D(32.103315, 35.209039, 670);
		Point3D p2 = new Point3D(32.106352, 35.205225, 650);
		MyCoords  C1  = new MyCoords();
		C1.distance3d(p1, p2);
		assertEquals(493.0523316830263, C1.distance3d(p1, p2));
		
	}

	@Test
	void testVector3D() {
		Point3D p1 = new Point3D(32.103315, 35.209039, 670);
		Point3D p2 = new Point3D(32.2352, 35.20225, 650);
		MyCoords  C1  = new MyCoords();
		Point3D vector = C1.vector3D(p1, p2);
		System.out.println(C1.vector3D(p1, p2));
		assertEquals("14664.929950374604,-639.4711223413741,-20.0", vector.toString());
	}

	@Test
	void testAzimuth_elevation_dist() {
		 Point3D p1 = new Point3D(32.103315, 35.209039, 670);
		 Point3D p2 = new Point3D(32.106352, 35.205225, 650);
		MyCoords  C1  = new MyCoords();
		double[] ans = C1.azimuth_elevation_dist(p1, p2);
		assertEquals("313.2304203264989", ans[0] + "");
		assertEquals("-2.3247635180912813", ans[1]+ "");
		assertEquals("493.0523316830263", ans[2]+"");
	}

	@Test
	void testIsValid_GPS_Point() {
		Point3D p1 = new Point3D(183,-99, 670);
		MyCoords  C1  = new MyCoords();
		assertFalse(C1.isValid_GPS_Point(p1));
		Point3D p2 = new Point3D(152,-45, 670);
		assertTrue(C1.isValid_GPS_Point(p2));
	}

}
