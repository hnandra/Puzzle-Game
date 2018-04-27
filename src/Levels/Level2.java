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
import Objects.Gate;
import Objects.Key;
import Objects.PressurePlate;
import Objects.Teleporter;
import org.jbox2d.common.Vec2;

import java.awt.*;

import static Main.Wall.*;

/**
 * Level 2
 * 
 * @author Harmeet
 */
public class Level2 extends GameLevel {

    private static Bat bat;
    private static Gate redGate;
    private static PressurePlate pPlate3;

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

        //objects in game
        Gate gate = new Gate(this);
        gate.setPosition(new Vec2(-10f, -2.5f));

        Gate gate2 = new Gate(this);
        gate2.setPosition(new Vec2(-3f, 3.5f));

        redGate = new Gate(this);
        redGate.setPosition(new Vec2(10.2f, 0.75f));
        redGate.setFillColor(Color.RED);

        getYellowGate().setDestroyed(false);
        getYellowGate().setPosition(yellowGateStartPosition());
        getYellowGate().rotateDegrees(90);

        StaticBody enemyCollisionWall = makeWall(this, 0.5f, 2.5f, -8.5f, 0.5f);
        Enemy enemy = new Enemy(this, super.getPlayer(), enemyCollisionWall, getYellowGate());
        enemy.setPosition(new Vec2(-3f, -1f));
        enemy.addCollisionListener(enemy);

        StaticBody topCollisionWall = makeWall(this, 4.0f, 0.5f, 7.0f, 14f);
        StaticBody bottomCollisionWall = makeWall(this, 3.2f, 0.5f, 6.2f, 2.5f);
        BodyImage image = new BodyImage("data/bat2.gif", 1.0f);
        bat = new Bat(this, super.getPlayer(), super.getBox(), topCollisionWall, bottomCollisionWall, image);
        bat.setPosition(new Vec2(7.5f, 4.0f));
        bat.addCollisionListener(bat);

        Teleporter teleporter = new Teleporter(this, 1.0f, -14.5f, super.getPlayer(), super.getBox(), enemy);
        teleporter.addCollisionListener(teleporter);

        getYellowKey().setPosition(yellowKeyStartPosition());
        getYellowKey().addCollisionListener(getYellowKey());

        Key redKey = new Key(this, "red", super.getPlayer(), redGate);
        redKey.addImage(Key.red);
        redKey.setPosition(new Vec2(10.5f, 11.5f));
        redKey.addCollisionListener(redKey);

        PressurePlate pPlate1 = new PressurePlate(this, super.getBox(), gate);
        pPlate1.addCollisionListener(pPlate1);
        pPlate1.setPosition(new Vec2(-10f, -10.75f));

        PressurePlate pPlate2 = new PressurePlate(this, super.getBox(), gate2);
        pPlate2.addCollisionListener(pPlate2);
        pPlate2.setPosition(new Vec2(-3f, 10.75f));
        pPlate2.rotateDegrees(180);

        pPlate3 = new PressurePlate(this, super.getBox(), true);
        pPlate3.addCollisionListener(pPlate3);
        pPlate3.setPosition(new Vec2(10.2f, 1.5f));

        getPPlate().addCollisionListener(getPPlate());
        getPPlate().setPosition(pPlateStartPosition());
    }

    /**
     * Overrides player starting position in the world.
     *
     * @return New player starting position.
     */
    @Override
    public Vec2 playerStartPosition() {
        return new Vec2(-5.0f, -5.0f);
    }

    /**
     * Overrides box starting position in the world.
     *
     * @return New box starting position.
     */
    @Override
    public Vec2 boxStartPosition() {
        return new Vec2(-7.0f, -5.0f);
    }

    /**
     * Overrides final pressure plate starting position in the world.
     *
     * @return New final pressure plate starting position.
     */
    @Override
    public Vec2 pPlateStartPosition() {
        return new Vec2(10.2f, -11.75f);
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
     * @return New starting position
     */
    @Override
    public Vec2 yellowGateStartPosition() {
        return new Vec2(-0.5f, -1);
    }

    /**
     * Overrides yellow key starting position in the world.
     *
     * @return New yellow key starting position.
     */
    @Override
    public Vec2 yellowKeyStartPosition() {
        return new Vec2(-7.5f, 2.5f);
    }

    /**
     * Encapsulation to retrieve private field redGate.
     *
     * @return the red gate
     */
    public static Gate getRedGate() {
        return redGate;
    }

    /**
     * Method to create the walls and corners of the map.
     */
    private void makeMap() {
        makeCorner(this, -10.75f, 7.75f, -90, 0.5f);

        makeWall(this, 2.0f, 0.5f, -10.0f, -11.5f);
        makeWall(this, 0.5f, 9.5f, -11.5f, -1.5f);
        makeWall(this, 0.5f, 1.5f, -8.5f, -9.5f);
        makeWall(this, 3.0f, 0.5f, -6.0f, -7.5f);
        makeWall(this, 0.5f, 2.0f, -3.5f, -5.0f);
        makeWall(this, 4.0f, 0.5f, -5.0f, -2.5f);
        makeWall(this, 2.5f, 0.5f, -6.5f, 3.5f);
        makeWall(this, 3.5f, 0.5f, -8.5f, 8.5f);
        makeWall(this, 0.5f, 1.5f, -4.5f, 9.5f);
        makeWall(this, 2.0f, 0.5f, -3.0f, 11.5f);
        makeWall(this, 0.5f, 4.0f, -1.5f, 7.0f);
        makeWall(this, 0.5f, 6.0f, -0.5f, -8.0f);
        makeWall(this, 0.5f, 7.0f, -0.5f, 7.0f);
        makeWall(this, 0.5f, 4.0f, 2.5f, 10.0f);
        makeWall(this, 2.0f, 0.5f, 1.0f, 14.5f); //above view
        makeWall(this, 1.0f, 0.5f, 1.0f, 2.5f);
        makeWall(this, 0.5f, 8.5f, 2.5f, -5.5f);
        makeWall(this, 0.5f, 13.25f, 11.5f, 1.25f);
        makeWall(this, 0.5f, 7.0f, 8.9f, -5.0f);
        makeWall(this, 1.8f, 0.5f, 10.2f, -12.5f);
    }
}
