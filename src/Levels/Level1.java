/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Levels;

import city.cs.engine.*;
import Characters.Bat;
import Characters.Enemy;
import Main.Game;
import Main.GameLevel;
import org.jbox2d.common.Vec2;

import static Main.Wall.*;

/**
 * Level 1
 * 
 * @author Harmeet
 */
public class Level1 extends GameLevel {

    /**
     * Instantiates the objects for this specific level
     * @param game The current game.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);
        makeMap();

        //objects in game
        Bat bat = new Bat(this, super.getPlayer(), super.getBox(), topCollisionWall(), bottomCollisionWall());
        bat.setPosition(new Vec2(8.0f, -10.0f));
        bat.addCollisionListener(bat);

        Enemy enemy = new Enemy(this, super.getPlayer(), leftCollisionCorner(), rightCollisionCorner());
        enemy.setPosition(new Vec2(0.0f, -4.25f));
        enemy.addCollisionListener(enemy);
    }

    /**
     * Overrides player starting position in the world.
     *
     * @return New player starting position.
     */
    @Override
    public Vec2 playerStartPosition() {
        return new Vec2(-7.0f, 8.5f);
    }

    /**
     * Overrides box starting position in the world.
     *
     * @return New box starting position.
     */
    @Override
    public Vec2 boxStartPosition() {
        return new Vec2(3.0f, 0.75f);
    }

    /**
     * Overrides final pressure plate starting position in the world.
     *
     * @return New final pressure plate starting position.
     */
    @Override
    public Vec2 pPlateStartPosition() {
        return new Vec2(-10.0f, -11.75f);
    }

    /**
     * Encapsulation to retrieve private field isLevelComplete.
     *
     * @return Boolean value whether or not the level is complete.
     */
    @Override
    public boolean isLevelComplete() {
        return isLevelComplete;
    }

    /**
     * Overrides yellow gate starting position in the world.
     *
     * @return New starting position.
     */
    @Override
    public Vec2 yellowGateStartPosition() {
        return new Vec2(-10.0f, -0.5f);
    }

    /**
     * Overrides yellow key starting position in the world.
     *
     * @return New yellow key starting position.
     */
    @Override
    public Vec2 yellowKeyStartPosition() {
        return new Vec2(-4.0f, 5.0f);
    }

    /**
     * Method to create the walls and corners of the map.
     */
    private void makeMap() {
        makeCorner(this, 10.5f, 0.5f, 90, 1); //first corner
        makeCorner(this, 10f, -10f, 90, 2); //fourth corner

        makeWall(this, 0.5f, 11.0f, 11.5f, 0.0f); //right
        makeWall(this, 0.5f, 11.5f, -11.5f, -0.5f); //left
        makeWall(this, 12.0f, 0.5f, 0.0f, 11.5f); //top
        makeWall(this, 9.0f, 0.5f, -2.0f, 6.5f); //first platform
        makeWall(this, 9.0f, 0.5f, -2.0f, -5.5f); //third platform
    }

    /**
     * Creates a wall to be able to be retrieved
     *.
     * @return The wall object made with the makeWall method.
     */
    private StaticBody topCollisionWall() {
        return makeWall(this, 10.0f, 0.5f, 1.0f, -0.5f); //second platform
    }

    /**
     * Creates a wall to be able to be retrieved.
     *
     * @return The wall object made with the makeWall method.
     */
    private StaticBody bottomCollisionWall() {
        return makeWall(this, 10.5f, 0.5f, 1.5f, -11.5f); //bottom
    }

    /**
     * Creates a wall to be able to be retrieved.
     *
     * @return The wall object made with the makeWall method.
     */
    private StaticBody leftCollisionCorner() {
        return makeCorner(this, -10.5f, -4.5f, 0, 1); //second corner
    }

    /**
     * Creates a wall to be able to be retrieved.
     *
     * @return The wall object made with the makeWall method.
     */
    private StaticBody rightCollisionCorner() {
        return makeCorner(this, 6.5f, -4.5f, 90, 1); //third corner
    }
}
