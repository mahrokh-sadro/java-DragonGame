package GameState;

import java.awt.Color;

import Main.GamePanel;
import TileMap.TileMap;

public class LevelState extends GameState{

	private TileMap m_tileMap;
	
	
	
	public LevelState(GameStateManager gsm) {
		m_gameStateManager=gsm;
		init();
		
	}
	
	public  void init() {
		
		m_tileMap=new TileMap(30);
		m_tileMap.loadTiles("/Tilesets/grasstileset.gif");
		m_tileMap.loadMap("/Maps/level1-1.map");
		m_tileMap.setPosition(0, 0);
	};
	
	public  void update(){};
	public  void draw(java.awt.Graphics2D g){
		
		//clear screen
		g.setColor(Color.red);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		//draw tilemap
		m_tileMap.draw(g,5);
		//
	};
	public  void keyPressed(int k){};
	public  void keyReleased(int k){};

	
}
