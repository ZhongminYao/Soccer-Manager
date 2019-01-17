/*  Soccer Manager
Tutorial.java
Zhongmin Yao
Alan
zyao02
section 4 */
import java.util.Scanner;
public class Tutorial
{
  // ask user if you want to have a turorial
  public void ask()
  {
    System.out.println("Do you want to hava a tutotial?(Y or N)");
    Scanner reader = new Scanner(System.in);
    char answer=reader.nextLine().charAt(0);
    while(Character.toUpperCase(answer)!='Y' && Character.toUpperCase(answer)!='N') // for bad input
         {System.out.println("Invalid Input!Do you want to hava a tutotial?(Y or N)");
           answer=reader.nextLine().charAt(0);
         }
        reader.close();
         if (Character.toUpperCase(answer)=='Y')
         {  tutorial1(); //(MYMETH)
           tutorial2(); //(MYMETH)
           tutorial3(); //(MYMETH)
           tutorial4(); //(MYMETH)
           tutorial5(); //(MYMETH)
         } 
  } 
  // print tutorial info
  public void tutorial1()
  {
    System.out.println("In the main layout, you can see several options.");
    System.out.println("1.View my personal information");
    System.out.println("2. Training my soccer player");
    System.out.println("3. Draw a superstar");
    System.out.println("4. Play league match");
    System.out.println("5. Save progress");
    System.out.print("In option 1, you will see your personal information, ");
    System.out.print("like your name, your club's name, and soccer players you have in your club, your golds and so on. ");
    System.out.println("(Enter anything and press Enter when you have read through)");
    Scanner reader = new Scanner(System.in);
    reader.nextLine();
    reader.close();
  }
  public void tutorial2()
  {
     System.out.print("In option 2, you will be able to train your players by spending golds, ");
     System.out.print("this will make your player and your team stronger without purchasing some superstars. ");
     System.out.print("There are two options, 10 golds or 100 gols. ");
     System.out.print("Spending 3 golds will get an improvement on ability value by 0 or 1(depends on your luck!). ");
     System.out.print("Spending 10 golds will get an improvement on ability value by 1 or 6(depends on your luck!).");
      System.out.println("(Enter anything and press Enter when you have read through)");
    Scanner reader = new Scanner(System.in);
    reader.nextLine();
    reader.close();
  }
  public void tutorial3()
  {
    System.out.print("In option 3, you can draw a player by spending golds, however, the player you get could be a 3 stars player, ");
    System.out.println(" or a 7 stars player, so it all depends on your luck! (Enter anything and press Enter when you have read through)");
    Scanner reader = new Scanner(System.in);
    reader.nextLine();
    reader.close();
  }
   public void tutorial4()
  {
    System.out.print("In option 4, you are going to play a match in league, in which you can manage and decided you player��s action, ");
    System.out.print("for example, you can choose a player to pass the ball, but there will be a possibility that your ball may out of bounds or intercepted by opponent;");
    System.out.print(" you can also choose to break through, or shooting as well. The possibility of kick a good pass  ");
    System.out.println("or shooting will all depend on the ability value of your player. (Enter anything and press Enter when you have read through)");
    Scanner reader = new Scanner(System.in);
    reader.nextLine();
    reader.close();
  }
   public void tutorial5()
   {
     System.out.print("Oh, I forgot to tell you that you are allowed to save the progress when you want to pause." );
    System.out.print("Okay, my tutorial is going to end here, now it��s your turn to build your ultimate team. Good Luck!");
     System.out.println("(Enter anything and press Enter when you are ready to start the game.)");
    Scanner reader = new Scanner(System.in);
    reader.nextLine();
    reader.close();
   }
}