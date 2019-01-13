package HaveFun;

import javax.swing.JFrame;

import GUI.MainWindow;
import Map.Map;
/**
 * this class run the game
 * @author Roi Abramovitch & Gal Hadida
 *
 */
public class PlayTheGame {
	public static void main(String[]args) {
		MainWindow h = new MainWindow();
		Map map = new Map();
		h.setVisible(true);
		h.setSize(map.getMyImage1().getWidth(),map.getMyImage1().getHeight());
		h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		h.setTitle("Packman");
	}
}
