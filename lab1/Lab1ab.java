import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class White extends JFrame {
	public White(Color color) {
		getContentPane().setBackground(color); }
	}

class TestWhite { 
	public static void main(String [] args) {
		White red;
 		red = new White(Color.red);
 		red.setSize(250,250);
 		red.setTitle("Red");
		red.setVisible(true);
		red.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
 		});

 		White blue;
 		blue = new White(Color.blue);
 		blue.setSize(350,100);
 		blue.setTitle("Blue");
		blue.setVisible(true);
		blue.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
 		});

 		White green;
 		green = new White(Color.green);
 		green.setSize(200,450);
 		green.setTitle("Green");
		green.setVisible(true);
		green.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
 		});
 	}
}