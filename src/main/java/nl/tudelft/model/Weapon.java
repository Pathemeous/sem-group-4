package nl.tudelft.model;

import org.newdawn.slick.Image;

import java.util.ArrayList;

public class Weapon {

    private ArrayList<Projectile> projectiles;
    private Image img;

    public Weapon(Image image) {
        img = image;
        projectiles = new ArrayList<Projectile>();
    }

    public void fire(int locX, int locY, int width) {
        if(projectiles.isEmpty()) {
            projectiles.add(new Projectile(img, locX, locY, width, 6, this));
            projectiles.get(0).fire();
        }
    }

    public void remove(Projectile proj) {
        if(projectiles.contains(proj)) projectiles.remove(proj);
    }

    public ArrayList<Projectile> getAL() {
        return this.projectiles;
    }
}
