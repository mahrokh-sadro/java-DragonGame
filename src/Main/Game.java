package Main;

import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		
		JFrame myFrame=new JFrame("Dragon");
		myFrame.setContentPane(new GamePanel());
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	myFrame.setSize(500, 500);
		myFrame.setResizable(false);
		myFrame.pack();
		myFrame.setVisible(true);
	}
	
	
}
