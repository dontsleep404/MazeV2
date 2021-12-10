package utils;

import java.awt.Color;
import java.awt.Graphics2D;

public class LightMap {
	private Map map;
	private Player player;
	private int[][] light;
	public LightMap(Map map, Player player) {
		this.map = map;
		this.player = player;
		this.light = new int[map.getWidth()][map.getHeight()];
		for(int x=0;x<map.getWidth();x++) {
			for(int y=0;y<map.getHeight();y++) {
				this.light[x][y] = 0;
			}
		}
	}
	public void update() {
		/*for(int x=0;x<map.getWidth();x++) {
			for(int y=0;y<map.getHeight();y++) {
				this.light[x][y] = 0;
			}
		}*/
		int lightlength = 5;
		updateLightMap(player.getX(), player.getPreY(), lightlength);
	}
	public void updateLightMap(int x,int y,int light) {
		getLight()[x][y] = Math.max(getLight()[x][y], light);
		if(light > 1) {
			for(int[] i : map.getList()) {
				if(map.canGo(x, y, x+i[0], y+i[1])) {
					updateLightMap(x+i[0], y+i[1], light-1);
				}
			}
		}		
	}
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, map.getWidth()*map.getSize(), map.getHeight()*map.getSize());
		for(int y = 0 ; y < map.getHeight();y++) {
			for(int x = 0 ; x < map.getWidth();x++) {
				if(light[x][y] > 0) {
					g2d.setColor(new Color(1f, 1f, 1f, (float) (light[x][y]/6.0)));
					g2d.fillRect(x*map.getSize(), y*map.getSize(), map.getSize(), map.getSize());
				}
			}
		}
	}
	public int[][] getLight() {
		return light;
	}
	public void setLight(int[][] light) {
		this.light = light;
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
}
