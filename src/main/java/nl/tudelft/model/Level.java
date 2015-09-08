package nl.tudelft.model;

import java.util.ArrayList;

import nl.tudelft.model.pickups.Pickup;

public class Level {

	private int id;
	private ArrayList<GameObject> objs;
	private double time;
	private double speed;

    public Level(int id, ArrayList<GameObject> objs, double time, double speed) {
        this.objs = objs;
        this.time = time;
        this.speed = speed;
	}

	public int getId() {
		return this.id;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public double getTime() {
		return this.time;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSpeed() {
		return this.speed;
	}
	
	/**
	 * Checks whether the level is completed.
	 * 
	 * A level is completed when there are no bubbles left.
	 * 
	 * @return true iff there are no bubbles remaining in this level.
	 */
	public boolean isCompleted() {
		for (GameObject ob : this.objs) {
		    if (ob instanceof Bubble) {
		        return false;
		    }
		}
		return true;
	}
	
	/**
	 * Checks whether the level has failed.
	 * 
	 * A level has failed when a) the timer runs out; b) a player collides with a bubble. 
	 * 
	 * @return
	 */
	public boolean hasFailed() {
		return this.time <= 0;
	}

}
