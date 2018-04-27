package Main;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;

/**
 * Set of methods to create wall shaped StaticBodys.
 * @author h_nan
 */
public class Wall {
    /**
     * Creates a wall object which consists of a BoxShape and a StaticBody.
     * @param world current level.
     * @param wFloat the width.
     * @param hFloat the height.
     * @param xFloat the x coordinate.
     * @param yFloat the y coordinate.
     * @return the StaticBody.
     */
    public static StaticBody makeWall(World world, float wFloat, float hFloat, float xFloat, float yFloat){
        Shape wallShape = new BoxShape(wFloat, hFloat, new Vec2(xFloat, yFloat));
        StaticBody wall = new StaticBody(world, wallShape);
        wall.setFillColor(Color.LIGHT_GRAY);
        return wall;
    }
    
    /**
     * Creates a set of platforms consisting of a BoxShape and a StaticBody.
     * @param world current level.
     * @param xFloat the x coordinate.
     * @param yFloat the y coordinate.
     */
    public static void makeJumps(World world, float xFloat, float yFloat){
        Shape wallShape = new BoxShape(0.5f, 0.25f, new Vec2(xFloat, yFloat));
        StaticBody wall = new StaticBody(world, wallShape);
        wall.setFillColor(Color.DARK_GRAY);
    }
    
    /**
     * Creates a triangular shaped object to fit into corners to prevent the 
     *  box from getting stuck.
     * Consists of a PolygonShape and a StaticBody.
     * @param world the current level.
     * @param xFloat the x coordinate.
     * @param yFloat the y coordinate.
     * @param theta the angle of rotation in degrees.
     * @param triangleScale - a multiplier to scale the object.
     * @return the StaticBody.
     */
    public static StaticBody makeCorner(World world, float xFloat, float yFloat, int theta, float triangleScale){
        Shape cornerShape = new PolygonShape(-0.49f*triangleScale,0.488f*triangleScale, -0.489f*triangleScale,-0.47f*triangleScale, 0.468f*triangleScale,-0.468f*triangleScale);
        StaticBody corner = new StaticBody(world, cornerShape);
        corner.setPosition(new Vec2(xFloat,yFloat));
        corner.rotateDegrees(theta);
        corner.setFillColor(Color.LIGHT_GRAY);
        return corner;
    }

    /**
     * Creates a fixed size wall object which consists of a BoxShape and a 
     *  StaticBody, but the width of the wall can be changed.
     * @param world the current level.
     * @param xFloat the x coordinate.
     * @param yFloat the y coordinate.
     * @param changeW a multiplier to change the width of the wall.
     * @return the StaticBody
     */
    public static StaticBody addWall(World world, float xFloat, float yFloat, float changeW){
        Shape wallShape = new BoxShape(changeW*1f, 0.5f, new Vec2(xFloat, yFloat));
        StaticBody wall = new StaticBody(world, wallShape);
        wall.setFillColor(Color.LIGHT_GRAY);
        return wall;
    }
}
