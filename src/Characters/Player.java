/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Characters;

import city.cs.engine.*;

/**
 * Creates a player.
 * @author h_nan
 */
public class Player extends Walker {

    /**
     * Integer multiplier to change the size of the shape equally.
     */
    public static final int SCALE = 4;

    private boolean isDead = false;

    private static final Shape shape = new PolygonShape(
            -0.246f * SCALE, -0.087f * SCALE, -0.257f * SCALE, -0.495f * SCALE,
            0.18f * SCALE, -0.493f * SCALE, 0.173f * SCALE, -0.176f * SCALE,
            -0.007f * SCALE, -0.008f * SCALE, -0.243f * SCALE, -0.086f * SCALE);

    private static final BodyImage IMAGE
            = new BodyImage("data/playerright.gif", SCALE);

    /**
     * Constructor of the Player class. Adds the image, creates the fixture and
     * sets the friction of the player.
     *
     * @param world The current level.
     */
    public Player(World world) {
        super(world, shape);
        addImage(IMAGE);
        SolidFixture fixture = new SolidFixture(this, shape);
        fixture.setFriction(0);
    }

    /**
     * Encapsulation to retrieve private field isDead.
     *
     * @return Boolean whether or not the player is dead.
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Encapsulation to set private field isDead.
     *
     * @param isDead Boolean whether or not the player is dead.
     */
    public void getDead(boolean isDead) {
        this.isDead = isDead;
    }

}
