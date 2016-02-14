import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

class Entry
{  String [] details;
   final int MAXDETAILS = 6;

   public Entry(String [] newdetails)
   {  details = new String[MAXDETAILS];
      for(int i=0;i<MAXDETAILS;i++)
       details[i]=newdetails[i];
   }

   public void writeEntry(PrintWriter file)
   {  for(int i=0;i<MAXDETAILS;i++)
       file.println(details[i]);
   }
}

class Address extends JFrame implements ActionListener
{  JLabel [] headings;
   String [] text = {"Name","Street","Town","Postcode","Telephone","Email"};
   JTextField [] details;
   final int MAXDETAILS = 6;
   JPanel entry,controls;
   JButton [] actions;
   String [] actionText ={"New","Add","Change","Delete","Forward","Back","Quit"};
   final int MAXACTIONS = 7;

   Entry [] entries;
   int entryno;
   final int MAXENTRIES = 100;
   int current;

   final String addressbook = "addressbook.txt";

   JTextField setupTextField(String s,Container c)
   {  JTextField t = new JTextField(s);
      t.setFont(new Font("Sansserif",Font.PLAIN,18));
      t.setBackground(Color.white);
      t.setOpaque(true);
      c.add(t);
      t.addActionListener(this);
      return t;
   }

   JButton setupButton(String s,Container c)
   {  JButton b = new JButton(s);
      b.setFont(new Font("Sansserif",Font.PLAIN,18));
      b.setBackground(Color.white);
      b.setOpaque(true);
      c.add(b);
      b.addActionListener(this);
      return b;
   }
   
   JLabel setupLabel(String s,Container c)
   {  JLabel l = new JLabel(s,JLabel.CENTER);
      l.setFont(new Font("Sansserif",Font.PLAIN,18));
      l.setBackground(Color.white);
      l.setOpaque(true);
      c.add(l);
      return l;
   }

   public Address()
   {  entry = new JPanel(new GridLayout(MAXDETAILS,2));
      headings = new JLabel[MAXDETAILS];
      details = new JTextField[MAXDETAILS];
      for(int i=0;i<6;i++)
      {  headings[i]=setupLabel(text[i],entry);
         details[i]=setupTextField("",entry);
      }
      add("Center",entry);
      controls = new JPanel(new GridLayout(MAXACTIONS,1));
      actions = new JButton[MAXACTIONS];
      for(int i=0;i<MAXACTIONS;i++)
       actions[i]=setupButton(actionText[i],controls);
      actions[1].setEnabled(false);
      add("West",controls);
   }

   void readEntries() throws IOException
   {  BufferedReader file;
      entries = new Entry[MAXENTRIES];
      entryno=0;
      String [] details = new String[MAXDETAILS];
      try
      {  file = new BufferedReader(new FileReader(addressbook));
      }
      catch(FileNotFoundException e)
      {  current=-1;
         return;
      }
      String line = file.readLine();
      while(line!=null)
      {  if(entryno==MAXENTRIES)
         {  System.out.println("More than "+MAXENTRIES+" entries.");
            break;
         }
         details[0]=line;
         for(int i=1;i<MAXDETAILS;i++)
          details[i]=file.readLine();
         entries[entryno]=new Entry(details);
         entryno++;
         line = file.readLine();
      }
      file.close();
      if(entryno!=0)
      {  current=0;
         showEntry(entries[0]);
      }
   }

   void showEntry(Entry e)
   {  for(int i=0;i<MAXDETAILS;i++)
       details[i].setText(e.details[i]);
   }

   void doQuit()
   {  if(entryno==0)
       System.exit(0);
      try
      {  PrintWriter file = new PrintWriter(new FileWriter(addressbook));
         for(int i=0;i<entryno;i++)
          entries[i].writeEntry(file);
         file.close();
         System.exit(0);
      }
      catch(IOException e){};
   }

   void doNew()
   {  int i;
      if(entryno==MAXENTRIES)
      {  System.out.println("More than "+MAXENTRIES+" entries.");
         return;
      }
      actions[0].setEnabled(false);
      actions[1].setEnabled(true);
      for(i=2;i<MAXACTIONS;i++)
       actions[i].setEnabled(false);
      for(i=0;i<MAXDETAILS;i++)
       details[i].setText("");
   }

   void doAdd()
   {  int i,j;
      String [] newdetails = new String[MAXDETAILS];
      for(i=0;i<MAXDETAILS;i++)
       newdetails[i]=details[i].getText();
      Entry e = new Entry(newdetails);
      for(i=0;i<entryno;i++)
       if(e.details[0].compareTo(entries[i].details[0])<0)
        break;
      for(j=entryno;j>i;j--)
       entries[j]=entries[j-1];
      entries[i]=e;
      current=i;
      entryno++;
      actions[0].setEnabled(true);
      actions[1].setEnabled(false);
      for(i=2;i<MAXACTIONS;i++)
       actions[i].setEnabled(true);
   }

   void doChange()
   {  if(entryno==0)
       return;
      for(int i=current;i<entryno-1;i++)
       entries[i]=entries[i+1];
      entryno--;
      if(entryno==0)
      {  for(int i=0;i<MAXDETAILS;i++)
          details[i].setText("");
         return;
      }
      if(current==entryno)
       current--;
      doAdd();
   }
       
   void doForward()
   {  if(entryno==0 || current+1==entryno)
       return;
      current++;
      showEntry(entries[current]);
   }

   void doBack()
   {  if(entryno==0 || current==0)
       return;
      current--;
      showEntry(entries[current]);
   }

   void doDelete()
   {  if(entryno==0)
       return;
      for(int i=current;i<entryno-1;i++)
       entries[i]=entries[i+1];
      entryno--;
      if(entryno==0)
      {  for(int i=0;i<MAXDETAILS;i++)
          details[i].setText("");
         return;
      }
      if(current==entryno)
       current--;
      showEntry(entries[current]);
   }

   int state;

   final int INSPECT = 0;
   final int EXTEND = 1;
      
   public void actionPerformed(ActionEvent e)
   {  switch(state)
      {  case INSPECT: if(e.getSource()==actions[0]) // NEW
                       {  doNew(); state = EXTEND; return;  }
                       if(e.getSource()==actions[2]) // CHANGE
                       {  doChange(); return;  }
                       if(e.getSource()==actions[3]) // DELETE
                       {  doDelete(); return;  }
                       if(e.getSource()==actions[4]) // UP
                       {  doForward(); return;  }
                       if(e.getSource()==actions[5]) // DOWN
                       {  doBack(); return;  }
                       if(e.getSource()==actions[6]) // QUIT
                       {  doQuit();  return;  }
        case EXTEND: if(e.getSource()==actions[1]) // ADD
                     {  doAdd(); state = INSPECT; return;  }
      }
   }
}

class TestAddress
{  public static void main(String [] args) throws IOException
   {  Address a;
      a = new Address();
      a.setSize(600,280);
      a.setTitle("Address");
      a.setVisible(true);
      a.addWindowListener(new WindowAdapter()
                              {  public void windowClosing(WindowEvent e)
                                 {  System.exit(0); }
                              });
      a.readEntries();
      a.state = a.INSPECT;
   }
}