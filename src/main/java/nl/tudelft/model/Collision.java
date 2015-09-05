package nl.tudelft.model;

import java.util.LinkedList;

public class Collision {
	public static boolean Colission(GameObject enta, LinkedList<GameObject> entb) {	
		for(int i = 0; i < entb.size(); i++) {
			if(enta.getBounds().intersects(entb.get(i).getBounds())) {
				return true;
			}			
		}
		return false;
	}
}
