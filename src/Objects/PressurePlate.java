/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import Characters.Box;
import city.cs.engine.*;
import Main.Game;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import static Main.Wall.addWall;
import static Main.Wall.makeJumps;

/**
 * Creates a pressure plate.
 * @author h_nan
 */
public class PressurePlate extends StaticBody implements CollisionListener {

    private boolean isStepped = false;

    private Game game;

    private Gate gate;

    private Box box, box2, box3;

    private Boolean noGate;

    private static SoundClip sound;

    private float scale = 0.375f;

    private Shape shape = new BoxShape(1, 0.25f);

    private Shape shape2 = new BoxShape(1, 0.01f);

    private SolidFixture fixture = new SolidFixture(this, shape);

    private SolidFixture fixture2 = new SolidFixture(this, shape2);

    private BodyImage image
            = new BodyImage("data/pplate.png", scale);

    /**
     * The constructor for the PressurePlate class. Adds the image of the
     * pressure plate. Used when a pressure plate is used to open a gate.
     *
     * @param world The current level.
     * @param box The box in the current level.
     * @param gate The corresponding gate for the pressure plate.
     */
    public PressurePlate(World world, Box box, Gate gate) {
        super(world);
        this.box = box;
        this.gate = gate;
        addImage(image);
    }

    /**
     * The constructor for the PressurePlate class. Adds the image of the
     * pressure plate Used when a pressure plate has no gate to open.
     *
     * @param world The current level.
     * @param box2 The box in the current level.
     * @param noGate Boolean whether or not there's a gate.
     */
    public PressurePlate(World world, Box box2, boolean noGate) {
        super(world);
        this.box2 = box2;
        this.noGate = noGate;
        addImage(image);
    }

    /**
     * The constructor for the PressurePlate class. Adds the image of the
     * pressure plate Used to end the game.
     *
     * @param world The current level.
     * @param box3 The box in the current level.
     * @param game The current game.
     */
    public PressurePlate(World world, Box box3, Game game) {
        super(world);
        this.box3 = box3;
        this.game = game;
        addImage(image);
    }

    /**
     * Checks for collisions with the box at differing levels and with different constructors.
     * Plays a sound clip when colliding with any box.
     * Advances to the next level if the final pressure plate is used.
     * @param e 
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Box) {
            try {
                sound = new SoundClip("src\\Main\\sounds\\click.wav");
                sound.play();
                sound.setVolume(0.05);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException f) {
                System.out.println(f);
            }
        }
        if (e.getOtherBody() == box) {
            setStepped(true);
            if (isStepped() && gate != null && !gate.isDestroyed()) {
                gate.destroy();
                gate.setDestroyed(true);
                changePressurePlate();
            }
        }
        if (Game.getLevel() == 2) {
            if (e.getOtherBody() == box2 && noGate) {
                changePressurePlate();
                jumpSection();
                addWall(this.getWorld(), 4.5f, -2.5f, 1.5f).rotateDegrees(90);
                gateCloseSound();
                noGate = false;
                destroy();
            }
        } else if (Game.getLevel() == 3) {
            if (e.getOtherBody() == box2 && noGate) {
                changePressurePlate();
                jumpSection2();
                addWall(this.getWorld(), -1.0f, 0.5f, 1.0f).rotateDegrees(90);
                gateCloseSound();
                noGate = false;
                destroy();
            }
        }
        if (e.getOtherBody() == box3 && !game.isLevelComplete()) {
            destroy();
            game.goNextLevel();
        }
    }

    private void changePressurePlate() {
        fixture.destroy();
        removeAllImages();
        addImage(new BodyImage("data/pplate2.png", scale));
    }
    
    /**
     * Encapsulation to retrieve private field isStepped.
     * 
     * @return Boolean whether or not the pressure plate has been stepped on.
     */
    public boolean isStepped() {
        return isStepped;
    }

    /**
     * Encapsulation to set private field isStepped.
     * 
     * @param isStepped Boolean whether or not the pressure plate has been stepped on.
     */
    public void setStepped(boolean isStepped) {
        this.isStepped = isStepped;
    }

    /**
     * Creates a set of platforms at fixed coordinates for level 2.
     */
    public void jumpSection() {
        makeJumps(this.getWorld(), 10.5f, 3.25f);
        makeJumps(this.getWorld(), 5.75f, 6.0f);
        makeJumps(this.getWorld(), 4f, 8.75f);
    }

    /**
     * Creates a set of platforms at fixed coordinates for level 3.
     */
    public void jumpSection2() {
        makeJumps(this.getWorld(), -10.5f, -1.75f);
        makeJumps(this.getWorld(), -7.5f, 1.0f);
        makeJumps(this.getWorld(), -1.5f, 3.0f);
        makeJumps(this.getWorld(), -10.5f, 3.5f);
        makeJumps(this.getWorld(), -5.5f, 6.0f);
    }

    /**
     * Plays a sound clip of the gate closing.
     */
    public void gateCloseSound() {
        try {
            sound = new SoundClip("src\\Main\\sounds\\doorclose.wav");
            sound.play();
            sound.setVolume(0.50);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
}
