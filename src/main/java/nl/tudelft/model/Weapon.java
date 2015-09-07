package nl.tudelft.model;

import org.newdawn.slick.Image;

import java.util.ArrayList;

public class Weapon {

    private ArrayList<Projectile> projectiles;
    private Player pl;
    private Image img;

    public Weapon(Image image, Player p) {
        img = image;
        projectiles = new ArrayList<Projectile>();
        pl=p;
    }

    public Fire(int delta) {
        if(projectiles.isEmpty()) {
            projectiles.add(new Projectile(img, pl.locX, pl.locY, pl.getWidth(), 6));
            projectiles.get(0).fire();
        }
    }

}
