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
import algoritem.Map;
import algoritem.Fruit;
import algoritem.Game;
import algoritem.Packman;
import algoritem.Path;
import File_format.*;
import Geom.*;
import GIS.*;



public class MainWindow extends JFrame implements MouseListener 
{




	ArrayList<Point3D> packpoint = new ArrayList<Point3D>();
	ArrayList<Point3D> fr = new ArrayList<Point3D>();
	//ArrayList<Packman> pacman3 = new ArrayList<Packman>();
	public BufferedImage myImage ,myImage1,myImage2;
	Game GuiGame;
	int inputstate=0;//packmen or fruit 
	Map map;



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

		//     menuItem /////
		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		menu2.add(item4);
		menu2.add(item5);
		menu3.add(item6);
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
				loadFile();
				repaint();
			}


		});
		item4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inputstate = 1;
				System.out.println("packman choose");

			}
		});

		item5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inputstate = 2;
				System.out.println("Fruit choose");
			}
		});
		item6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GuiGame.runGame();
				repaint();
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

	public void loadFile() {
		Game newfile = new Game(readFileDialog());
		Iterator<Packman> iterPac = newfile.getPack().iterator();
		Iterator<Fruit> iterFruit =  newfile.getFruit().iterator();

		while(iterPac.hasNext()) {
			Packman f = iterPac.next();
			GuiGame.getPack().add(f);
			System.out.println(f.getIDpack());
		}
		while(iterFruit.hasNext()) {
			Fruit f = iterFruit.next();
			GuiGame.getFruit().add(f);

		}

		repaint();
	}
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
		Iterator<Packman> iterPac = GuiGame.getPack().iterator();
		Iterator<Fruit> iterFruit =  GuiGame.getFruit().iterator();

		while(iterPac.hasNext()) {
			Packman f = iterPac.next();
			Point3D p = new Point3D(Map.ConvertorToScreen(f.getPointer_packmen().x(),f.getPointer_packmen().y(),this.getHeight(),this.getWidth()));

			g.drawImage(myImage1, (int)p.x(), (int)p.y(), 20,20,this);


		}
		while(iterFruit.hasNext()) {
			Fruit f = iterFruit.next();
			Point3D p = new Point3D(Map.ConvertorToScreen(f.getPointer_fruit().x(), f.getPointer_fruit().y(),this.getHeight(),this.getWidth()));
			g.drawImage(myImage2, (int)p.x(), (int)p.y(), 20,20,this);
		}

	}

	public void mouseClicked(MouseEvent arg) {
		if(inputstate == 1) {
			int id = 0;
			x = arg.getX();
			y = arg.getY();
			Point3D p = new Point3D(x, y);
			p = Map.ConvertorFromScreen(x, y,this.getHeight(),this.getWidth());
			Packman packi = new Packman();
						packi.setPointer_packmen(p);
			if(GuiGame.getPack().size() == 0) {
				packi.setIDpack(1);
				id++;
			}
			else
				packi.setIDpack(id);
			packi.setSpeed(1);
			packi.setRadiuos(1);
			GuiGame.getPack().add(packi);
			packi.setType("P");
			System.out.println("hi");
			repaint();
		}
		else if(inputstate == 2) {
			int id = 0;
			x = arg.getX();
			y = arg.getY();
			Point3D p = new Point3D(x, y);
			p = Map.ConvertorFromScreen(x, y,this.getHeight(),this.getWidth());
			Fruit fruti = new Fruit();
			fruti.setPointer_fruit(p);
			if(GuiGame.getPack().size() == 0) {
				fruti.setIdfruit(1);
				id++;
			}
			else
				fruti.setIdfruit(id);
			GuiGame.getFruit().add(fruti);
			repaint();
		}

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
		fd.setFile("*.txt");
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});
		fd.setVisible(true);
		String folder = fd.getDirectory();
		String fileName = fd.getFile();
		try {
			FileWriter fw = new FileWriter(folder + fileName);
			PrintWriter outs = new PrintWriter(fw);
			//outs = MultiCsv.layer2csv(layer, output);
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

	public static void main(String[] args)
	{
		MainWindow window = new MainWindow();
		window.setVisible(true);
		window.setSize(window.myImage.getWidth(),window.myImage.getHeight());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("MAROCO VS TUNIS");

	}


}
