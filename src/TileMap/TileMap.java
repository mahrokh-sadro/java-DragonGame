package TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileMap {

	// position
	private double m_x;
	private double m_y;

	// bounds
	private int m_xmin;
	private int m_ymin;
	private int m_xmax;
	private int m_ymax;

	private double m_tween;

	// map
	private int[][] m_map;
	private int m_tileSize;
	private int m_numOfRows;
	private int m_numOfCols;
	private int m_width;
	private int m_height;

	// tileset
	private BufferedImage m_tileSet;
	private int m_numOfTilesAcross;
	private Tile[][] m_tiles;

	// drawing
	private int m_rowsOffset;
	private int m_colsOffset;
	private int m_rowsToDraw;
	private int m_colsToDraw;

	public TileMap(int tileSize) {

		m_tileSize = tileSize;
		m_rowsToDraw = GamePanel.HEIGHT / tileSize + 2;
		m_colsToDraw = GamePanel.WIDTH / tileSize + 2;
		m_tween = 0.07;
	}

	public void loadTiles(String s) {

		try {
			m_tileSet = ImageIO.read(getClass().getResourceAsStream(s));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_numOfTilesAcross = m_tileSet.getWidth() / m_tileSize;
		m_tiles = new Tile[2][m_numOfTilesAcross];

		BufferedImage m_suBufferedImage;
		for (int col = 0; col < m_numOfTilesAcross; col++) {

			m_suBufferedImage = m_tileSet.getSubimage(col * m_tileSize, 0, m_tileSize, m_tileSize);
			m_tiles[0][col] = new Tile(m_suBufferedImage, Tile.NORMAL);
			m_suBufferedImage = m_tileSet.getSubimage(col * m_tileSize, m_tileSize, m_tileSize, m_tileSize);
			m_tiles[0][col] = new Tile(m_suBufferedImage, Tile.BLOCKED);

		}

	}

	public void loadMap(String s) {

		InputStream input = getClass().getResourceAsStream(s);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));

		try {
			m_numOfCols = Integer.parseInt(bufferedReader.readLine());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			m_numOfRows = Integer.parseInt(bufferedReader.readLine());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_map = new int[m_numOfRows][m_numOfCols];

		m_width = m_numOfCols * m_tileSize;
		m_height = m_numOfRows * m_tileSize;

		String delims = "\\s+";

		String lineString;
		for (int row = 0; row < m_numOfRows; row++) {

			try {
				lineString = bufferedReader.readLine();
				String[] tokens = lineString.split(delims);
				for (int col = 0; col < m_numOfCols; col++)
					m_map[row][col] = Integer.parseInt(tokens[col]);

			}

			catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public double getx() {
		return m_x;
	}

	public double gety() {
		return m_y;
	}

	public int getTileSize() {
		return m_tileSize;
	}

	public int getWidth() {
		return m_width;
	}

	public int getHeight() {
		return m_height;
	}

	public int getType(int row, int col) {
		int rc = m_map[row][col];
		int r = rc / m_numOfTilesAcross;
		int c = rc % m_numOfTilesAcross;
		return m_tiles[r][c].getType();
	}

	public void setPosition(double x, double y) {

		m_x += (x - m_x) * m_tween;
		m_y += (y - m_y) * m_tween;

		fixBounds();

		m_colsOffset = (int) -m_x / m_tileSize;
		m_rowsOffset = (int) -m_y / m_tileSize;

	}

	private void fixBounds() {
		if (m_x < m_xmin)
			m_x = m_xmin;
		if (m_y < m_ymin)
			m_y = m_ymin;
		if (m_x > m_xmax)
			m_x = m_xmax;
		if (m_y > m_ymax)
			m_y = m_ymax;
	}

	public void draw(Graphics2D g, int numColsToDraw) {

		for (int row = m_rowsOffset; row < m_rowsOffset + m_rowsToDraw; row++) {

			if (row >= m_numOfRows)
				break;

			for (int col = m_colsOffset; col < m_colsOffset + numColsToDraw; col++) {

				if (col >= m_numOfCols)
					break;

				if (m_map[row][col] == 0)
					continue;

				int rc = m_map[row][col];
				int r = rc / m_numOfTilesAcross;
				int c = rc % m_numOfTilesAcross;

				g.drawImage(m_tiles[r][c].getImage(), (int) m_x + col * m_tileSize, (int) m_y + row * m_tileSize, null);

			}

		}

	}

}
