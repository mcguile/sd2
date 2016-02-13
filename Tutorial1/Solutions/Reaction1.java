import java.io.*;
import java.util.*;

class Reaction1
{  Scanner keyboard = new Scanner(System.in);
   PrintWriter screen = new PrintWriter(System.out,true);

   void pause(long millisecs)
   {  long startTime = Calendar.getInstance().getTimeInMillis();
      while(Calendar.getInstance().getTimeInMillis()-startTime<millisecs);
   }

   int abs(int x)
   {  if(x>=0)
       return x;
      return -x;
   }

   void reaction() throws IOException
   {  screen.println("REACTION TIMER");
      pause((long)abs(new Random().nextInt())%5000);
      long initial = Calendar.getInstance().getTimeInMillis();
      screen.println("Press RETURN now!");
      String r = keyboard.nextLine();
      long current = Calendar.getInstance().getTimeInMillis();
      screen.println((current-initial)+" millisecs");
   }
}

class TestReaction1
{  public static void main(String [] args) throws IOException
   {  Reaction1 r = new Reaction1();
      r.reaction();
   }
}
