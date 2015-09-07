package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bubble extends GameObject {
    private GameContainer gc;
    private int counter;
    private int size;
    private int speed;



    public Bubble(Image image, int x, int y, int size) {
        super(image,x,y);
        this.size = size;
        this.counter=0;
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        super.render(container, g);
    }

    @Override
    public void update(GameContainer container, int delta) {
        this.locY = getImage().getWidth() * speed * Math.abs((int)((Math.sin(counter)*100)));
        counter++;
    }

}
