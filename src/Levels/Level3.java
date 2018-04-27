/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Levels;

import city.cs.engine.*;
import Main.Game;
import Main.GameLevel;
import Objects.Gate;
import Objects.Key;
import Objects.PressurePlate;
import Objects.Teleporter;
import org.jbox2d.common.Vec2;

import java.awt.*;

import static Main.Wall.*;

/**
 * Level 3
 * 
 * @author Harmeet
 */
public class Level3 extends GameLevel{
    private static Gate redGate;

    /**
     * Instantiates the objects for this specific level
     *
     * @param game The current game.
     */
    @Override
    public void populate(Game game) {
        makeMap();
        super.populate(game);
        Key.getKeyList().clear();

        redGate = new Gate(this);
        redGate.setPosition(new Vec2(-10.0f,-4.25f));
        redGate.setFillColor(Color.RED);

        Key redKey = new Key(this, "red", super.getPlayer(), redGate);
        redKey.addImage(Key.red);
        redKey.setPosition(new Vec2(-6.5f, 10.5f));
        redKey.addCollisionListener(redKey);

        Gate gate = new Gate(this);
        gate.setPosition(new Vec2(-0.5f,-1.0f));
        gate.rotateDegrees(90);

        PressurePlate pPlate1 = new PressurePlate(this, getBox(), gate);
        pPlate1.setPosition(new Vec2(10.0f,-2.75f));
        pPlate1.addCollisionListener(pPlate1);

        PressurePlate pPlate2 = new PressurePlate(this, getBox(), true);
        pPlate2.setPosition(new Vec2(-10.0f, -3.50f));
        pPlate2.addCollisionListener(pPlate2);

        Teleporter tp = new Teleporter(this, 10.0f, -15.0f, super.getPlayer(), super.getBox());
        tp.addCollisionListener(tp);
    }

    /**
     * Overrides player starting position in the world.
     *
     * @return New player starting position
     */
    @Override
    public Vec2 playerStartPosition() {
        return new Vec2(4.5f, -6.0f);
    }
    
    /**
     * Overrides box starting position in the world.
     *
     * @return New box starting position
     */
    @Override
    public Vec2 boxStartPosition() {
        return new Vec2(6.5f, -5.0f);
    }
    
    /**
     * Overrides final pressure plate starting position in the world.
     *
     * @return New final pressure plate starting position
     */
    @Override
    public Vec2 pPlateStartPosition() {
        return new Vec2(-10.0f, -11.75f);
    }
    
    /**
     * Encapsulation to retrieve private field isLevelComplete.
     *
     * @return Boolean value whether or not the level is complete
     */
    @Override
    public boolean isLevelComplete() {
        return isLevelComplete;
    }
    
    /**
     * Overrides yellow gate starting position in the world.
     *
     * @return New starting position
     */
    @Override
    public Vec2 yellowGateStartPosition() {
        return new Vec2(1.0f,6.5f);
    }
    
    /**
     * Overrides yellow key starting position in the world.
     *
     * @return New yellow key starting position
     */
    @Override
    public Vec2 yellowKeyStartPosition() {
        return new Vec2(5.5f, 10.5f);
    }

    /**
     * Encapsulation to retrieve private field redGate.
     *
     * @return the red gate
     */
    public static Gate getRedGate(){
        return redGate;
    }

    /**
     * Method to create the walls and corners of the map
     */
    private void makeMap(){
        makeCorner(this,10.5f,7.5f,90,1.0f);
        makeCorner(this,0.25f,0.25f,0,0.5f);
        makeCorner(this,10.5f,5.5f,180,1.0f);

        makeWall(this, 10.5f, 0.5f,-1.5f, 11.5f); //top
        makeWall(this, 8.5f, 0.5f,-0.5f, -11.5f); //bottom
        makeWall(this, 0.5f, 11.5f,-11.5f, -0.5f); //left comment
        makeWall(this, 0.5f, 15.0f,11.5f, 0.5f); //right
        makeWall(this, 9.0f, 0.5f,0.0f, -2.5f);
        makeWall(this,1.5f,0.5f,9.5f,-3.5f);
        makeWall(this, 0.5f, 4.0f, -8.5f, -7.0f);
        makeWall(this, 0.5f, 3.75f, 8.5f, -10.75f);
        makeWall(this,0.5f, 2.5f, 2.5f,-5.5f);
        makeWall(this,2.5f, 0.5f, 5.5f,-7.5f);
        makeWall(this,0.5f, 5.5f, -0.5f,5.5f); //comment
        makeWall(this, 4.5f, 0.5f,6.5f,6.5f);
        makeWall(this, 0.5f,1.75f,8.5f,13.75f);
        makeWall(this,1.0f,0.5f,10.0f,15.0f);
    }
}
