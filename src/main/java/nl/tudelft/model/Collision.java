package nl.tudelft.model;

import java.util.LinkedList;

public class Collision {
	public static GameObject Colission(GameObject enta, LinkedList<GameObject> entbList) {	
		for(int i = 0; i < entbList.size(); i++) {
			if(!enta.equals(entbList.get(i))){
				if(enta.getBounds().intersects(entbList.get(i).getBounds())) {
					playerWallCollision(enta, entbList.get(i));
				}			
			}
		}
		return null;
	}
	
	public static void playerWallCollision(GameObject enta, GameObject entb) {		
		if(entb.getClass().getName() == "nl.tudelft.model.Wall") {
			System.out.println("COLLISION");
			if(entb.x_location < enta.x_location) {
				enta.setX_location(entb.x_location + entb.getImage().getWidth());
			}else{
				enta.setX_location(entb.x_location - 26);
			}
		}
	}
}

