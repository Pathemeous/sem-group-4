package nl.tudelft.model;

import org.newdawn.slick.Image;

import java.util.ArrayList;
import java.util.LinkedList;


public class Weapon {

    private ArrayList<Projectile> projectiles;
    private Image img;
    private LinkedList<GameObject> obj, del;

    public Weapon(Image image, LinkedList<GameObject> objectList, LinkedList<GameObject> toDel) {
        img = image;
        obj = objectList;
        del = toDel;
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
        del.add(proj);
    }

    public ArrayList<Projectile> getAL() {
        return this.projectiles;
    }
}
