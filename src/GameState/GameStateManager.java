package GameState;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import sun.jvm.hotspot.oops.java_lang_Class;

public class GameStateManager {

	private ArrayList<GameState> m_gameStates;
	private int m_currentState;
	
	public static final int MENUSTATE=0;
	public static final int LEVELSTATE=1;

	
	
	
	public GameStateManager() {
		
		m_gameStates=new ArrayList<GameState>();
		
		m_currentState=MENUSTATE;
		m_gameStates.add(new MenuState(this));
	}
	
	public void setState(int state) {
		m_currentState=state;
		
		m_gameStates.get(m_currentState).init();
		
	}
	
	public void update() {
		
		m_gameStates.get(m_gameStates).update();
	}
	public void draw(java.awt.Graphics2D g) {
		
		m_gameStates.get(m_gameStates).draw(g);

	}
	
	public void keyPressed(int k) {
		m_gameStates.get(m_gameStates).keyPressed(k);

	}

	public void keyReleased(int k) {
		m_gameStates.get(m_gameStates).keyReleased(k);

	}
	
	
	
	
	
	
	
}
