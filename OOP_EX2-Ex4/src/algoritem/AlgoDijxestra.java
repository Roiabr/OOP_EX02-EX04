package algoritem;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;

import com.sun.tools.javac.code.Type.ForAll;

import Game.Block;
import Game.Fruit;
import Game.Game;
import Game.Packman;
import Game.Packmen_me;
import Robot.Play;
import graph.Graph;
import graph.Graph_Algo;
import graph.Node;
import sun.java2d.pipe.SpanShapeRenderer;
import Geom.Point3D;
import java.awt.Shape;
import java.awt.geom.Line2D;

public class AlgoDijxestra  {
	private Play play1;
	private Game game1;
	public static ArrayList<Block> BlockTOuse= new ArrayList<Block>();
	public static ArrayList<Line2D> LINE= new ArrayList<Line2D>();
	private static ArrayList<Point3D> path = new ArrayList<Point3D>();
	public static int SizeNode;
	public static Graph Kosher;
	public static Point3D[]p3d;
	boolean freeOrGraph = false;
	int flag;


	public ArrayList<Line2D> getLINE() {
		return LINE;

	}


	public void setLINE(ArrayList<Line2D> lINE) {
		LINE = lINE;
	}


	public void MakeLine(Game game) {
		BlockTOuse= new ArrayList<Block>(game.getBlock());
		Iterator<Block>gal = BlockTOuse.iterator();

		while(gal.hasNext()) {
			Block sd = gal.next();
			Line2D templine  = new Line2D.Double(sd.getpoint_BlockTopLeft().x(),sd.getpoint_BlockTopLeft().y(),sd.getpoint_BlockTopRight().x(),sd.getpoint_BlockTopRight().y());
			Line2D templine1  = new Line2D.Double(sd.getpoint_BlockTopLeft().x(),sd.getpoint_BlockTopLeft().y(),sd.getpoint_BlockDownleft().x(),sd.getpoint_BlockDownleft().y());
			Line2D templine2  = new Line2D.Double(sd.getpoint_BlockTopLeft().x(),sd.getpoint_BlockTopLeft().y(),sd.getPoint_BlockDownRight().x(),sd.getPoint_BlockDownRight().y());
			Line2D templine3  = new Line2D.Double(sd.getpoint_BlockTopRight().x(),sd.getpoint_BlockTopRight().y(),sd.getpoint_BlockDownleft().x(),sd.getpoint_BlockDownleft().y());
			Line2D templine4  = new Line2D.Double(sd.getpoint_BlockTopRight().x(),sd.getpoint_BlockTopRight().y(),sd.getPoint_BlockDownRight().x(),sd.getPoint_BlockDownRight().y());
			Line2D templine5  = new Line2D.Double(sd.getpoint_BlockDownleft().x(),sd.getpoint_BlockDownleft().y(),sd.getPoint_BlockDownRight().x(),sd.getPoint_BlockDownRight().y());
			LINE.add(templine);
			LINE.add(templine1);
			LINE.add(templine2);
			LINE.add(templine3);
			LINE.add(templine4);
			LINE.add(templine5);
		}
	}


	public ArrayList<Block> getBlock() {
		return BlockTOuse;
	}

	public void setBlock(ArrayList<Block> block) {
		this.BlockTOuse = block;
	}

	public AlgoDijxestra(Game game) {
		MakeLine(game);
		Kosher = new Graph();
		int id = 0;
		double dis = 0;
		double bestDis = 100000000;
		int TempId = 0;
		Iterator<Fruit> fru = game.getFruit().iterator();
		Node b=null;
		while(fru.hasNext()) {
			Fruit f = fru.next();
			if(freeLine(game.getPlayer(), f)) {
				dis = game.getPlayer().getFirst().distance2D(f.getPointer_fruit());
				TempId = f.getIdfruit();
				freeOrGraph = false;
			}
			else {
				MakeGraph(game,game.getPlayer(),f);
				Graph_Algo.dijkstra(Kosher, game.getPlayer().getType());
				b = Kosher.getNodeByName(f.getType());
				dis = b.getDist();
				ArrayList<String> shortestPath = b.getPath();
				for(int i=0;i<shortestPath.size();i++) {
					System.out.print(","+shortestPath.get(i));
				}
				freeOrGraph  = true;
				TempId = f.getIdfruit();
			}
			if(dis<bestDis) {
				bestDis = dis;
				id = TempId;
				if(freeOrGraph) {
					flag = 1;
				}
				else {
					flag = 2;
				}
			}
		}
		
		if(flag == 1) {
			ArrayList<String> shortestPath = b.getPath();
			for(int i=1;i<shortestPath.size();i++) {
				int y=Kosher.getNodeIndexByName(shortestPath.get(i));
				path.add(p3d[y]);
			}
		}
		else
		{
			Iterator<Fruit> fru2 = game1.getFruit().iterator();
			while(fru2.hasNext()) {
				Fruit f = fru2.next();
				if(f.getIdfruit() == id) {
					path.add(f.getPointer_fruit());
				}
			}
		}

	}


	public static ArrayList<Point3D> getPath() {
		return path;
	}


	public static boolean freeLine(Packmen_me me,Fruit g) {
		Line2D templine = new Line2D.Double(me.getFirst().x(), me.getFirst().y(),g.getPointer_fruit().x(), g.getPointer_fruit().y());
		Iterator<Line2D>RectangleLines =LINE.iterator();
		while(RectangleLines.hasNext()) {
			Line2D fs = RectangleLines.next();
			if(fs.intersectsLine(templine)) {
				return false;
			}
		}
		return true;
	}
	public static void MakeGraph(Game game,Packmen_me me,Fruit f) {
		BlockTOuse= new ArrayList<Block>(game.getBlock());
		p3d= new Point3D[BlockTOuse.size()*4+2];
		String source =me.getType();
		String target = f.getType();
		Kosher.add(new Node(source)); // Node "a" (0)
		Iterator<Block>BlockNode = BlockTOuse.iterator();
		int i =1;

		while(BlockNode.hasNext())
		{
			Block sd = BlockNode.next();
			ArrayList<Point3D> pivots=sd.getVertex(sd);

			for(int j=0;j<4;j++) {
				p3d[i] = new Point3D(pivots.get(j));
				Node d = new Node(""+i);
				Kosher.add(d);
				i++;
			}

		}
		SizeNode=i;
		p3d[i] = f.getPointer_fruit();
		Kosher.add(new Node(target)); 
		AddEdges();



	}
	public static void AddEdges() {
		for(int i=0;i<Kosher.size();i++) {
			for (int j = 0; j < Kosher.size(); j++) {
				Line2D templine = new Line2D.Double(p3d[i].x(), p3d[i].y(),p3d[j].x(), p3d[j].y());
				boolean flag= DirectLine(templine);
				if(flag) {
					Kosher.addEdge(""+ i, ""+ j, p3d[i].distance2D(p3d[j]));
				}
			}

		}
	}


	public static  boolean DirectLine(Line2D t) {
		Iterator<Line2D>RectangleLines =LINE.iterator();
		while(RectangleLines.hasNext()) {
			Line2D g = RectangleLines.next();
			if(g.intersectsLine(t)) {
				if((g.getX1() != t.getX2() || g.getX1() != t.getX1()) && (g.getY1() != t.getY2() || g.getY1() != t.getY1()))
					return false;
			}
			return true;
		}


		return true;


	}
	public static void main (String []args ) {
		Point3D start = new Point3D(0, 0);
		Point3D fruit = new Point3D(10, 1000);
		//	line.setLine(start.x(), start.y(), fruit.x(), fruit.y());
		Line2D gal = new Line2D.Double(start.x(), start.y(), fruit.x(), fruit.y());
		//	System.out.println(gal.);
	}

}
