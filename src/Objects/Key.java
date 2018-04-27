/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import Characters.Player;
import city.cs.engine.*;
import Main.Game;
import Main.Wall;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Creates a key.
 * @author h_nan
 */
public class Key extends StaticBody implements CollisionListener {

    private static LinkedList<Key> keyList = new LinkedList<>();

    private Player player;

    private Gate gate;

    private String keyColor;

    private static SoundClip sound;

    private static final Shape SHAPE = new BoxShape(0.5f, 0.5f);

    private final BodyImage yellow
            = new BodyImage("data/key.png", 1);

    /**
     * Instantiates a BodyImage for the red key.
     */
    public static final BodyImage red
            = new BodyImage("data/key2.png", 1);

    /**
     * Constructor for the Key class. Adds the image and fixture for the key.
     *
     * @param world The current level.
     * @param keyColor The colour of the key.
     * @param player The player in the current level.
     * @param gate The gate for the key in the current level.
     */
    public Key(World world, String keyColor, Player player, Gate gate) {
        super(world);
        this.keyColor = keyColor;
        this.player = player;
        this.gate = gate;
        addImage(yellow);
        SolidFixture fixture = new SolidFixture(this, SHAPE);

    }

    /**
     * Checks for collision with a player. If a key collides with a player, it
     * will play a sound clip, add the key to a key linkedlist and then destroy
     * the key.
     *
     * @param e
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == player) {
            try {
                sound = new SoundClip("src\\Main\\sounds\\pickup.wav");
                sound.play();
                sound.setVolume(0.20);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException f) {
                System.out.println(f);
            }
            addKey(keyList, this);
            getKey(keyList, 0);
            e.getReportingBody().destroy();
            System.out.println("You are holding the " + keyColor + " key.");
            System.out.println("Press E next to the " + keyColor + " door to open it.");
            if ("yellow".equals(this.getKeyColor()) && Game.getLevel() == 2) {
                Wall.makeCorner(this.getWorld(), -7.75f, 2.75f, -90, 0.5f);
            }
        }
    }

    private String getKeyColor() {
        return keyColor;
    }

    private static Key getKey(LinkedList<Key> list, int pos) {
        return list.get(pos);
    }

    private static void addKey(LinkedList<Key> list, Key key) {
        list.add(key);
    }

    /**
     * Method to retrieve the key linkedlist.
     *
     * @return LinkedList keyList.
     */
    public static LinkedList<Key> getKeyList() {
        return keyList;
    }
}
