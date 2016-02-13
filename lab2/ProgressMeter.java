import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

// x
class ProgressMeter extends JFrame {
	BufferedReader keyboard =
    new BufferedReader(new InputStreamReader(System.in));

	JLabel[] labels;
	JLabel percentage;

	int perc;


	ProgressMeter() {
		setLayout(new GridLayout(11,1));

		labels=new JLabel[10];
        for (int i=0;i<10;i++){
            labels[i]=new JLabel();
            labels[i].setOpaque(true);
            labels[i].setBackground(Color.red);
            add(labels[i]);
        }

        percentage = new JLabel(String.valueOf(perc) + "0% complete", JLabel.CENTER);
		add(percentage);
	}

	void incPercentage() throws IOException {
		while (perc<10) {
			String enter = keyboard.readLine();
			labels[abs(perc-9)].setBackground(Color.green);
			perc++;
			percentage.setText(String.valueOf(perc) + "0% complete");
		}
	}

	int abs(int x) {  
		if(x>=0) {
       		return x;
		}
      	return -x;
   }

}

class TestProgressMeter {
	public static void main(String[] args) throws IOException {
		ProgressMeter pm = new ProgressMeter();
		pm.setTitle("Progress Meter");
		pm.setSize(200,600);
		pm.setVisible(true);
		pm.addWindowListener(new WindowAdapter()
                              {  public void windowClosing(WindowEvent e)
                                 {  System.exit(0); }
                              });
		pm.incPercentage();
	}
}