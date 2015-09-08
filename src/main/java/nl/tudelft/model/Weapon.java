package nl.tudelft.model;

import org.newdawn.slick.Image;

import java.util.ArrayList;
import java.util.LinkedList;


public class Weapon {

    private ArrayList<Projectile> projectiles;
    private Image img;
    private LinkedList<GameObject> obj;

    public Weapon(Image image, LinkedList<GameObject> objectList) {
        img = image;
        obj = objectList;
        projectiles = new ArrayList<Projectile>();
    }

    public void fire(int locX, int locY, int width) {
        if(projectiles.isEmpty()) {
            projectiles.add(new Projectile(img, locX, locY, width, 6, this));
            obj.add(projectiles.get(0));
            projectiles.get(0).fire();
        }
    }

    public void remove(Projectile proj) {
        projectiles.remove(proj);
        obj.remove(proj);
    }

    public ArrayList<Projectile> getAL() {
        return this.projectiles;
    }
}
