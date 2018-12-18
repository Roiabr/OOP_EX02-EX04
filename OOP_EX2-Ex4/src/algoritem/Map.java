package algoritem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

//import GUI.main_gui;
import Geom.Point3D;


import Geom.Point3D;

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

	public static Point3D ConvertorToScreen(double longitude ,double latitude,double pyHeighty,double pxwidth) {
		double x =  leftup.x() -longitude ;
		double y = rightup.y()-latitude;
		double XScreen = ((x/Longitudedistance)*pxwidth);
		double YScreen = ((y/Latitudedistance)*pyHeighty);
		return new Point3D(XScreen, YScreen);

	}

	public static  Point3D ConvertorFromScreen(double XScreen, double YScreen ,double pyHeight , double pxwidth) {

		double longitude = leftup.x() -XScreen/pxwidth*Longitudedistance;
		double latitude = rightup.y() -YScreen/pyHeight*Latitudedistance;
		return new Point3D(longitude, latitude);



	}

	public static void main(String[] args) {
		Map a=new Map();
//		System.out.println(a.getPositionOnScreen( 35.20747075,32.10247755));
//		System.out.println(a.getgps(713.0,547.0));

	}

}