/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Objects.Key;
import Objects.PressurePlate;
import Objects.Gate;
import Characters.Player;
import Characters.Box;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.*;

/**
 * A basic version of each level.
 * 
 * @author Harmeet
 */
public abstract class GameLevel extends World {

    private Player player;
    private Box box;
    private static PressurePlate pPlate;
    private Gate yellowGate;
    private Key yellowKey;
    /**
     * Boolean to check if level is complete.
     */
    public boolean isLevelComplete;

    /**
     * Creates a new GameLevel with 60 FPS.
     */
    public GameLevel() {
        super();
    }

    /**
     * Encapsulation to retrieve private field player.
     *
     * @return The player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Encapsulation to retrieve private field box.
     *
     * @return The box.
     */
    public Box getBox() {
        return box;
    }

    /**
     * Encapsulation to retrieve private field pPlate.
     *
     * @return The final pressure plate.
     */
    public static PressurePlate getPPlate() {
        return pPlate;
    }

    /**
     * Encapsulation to retrieve private field yellowGate.
     *
     * @return The yellow gate .
     */
    public Gate getYellowGate() {
        return yellowGate;
    }

    /**
     * Encapsulation to retrieve private field yellowKey.
     *
     * @return The yellow key .
     */
    public Key getYellowKey() {
        return yellowKey;
    }

    /**
     * Encapsulation to set private field isLevelComplete.
     *
     * @param isLevelComplete boolean value.
     * @return Sets the level complete or not.
     */
    public boolean setLevelComplete(boolean isLevelComplete) {
        return this.isLevelComplete = isLevelComplete;
    }

    /**
     * Instantiates the objects which recur throughout each level.
     *
     * @param game Current level.
     */
    public void populate(Game game) {
        player = new Player(this);
        player.setPosition(playerStartPosition());

        box = new Box(this);
        box.setPosition(boxStartPosition());

        pPlate = new PressurePlate(this, box, game);
        pPlate.setPosition(pPlateStartPosition());
        pPlate.addCollisionListener(pPlate);

        yellowGate = new Gate(this);
        yellowGate.setPosition(yellowGateStartPosition());
        yellowGate.setFillColor(Color.YELLOW);

        yellowKey = new Key(this, "yellow", getPlayer(), getYellowGate());
        yellowKey.setPosition(yellowKeyStartPosition());
        yellowKey.addCollisionListener(yellowKey);
        yellowGate.setDestroyed(false);
    }

    /**
     * Player starting position to be inherited in each level.
     *
     * @return Player starting position.
     */
    public abstract Vec2 playerStartPosition();

    /**
     * Box starting position to be inherited in each level.
     *
     * @return Box starting position.
     */
    public abstract Vec2 boxStartPosition();

    /**
     * Final pressure plate starting position to be inherited in each level.
     *
     * @return Final pressure plate starting position.
     */
    public abstract Vec2 pPlateStartPosition();

    /**
     * Yellow gate starting position to be inherited in each level.
     *
     * @return Yellow gate starting position.
     */
    public abstract Vec2 yellowGateStartPosition();

    /**
     * Yellow key starting position to be inherited in each level.
     *
     * @return Yellow key starting position.
     */
    public abstract Vec2 yellowKeyStartPosition();

    /**
     * Boolean whether or not the level is complete to be inherited in each
     * level.
     *
     * @return Is the level complete.
     */
    public abstract boolean isLevelComplete();
}
