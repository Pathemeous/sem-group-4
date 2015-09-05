package nl.tudelft.model;

import java.util.LinkedList;

public class Collision {
	public static boolean Colission(GameObject enta, GameObject entb) {		
		if(enta.getBounds().intersects(entb.getBounds())) {
			return true;
		}				
		return false;
	}
}
