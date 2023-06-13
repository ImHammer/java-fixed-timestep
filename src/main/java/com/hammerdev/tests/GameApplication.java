package com.hammerdev.tests;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Hello world!
 *
 */
public class GameApplication extends Canvas implements Runnable
{
    private static GameApplication mainGame;

    public static void main(String[] args)
    {
        mainGame = new GameApplication();
        mainGame.start();

        System.out.println("Game Started...");
    }

    public static final double FIXED_FPS = 60.0;
    public static final double FRAME_DELAY = 1000.0 / FIXED_FPS;

    Thread thread;
    JFrame frame;
    BufferStrategy strategy;
    public boolean isRunning;

    public GameApplication()
    {
        frame = new JFrame("Window");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(100, 200);
        frame.setResizable(false);
        frame.setVisible(true);

        setSize(100, 200);
        setBackground(Color.BLACK);

        // createBufferStrategy(2);
        // strategy = getBufferStrategy();

        frame.add(this);
    }

    @Override
    public void run()
    {
        //// FIXED TIMESTEP
        double previousTime = System.currentTimeMillis();

        double deltaTime = 0.0;
        double frames = 0.0;
        double timer = System.currentTimeMillis();
        while (isRunning) {
            double currentTime = System.currentTimeMillis();
            double elapsedTime = (currentTime - previousTime);
            previousTime = currentTime;

            deltaTime += elapsedTime;
            
            while (deltaTime >= FRAME_DELAY) {
                update(deltaTime);
                deltaTime -= FRAME_DELAY;
                frames++;
            }

            if ((System.currentTimeMillis() - timer) >= 1000) {
                frames = 0.0;
                timer += 1000;
            }

            render();
        }
    }

    public void update(double deltaTime)
    {
        
    }

    public void render()
    {
        // System.out.println("RENDER");
    }

    public synchronized void start()
    {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stop()
    {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
