import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class Timer3 extends JFrame implements ActionListener
{  JLabel prompt;
   Label countdown;
   JButton inc;
   JButton go;
   JPanel p;

   long time = 0;

   Timer3()
   {  setLayout(new GridLayout(3,1));

      prompt = new JLabel("Press + to increment time. 
      		Press "Go" to start timer.",JLabel.CENTER);
      add(prompt);

      countdown = new Label("0",JLabel.CENTER);
      countdown.setFont(new Font("Serif",Font.ITALIC,36));
      add(countdown);

      p = new JPanel(new GridLayout(1,2));

      inc = new JButton("+");
      inc.addActionListener(this);
      p.add(inc);

      go = new JButton("GO");
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

   void dotime()
   {  while(time>0)
      {  pause(1000);
         time--;
         countdown.setText(time+"");
         System.out.println(time+"");
      }
   }

   public void actionPerformed(ActionEvent e)
   {  if(e.getSource()==inc)
       inctime();
      else
      if(e.getSource()==go)
       dotime();
   }
}

class TestTimer3 
{  static public void main(String [] args)
   {  Timer3 t = new Timer3();
      t.setTitle("Timer");
      t.setSize(220,200);
      t.setVisible(true);
      t.addWindowListener(new WindowAdapter()
                              {  public void windowClosing(WindowEvent e)
                                 {  System.exit(0); }
                              });
   }
}

