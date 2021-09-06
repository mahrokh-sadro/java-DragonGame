package GameState;

public class LevelState extends GameState{

	private TileMap m_tileMap;
	
	
	
	public LevelState(GameStateManager gsm) {
		m_gameStateManager=gsm;
		init();
		
	}
	
	public  void init() {
		
		//m_tileMap=new TileMap()
	};
	
	public  void update(){};
	public  void draw(java.awt.Graphics2D g){};
	public  void keyPressed(int k){};
	public  void keyReleased(int k){};

	
}
