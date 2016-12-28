import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.lang.Math;
import java.util.Random;

/*

The aim for this code is to create a program that draws a set quadratic curve and then uses a Neural Network
to find that quadratic curves values afterwards by plotting points on a graph and returning the
error, this program will display the process

*/

public class NeuralNet extends Canvas implements Runnable{

    //thses two objects are the key for clipping/blitting
    private BufferStrategy bs;
    private Graphics graphics;

    //these values are for the actual quadratic curve
    float a,b,c;

    //these values are for the hypothesis curve and are randomly
    //initialised on constriction
    float ha, hb, hc;

    //this stores the total number of plotpoints to created each
    //iteration of the program
    int totalppoints;

    //screen dimensions
    int WIDTH, HEIGHT;

    //a constructor takes parameters for a curve to be drawn
    public NeuralNet( float _a, float _b, float _c, int points, int _w, int _h){

        //allows the buffer and window to be sized to args
        Dimension size = new Dimension( WIDTH = _w, HEIGHT = _h);
        setPreferredSize(size);

        a = _a;
        b = _b;
        c = _c;
        ha = -10 + (int)(Math.random() * 10);
        ha = -10 + (int)(Math.random() * 10);
        ha = -20 + (int)(Math.random() * 20);
        totalppoints = points;
    }

    public void drawCurve( Graphics g){

        //change colour to draw curve to fit to
        g.setColor( Color.RED);
        for (int x = -(WIDTH/2); x < WIDTH/2; x++) {

            g.drawOval( x +(WIDTH/2), (int)f(x) +(HEIGHT/2),  4, 4);
        }
    }

    public void drawHypoCurve( Graphics g){

        //change to blue for drawring hypothetical curve
        g.setColor( Color.BLUE);

        for (int x = -(WIDTH/2); x < WIDTH/2; x++) {

            g.drawOval( x +(WIDTH/2), (int)h(x) +(HEIGHT/2),  4, 4);
        }
    }

    //returns how far off a point was from the curve, +ve
    //for greater than and -ve for less
    public float pointError( float x, float y){

        float out = f( x);
        return y - out;
    }

    public float f( float x){

        return  (a * (x*x)) + (b * x) + c;
    }

    public float h( float x){

        return  (ha * (x*x)) + (hb * x) + hc;
    }

    //each step of simulation is ran here
    public void runSim(){

        bs = getBufferStrategy();

        try{

            if( bs == null){

                createBufferStrategy(1);

                return;
            }

            graphics = bs.getDrawGraphics();
        }catch( Exception e){

            System.out.println(" error running simulation");
        }

        //run program steps here
        drawCurve(graphics);
        drawHypoCurve(graphics);

        Toolkit.getDefaultToolkit().sync();
    }

    public void run(){

        while(true){

            runSim();
        }
    }
}
