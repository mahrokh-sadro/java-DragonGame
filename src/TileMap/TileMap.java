package TileMap;

import java.awt.image.BufferedImage;

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

	}

	public void loadTiles(String s) {

		m_tileSet = ImageIO.read(getClass().getResourceAsStream(s));
		m_numOfTilesAcross = m_tileSet.getWidth() / m_tileSize;
		m_tiles = new Tile[2][m_numOfTilesAcross];

		BufferedImage m_suBufferedImage;
		for (int col = 0; col < m_numOfTilesAcross; col++) {

			m_suBufferedImage = m_tileSet.getSubimage(col * m_tileSize, 0, m_tileSize, m_tileSize);
			m_tiles[0][col] = new TileMap(m_suBufferedImage, Tile.NORMAL);
			m_suBufferedImage = m_tileSet.getSubimage(col * m_tileSize, m_tileSize, m_tileSize, m_tileSize);
			m_tiles[0][col] = new TileMap(m_suBufferedImage, Tile.BLOCKED);

	
		
		}

	}

	public void loadMap(String s) {

	}

}
