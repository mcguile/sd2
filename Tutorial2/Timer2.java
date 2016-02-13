import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

class Timer2 extends JFrame
{  BufferedReader keyboard =
    new BufferedReader(new InputStreamReader(System.in));

   JLabel prompt;
   JLabel countdown;

   Timer2()
   {  setLayout(new GridLayout(2,1));

      prompt = new JLabel("Please enter a time in seconds and press RETURN");
      prompt.setOpaque(true);
      add(prompt);

      countdown = new JLabel("",JLabel.CENTER);
      countdown.setFont(new Font("Serif",Font.ITALIC,36));
      countdown.setOpaque(true);
      add(countdown);
   }

   void pause(long millisecs)
   {  long current = Calendar.getInstance().getTimeInMillis();
      while(Calendar.getInstance().getTimeInMillis()-current<millisecs);
   }

   public void timer() throws IOException
   {  int time = Integer.parseInt(keyboard.readLine());
      while(time>=0)
      {  countdown.setText(time+"");
         pause(1000);
         time--;
      }
   }
}

class TestTimer2 
{  static public void main(String [] args) throws IOException
   {  Timer2 t = new Timer2();
      t.setTitle("Timer");
      t.setSize(320,120);
      t.setVisible(true);
      t.addWindowListener(new WindowAdapter()
                              {  public void windowClosing(WindowEvent e)
                                 {  System.exit(0); }
                              });
      t.timer();
   }
}

