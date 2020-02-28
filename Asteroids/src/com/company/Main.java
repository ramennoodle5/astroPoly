package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class Main implements Runnable {

    final int WIDTH = 800;
    final int HEIGHT = 700;
    public BufferStrategy bufferStrategy;
    public JFrame frame;
    public JPanel panel;
    public Canvas canvas;
    public Image asteroid;
    public Rectangle rectangle;


    polyCentre polyCentre1 = new polyCentre(0, 0, 1, 2);
    polyvector vector1 = new polyvector(polyCentre1.xpos, polyCentre1.ypos, 30, 30);

    Vector vector3 = new Vector(1.5708, 5);


    public static void main(String[] args) {

        Main ex = new Main();
        new Thread(ex).start();

    }

    public void setUpGraphics() {
        frame = new JFrame("PathFinder");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);


        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("Graphic Setup Finished");

    }

    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.drawRect(vector1.xpos,vector1.ypos, 3,3);
        g.drawRect(polyCentre1.xpos, polyCentre1.ypos, 10, 10);

        g.dispose();
        bufferStrategy.show();

    }

    @Override
    public void run() {

        asteroid = Toolkit.getDefaultToolkit().getImage("Back.png");

        setUpGraphics();


        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            polyCentre1.xpos = polyCentre1.xpos + polyCentre1.slopex;
            polyCentre1.ypos = polyCentre1.ypos + polyCentre1.slopey;

            vector1.xpos = polyCentre1.xpos + vector1.displacementX;
            vector1.ypos = polyCentre1.ypos + vector1.displacementY;

            render();

            System.out.println(vector3.xcom + ", " + vector3.ycom);

            System.out.println("center x,y: " + polyCentre1.xpos + "," + polyCentre1.ypos);
            System.out.println("vector 1 x,y: " + vector1.xpos + "," + vector1.ypos);
        }
    }
}
