package GameState;
import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {

	private Background m_background;

	private int m_currentchoice = 0;
	private String[] m_options = { "Start", "Help", "Quit" };

	private Color m_titleColor;
	private Font m_titleFont;
	private Font m_font;

	public MenuState(GameStateManager gsm) {
		m_gameStateManager = gsm;

		m_background = new Background("/Backgrounds/menubg.gif", 1);
		m_background.setVector(-0.1, 0);

		m_titleColor = new Color(128, 0, 0);
		m_titleFont = new Font("Century Gothic", Font.PLAIN, 28);
		m_font = new Font("Arial", Font.PLAIN, 12);

	}

	public void init() {
	};

	public void update() {
		m_background.update();
	};

	public void draw(Graphics2D g) {
		// draw bg
		m_background.draw(g);

		// draw title
		g.setColor(m_titleColor);
		g.setFont(m_titleFont);
		g.drawString("Dragon Tale", 80, 70);

		// draw menu options
		g.setFont(m_font);

		for (int i = 0; i < m_options.length; i++) {

			if (i == m_currentchoice) {
				g.setColor(Color.black);
			} else
				g.setColor(Color.red);

			g.drawString(m_options[i], 145, 140 + i * 15);
		}
	};

	private void select() {
		if (m_currentchoice == 0) {
			// start
			m_gameStateManager.setState(GameStateManager.LEVELSTATE);
		}

		else if (m_currentchoice == 1) {
			// help
		} else if (m_currentchoice == 2) {
			System.exit(0);
		}
	}

	public void keyPressed(int k) {

		if (k == KeyEvent.VK_ENTER) {

			select();
		}
		else if (k == KeyEvent.VK_UP) {

			m_currentchoice--;
			if(m_currentchoice==-1) m_currentchoice=m_options.length-1;
		}
		else if (k == KeyEvent.VK_DOWN) {

			m_currentchoice++;
			if(m_currentchoice==m_options.length) m_currentchoice=0;
		}
	};

	public void keyReleased(int k) {

	};

}
