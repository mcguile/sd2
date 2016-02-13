import java.util.*;
import java.io.*;

class Timer1 {
	Scanner s = new Scanner(System.in);
	
	void pause(long millisecs) {
			long current = Calendar.getInstance().getTimeInMillis();
			while (Calendar.getInstance().getTimeInMillis() - current<millisecs);
		}

	void Timer1() {
		System.out.println("Enter a time in seconds: ");
		int seconds = s.nextInt();
		while (seconds>0) {
			System.out.println(seconds);
			pause(1000);
			seconds--;
		}
	}
}

class TestTimer1 {
	public static void main(String[] args) {
		Timer1 t = new Timer1();
		t.Timer1();
	}	
}