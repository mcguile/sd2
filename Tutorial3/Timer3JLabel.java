import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class Timer3JLabel extends JFrame implements ActionListener
{  JLabel prompt;
   JLabel countdown;
   JButton inc;
   JButton go;
   JPanel p;
   boolean count = false;

   long time = 0;

   Timer3JLabel()
   {  setLayout(new GridLayout(3,1));

      prompt = new JLabel("Set time with +",JLabel.CENTER);
      add(prompt);

      countdown = new JLabel("0",JLabel.CENTER);
      countdown.setFont(new Font("Serif",Font.ITALIC,36));
      add(countdown);

      p = new JPanel(new GridLayout(1,2));

      inc = new JButton("+");
      inc.addActionListener(this);
      p.add(inc);

      go = new JButton("START");
      go.addActionListener(this);
      p.add(go);

      add(p);
   }

   void pause(long millisecs)
   {  long current = Calendar.getInstance().getTimeInMillis();
      while(Calendar.getInstance().getTimeInMillis()-current<millisecs);
   }

   void inctime()
   {  time++;
      countdown.setText(time+"");
   }

   public void dotime()
   {  while(true)
      {  pause(1000);
      	 if(count && time>0) {
			 time--;
			 countdown.setText(time+"");
			 System.out.println(time+"");
		 }
		 if(time==0) {
		 	 count = false;
		 }
      }
   }

   public void actionPerformed(ActionEvent e)
   {  if(e.getSource()==inc)
       inctime();
      else
      if(e.getSource()==go)
       count = true;
   }
}

class TestTimer3JLabel 
{  static public void main(String [] args)
   {  Timer3JLabel t = new Timer3JLabel();
      t.setTitle("Timer");
      t.setSize(220,200);
      t.setVisible(true);
      t.addWindowListener(new WindowAdapter()
                              {  public void windowClosing(WindowEvent e)
                                 {  System.exit(0); }
                              });
      
      // the counting loop is run continually, but only counts when the 'count' variable is set to true.
      t.dotime();
   }
}

