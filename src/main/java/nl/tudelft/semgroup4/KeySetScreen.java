package nl.tudelft.semgroup4;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.gui.MouseOverArea;

public class KeySetScreen {
    private final MouseOverArea mouseOver;
    private final Image newKeyText;
    private final Image backText;

    /**
     * Constructs a KeySetScreen with the correct MouseOverArea and Resources.
     *
     * @param resources
     *            {@link ResourcesWrapper} - a new ResourcesWrapper.
     * @param mouseOver
     *            {@link MouseOverArea} - the Slick MouseOverArea to use.
     */
    public KeySetScreen(ResourcesWrapper resources, MouseOverArea mouseOver) {
        this.mouseOver = mouseOver;
        this.newKeyText = resources.getNewKeyText();
        this.backText = resources.getBackText();
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
     * @param optionsState
     *            the options screen
     */
    public void show(Graphics graphics, GameContainer container, Input input,
            KeyBindState optionsState) {
        graphics.setColor(Color.yellow);
        graphics.setAntiAlias(true);
        Color color = new Color(0f, 0f, 0f, 0.5f);
        graphics.setColor(color);
        graphics.fillRect(0, 0, container.getWidth(), container.getHeight());
        graphics.drawImage(newKeyText, container.getWidth() / 2 - newKeyText.getWidth() / 2,
                container.getHeight() / 3.0f);
        graphics.drawImage(backText, container.getWidth() / 2 - backText.getWidth() / 2,
                container.getHeight() / 2.0f);
    }
}
