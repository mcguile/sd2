import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Reaction2 extends JFrame
{  BufferedReader keyboard =
    new BufferedReader(new InputStreamReader(System.in));

   JLabel instructions;
   JLabel stopgo;
   JLabel time;

   Reaction2()
   {  setLayout(new GridLayout(3,1));

      instructions = new JLabel("Press RETURN when the red changes to green");
      instructions.setOpaque(true);
      add(instructions);

      stopgo = new JLabel("");
      stopgo.setBackground(Color.red);
      stopgo.setOpaque(true);
      add(stopgo);

      time = new JLabel("",JLabel.CENTER);
      time.setOpaque(true);
      add(time);
   }

   void pause(long millisecs)
   {  long current = Calendar.getInstance().getTimeInMillis();
      while(Calendar.getInstance().getTimeInMillis()-current<millisecs);
   }

   int abs(int x)
   {  if(x>=0)
       return x;
      return -x;
   }

   void reaction() throws IOException
   {   pause((long)abs(new Random().nextInt())%5000);
       long initial = Calendar.getInstance().getTimeInMillis();
       stopgo.setBackground(Color.green);
       String r = keyboard.readLine();
       long current = Calendar.getInstance().getTimeInMillis();
       time.setText((current-initial)+" millisecs");
   }
}

class TestReaction2
{  public static void main(String [] args) throws IOException
   {  Reaction2 r = new Reaction2();
      r.setTitle("Reaction Timer");
      r.setSize(300,320);
      r.setVisible(true);
      r.addWindowListener(new WindowAdapter()
                              {  public void windowClosing(WindowEvent e)
                                 {  System.exit(0); }
                              });
      r.reaction();
   }
}

