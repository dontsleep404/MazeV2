package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;

import utils.LightMap;
import utils.Map;
import utils.Player;


@SuppressWarnings("serial")
public class Maze extends JPanel implements MouseListener,MouseMotionListener,MouseWheelListener,KeyListener{
	private int width;
	private int height;
	private Map map;
	private Player player;
	private LightMap lightmap;
	private boolean showMinimap;
	private int level;
	private int cellSize;
	private boolean gameOver;
	public void addListener() {
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		addMouseWheelListener(this);
	}
	public Maze() {
		addListener();
		this.width = 0;
		this.height = 0;
		setFocusable(true);
	}
	public void newGame() {		
		map = new Map(getLevel(), getLevel(),cellSize );
		player = new Player(0, 0, cellSize, map);
		setLightmap(new LightMap(map, player));
		gameOver = false;
	}
	public Maze(int level,int size,int width, int height) {
		addListener();
		this.height = height;
		this.width = width;
		this.setShowMinimap(false);
		setFocusable(true);
		setBackground(new Color(27, 27, 27));	
		setLevel(level+2);
		this.cellSize = size;
		newGame();
	}
	Font arial = new Font("Arial", 1, 30);
	int mouseX = 0;
	int mouseY = 0;
	public void drawGameOver(Graphics2D g2d) {
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;		
		super.paintComponent(g);
		smoothPaint(g2d);
		if(gameOver) {
			drawGameOver(g2d);
			return;
		}
		double scale = 5;		
		g2d.scale(scale, scale);
		g2d.translate((int)((-player.smoothX()*map.getSize()-player.getSize()/2)+getWidth()/(2*scale)),(int)((-player.smoothY()*map.getSize()-player.getSize()/2) + getHeight()/(2*scale)));		
		lightmap.draw(g2d);
		map.drawMap(g2d);
		player.draw(g2d);		
		g2d.translate((int)-((-player.smoothX()*map.getSize()-player.getSize()/2)+getWidth()/(2*scale)),(int)-((-player.smoothY()*map.getSize()-player.getSize()/2) + getHeight()/(2*scale)));
		g2d.scale(1.0/scale, 1.0/scale);
		if(isShowMinimap()) {
			scale = 1;
			g2d.scale(scale, scale);
			g2d.translate((int)((-player.smoothX()*map.getSize()-player.getSize()/2)+getWidth()/(2*scale)),(int)((-player.smoothY()*map.getSize()-player.getSize()/2) + getHeight()/(2*scale)));		
			lightmap.draw(g2d);
			map.drawMap(g2d);
			player.draw(g2d);		
			g2d.translate((int)-((-player.smoothX()*map.getSize()-player.getSize()/2)+getWidth()/(2*scale)),(int)-((-player.smoothY()*map.getSize()-player.getSize()/2) + getHeight()/(2*scale)));
			g2d.scale(1.0/scale, 1.0/scale);
		}
		g2d.dispose();
	}	
	public void drawFrame(int w,int h,Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(new Color(255,255,255));
		int s = map.getSize();;
		for(int x = 0;x<=w*s;x+=s) {
			g2d.drawLine(x, 0, x, h*s);
		}
		for(int y = 0;y<=h*s;y+=s) {
			g2d.drawLine(0, y, w*s, y);
		}
	}
	public void onUpdate() {	
		if(player.getX() == map.getEndX() && player.getY() == map.getEndY()) {
			setLevel(getLevel()+1);
			newGame();
		}
		player.update();
		lightmap.update();
	}
	public Dimension getPreferredSize() {
		return new Dimension(getWidth(), getHeight());
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	@Override
	public void keyTyped(KeyEvent e) {		
	}
	@Override
	public void keyPressed(KeyEvent e) {	
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:			
			setShowMinimap(true);
			break;
		case KeyEvent.VK_UP:
			player.getDir()[0] = true;
			break;
		case KeyEvent.VK_DOWN:
			player.getDir()[1] = true;
			break;
		case KeyEvent.VK_LEFT:
			player.getDir()[2] = true;
			break;
		case KeyEvent.VK_RIGHT:
			player.getDir()[3] = true;
			break;
		default:
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			if(gameOver) {
				newGame();
			}
			setShowMinimap(false);
			break;
		case KeyEvent.VK_UP:
			player.getDir()[0] = false;
			break;
		case KeyEvent.VK_DOWN:
			player.getDir()[1] = false;
			break;
		case KeyEvent.VK_LEFT:
			player.getDir()[2] = false;
			break;
		case KeyEvent.VK_RIGHT:
			player.getDir()[3] = false;
			break;
		default:
			break;
		}
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub		
		//newGame();
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void smoothPaint(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);            
        g2d.setStroke(new BasicStroke(6,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public LightMap getLightmap() {
		return lightmap;
	}
	public void setLightmap(LightMap lightmap) {
		this.lightmap = lightmap;
	}
	public boolean isShowMinimap() {
		return showMinimap;
	}
	public void setShowMinimap(boolean showMinimap) {
		this.showMinimap = showMinimap;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public boolean isGameOver() {
		return gameOver;
	}
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
}
