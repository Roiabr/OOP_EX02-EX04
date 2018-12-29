package PlayGame;

import javax.swing.JFrame;

import GUI.MainWindow;

public class HaveFun {
	public static void main (String[] args ) {

		MainWindow h = new MainWindow();
		h.setVisible(true);
		h.setSize(h.getMyImage().getWidth(),h.getMyImage().getHeight());
		h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		h.setTitle("Packmans in ariel");
	}
}
