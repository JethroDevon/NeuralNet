import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.BitSet;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Font;
import java.lang.Math;
import java.awt.*;
import java.io.*;

class Main extends JFrame{

  NeuralNet nn;
	public Main(){

    nn = new NeuralNet( 0, 5, 0, 1000, 800, 600);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();

		width = 800;
		height = 600;

		this.setUndecorated(true);
		this.add(nn);


    this.setLocation( screenSize.width/4,  screenSize.height/4);
	  this.pack();
		this.setSize( width, height);
		this.setVisible(true);

		//calls runnable function
		nn.run();

	}

	public static void main(String[] args) throws InterruptedException {

		new Main();
	}
}
