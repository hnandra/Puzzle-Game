/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import city.cs.engine.UserView;
import city.cs.engine.World;

import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Draws the background for each level.
 * 
 * @author h_nan
 */
public class UserViewBackground extends UserView {
    private Game game;
    private Image bg1, bg2, bg3;

    public UserViewBackground(World world,  int width, int height, Game game){
        super(world, width, height);
        this.game = game;
        bg1 = new ImageIcon("data/bg1.png").getImage();
        bg2 = new ImageIcon("data/bg2.png").getImage();
        bg3 = new ImageIcon("data/bg3.gif").getImage();
    }

    @Override
    protected void paintBackground(Graphics2D g){
        if (game.getLevel() == 1){
            g.drawImage(bg1, 0, 0, this);
        }
        if (game.getLevel() == 2){
            g.drawImage(bg2, 0, 0, this);
        }
        if (game.getLevel() == 3){
            g.drawImage(bg3, 0, 0, this);
        }
    }
    
    @Override
    protected void paintForeground(Graphics2D g){
        int fontSize = 15;
        g.setFont(new Font("Calibri", Font.BOLD, fontSize));
        g.setColor(Color.black);
        g.drawString("TIME TAKEN: " + game.getHours() + ":" + game.getMinutes() + ":" + game.getSeconds() + "", 5, 15);
    }
}
