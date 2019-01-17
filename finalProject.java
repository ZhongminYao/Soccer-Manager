/*  Soccer Manager
finalProject.java
Zhongmin Yao
Alan
zyao02
section 4 */
import java.io.*;
public class finalProject
{
  public static void main(String[] args) throws IOException
  {
    User user=new User();  //create an object of class User
    int numOfLines = user.numberOfLines(); // a method call in class User, to get how many users in the record  //(MYMETH)
    String nameUser = user.askUserName(); //user's name  //(MYMETH)
    //declare some primitive arrays and a variable
    String[] name=new String[1]; //(AR)
    String[] clubName=new String[1]; //(AR)
    int[] gold=new int[1];//(AR)
    int userNameIndex=0;
    // if there is any record of users, I mean the record file is not empty, declare the arrays with proper size
    if(numOfLines!=0)
    { 
      name=new String[numOfLines];  //(AR)
      clubName=new String[numOfLines];  //(AR)
      gold=new int[numOfLines];  //(AR)
      user.readUserRecord(name,clubName,gold);  //read user's record from record file, the arguments are 3 arrays.  //(MYMETH)
      //(SEARCH)
      //(MYMETH)
      userNameIndex=user.searchUserName(name,nameUser); //use search method to find the user name's index in the array, the arguments are user's name, and the array name 
      
      // if return value is -1( means not found)
      if (userNameIndex==-1)
      { 
        //(MYMETH)
        user.noRecord(name,clubName,nameUser,gold);// call noRecrd method is class User, arguments are 3 arrays and user's name
      }
      //(MYMETH)
      else { user.hasRecord(name,clubName,nameUser,gold);} // if record found, call hasRecord method, arguments are 3 arrays and user's name
    }
    
    //if no record in record file
    if(numOfLines==0)
    {
      user.createNew(nameUser);  // same meaning as above  //(MYMETH)
      user.readUserRecord(name,clubName,gold);      //(MYMETH)
      numOfLines = user.numberOfLines();  //(MYMETH)
      name=new String[numOfLines];  //(AR)  
      clubName=new String[numOfLines];  //(AR)
      gold=new int[numOfLines]; //(AR)
    }
      numOfLines = user.numberOfLines(); //(AR)  //(MYMETH)
      name=new String[numOfLines];  //(AR)
      clubName=new String[numOfLines]; //(AR0
      gold=new int[numOfLines]; //(AR)
      user.readUserRecord(name,clubName,gold);  //(MYMETH)
      userNameIndex=user.searchUserName(name,nameUser); //(SEARCH)  //(MYMETH)

    Tutorial tutorial=new Tutorial();  // crate an object for class Tutorial
    tutorial.ask(); // call ask method  //(MYMETH)
    System.out.println("Let's start the game.");

    Game game=new Game();// create an object for class game
    Player player=new Player(); // create an object for class player   
    int amountPlayer=player.getPlayerNumber(nameUser);//get amount of players  //(MYMETH)
    Player[] playerArray=new Player[amountPlayer];// an array of objects of class player  //(AR)
    player.loadPlayer(playerArray,nameUser);// get players info  //(MYMETH)  
    
    String[] playerName=player.getPlayerName(nameUser);// get player name  //(MYMETH)
    int[] playerValue=player.getPlayerValue(nameUser);// get player value  //(MYMETH)
    game.showLayout();//(MYMETH)
    //call action method, arguments are user's name,clubname,gold,the number of players, player's name,value, and 3 arrays, and number of users
    //(MYMETH)
    gold[userNameIndex]=game.action(name[userNameIndex],clubName[userNameIndex],gold[userNameIndex],amountPlayer,playerName,playerValue,playerArray,name,clubName,gold,numOfLines);  
    
    // print user's info to record file
    //(I/O)
    PrintWriter pw = new PrintWriter("userRecord.txt");
      for(int i=0;i<numOfLines;i++)
      {pw.println(name[i]+"\t"+clubName[i]+"\t"+gold[i]);  //(MYMETH)
      }
      pw.close();
    System.out.println("Your record has been saved!");
    System.out.println("See you next time.");
  }
}


      


