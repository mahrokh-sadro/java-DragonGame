package Main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class GamePanel extends JFrame implements Runnable, KeyListener {

	// dimentions
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;

	// game thread
	private Thread m_thread;
	private boolean m_isRunning;
	private int m_FPS = 60;
	private long m_targetTime = 1000 / m_FPS;

	// image
	private BufferedImage m_image;
	private Graphics2D m_g2d;

	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setFocusable(true);
		requestFocus();
	}

	public void addNotify() {
		super.addNotify();
		if (m_thread == null) {
			m_thread = new Thread(this);
			addKeyListener(this);
			m_thread.start();
		}
	}

	private void init() {
		
		m_image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB );
				
		m_g2d=(Graphics2D) m_g2d;//?????
		m_isRunning=true;
		
	}
	
	
	public void run() {

		init();
		
		long start;
		long elapsed;
		long wait;
		
		
		
		//game loop
		
		while(m_isRunning) {
			
			start=System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed=System.nanoTime()-start;
			
			wait=m_targetTime-elapsed/1000000;
			
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

	private void update() {}
	private void draw() {}
	private void drawToScreen() {
		
		Graphics g2=getGraphics();
		g2.drawImage(m_image,0,0,null);
		g2.dispose();
	}

	
	
	
	public void keyTyped(KeyEvent key) {

	}

	public void keyPressed(KeyEvent key) {

	}

	public void keyReleased(KeyEvent key) {

	}

}
