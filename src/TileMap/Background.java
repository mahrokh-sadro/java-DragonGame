package TileMap;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Background {

	private BufferedImage m_image;

	private double m_x;
	private double m_y;
	private double m_dx;
	private double m_dy;

	private double moveScale;

	public Background(String str, double ms) {

		try {
			m_image = ImageIO.read(getClass().getResourceAsStream(str));
			moveScale = ms;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPosition(double x, double y) {

		m_x = (x*moveScale)%GamePanel.WIDTH;
		m_y = (y*moveScale)%GamePanel.HEIGHT;
	}

	public void setVector(double dx, double dy) {

		m_dx = dx;
		m_dy = dy;
	}

	public void update() {

		m_x += m_dx;
		m_y += m_dy;
	}

	public void draw(Graphics2D g) {

		g.drawImage(m_image, (int) m_x, (int) m_y, null);
	
		if (m_x < 0) {
			g.drawImage(m_image, (int) m_x + GamePanel.WIDTH, (int) m_y, null);

		} else {
			g.drawImage(m_image, (int) m_x - GamePanel.WIDTH, (int) m_y, null);
		}

	}

}
