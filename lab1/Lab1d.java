import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class Blackwhite extends JFrame {

	Scanner keyboard = new Scanner(System.in);

	public Blackwhite() {
		getContentPane().setBackground(Color.black); 
	}

	private void pause(long millisecs) {
		long startTime = Calendar.getInstance().getTimeInMillis();
		while (Calendar.getInstance().getTimeInMillis()- 
			startTime<millisecs);
	}

	public void flash() { 
		Color[] rainbow = {Color.red,Color.orange,Color.yellow,
 			Color.green,Color.blue,Color.magenta,Color.black};
 		int index = 0;
 		System.out.println("Press ENTER to change color");
		while (keyboard.hasNextLine()) {
			if (index==rainbow.length) index = 0;
			pause(1000);
			getContentPane().setBackground(rainbow[index]);
			index++;
		}
	}
}

class TestRainbowUserInput {
	public static void main(String [] args) {
		Blackwhite b;
 		b = new Blackwhite();
 		b.setSize(200,220);
 		b.setTitle("Rainbow User Input");
 		b.setVisible(true);
 		b.addWindowListener(new WindowAdapter() {
 			public void windowClosing(WindowEvent e) {
 				System.exit(0); 
 			}
 		});
 		b.flash();
 	}
 }