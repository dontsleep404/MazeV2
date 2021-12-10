package utils;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Main;

public class Player {
	private int size;
	private int preX;
	private int preY;	
	private int x;
	private int y;
	private int space;
	private Map map;
	private boolean dir[] = {false,false,false,false};
	public Player(int x, int y, int size, Map map) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.space = 4;
		this.map = map;
	}
	public void update() {
		preX = x;
		preY = y;
		int ho = (dir[2] ? -1 : 0) + (dir[3] ? 1 : 0);
		int ve = (dir[0] ? -1 : 0) + (dir[1] ? 1 : 0);
		if(ho != 0) {
			if(canGo(x, y, x+ho, y)) {
				x+=ho;
			}			
		}else if(ve != 0) {
			if(canGo(x, y, x, y+ve)) {
				y+=ve;
			}
		}
	}
	public double smoothX() {
		return Helper.smoothPos(preX, x, Main.timer.getParaticks());
	}
	public double smoothY() {
		return Helper.smoothPos(preY, y, Main.timer.getParaticks());
	}
	public void draw(Graphics2D g2d) {
		g2d.setColor(new Color(200, 47, 96));
		double sx = smoothX();
		double sy = smoothY();
		g2d.fillRect((int)(sx*size+space), (int)(sy*size+space), size-space*2, size-space*2);
	}
	public boolean canGo(int x1,int y1,int x2,int y2) {
		if(x1<0||y1<0||x1>=map.getWidth()||y1>=map.getHeight()||x2<0||y2<0||x2>=map.getWidth()||y2>=map.getHeight()) {
			return false;
		}
		return Math.abs(map.getMaps()[x1][y1] - map.getMaps()[x2][y2]) == 1;
	}
	public int getPreX() {
		return preX;
	}
	public void setPreX(int preX) {
		this.preX = preX;
	}
	public int getPreY() {
		return preY;
	}
	public boolean[] getDir() {
		return dir;
	}
	public void setDir(boolean dir[]) {
		this.dir = dir;
	}
	public void setPreY(int preY) {
		this.preY = preY;
	}
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getSpace() {
		return space;
	}
	public void setSpace(int space) {
		this.space = space;
	}
	
}

