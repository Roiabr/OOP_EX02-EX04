package GUI;


import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


import File_format.*;
import Geom.*;
import GIS.*;





public class MainWindow extends JFrame implements MouseListener , MouseMotionListener
{

	private boolean isGamer;
	MultiCsv C;
	ArrayList<Point3D> pac = new ArrayList<Point3D>();
	ArrayList<Point3D> fr = new ArrayList<Point3D>();
	public BufferedImage myImage;
	
	public MainWindow() 
	{
		initGUI();		
		this.addMouseMotionListener(this); 
		this.addMouseListener(this); 
	}

	private void initGUI() 
	{
		MenuBar menuBar = new MenuBar();   // the menu-bar
		
		// First Menu
		
		Menu menu1 = new Menu("File");
		Menu menu2 = new Menu("Edit");
		MenuItem item1 = new MenuItem("NewGame");
		MenuItem item2 = new MenuItem("Save Game");
		MenuItem item3 = new MenuItem("Load Game");
		MenuItem item4 = new MenuItem("Add Packman");
		MenuItem item5 = new MenuItem("Add Fruit");

		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		menu2.add(item4);
		menu2.add(item5);
		menuBar.add(menu1);
		menuBar.add(menu2);
		item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("New Game");
            	pac.removeAll(pac);
            	fr.removeAll(fr);
            	repaint();
                
            }
        });
		item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("load game");
                readFileDialog();
            }
        });
		item4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = true;
				System.out.println("packman choose");
			}
		});
		
		item5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = false;
				System.out.println("Fruit choose");
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
		if(x!=-1 && y!=-1) {
			Iterator<Point3D> iterPac = pac.iterator();
			Iterator<Point3D> iterFruit = fr.iterator();
			while(iterPac.hasNext()) {
				Point3D p = iterPac.next();
				g.setColor(Color.YELLOW);
				g.fillOval((int)p.x(),(int)p.y(),10,10);
			}
			while(iterFruit.hasNext()) {
				Point3D p = iterFruit.next();
				g.setColor(Color.red);
				g.fillOval((int)p.x(),(int)p.y(),10,10);
		
			}
		}
	}
	public BufferedImage getMyImage() {
		return myImage;
	}

	@Override
	//	    public void paintComponent(Graphics G) {
	//	        super.paintComponent(G);
		      
	//	    }

	public void mouseClicked(MouseEvent arg) {
		if(isGamer) {
			x = arg.getX();
			y = arg.getY();
			Point3D p = new Point3D(x, y);
			pac.add(p);
			repaint();
		}
		if(!isGamer) {
			x = arg.getX();
			y = arg.getY();
			Point3D p = new Point3D(x, y);
			fr.add(p);
			repaint();
		}

	}

	  public void readFileDialog() {
	        //		try read from the file
	        FileDialog fd = new FileDialog(this, "Open text file", FileDialog.LOAD);
	        fd.setFile("*.csv");
	        fd.setDirectory("C:\\Users\\Roi Abramovitch\\Documents\\לימודים מדעי המחשב\\מדמ''ח שנה ב' סמסטר א\\מונחה עצמים\\מטלות\\מטלה 3\\Ex3\\data");
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
	            String str = new String();
	            for (int i = 0; str != null; i = i + 1) {
	                str = br.readLine();
	                if (str != null) {
	                    System.out.println(str);
	                }
	            }
	            GisLayer layer =  MultiCsv.Csv2Layer(folder + fileName);
	            System.out.println(layer.get_Meta_data());
	            br.close();
	            fr.close();
	        } catch (IOException ex) {
	            System.out.print("Error reading file " + ex);
	            System.exit(2);
	        }
	    }



	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
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

	}

}