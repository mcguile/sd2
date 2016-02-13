import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

class DigitalClock extends JFrame {

	final int TIMENO = 3;
	JLabel[] times = new JLabel[TIMENO];
	JLabel ampm = new JLabel("AM/PM", JLabel.CENTER);

	JPanel clock;

	Calendar currentTime = Calendar.getInstance();

	public DigitalClock() {
		setLayout(new GridLayout(2,1));
		times[0] = new JLabel(Integer.toString(currentTime.get(Calendar.HOUR)), JLabel.CENTER);
		times[1] = new JLabel(Integer.toString(currentTime.get(Calendar.MINUTE)), JLabel.CENTER);
		times[2] = new JLabel(Integer.toString(currentTime.get(Calendar.SECOND)), JLabel.CENTER);
		int amOrPm = currentTime.get(Calendar.AM_PM);
		if (amOrPm==0) {
			ampm.setText("AM");
		} else {
			ampm.setText("PM");
		}
		for (int i=0; i<TIMENO; i++) {
			times[i].setBackground(Color.yellow);
			times[i].setOpaque(true);
			times[i].setFont(new Font("Serif", Font.ITALIC, 36));
		}
		clock = new JPanel(new GridLayout(1,3));
		for (int i=0; i<TIMENO; i++) {
			clock.add(times[i]);
		}
		add(clock);
		add(ampm);
	}


	public void incrementTime() {
		while(true) {
			currentTime = Calendar.getInstance();
			int sec = currentTime.get(Calendar.SECOND);
			int min = currentTime.get(Calendar.MINUTE);
			int hour = currentTime.get(Calendar.HOUR);
			if (sec<59) {
				sec++;
				currentTime.set(Calendar.SECOND, sec);
				times[2].setText(sec + "");
			} else if (sec==59) {
				sec=0;
				min++;
				currentTime.set(Calendar.SECOND, 0);
				currentTime.set(Calendar.MINUTE, min);
				times[2].setText(sec + "");
				if (min<59) {
					times[1].setText(min + "");
				} else if (min==59) {
					min=0;
					hour++;
					currentTime.set(Calendar.MINUTE, 0);
					currentTime.set(Calendar.HOUR, hour);
					times[1].setText(min + "");
					if (hour<13) {
						currentTime.set(Calendar.HOUR, hour);
						times[0].setText(hour + "");
						ampm.setText("AM");
					} else if (hour>12 && hour!=23) {
						currentTime.set(Calendar.HOUR, hour-12);
						times[0].setText(hour-12 + "");
						ampm.setText("PM");
					} else if (hour==23) {
						hour=0;
						currentTime.set(Calendar.HOUR, 0);
						times[0].setText(hour + "");
					}
				}
			} 
		}
	}
}


class TestDigitalClock {
	public static void main(String[] args) throws IOException {
		DigitalClock dc = new DigitalClock();
		dc.setTitle("DigitalClock");
		dc.setSize(300, 200);
		dc.setVisible(true);
		dc.addWindowListener(new WindowAdapter()
                              {  public void windowClosing(WindowEvent e)
                                 {  System.exit(0); }
                              });
		dc.incrementTime();
	}
}