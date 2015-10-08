package nl.tudelft.semgroup4;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class KeySetScreen {
    private final Image newKeyText;

    /**
     * Constructs a KeySetScreen with the correct MouseOverArea and Resources.
     *
     * @param resources
     *            {@link ResourcesWrapper} - a new ResourcesWrapper.
     */
    public KeySetScreen(ResourcesWrapper resources) {
        this.newKeyText = resources.getNewKeyText();
    }

    /**
     * makes the background opaque and opens the keyset screen.
     *
     * @param graphics
     *            graphics to draw with
     * @param container
     *            to draw in
     * @param input
     *            to find out where click events happened
     */
    public void show(Graphics graphics, GameContainer container, Input input) {
        graphics.setColor(Color.yellow);
        graphics.setAntiAlias(true);
        Color color = new Color(0f, 0f, 0f, 0.5f);
        graphics.setColor(color);
        graphics.fillRect(0, 0, container.getWidth(), container.getHeight());
        graphics.drawImage(newKeyText, container.getWidth() / 2 - newKeyText.getWidth() / 2,
                container.getHeight() / 3.0f);
    }
}
