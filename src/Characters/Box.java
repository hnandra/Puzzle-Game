/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Characters;

import city.cs.engine.*;

/**
 * Creates a box.
 * @author h_nan
 */
public class Box extends DynamicBody {

    private static Shape shape = new BoxShape(0.75f, 0.75f);

    private static final BodyImage image
            = new BodyImage("data/box.png", 1.5f);

    /**
     * Constructor of the Box class. Adds the image, creates the fixture, sets
     * the friction and the restitution for the box.
     *
     * @param world The current level.
     */
    public Box(World world) {
        super(world, shape);
        addImage(image);
        SolidFixture fixture = new SolidFixture(this, shape, 100);
        fixture.setFriction(1f);
        fixture.setRestitution(0.1f);
    }
}
