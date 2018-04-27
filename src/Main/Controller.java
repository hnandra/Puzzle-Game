package Main;

import Objects.Key;
import Objects.Gate;
import Characters.Player;
import Characters.Box;;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.LinkedList;

import static Main.Wall.*;

/**
 * Key handler to control an Walker.
 */
public class Controller extends KeyAdapter {
    private static float JUMPING_SPEED = 7.5f;
    private static final float WALKING_SPEED = 4;
    
    private World world;
    private Player player;
    private Box box;
    private Gate yellowGate;
    private Gate redGate;
    private LinkedList<Key> keyList;
    private static SoundClip sound;
    
    
    public Controller(World world, Player player, Box box, LinkedList<Key> keyList, Gate yellowGate, Gate redGate) {
        this.world = world;
        this.player = player;
        this.box = box;
        this.yellowGate = yellowGate;
        this.redGate = redGate;
        this.keyList = keyList;
    }
    
    /**
     * Handle key press events for walking and jumping.
     * @param e description of the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_ESCAPE:
                // ESC = quit
                System.exit(0);
            case KeyEvent.VK_SPACE:
                // Space = jump
                Vec2 v = player.getLinearVelocity();
                // only jump if body is not already jumping
                if (Math.abs(v.y) < 0.01f) {
                    if(Game.getLevel() == 1) {
                        JUMPING_SPEED = 6;
                        player.jump(JUMPING_SPEED);
                        JUMPING_SPEED = 7.5f;
                    } else{
                        player.jump(JUMPING_SPEED);
                    }
                }   break;
            case KeyEvent.VK_A:
                player.startWalking(-WALKING_SPEED); // A = walk left
                player.removeAllImages(); // removes the players image
                player.addImage(new BodyImage("data/playerleft.gif", Player.SCALE)); // adds the image of the player looking left
                break;
            case KeyEvent.VK_D:
                player.startWalking(WALKING_SPEED); // D = walk right
                player.removeAllImages(); // remoevs the players image
                player.addImage(new BodyImage("data/playerright.gif", Player.SCALE)); // adds the image of the player looking right
                break;
            case KeyEvent.VK_W:
                player.setGravityScale(-1); // sets negative gravity
                box.setGravityScale(-1); // """"
                break;
            case KeyEvent.VK_S:
                player.setGravityScale(1); // resets the gravity to positive
                box.setGravityScale(1); // """"
                break;
            case KeyEvent.VK_E:
                if (Game.getLevel() == 1){
                    //System.out.println("empty: " + keyList.isEmpty() + ", yellowgatedestroyed: " + yellowGate.isDestroyed());
                    if (!keyList.isEmpty() && !yellowGate.isDestroyed()){
                        yellowGate.destroy();
                        yellowGate.setDestroyed(true);
                        gateDestroySound();
                        System.out.println("The yellow door has opened.");
                    } else {
                        System.out.println("You do not have any available keys!");
                    }
                }
                if (Game.getLevel() == 2) {
                    if (!keyList.isEmpty() && !yellowGate.isDestroyed() && !redGate.isDestroyed()) {
                        yellowGate.destroy();
                        yellowGate.setDestroyed(true);
                        gateDestroySound();
                        System.out.println("The yellow door has opened.");
                        addWall(this.player.getWorld(), -3.0f, 3.5f, 1f);
                    } else if (keyList.size() == 2 && !redGate.isDestroyed()) {
                        redGate.destroy();
                        redGate.setDestroyed(true);
                        gateDestroySound();
                        System.out.println("The red door has opened.");
                    } else {
                        System.out.println("You do not have any available keys!");
                    }
                } else if (Game.getLevel() == 3) {
                if (!keyList.isEmpty() && !yellowGate.isDestroyed() && !redGate.isDestroyed()) {
                    yellowGate.destroy();
                    yellowGate.setDestroyed(true);
                    gateDestroySound();
                    System.out.println("The yellow door has opened.");
                } else if (keyList.size() == 2 && !redGate.isDestroyed()) {
                    redGate.destroy();
                    redGate.setDestroyed(true);
                    gateDestroySound();
                    System.out.println("The red door has opened.");
                } else {
                    System.out.println("You do not have any available keys!");
                }
            }
                break;
            default:
                break;
        }
    }
    
    /**
     * Handle key release events (stop walking).
     * @param e description of the key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            player.startWalking(0);
        } else if (code == KeyEvent.VK_D) {
            player.startWalking(0);
        }
    }

    public void setBody(Player player, Box box, Gate yellowGate) {
        this.player = player;
        this.box = box;
        this.yellowGate = yellowGate;
    }

    public void setBody2(Player player, Box box, Gate yellowGate, Gate redGate) {
        this.player = player;
        this.box = box;
        this.yellowGate = yellowGate;
        this.redGate = redGate;
    }

    private void gateDestroySound(){
        try {
            sound = new SoundClip("src\\Main\\sounds\\dooropen.wav");
            sound.play();
            sound.setVolume(0.55);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
}
