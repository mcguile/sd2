import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;

class Reaction3 extends JFrame implements ActionListener
{  JLabel instructions;
   JLabel stopgo;
   JLabel time;
   JButton bstop,bstart;
   JPanel p;

   long initial;

   Reaction3()
   {  setLayout(new GridLayout(4,1));

      instructions =
       new JLabel("Press START. After green press STOP.",JLabel.CENTER);
      add(instructions);

      stopgo = new JLabel("");
      stopgo.setBackground(Color.red);
      stopgo.setOpaque(true);
      add(stopgo);

      time = new JLabel("",JLabel.CENTER);
      time.setOpaque(true);
      add(time);

      p = new JPanel(new GridLayout(1,2));

      bstart = new JButton("START");
      bstart.addActionListener(this);
      p.add(bstart);

      bstop = new JButton("STOP");
      bstop.addActionListener(this);
      p.add(bstop);

      add(p);
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

   void go()
   {   time.setText("");
       pause((long)abs(new Random().nextInt())%5000);
       initial = new Date().getTime();
       stopgo.setBackground(Color.green);
   }
 
   void done()
   {   long current = new Date().getTime();
       time.setText((current-initial)+" millisecs");
       stopgo.setBackground(Color.red);
   }

   public void actionPerformed(ActionEvent e)
   {  if(e.getSource()==bstart)
       go();
      else
      if(e.getSource()==bstop)
       done();
   }
      
}

class TestReaction3 
{  public static void main(String [] args)
   {  Reaction3 r = new Reaction3();
      r.setTitle("Reaction Timer");
      r.setSize(300,320);
      r.setVisible(true);
      r.addWindowListener(new WindowAdapter()
                              {  public void windowClosing(WindowEvent e)
                                 {  System.exit(0); }
                              });
   }
}

