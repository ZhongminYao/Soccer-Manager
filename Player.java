/*  Soccer Manager
Player.java
Zhongmin Yao
Alan
zyao02
section 4 */
import java.util.*;
import java.io.*;
import java.util.Random;
public class Player 
{
  String name;
  int abilityValue;
  
  public Player()
  {name="";
    abilityValue=0;
  }
  
  public Player(String n,int v) // construcor with parameter name, and ability value to be set
  {name=n;
    abilityValue=v;
  }
  
  // a method to get player info from record file
  //(I/O)
  public Player[] loadPlayer(Player[] p,String userName) throws IOException
  {String target=userName+"Player.txt";
   Scanner filescan=new Scanner(new File(target));

     int number=0;
    while(filescan.hasNext())
    {filescan.nextLine();
     number++;
    }
    String[] playerName=new String[number];
    int[] playerValue=new int[number];
    filescan.close();
    filescan=new Scanner(new File(target));
     for(int i=0;i<number;i++)
    {playerName[i]=filescan.next();
     playerValue[i]=Integer.parseInt(filescan.next());
     p[i]=new Player(playerName[i],playerValue[i]);
    }
     filescan.close();
     return p;
    
  }
   
  // a method to get how many players are in the record file
  public int getPlayerNumber(String userName)  throws IOException
  {String target=userName+"Player.txt"; 
   Scanner filescan=new Scanner(new File(target));// (I/0)
   int number=0;
    while(filescan.hasNext())
    {filescan.nextLine();
     number++;
    }
    filescan.close();
    return number;
  }
  
  // a method to get player's name from record file, with parameter user's name
  public String[] getPlayerName(String userName) throws IOException
  {
    String target=userName+"Player.txt";
   Scanner filescan=new Scanner(new File(target));//(I/O)

     int number=0;
    while(filescan.hasNext())
    {filescan.nextLine();
     number++;
    }
    String[] playerName=new String[number];
    int[] playerValue=new int[number];
    filescan.close();
    filescan=new Scanner(new File(target));
     for(int i=0;i<number;i++)
    {playerName[i]=filescan.next();
     playerValue[i]=filescan.nextInt();
    }
     filescan.close();
     return playerName;
  }
  
  // a method to get player's ability value with parater user's name
  public int[] getPlayerValue(String userName) throws IOException
  {
    String target=userName+"Player.txt";
   Scanner filescan=new Scanner(new File(target)); // (I/0)
      int number=0;
    while(filescan.hasNext())
    {filescan.nextLine();
     number++;
    }
    String[] playerName=new String[number];
    int[] playerValue=new int[number];
    filescan.close();
    filescan=new Scanner(new File(target));
     for(int i=0;i<number;i++)
    {playerName[i]=filescan.next();
     playerValue[i]=Integer.parseInt(filescan.next());
    }
     filescan.close();
     return playerValue;
  }
  
  // a toString method to print player's info
  public String toString()
  {return "name; "+name+"\tabilityValue: "+abilityValue;
  }
  
  //a method to set 6 players for new user, parameter is user's name
  public void initialPlayer(String userName) throws IOException
  { String target=userName+"Player.txt";
      FileWriter fw =  new FileWriter(target, true); // (I/O)
      PrintWriter pw = new PrintWriter(fw);
      pw.println("Eddie \t51");
      pw.println("Henry \t43");
      pw.println("David \t38");
      pw.println("Sky   \t42");
      pw.println("Steven\t45");
      pw.println("Sam   \t50");
      pw.close();
  }
  
  Random gen = new Random();
  // a method to train player, takes an object of a class and gold as parameters
  public int train(Player playertrain,int gold)
  { if(gold>=3) // if gold >=3, means user has enough money to train player
    {System.out.println("You have "+gold+" golds now, do you want to train your player for 3 golds? (Y or N)");
    Scanner reader = new Scanner(System.in);
    char answer=reader.nextLine().charAt(0);
    while(Character.toUpperCase(answer)!='Y' && Character.toUpperCase(answer)!='N') // for bad input
         {System.out.println("Invalid Input! do you want to train your player for 3 golds? (Y or N)");
           answer=reader.nextLine().charAt(0);
         }
        reader.close();
      if(Character.toUpperCase(answer)=='Y') // if user choose to train
      {gold-=3; // gold get -3
        int improve=gen.nextInt(2); // improve number could be 0 or 1, so there is 50% possibility of getting no improvent even if spend 3 gols
        playertrain.abilityValue+=improve;
        if(improve!=0) // if improve is not 0
        {
        System.out.println("Congratulation! Your player "+playertrain.name+"'s abilityvalue has been improved from "+(playertrain.abilityValue-improve)+" to "+(playertrain.abilityValue));
        }
        else  // if improve is 0
        {
        System.out.println("Sorry, Your player "+playertrain.name+" did't get any improvement, good luck for next time");
       }
  }
      else if(Character.toUpperCase(answer)=='N') // if user choose to not train player
      {
        System.out.println("Why don't train your player!");
      }
  }
  else if(gold<3)  // if user's gold is less than 3, no enough money
  {System.out.println("Sorry, it seems you only have "+gold+" golds, it's not enough.");
  }
      return gold;
  }

}