package nl.tudelft.model;

import org.newdawn.slick.Image;

import java.util.ArrayList;

public class Weapon {

    private ArrayList<Projectile> projectiles;
    private Image img;
    private Player player;

    public Weapon(Image image) {
        img = image;
        projectiles = new ArrayList<Projectile>();
        this.player = null;
    }

    public void fire(int delta) {
        if(projectiles.isEmpty()) {
            projectiles.add(new Projectile(img, player.locX, player.locY, player.getWidth(), 6, player.getWeapon()));
            projectiles.get(0).fire();
        }
    }

    public void remove(Projectile proj) {
        if(projectiles.contains(proj)) projectiles.remove(proj);
    }

    public void setPlayer(Player p) {
        this.player = p;
    }

    public ArrayList<Projectile> getAL() {
        return this.projectiles;
    }
}
