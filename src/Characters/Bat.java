package Characters;

import city.cs.engine.*;

/**
 * Creates a bat.
 * @author h_nan
 */

public class Bat extends DynamicBody implements CollisionListener {

    private Player player;

    private Box box;

    private StaticBody topWall, bottomWall;

    private final float scale = 0.75f;

    private Shape shape = new PolygonShape(
            scale * -1.029f, scale * 0.488f, scale * -1.029f, scale * -0.496f,
            scale * 1.029f, scale * -0.496f, scale * 1.033f, scale * 0.496f);
    private BodyImage image
            = new BodyImage("data/bat1.gif", scale * 1.0f);

    /**
     * Constructor of the Bat class. Adds the image and creates a fixture for
     * the bat.
     *
     * @param world The current level.
     * @param player The player in that level.
     * @param box The box in that level.
     * @param topWall The top wall to check for collision.
     * @param bottomWall The bottom wall to check for collision.
     */
    public Bat(World world, Player player, Box box, StaticBody topWall,
            StaticBody bottomWall) {
        super(world);
        this.player = player;
        this.box = box;
        this.topWall = topWall;
        this.bottomWall = bottomWall;
        addImage(image);
        SolidFixture fixture = new SolidFixture(this, shape, 2);
    }

    /**
     * Constructor of the Bat class. Creates a fixture for the bat.
     *
     * @param world The current level.
     * @param player The player in that level.
     * @param box The box in that level.
     * @param topWall The top wall to check for collision.
     * @param bottomWall The bottom wall to check for collision.
     * @param image The new image for the bat.
     */
    public Bat(World world, Player player, Box box, StaticBody topWall,
            StaticBody bottomWall, BodyImage image) {
        super(world);
        this.player = player;
        this.box = box;
        this.topWall = topWall;
        this.bottomWall = bottomWall;
        this.image = image;
        addImage(image);
        SolidFixture fixture = new SolidFixture(this, shape, 2);
    }

    /**
     * Checks for collision with a player, box, top wall and bottom wall.
     *
     * If collided with a player, destroy the player.
     *
     * If collided with a box, destroy the box.
     *
     * If collision with the bottom wall or top wall, reverse and revert gravity
     * respectively.
     *
     * @param e
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == player) {
            System.out.println("You have died!");
            e.getOtherBody().destroy();
        } else if (e.getOtherBody() == box) {
            e.getOtherBody().destroy();
            System.out.println("The bat broke your box!");
        }
        if (e.getOtherBody() == topWall) {
            setGravityScale(0.1f);
        }
        if (e.getOtherBody() == bottomWall) {
            setGravityScale(-0.1f);
        }
    }

}
