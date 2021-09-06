package Main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GameState.GameStateManager;


public class GamePanel extends JPanel implements Runnable, KeyListener {

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

	//game state manager
	
	private GameStateManager m_gameStateManager;
	
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
				
		m_g2d=(Graphics2D) m_image.getGraphics();//?????
		m_isRunning=true;
		
		m_gameStateManager=new GameStateManager();
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
			if(wait<0) wait=1;
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

	private void update() {
		
		m_gameStateManager.update();
	}
	private void draw() {
		
		m_gameStateManager.draw(m_g2d);
	}
	private void drawToScreen() {
		
		Graphics g2=getGraphics();
		g2.drawImage(m_image,0,0,WIDTH*SCALE,HEIGHT*SCALE  ,null);
		g2.dispose();
	}

	
	
	
	public void keyTyped(KeyEvent key) {
	}

	public void keyPressed(KeyEvent key) {
		m_gameStateManager.keyPressed(key.getKeyCode());

	}

	public void keyReleased(KeyEvent key) {
		m_gameStateManager.keyReleased(key.getKeyCode());

	}

}
