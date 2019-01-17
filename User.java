/*  Soccer Manager
User.java
Zhongmin Yao
Alan
zyao02
section 4 */
import java.io.*;
import java.util.Scanner;
public class User
{  
    //(I/O)
  public int numberOfLines() throws IOException
  {Scanner filescan=new Scanner(new File("userRecord.txt")); // read from the file
    int i=0;
    //use a while loop to get how many lines are in the file( same meaning as how many users)
   while(filescan.hasNext())
   {filescan.nextLine();
     i++;}
   filescan.close();
   return i; // return the number of lines
  }
    // a method to read user's record, the parameters are 3 arrays
  //(I/O)
    public void readUserRecord(String[] name,String[] clubName,int[] gold) throws IOException
    { 
        int i=0;
        Scanner filescan=new Scanner(new File("userRecord.txt")); // read from a file
        //use a while loop to read user's record to the 3 arrays
        while(filescan.hasNext())
        {
          name[i]=filescan.next();
          clubName[i]=filescan.next();
          gold[i]=filescan.nextInt();
          i++;
        }
        filescan.close();
      }
       
      public String askUserName()
       //ask user's name
      {Scanner reader = new Scanner(System.in);
    System.out.println( "Welcome to soccer manager club center!");
    System.out.println( "May I have your name?");
    String nameUser = reader.nextLine();
    reader.close();
    return nameUser; //return user's name
      }
      
      //a search method to search the index of user's name in the array, the parameters are array name, and user's name
      public int searchUserName(String[] array, String target) 
      {for(int i=0;i<array.length;i++)
        {
        if(array[i].equals(target))  // if found, return index
          return i;}
      return -1;  // if not found, return -1
      }
      
      public String searchUserClubName(String[] array, int index)
      {return array[index];}
      public int searchUserGold(int[] array, int index)
      {return array[index];
      }
      
      char answer1;
      boolean gameStart=false;
      
      // noRecord method, parameters are 3 arrays, and user's name
      public void noRecord(String[] arrayUserName,String[] arrayClubName,String nameUser,int[] arrayUserGold) throws IOException
      {
      // while booelean gamStart is not true, do this loop
      do{
      System.out.println("Sorry, "+nameUser+", I can't find your record, are you a noob? (Y or N)");
        Scanner reader = new Scanner(System.in);
         answer1= reader.nextLine().charAt(0);
         while(Character.toUpperCase(answer1)!='Y' && Character.toUpperCase(answer1)!='N') // for bad input
         {System.out.println("Invalid Input!Sorry, "+nameUser+", I can't find your record, are you a noob? (Y or N)");
           answer1=reader.nextLine().charAt(0);
         }
         if (Character.toUpperCase(answer1)=='N')
         {
         System.out.println("Can you enter your name again?");
         nameUser=reader.nextLine();
         int index=searchUserName(arrayUserName,nameUser); // do search method to find if there is user's record  //(MYMETH)
         if (index!=-1) // if there is a  record
         { String clubName=searchUserClubName(arrayClubName,index); // search for user's club name
           System.out.println("Okay, "+nameUser+", I see you have a club named "+clubName);
             System.out.println("Dear "+nameUser+", your record has been loaded, you are able to play.");
              // set gamestart to true 
               gameStart=true; //(BOOL)
                    
         }
         else if(index==-1) // if no record
         {
           noRecord(arrayUserName,arrayClubName,nameUser,arrayUserGold); //call noRecord method again until user choose "I'm a noob"
         }
         }         
        else if(Character.toUpperCase(answer1)=='Y') // if user choose "I'm a noob"
        {
          gameStart=true; // set gameStart to ture // (BOOL)
          createNew(nameUser);// call creatNew for user
        }
      reader.close();
      }while(gameStart=false); // do while loop  //(BOOL)
      }

// parameters are 3 arrays and user's name
public void hasRecord(String[] arrayUserName,String[] arrayClubName,String nameUser,int[] arrayUserGold)
{
  //(SEARCH)
  int index=searchUserName(arrayUserName,nameUser); // use search method to find index  //(MYMETH)
  String clubName=searchUserClubName(arrayClubName,index);  //(MYMETH)
  System.out.println("Okay, "+nameUser+", I see you have a club named "+clubName);
  System.out.println("Dear "+nameUser+", your record has been loaded, you can play now.");
                          
   }

// a method to create for new user, parameter is user's name
public void createNew(String userName) throws IOException
{
  System.out.println("How do you want to name your club?");
  Scanner reader = new Scanner(System.in);
  String clubName=reader.nextLine();
  reader.close();
  int gold=0;
  //print user's info to record file
  FileWriter fw =  new FileWriter("userRecord.txt", true);  //(I/0)
  PrintWriter  pw = new PrintWriter(fw);
  pw.println(userName+"\t"+clubName+"\t"+gold);
  pw.close();
  Player player=new Player();
  player.initialPlayer(userName); // call initialPlayer for user

}

}
