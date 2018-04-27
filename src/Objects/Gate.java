/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import city.cs.engine.*;
import java.awt.Color;

/**
 * Creates a gate.
 * @author h_nan
 */
public class Gate extends StaticBody {

    private boolean isDestroyed = false;

    private static final Shape SHAPE = new BoxShape(1f, 0.5f);

    /**
     * Constructor of the Gate class. Sets the colour of the gate to orange.
     *
     * @param world The current level.
     */
    public Gate(World world) {
        super(world, SHAPE);
        setFillColor(Color.ORANGE);
    }

    /**
     * Encapsulation to retrieve private field isDestroyed.
     *
     * @return Whether or not the gate is destroyed.
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }

    /**
     * Encapsulation to set private field isDestroyed.
     *
     * @param isDestroyed Boolean to check whether or not the gate is destroyed.
     */
    public void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }

}
