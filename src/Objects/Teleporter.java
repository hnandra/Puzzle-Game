/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import Characters.Enemy;
import Characters.Player;
import Characters.Box;
import city.cs.engine.*;
import Main.Game;
import Main.Wall;
import org.jbox2d.common.Vec2;

/**
 * Creates a teleporter.
 * 
 * @author h_nan
 */
public class Teleporter extends StaticBody implements CollisionListener {

    private final Player player;

    private final Box box;

    private Enemy enemy;

    private boolean cornerCreated = false;

    /**
     * Constructor of the Teleporter class. Creates the box shape and fixture of
     * the teleporter.
     *
     * @param world The current level.
     * @param x The x coordinate of the teleporter.
     * @param y The y coordinate of the teleporter.
     * @param player The player in the current level.
     * @param box The box in the current level.
     */
    public Teleporter(World world, float x, float y, Player player, Box box) {
        super(world);
        this.player = player;
        this.box = box;
        Shape wallShape = new BoxShape(2f, 0.5f, new Vec2(x, y));
        SolidFixture fixture = new SolidFixture(this, wallShape);
    }

    /**
     * Constructor of the Teleporter class. Creates the box shape and fixture of
     * the teleporter.
     *
     * @param world The current level.
     * @param x The x coordinate of the teleporter.
     * @param y The y coordinate of the teleporter.
     * @param player The player in the current level.
     * @param box The box in the current level.
     * @param enemy The enemy in the current level.
     */
    public Teleporter(World world, float x, float y, Player player, Box box, Enemy enemy) {
        super(world);
        this.player = player;
        this.box = box;
        this.enemy = enemy;
        Shape wallShape = new BoxShape(2f, 0.5f, new Vec2(x, y));
        SolidFixture fixture = new SolidFixture(this, wallShape);
    }

    /**
     * Checks for collision with an enemy, player and a box.
     *
     * If collided with an enemy, create a corner object if not already created.
     *
     * If collided with a player, set the player in a new position.
     *
     * If collided with a box, set the box in a new position.
     *
     * If collision with the bottom wall or top wall, reverse and revert gravity
     * respectively.
     *
     * @param e
     */
    @Override
    public void collide(CollisionEvent e) {
        if (Game.getLevel() == 2) {
            if (e.getOtherBody() != enemy) {
                if (!cornerCreated) {
                    Wall.makeCorner(this.getWorld(), 0.5f, 3.5f, 0, 1);
                    cornerCreated = true;
                }
                if (e.getOtherBody() == player) {
                    player.setPosition(new Vec2(1f, 13f));
                    System.out.println("Please place the box in the hole to begin.");
                }
                if (e.getOtherBody() == box) {
                    box.setPosition(new Vec2(1f, 13f));
                }
            } else {
                enemy.destroy();
            }
        } else if (Game.getLevel() == 3) {
            if (e.getOtherBody() == player) {
                player.setPosition(new Vec2(10.25f, 14.0f));
            }
            if (e.getOtherBody() == box) {
                box.setPosition(new Vec2(10.0f, 13.0f));
            }

        }

    }
}
