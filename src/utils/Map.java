package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

public class Map {
	private int[][] maps;
	private int firstX;
	private int firstY;	
	private int endX;
	private int endY;	
	private int width;
	private int height;
	private int size;
	private ArrayList<int[]> list;
	public Map(int width, int height,int size) {
		setSize(size);
		setFirstX(0);
		setFirstY(0);
		setEndX(0);
		setEndY(0);
		setWidth(width);
		setHeight(height);
		setMaps(new int[width][height]);
		setList(new ArrayList<>());
		getList().add(new int[] {1,0});
		getList().add(new int[] {0,1});
		getList().add(new int[] {-1,0});
		getList().add(new int[] {0,-1});
		genMap(getFirstX(), getFirstY(), 1);
	}
	public ArrayList<int[]> getRandomList(){
		ArrayList<int[]> l = new ArrayList<>();
		for(int[] i : list) {
			l.add(i);
		}
		Collections.shuffle(l);
		return l;
	}
	public void genMap(int x,int y,int val) {
		ArrayList<int[]> rd = getRandomList();		
		getMaps()[x][y] = val;
		if(getMaps()[endX][endY] < val) {
			endX = x;
			endY = y;
		}
		for(int[] i : rd) {
			if(canGo(x+i[0], y+i[1])) genMap(x+i[0], y+i[1], val+1);
		}
	}
	public boolean canGo(int x,int y) {
		if(x<0||y<0||x>=getWidth()||y>=getHeight()) {
			return false;
		}
		return getMaps()[x][y] == 0;
	}
	public boolean canGo(int x1,int y1,int x2,int y2) {
		if(x1<0||y1<0||x1>=getWidth()||y1>=getHeight()||x2<0||y2<0||x2>=getWidth()||y2>=getHeight()) {
			return false;
		}
		return Math.abs(getMaps()[x1][y1] - getMaps()[x2][y2]) == 1;
	}
	public void drawMap(Graphics2D g2d) {	
		g2d.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2d.setColor(new Color(55, 225, 122));
		g2d.fillRect(getFirstX()*size, getFirstY()*size, size, size);	
		g2d.setColor(new Color(66, 66, 155));
		g2d.fillRect(getEndX()*size, getEndY()*size, size, size);
		for(int y = 0 ; y < height;y++) {
			for(int x = 0 ; x < width;x++) {					
				g2d.setColor(Color.black);
				if(!canGo(x, y, x+1, y)) {
					g2d.drawLine((x+1)*size, y*size, (x+1)*size, (y+1)*size);
				}
				if(!canGo(x, y, x, y+1)) {
					g2d.drawLine((x)*size, (y+1)*size, (x+1)*size, (y+1)*size);
				}
				//g2d.setColor(Color.red);
				//g2d.drawString(getMaps()[x][y]+"", x*size+size/2, y*size+size/2);
				/*int sp = 1;
				int bX = x*size+sp;
				int bY = y*size+sp;
				int bW = size-sp*2;
				int bH = size-sp*2;
				if(canGo(x, y, x+1, y)) bW+=sp*2;
				if(canGo(x, y, x, y+1)) bH+=sp*2;
				if(canGo(x, y, x-1, y)) {bW+=sp*2;bX-=sp*2;}
				if(canGo(x, y, x, y-1)) {bH+=sp*2;bY-=sp*2;}				
				g2d.fillRect(bX, bY, bW, bH);*/
				
			}
		}
		g2d.setColor(Color.black);		
		g2d.drawRect(0, 0, width*size, height*size);
	}
	public ArrayList<int[]> getList() {
		return list;
	}
	public void setList(ArrayList<int[]> list) {
		this.list = list;
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
	public int[][] getMaps() {
		return maps;
	}
	public void setMaps(int[][] maps) {
		this.maps = maps;
	}
	public int getFirstX() {
		return firstX;
	}
	public void setFirstX(int firstX) {
		this.firstX = firstX;
	}
	public int getFirstY() {
		return firstY;
	}
	public void setFirstY(int firstY) {
		this.firstY = firstY;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getEndX() {
		return endX;
	}
	public void setEndX(int endX) {
		this.endX = endX;
	}
	public int getEndY() {
		return endY;
	}
	public void setEndY(int endY) {
		this.endY = endY;
	}
}
