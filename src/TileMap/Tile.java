package TileMap;

import java.awt.image.BufferedImage;

public class Tile {

	private BufferedImage m_image;
	private int m_type;
	
	//tile types
	public static final int NORMAL=0;
	public static final int BLOCKED=1;

	
	public Tile(BufferedImage img,int type) {
		
		m_image=img;
		m_type=type;
		
	}


	public BufferedImage getImage() {
		return m_image;
	}


	public int getType() {
		return m_type;
	}



	
	
	
}
