package Main;

import Objects.Key;
import Levels.Level3;
import Levels.Level2;
import Levels.Level1;
import city.cs.engine.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

/**
 * The computer game.
 */
public class Game implements ActionListener {

    /**
     * The World in which the bodies move and interact.
     */
    private GameLevel world;

    /**
     * A graphical display of the world (a specialised JPanel).
     */
    private UserView view;

    private static int level;

    private Controller controller;

    private JFrame debugView;

    private static SoundClip sound;

    private Timer timer;

    private int seconds;

    private int minutes;

    private int hours;

    private DecimalFormat formatter;

    private double volume = 0.2;

    private JFrame frame;

    /**
     * Initialise a new Game.
     */
    public Game() {
        // make the world
        level = 1;
        world = new Level1();
        world.populate(this);

        playLevel1Song();

        timer = new Timer(1000, this);
        timer.start();

        formatter = new DecimalFormat("00");

        // make a view
        view = new UserViewBackground(world, 480, 480, this);

        // uncomment this to draw a 1-metre grid over the view
//        view.setGridResolution(1);
        // display the view in a frame
        frame = new JFrame("Puzzle game");

        Container buttons = new ControlPanel(this);
        frame.add(buttons, BorderLayout.NORTH);

        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // display the world in the window
        frame.add(view);
        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        // make the window visible
        frame.setVisible(true);
        // get keyboard focus
        frame.requestFocus();
        // give keyboard focus to the frame whenever the mouse enters the view
        view.addMouseListener(new GiveFocus(frame));

        //debugView = new DebugViewer(world, 480, 480);
        controller = new Controller(world, world.getPlayer(), world.getBox(), 
                Key.getKeyList(), world.getYellowGate(), Level2.getRedGate());
        frame.addKeyListener(controller);

        // start!
        world.start();
    }

    /**
     * Stops the game and closes the JFrame.
     */
    public void stopGame() {
        sound.stop();
        world.stop();
        frame.dispose();
    }

    /**
     * Is the current level of the game finished?
     *
     * @return if it is completed or not.
     */
    public boolean isLevelComplete() {
        return world.isLevelComplete();
    }

    /**
     * Restarts the game.
     */
    public void restart() {
        timer.restart();
        seconds = 0;
        minutes = 0;
        hours = 0;
        goLevel1();
    }

    /**
     * Pauses the current level.
     */
    public void pause() {
        timer.stop();
        sound.pause();
        world.stop();
    }

    /**
     * Resumes the current level.
     */
    public void resume() {
        timer.start();
        sound.resume();
        world.start();
    }

    /**
     * Advances to the next level and goes back to the main menu 
     * if game completed
     */
    public void goNextLevel() {
        world.stop();
        switch (level) {
            case 3:
                writeToFile();
                frame.dispose();
                MainMenu mm = new MainMenu();
                mm.setVisible(true);
                JOptionPane.showMessageDialog(frame, "Congratulations on "
                        + "completing the game.");
            case 1:
                goLevel2();
                System.out.println("Congratulations, you have completed "
                        + "the first level!");
                break;
            case 2:
                goLevel3();
                System.out.println("Congratulations, you have completed "
                        + "the second level!");
                break;
        }
    }

    /**
     * Creates a level 1 and sets the view to that level.
     */
    public void goLevel1() {
        world.stop();
        level = 1;
        world = new Level1();
        world.populate(this);
        controller.setBody(world.getPlayer(), world.getBox(), 
                world.getYellowGate());
        view.setWorld(world);
        sound.stop();
        playLevel1Song();
        world.start();
    }

    /**
     * Creates a level 2 and sets the view to that level.
     */
    public void goLevel2() {
        world.stop();
        level = 2;
        world = new Level2();
        world.populate(this);
        controller.setBody2(world.getPlayer(), world.getBox(), 
                world.getYellowGate(), Level2.getRedGate());
        view.setWorld(world);
        sound.stop();
        try {
            sound = new SoundClip("src\\Main\\sounds\\level2.wav");
            sound.play();
            sound.setVolume(volume + 0.1);
            sound.loop();
        } catch (UnsupportedAudioFileException | IOException 
                | LineUnavailableException e) {
            System.out.println(e);
        }
        world.start();
    }

    /**
     * Creates a level 3 and sets the view to that level.
     */
    public void goLevel3() {
        world.stop();
        level = 3;
        world = new Level3();
        world.populate(this);
        controller.setBody2(world.getPlayer(), world.getBox(), 
                world.getYellowGate(), Level3.getRedGate());
        view.setWorld(world);
        sound.stop();
        try {
            sound = new SoundClip("src\\Main\\sounds\\level3.wav");
            sound.play();
            sound.setVolume(volume);
            sound.loop();
        } catch (UnsupportedAudioFileException | IOException 
                | LineUnavailableException e) {
            System.out.println(e);
        }
        world.start();

    }

    /**
     * Creates a high score text file if not already created and writes to that 
     * file with the final time taken.
     */
    private void writeToFile() {
        Path path = Paths.get("src\\Main\\highscores.txt");
        File file = new File("src\\Main\\highscores.txt");
        try {
            Files.createFile(path);
            System.out.println("File created.");
        } catch (IOException e) {
            System.out.println("File already exists.");
        }
        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(";" + MainMenu.getPlayerName() + ";" + getHours() + ":" 
                    + getMinutes() + ":" + getSeconds());
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Writing to file error");
        }
    }

    /**
     * Plays a background music in level 1.
     */
    private void playLevel1Song() {
        try {
            sound = new SoundClip("src\\Main\\sounds\\level1.wav");
            sound.play();
            sound.setVolume(volume);
            sound.loop();
        } catch (UnsupportedAudioFileException | IOException 
                | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Using the timer, every second is incremented and every 60 seconds is
     * converted into a minute, and every 60 minutes is converted into an hour.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        seconds++;
        if (seconds % 60 == 0) {
            minutes++;
            seconds = 0;
        } else if (minutes % 60 == 0 && minutes > 0) {
            hours++;
            minutes = 0;
        }
    }

    /**
     *
     * @return seconds converted into double digits
     */
    public String getSeconds() {
        return format00(seconds);
    }

    /**
     *
     * @return seconds converted into double digits
     */
    public String getMinutes() {
        return format00(minutes);
    }

    /**
     *
     * @return seconds converted into double digits
     */
    public String getHours() {
        return format00(hours);
    }

    /**
     * Converted an integer from a single digit to double digits
     *
     * @param a integer to be converted
     * @return converted integer
     */
    public String format00(int a) {
        return formatter.format(a);
    }

    /**
     *
     * @return the current SoundClip playing
     */
    public static SoundClip getSound() {
        return sound;
    }

    /**
     *
     * @return the volume used for all the background musics
     */
    public double getVolume() {
        return volume;
    }

    /**
     * @return level
     */
    public static int getLevel() {
        return level;
    }

    /**
     * Runs the game.
     *
     * @param args N/A
     */
    public static void main(String[] args) {
        Game game = new Game();
    }
}
