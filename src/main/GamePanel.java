package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import main.settings.Settings;

public class GamePanel extends JPanel implements Runnable {
    
    BufferedImage testImage;
    public String PLAYMODE = "k"; // k = keyboard | c = controller
    String line;
    Thread gameThread;
    public KeyHandler keyHandler = new KeyHandler(this);

    public GamePanel() {

        this.setPreferredSize(new Dimension(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }



    public void startgameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }



    @Override
    public void run() {

        //* Main game loop

        double drawInterval = 1000000000 / Settings.FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {

                // UPDATE
                update();

                // DRAW
                //? Uses built-in method to call the other overridden methods
                repaint();

                delta--;

            }

        }

    }



    public void update() {
        
        //

    }



    //? Default method overridden
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;


        BufferedImage baseDisplay = new BufferedImage(Settings.BASE_SCREEN_WIDTH, Settings.BASE_SCREEN_WIDTH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D bg2 = (Graphics2D) baseDisplay.getGraphics();


        //


        bg2.dispose();

        g2.drawImage(baseDisplay, 0, 0, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT, null);

        g2.dispose();

    }

}
