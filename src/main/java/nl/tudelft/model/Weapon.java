package nl.tudelft.model;

import org.newdawn.slick.Image;

import java.util.ArrayList;
import java.util.LinkedList;


public class Weapon {

    private ArrayList<Projectile> projectiles;
    private Image img;
    private LinkedList<GameObject> del, add;

    public Weapon(Image image, LinkedList<GameObject> toDel, LinkedList<GameObject> toAdd) {
        img = image;
        del = toDel;
        add = toAdd;
        projectiles = new ArrayList<Projectile>();
    }

    public void fire(int locX, int locY, int width, int height) {
        if(projectiles.isEmpty()) {
            add.add(new Projectile(img, locX, locY, width, height, 6, this));
        }
    }

    public void remove(Projectile proj) {
    	projectiles.remove(proj);
        del.add(proj);
    }

    public ArrayList<Projectile> getAL() {
        return this.projectiles;
    }
}
