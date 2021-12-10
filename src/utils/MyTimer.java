package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Timer;


public class MyTimer {
	private long preUpdate;
	private long lastUpdate;
	private double paraticks;
	Thread uT;
	Thread rT;
	public MyTimer(int timeToUpdate, int timeToRender, ActionListener update, ActionListener render) {
		Date d = new Date();
		this.lastUpdate = d.getTime();
		this.preUpdate = this.lastUpdate;		
		Timer uTimer = new Timer(timeToUpdate, new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {				
				preUpdate = lastUpdate;
				lastUpdate = new Date().getTime();
				update.actionPerformed(null);	
				
			}
		});
		uTimer.start();
		Timer rTimer = new Timer(timeToRender, new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateParaticks();
				render.actionPerformed(null);				
			}
		});
		rTimer.start();
		
	}
	public void updateParaticks() {
		Date d = new Date();
		setParaticks(((d.getTime() - getLastUpdate()) / Math.max(getLastUpdate() - getPreUpdate(), 0.00001)));
	}
	public double getParaticks() {
		return this.paraticks;		
	}
	public long getPreUpdate() {
		return preUpdate;
	}
	public void setPreUpdate(int preUpdate) {
		this.preUpdate = preUpdate;
	}
	public long getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(int lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public void setParaticks(double paraticks) {
		this.paraticks = paraticks;
	}
	
}
