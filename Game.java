/*  Soccer Manager
Game.java
Zhongmin Yao
Alan
zyao02
section 4 */
import java.util.Scanner;
import java.util.Random;
import java.io.*; 

public class Game
{
  // layout of the game
  public void showLayout()
  {
    System.out.println("1. View my personal information");
    System.out.println("2. Training my soccer player");
    System.out.println("3. Draw a superstar");
    System.out.println("4. Play league match");
    System.out.println("5. Save progress");    
  }
  //parameters are user's name,clubname,gold,the number of players, player's name,value, and 3 arrays, and number of users
  public int action(String userName,String clubName,int gold,int amountPlayer,String[] name,int[] value,Player[] p,String[] uName,String[]cName, int[] golds,int amountOfUser) throws IOException
  {
    System.out.println("Choose the number to go into that page.");
    Scanner reader = new Scanner(System.in);
    int answer = reader.nextInt();
     while(answer!=1 && answer!=2 && answer!=3 && answer!=4 && answer!=5) // for bad input
         {System.out.println("Invalid Input!Please choose a number from 1 to 5.");
           answer=reader.nextInt();
         }
          reader.close();
    if (answer==1)
    {
      userInfo(userName,clubName,gold,amountPlayer); // call userinfo method  //(MYMETH)
      showLayout();  //(MYMETH)
      action(userName,clubName,gold,amountPlayer,name,value,p,uName,cName,golds,amountOfUser); //go back to this method   //(MYMETH)
    }
    else if(answer==2)
    {
      int index=1;
      // print players info
      for(Player in:p)
      {System.out.println("Number: "+ index+"\t"+in);
        index++;
      }
      System.out.println("Choose the one you want to train");
      reader = new Scanner(System.in);
      int input=reader.nextInt(); 
      while(input!=1 && input!=2 && input!=3 && input!=4 && input!=5 && input!=6) // for bad input
         {System.out.println("Invalid Input!Please choose a number from 1 to 6.");
           input=reader.nextInt();
         }
       reader.close();
      int number=input-1;
      Player player=new Player();
      gold=player.train(p[number],gold); // call train method  //(MYMETH)  //(MYMETH(O))
      System.out.println("(Enter anything and press Enter to go back to main menu)");
      reader = new Scanner(System.in);
      reader.nextLine();
      reader.close();
      showLayout();  //(MYMETH)
      gold = action(userName,clubName,gold,amountPlayer,name,value,p,uName,cName,golds,amountOfUser);  //(MYMETH)
    }
    else if(answer==3)
    {
     System.out.println("To be explored..."); 
      System.out.println("(Enter anything and press Enter to go back to main menu)");
      reader = new Scanner(System.in);
      reader.nextLine();
      reader.close();
     showLayout();  //(MYMETH)
     action(userName,clubName,gold,amountPlayer,name,value,p,uName,cName,golds,amountOfUser);  //(MYMETH)    
    }
    else if(answer==4)
    {
      gold=match(name,value,gold);// call mathch method  //(MYMETH)
      showLayout();//(MYMETH)
      action(userName,clubName,gold,amountPlayer,name,value,p,uName,cName,golds,amountOfUser);    //(MYMETH)      
    }
    else if(answer==5)
      // record info to file
      //(I/O)
    {String target=userName+"Player.txt";
      PrintWriter pw = new PrintWriter(target);
      for(int i=0;i<amountPlayer;i++)
      {pw.println(p[i].name+"\t"+p[i].abilityValue);  
      }
      pw.close();
    } 
      return gold;
  }
  
  // a method to show user's info, parameters are user's name,club's name, gold, and number of players
  public void userInfo(String userName,String clubName,int gold, int amountPlayer)
  {
    System.out.println("Your name: "+ userName);
    System.out.println("Your club's name: "+ clubName);
    System.out.println("Your gold: "+gold+" golds");
    System.out.println("Player: "+amountPlayer+", for more details, go to see "+userName+"Player.txt file");
    System.out.println("(Enter anything and press Enter to go back to main menu)");
    Scanner reader = new Scanner(System.in);
    reader.nextLine();
    reader.close();
  }
  
  // a method with parameters name array, value array , and gold
  public int match(String[] name,int[] value,int gold)
  {
    System.out.println("There are 3 clubs for you to against with, apparently they are easy, normal or difficult");
    System.out.println("Choose the one you want to against with (1=easy,2=normal,3=difficult)");
    Scanner reader = new Scanner(System.in);
    int level=reader.nextInt();
    while(level!=1 && level!=2 && level!=3) //for bad input
         {System.out.println("Invalid Input!Please choose a number from 1 to 3.");
           level=reader.nextInt();
         }
    reader.close();
    
    if (level==1) // if user choose easy
    {
     int difficulty=40; // set difficulty to 40
     gold= against(difficulty,name,value,gold); // call against method   //(MYMETH)
     return gold;
    }
    if (level==2) // if user choose normal
    {
      int difficulty=60; // set difficulty to 60
      gold=against(difficulty,name,value,gold);       //(MYMETH)
      return gold;
    }
    if (level==3) // if user choose hard
    {
      int difficulty=80;
      gold=against(difficulty,name,value,gold);   //(MYMETH)
      return gold;
    }
    return gold;
 }
      
  // declare some variables
      int scoreMe=0;
      int scoreOpponent=0;
      int turnRemaining=50;
      int distance=100;
      int numberBallHolder=1;
      Random gen = new Random();// create a random generator for later use

      // a method with parameter of difficulty level, array name, array value and gold
  public int against(int difficulty,String[] name,int[] value, int gold)
  {
    parallelSort(value,name); // use parallel sort to sort players  // (SORT)  //(MYMETH)

    System.out.println("We have automatically chosen the 5 higher ability value players for you. ");
    System.out.println("PlayerNumber\tPlayerName\t\tAbilityValue ");
    for (int i=0;i<=4;i++)
    {System.out.println(i+"\t\t"+name[i]+"\t\t"+value[i]);  }
    System.out.println("The match starts!It's your turn to kick off.");
    distance=distance/2; // since the opening is from the center of the pitch, so distance is half of the whole size of the pitch
    System.out.println(name[0] +" kick off the ball to "+name[1]+". What do you expect "+name[1]+" to do?");
    while(turnRemaining>0) // do the while loop when timer is larger than 1
    { System.out.println("(Enter P to pass the ball; Enter B to break through; Enter S to shoot)(The ball is "+distance+"m far from the goal)(turnremaining is "+turnRemaining+")");
    Scanner reader = new Scanner(System.in);
    char answer=reader.nextLine().charAt(0);
     while(Character.toUpperCase(answer)!='P' && Character.toUpperCase(answer)!='B'  && Character.toUpperCase(answer)!='S') // for bad input
         {System.out.println("Invalid Input!(Enter P to pass the ball; Enter B to break through; Enter S to shoot)");
           answer=reader.nextLine().charAt(0);
         }
    //reader.close();
    if (Character.toUpperCase(answer)=='P')
    {System.out.println("Enter the player's number you want to pass the ball to");
      int answerNumber=reader.nextInt();
      while(answerNumber!=1 && answerNumber!=2 && answerNumber!=3 && answerNumber!=4 && answerNumber!=5 ) // for bad input
         {System.out.println("Invalid Input!Please choose a number from 1 to 5.");
           answerNumber=reader.nextInt();
         }
      int pb=gen.nextInt(100); // create a random number from 0-99  //(RANDOM)
      if (pb<=(value[answerNumber]+value[numberBallHolder])/2)  // if average of the two players(player who pass the to, and get the ball) 's value is less than the random number  
      {System.out.println(name[numberBallHolder]+" pass the ball to "+name[answerNumber]);   // pass successively
        numberBallHolder=answerNumber;
        ////(RANDOM)
        distance=distance-(gen.nextInt(20)-4);  // distance get shorter with a random number, which could be negative or postive, but mostly it's positve
        turnRemaining--; // turn remaing is getting less
        System.out.println("(The ball is "+distance+"m far from the goal)(turnremaining is "+turnRemaining+")");
      }
      else if(pb>(value[answerNumber]+value[numberBallHolder])/2) // if larger than the random number
      {int pb2=gen.nextInt(100); // create a new random number  //(RANDOM)
        if (pb2<=difficulty) // if the random number is not larger than the random number, get intercepted by the opponent. since in the hard mode, difficulty level is 80
          // so the random number from 0-99 will mostly be intercepted by opponent  
        {turnRemaining--;
          System.out.println("Ball is intercepted by the opponent.Opponent take control of the ball now)(turnremaining is "+turnRemaining+")"); 
          opponentAction(difficulty,name); // opponent do actions with parater difficulty level ans name.  //(MYMETH)
          }
        
        if(pb2>difficulty) // if the random is larger, ball will be out of bound 
        {turnRemaining--;
          System.out.println(name[numberBallHolder]+" pass the ball out of bound. Opponent take control of the ball now)(turnremaining is "+turnRemaining+")");
        opponentAction(difficulty,name);} //opponent do actions with parater difficulty level ans name.  //(MYMETH)
       }
    }
        else if(Character.toUpperCase(answer)=='B') // if user choose to break through
        { int pb=gen.nextInt(100); // create a random number from 0-99  //(RANDOM)
          if(pb<=value[numberBallHolder]) // if the number is not larger than the ability value of the player, so high value player have larger opportunity to break through successively
          {turnRemaining--;
            distance=distance-(gen.nextInt(15)); //distace number will be smaller  //(RANDOM)
            System.out.println(name[numberBallHolder]+" break throught one defender successfully.");
           System.out.println("(The ball is "+distance+"m far from the goal)(turnremaining is "+turnRemaining+")");
          }
          else if(pb>value[numberBallHolder]) // if the number is larger than value of the player
          {turnRemaining--;
          System.out.println("Ball is intercepted by the opponent.Opponent take control of the ball now)(turnremaining is "+turnRemaining+")");
          opponentAction(difficulty,name);} // turn for opponent to do actions  //(MYMETH)
        }
        else if(Character.toUpperCase(answer)=='S') // if the user choose to shoot
        { int pb=gen.nextInt(100);  //(RANDOM)
          if(pb>((100-distance)*(value[numberBallHolder]/100))) // if the random number is larger than the distance times value of player/100, no goals
            // so, if the player has high ability value or distance to the goal is short, the possibility of shooting a goal will be larger.
          {turnRemaining--;
            System.out.println(name[numberBallHolder]+" shoot the ball! He miss it! Opponent take control of the ball");
            distance=100/2;
            opponentAction(difficulty,name);// opponent's turn  //(MYMETH)
          }
          else if(pb<=((100-distance)*(value[numberBallHolder]/100))) // if the random number is less or equal to, goal!
          {turnRemaining--;
            System.out.println(name[numberBallHolder]+" shoot the ball! Goal! Goal! Goal! A brilliant goaal!");
            distance=100/2;
            scoreMe++; // user's score+1;
            System.out.println("The score has changed to "+ scoreMe+" : "+scoreOpponent);
            opponentAction(difficulty,name); //opponent's actions          //(MYMETH)                
          }
        }
    }
    gold=endGame(gold);// game ends, call endGame method
    System.out.println(gold);//
    return gold;
    }
  
  // a method with parameter gold
  public int endGame(int gold) 
  {
     Scanner reader = new Scanner(System.in);
    if(scoreMe>scoreOpponent) // if user's score is larger than opponent's score, win!
    { System.out.println("Finally, the referee blow the final whistle! You win this match with score " +scoreMe+" - "+scoreOpponent);
       System.out.println("Congratulations! You get 3 golds from sponsor. (Enter anything and press Enter to go back to main menu)");
       gold+=3; // earn 3 golds
       reader.nextLine();
       reader.close();
       
    }
    else if(scoreMe<scoreOpponent) // if user's score is less than opponent's scrore, lose! no gold earned if lose
    {System.out.println("Finally, the referee blow the final whistle! You lose this match with score " +scoreMe+" - "+scoreOpponent);
      System.out.println("Sorry! You didn't get golds from sponsor. (Enter anything and press Enter to go back to main menu)");
       reader.nextLine();
       reader.close();      
    }
    
    else if(scoreMe==scoreOpponent) // if user's score is equals to opponent's score, draw!
    {System.out.println("Finally, the referee blow the final whistle! It's a draw with score " +scoreMe+" - "+scoreOpponent);
      System.out.println("Congratulations! You gets 1 gold from sponsor. (Enter anything and press Enter to go back to main menu)");
      gold+=1; //earn 1 gold if draw
       reader.nextLine();
       reader.close();      
    }
    return gold;
            
    }

    // a method for opponent to do actions, with parameters difficulty level, and array name
  public void opponentAction(int difficulty,String[] name)
  {
    //(RANDOM)
    if(gen.nextInt(100)<=(difficulty/10)) // if random number of 0-99 is less than opponent's difficulty level/10, opponent shoot a goal! 
      // so if the difficulty level is larger, they get a higher possibility to shoot a goal. 
          {scoreOpponent++; // score of opponent +1
            System.out.println("Opponent shoot a brilliant goal! The score has changed to "+ scoreMe+" : "+scoreOpponent);
            distance=100/2;
            turnRemaining-=gen.nextInt(6); // turn for opponent do actions is a random number 0 to 5  //(RANDOM)
            System.out.println(name[0] +" kick off the ball to "+name[1]+". What do you expect "+name[1]+" to do?");
          }
          else if(gen.nextInt(100)>(difficulty/10)) // if larger, no goals!   //(RANDOM)
          {numberBallHolder=gen.nextInt(5); // which means opponent get intercepeted by a random player from user's team   //(RANDOM)
            System.out.println("Opponent miss the goal!"+name[numberBallHolder]+" take control of the ball now!");  
            distance+=(gen.nextInt(20)-5); // distace to opponent gaol get larger for a random number, which could be postive or negative   //(RANDOM)
            turnRemaining-=gen.nextInt(6); // turn for opponent do actions is a random number 0 to 5  //(RANDOM)
          }
  }
  
  // a method to do parallel sort for players, with parameter of array value and array name
  public void parallelSort(int[] value,String[] name) //list to be parallel sorted by select sort 
  {
   int max;//index of smallest element of sublist
   int tempI;//for swaping
   String tempS;
   for(int startIndex=0;startIndex<value.length;startIndex++)
   {
     max=startIndex;
     for(int i=startIndex+1;i<value.length;i++)
       if(value[i]>value[max])
       max=i;
     
     
     tempI=value[max];
     value[max]=value[startIndex];
     value[startIndex]=tempI;
     tempS=name[max];
     name[max]=name[startIndex];
     name[startIndex]=tempS;
   }
  }
}