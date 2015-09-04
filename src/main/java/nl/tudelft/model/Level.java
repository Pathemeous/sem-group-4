package nl.tudelft.model;

import java.util.ArrayList;

public class Level {

	private int id;
	private ArrayList<Wall> walls;
	private ArrayList<Bubble> bubbles;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Pickup> pickups;
	private double time;
	private double speed;

	public Level(int id, ArrayList<Wall> walls, ArrayList<Bubble> bubbles,
			ArrayList<Projectile> projectiles, ArrayList<Pickup> pickups,
			double time, double speed) {
		this.walls = walls;
		this.bubbles = bubbles;
		this.projectiles = projectiles;
		this.pickups = pickups;
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
		return this.bubbles.isEmpty();
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
