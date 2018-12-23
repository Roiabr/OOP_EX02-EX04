package GUI;





import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import algoritem.ShortestPathAlg;
import File_format.*;
import Geom.*;
import Map.Map;
import Threads.RunPackmen;
import GIS.*;
import Game.Fruit;
import Game.Game;
import Game.Packman;



public class MainWindow extends JFrame implements MouseListener 
{




	ArrayList<Point3D> packpoint = new ArrayList<Point3D>();
	ArrayList<Point3D> fr = new ArrayList<Point3D>();
	//ArrayList<Packman> pacman3 = new ArrayList<Packman>();
	public BufferedImage myImage ,myImage1,myImage2;
	Game GuiGame;
	int inputstate=0;
	int packmen_furit=0;//packmen or fruit 
	boolean run=false;

	Map map;
	ShortestPathAlg spa;

	RunPackmen runi;

	boolean isendGame = false;


	public MainWindow() 
	{
		GuiGame = new Game();
		initGUI();		
		this.addMouseListener(this); 
	}

	private void initGUI() 
	{

		MenuBar menuBar = new MenuBar(); 
		Menu menu1 = new Menu("File");
		Menu menu2 = new Menu("Input");
		Menu menu3 = new Menu("Edit");

		MenuItem item1 = new MenuItem("NewGame");
		MenuItem item2 = new MenuItem("Save Game");
		MenuItem item3 = new MenuItem("Load Game");
		MenuItem item4 = new MenuItem("Add Packman");
		MenuItem item5 = new MenuItem("Add Fruit");
		MenuItem item6 = new MenuItem("Run");
		MenuItem item7 = new MenuItem("kml");

		//     menuItem /////
		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		menu2.add(item4);
		menu2.add(item5);
		menu3.add(item6);
		menu3.add(item7);
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("New Game");
				GuiGame.getPack().removeAll(GuiGame.getPack());
				GuiGame.getFruit().removeAll(GuiGame.getFruit());
				repaint();

			}
		});
		item2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Save game");
				writeFileDialog();
			}
		});
		item3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("load game");
				GuiGame.getPack().removeAll(GuiGame.getPack());
				GuiGame.getFruit().removeAll(GuiGame.getFruit());
				loadFile();
				repaint();
			}


		});
		item4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				packmen_furit = 1;
				System.out.println("packman choose");

			}
		});

		item5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				packmen_furit = 2;
				System.out.println("Fruit choose");
			}
		});
		item6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GuiGame.runGame();
				run=true;
				repaint();
			}
		});
		item7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MultiCsv.Game2Kml(GuiGame);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		this.setMenuBar(menuBar);
		try {
			myImage = ImageIO.read(new File("Ariel1.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	int x = -1;
	int y = -1;

	public void paint(Graphics g)
	{

		g.drawImage(myImage, 0, 0,getWidth(),getHeight(), this);
		try {
			myImage1 = ImageIO.read(new File("packman.png"));
			myImage2 = ImageIO.read(new File("apple.png"));

		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<Fruit> iterFruit =  GuiGame.getFruit().iterator();
		while(iterFruit.hasNext()) {		//All the time paint the fruit
			Fruit f = iterFruit.next();
			Point3D p = new Point3D(Map.gpsToPix(f.getPointer_fruit().y(),f.getPointer_fruit().x(),this.getHeight(),this.getWidth()));
			g.drawImage(myImage2, (int)p.x(), (int)p.y(), 20,20,this);
		}	


		if(run==false) ///for any position change the packman by the real location
		{
			Iterator<Packman> iterPac = GuiGame.getPack().iterator();
			while(iterPac.hasNext()) {
				Packman pac = iterPac.next();
				Point3D p = new Point3D(Map.gpsToPix(pac.getFirstPointCor().y(),pac.getFirstPointCor().x(),this.getHeight(),this.getWidth()));

				g.drawImage(myImage1, (int)p.x(), (int)p.y(), 20,20,this);
			}

		}

		if(run==true)//just for create thread one time 
		{
			Iterator<Packman> timetoeat = GuiGame.getPack().iterator();
			while(timetoeat.hasNext()) {
				Packman p = timetoeat.next();
				RunPackmen sh= new RunPackmen();
				sh.runpackmen(this, p);
			}
			
			
			
			
			
			
			
			
//			RunPackmen gs = new RunPackmen(this,GuiGame);
//			
			run=false;
		}

	}


	
	public void mouseClicked(MouseEvent arg) {
		int idPackman = 1;
		int idFruit = 1;
		if((GuiGame.getPack().size() != 0) && (GuiGame.getFruit().size() != 0)) {
			idPackman = GuiGame.getPack().get(GuiGame.getPack().size()-1).getIDpack()+1;
			idFruit = GuiGame.getFruit().get(GuiGame.getFruit().size()-1).getIdfruit()+1;
		}
		else {
			idPackman = idPackman++;
			idFruit = idFruit++;
		}
		if(packmen_furit == 1) {
			x = arg.getX();
			y = arg.getY();
			Point3D p = new Point3D(x, y);
			p = Map.pixToGps(x, y,this.getHeight(),this.getWidth());
			System.out.println(p);
			Packman packi = new Packman();
			packi.setPointer_packmen(p);
			packi.setFirstPointCor(p);
			packi.setType("P");
			packi.setIDpack(idPackman);
			idPackman++;
			packi.setRadiuos(1);
			packi.setSpeed(1);
			GuiGame.getPack().add(packi);
			repaint();
		}
		else if(packmen_furit == 2) {
			x = arg.getX();
			y = arg.getY();
			Point3D p = new Point3D(x, y);
			p = Map.pixToGps(x, y,this.getHeight(),this.getWidth());
			Fruit fruti = new Fruit();
			fruti.setType("F");
			fruti.setIdfruit(idFruit);
			idFruit++;
			fruti.setPointer_fruit(p);
			fruti.setLife(true);
			fruti.setSpeed(1);
			GuiGame.getFruit().add(fruti);
			repaint();
		}

	}
	public void loadFile() {
		Game newfile = new Game(readFileDialog());
		Iterator<Packman> iterPac = newfile.getPack().iterator();
		Iterator<Fruit> iterFruit =  newfile.getFruit().iterator();

		while(iterPac.hasNext()) {
			Packman f = iterPac.next();
			GuiGame.getPack().add(f);
		}
		while(iterFruit.hasNext()) {
			Fruit f = iterFruit.next();
			GuiGame.getFruit().add(f);

		}

		repaint();
	}

	public GisLayer readFileDialog() {
		//		try read from the file
		FileDialog fd = new FileDialog(this, "Open text file", FileDialog.LOAD);
		fd.setFile("*.csv");
		fd.setDirectory("C:\\Users\\Roi Abramovitch\\eclipse-workspace\\OOP_EX02-EX04 - Copy\\data");
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});
		fd.setVisible(true);
		String folder = fd.getDirectory();
		String fileName = fd.getFile();
		try {
			FileReader fr = new FileReader(folder + fileName);
			BufferedReader br = new BufferedReader(fr);
			br.close();
			fr.close();
		} catch (IOException ex) {
			System.out.print("Error reading file " + ex);
			System.exit(2);
		}
		GisLayer layer =  MultiCsv.Csv2Layer(folder + fileName);
		System.out.println(layer.get_Meta_data());
		return layer;

	}
	public void writeFileDialog() {
		//		 try write to the file
		FileDialog fd = new FileDialog(this, "Save the text file", FileDialog.SAVE);
		fd.setFile("*.csv");
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});
		fd.setVisible(true);
		String folder = fd.getDirectory();
		String fileName = fd.getFile();
		try {
			FileWriter fw = new FileWriter(folder + fileName);
			PrintWriter outs = new PrintWriter(MultiCsv.Game2csv(GuiGame,folder + fileName));

			outs.close();
			fw.close();
		} catch (IOException ex) {
			System.out.print("Error writing file  " + ex);
		}


	}


	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseDragged(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub	
	}



}
