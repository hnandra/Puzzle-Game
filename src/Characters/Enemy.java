/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Characters;

import city.cs.engine.*;

/**
 * Creates an enemy.
 * @author h_nan
 */
public class Enemy extends Walker implements CollisionListener {

    private boolean isTouching = false;

    private Body leftWall, rightWall;

    private Player PLAYER;

    private final Shape shape = new PolygonShape(
            0.86f, -0.822f, 0.86f, 0.348f, 0.3f, 0.905f, -0.305f, 0.903f,
            -0.865f, 0.335f, -0.857f, -0.812f);
    private final BodyImage image
            = new BodyImage("data/enemyleft.gif", 1.75f);

    /**
     * Constructor of the Enemy class. Adds the image, creates the fixture and
     * sets the friction of the enemy and makes it move to the right.
     *
     * @param world The current level.
     * @param PLAYER The player in the level.
     * @param leftWall The left wall to collide with.
     * @param rightWall The right wall to collide with.
     */
    public Enemy(World world, Player PLAYER, Body leftWall,
            Body rightWall) {
        super(world);
        this.PLAYER = PLAYER;
        this.leftWall = leftWall;
        this.rightWall = rightWall;
        addImage(image);
        SolidFixture fixture = new SolidFixture(this, shape);
        fixture.setFriction(0);
        startWalking(-1);
    }

    /**
     * Checks for collision with a player, left wall and a right wall. If
     * collided with player, destroy the player. If collision with the left wall
     * or right wall, move right and move left respectively.
     *
     * @param e
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == PLAYER) {
            setTouching(true);
            if (isTouching()) {
//                System.out.println("touching");
                System.out.println("You have died!");
                PLAYER.destroy();
            }
        }
        if (e.getOtherBody() == leftWall) {
            startWalking(1);
            removeAllImages(); // removes the enemy image
            addImage(new BodyImage("data/enemyright.gif", 1.75f)); // adds the image of the enemy looking right
        }
        if (e.getOtherBody() == rightWall) {
            startWalking(-1);
            removeAllImages(); // removes the enemy image
            addImage(new BodyImage("data/enemyleft.gif", 1.75f)); // adds the image of the enemy looking left
        }
    }

    /**
     * Encapsulation to retrieve private field isTouching.
     *
     * @return Boolean whether or not the enemy is touching something.
     */
    public boolean isTouching() {
        return isTouching;
    }

    /**
     * Encapsulation to set private field isTouching.
     *
     * @param isTouching Boolean whether or not the enemy is touching something.
     */
    public void setTouching(boolean isTouching) {
        this.isTouching = isTouching;
    }
}
