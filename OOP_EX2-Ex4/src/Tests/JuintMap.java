package Tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GUI.MainWindow;
import Geom.Point3D;
import Map.Map;

class JuintMap {
	
	Point3D cor1 = new Point3D(35.2066832, 32.10256305);
	Point3D cor2 = new Point3D(35.20638557,32.10463247);
	Point3D pix1 = new Point3D(268.34568202642083,1191.7543815661488,650);
	Point3D pix2 = new Point3D(248.90936222180926,483.6648734482664,680);
	

	@Test
	void testGpsToPix() {
		assertEquals(("268.34568202642083,1191.7543815661488,0.0"), (""+Map.gpsToPix(cor1.x(), cor1.y(),1433,642)));

	}

	@Test
	void testPixToGps() {
		Map map = new Map();
		Point3D p1= new Point3D(268.34568202642083,1191.7543815661488);
		Point3D ans= new Point3D(map.pixToGps(p1.y(), p1.x(),1433,642));
		assertEquals("32.105261748976744,35.22082343508594,0.0",""+ ans);
		
		
	}

	@Test
	void testPixelDistance() {
		Map map = new Map();
		double dis = map.pixelDistance(pix1, pix2, 1433,642);
		assertEquals("231.81044808891687", ""+ dis);
	}

	@Test
	void testPixelangel() {
		Map map = new Map();
		double azi = map.pixelangel(pix1, pix2,1433,642);
		System.out.println(azi);
		assertEquals("231.81044808891687", ""+ azi);
	}

}
