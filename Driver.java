package Music;
//        1)This program is done by Wenqing Wang from November 1st to 12th
//        2)It is a program that can read playlists from multiple files and let the users do manipulation based their chioce in
//          the main menu and two submenu.
//        3)It should be able to deal with improper input from the user.

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver
{
    public static int theNumOfSelectedPL = -1;
    // Description: This is a method that shows the user their options and ask them for the next instruction
    // Parameters: the menu number that tells the menu being used, a BufferedReader.
    // Return: The option the user selects.

    public static int displayMenu (int menuNum, BufferedReader stdIn) throws IOException
    {
        //Variables
        Scanner s = new Scanner(System.in);
        int choice =0;
        try{
            if (menuNum == 0)
            {
                //Main menu
                System.out.println ("----------  MAIN MENU  -----------");
                System.out.println ("1) Accessing your playlists");
                System.out.println ("2) Accessing within a particular playlist");
                System.out.println ("3) Exit");
                System.out.println ("----------------------------------");
                System.out.print ("\nPlease enter your choice:  ");

                //Check for main menu
                choice = Integer.parseInt (stdIn.readLine ());
                if(choice != 1&& choice !=2 && choice != 3) throw new NumberFormatException();
            }
            else if (menuNum == 1)
            {
                //Sub menu 1
                System.out.println ("\n---------  SUB-MENU #1  ----------");
                System.out.println ("1) Display all of your playlists");
                System.out.println ("2) Display info on a particular playlist");
                System.out.println ("3) Add a playlist");
                System.out.println ("4) Remove a playlist");
                System.out.println ("5) Copy a playlist");
                System.out.println ("6) Create a sub-playlist");
                System.out.println ("7) List songs in common between two playlists");
                System.out.println ("8) Return back to main menu.");
                System.out.println ("----------------------------------");
                System.out.print ("\nPlease enter your choice:  ");

                //Check for sub-menu 1
                choice = Integer.parseInt (stdIn.readLine ());
                if (choice != 1&& choice !=2 && choice != 3&& choice != 4&& choice != 5&& choice != 6&& choice != 7&& choice != 8) throw new NumberFormatException();
            }
            else
            {
                // Ask for a playlist from the user
                if(theNumOfSelectedPL==-1){
                    if(list1.size()==0){
                        System.out.println("No playlist exist.");
                        return 6;
                    }
                    else {
                        System.out.println();
                        for(int i=0;i<list1.size();i++) {
                            System.out.println("#"+(++i));
                            i--;
                            System.out.println(list1.get(i).getListTitle());
                        }
                    }
                    System.out.println("Enter the number of a playlist from above : ");
                    try{
                        theNumOfSelectedPL= s.nextInt();
                        if(theNumOfSelectedPL<=0||theNumOfSelectedPL>list1.size()){
                            throw new InputMismatchException();
                        }
                    }

                    //Check for the playlist
                    catch(InputMismatchException e){
                        System.out.println("Input invalid. Please enter a # between 1 and " + list1.size());
                        return 6;
                    }
                }

                //Sub-menu 2
                System.out.println ("\n---------  SUB-MENU #2  ----------");
                System.out.println ("1) Display all songs (in the last sorted order) ");
                System.out.println ("2) Display info on a particular song ");
                System.out.println ("3) Add song");
                System.out.println ("4) Remove Song (4 options)");
                System.out.println ("5) Sort songs (3 options)");
                System.out.println ("6) Return back to main menu");
                System.out.println ("----------------------------------");

                //Check for the option of sub-menu 2
                System.out.print ("\nPlease enter your choice:  ");
                choice = s.nextInt();
                s.nextLine();
                if (choice != 1&& choice !=2 && choice != 3&& choice != 4&& choice != 5&& choice != 6) throw new NumberFormatException();
            }
        }

        //check for exceptions in the menu navigation
       catch(InputMismatchException | NumberFormatException e  ){
            System.out.println("Invalid input. \n");
            return displayMenu(menuNum,stdIn);
       }
        return choice;
    }

    public static ArrayList<Playlist> list1=new ArrayList<Playlist>();
    public static void main (String[] args) throws IOException
    {
        //Variables
        Scanner s =new Scanner(System.in);
        BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in));

        int mainMenuChoice = 0;
        while(true) {
            int  subMenu1Choice = 0, subMenu2Choice = 0; ;

            if(mainMenuChoice==0){
                theNumOfSelectedPL=-1;
                mainMenuChoice = displayMenu(0, stdIn);
            }
            if (mainMenuChoice == 1)
                subMenu1Choice = displayMenu(1, stdIn);
            else if (mainMenuChoice == 2){
                subMenu2Choice = displayMenu(2, stdIn);
            }

            else if (mainMenuChoice == 3)
                System.exit(0);

            //submenu 1: enter the specific method
            if (subMenu1Choice == 1) {displayAllList();}
            if (subMenu1Choice == 2) {displayAList();}
            if (subMenu1Choice == 3) {addAList();}
            if (subMenu1Choice == 4) {removeAList();}
            if (subMenu1Choice == 5) {copyAList();}
            if (subMenu1Choice == 6) {createSubList();}
            if (subMenu1Choice == 7) {listCommonSongs();}
            if (subMenu1Choice == 8) {mainMenuChoice=0;}

            //submenu 2
            if (subMenu2Choice == 1) {
                //this choice display a list of song titles.
                int i=theNumOfSelectedPL-1;
                if(list1.get(i).getList2().size()==0){
                    System.out.println("No song exists.");
                }
                else {
                    list1.get(i).displayAllSongs();
                }
            }
            if (subMenu2Choice == 2) {
                //This choice display information on a particular song
                //(title, genre,artist, rating, and time (mm:ss)).
                boolean inputFormatCorrect = false;
                int i=theNumOfSelectedPL-1;
                int j = -1;
                if(list1.get(i).getList2().size()==0){
                    System.out.println("No song exists");
                }
                else {
                    list1.get(i).displayAllSongs();
                    while (!inputFormatCorrect){
                        try{
                            System.out.println("Please enter the number of the song that you are looking for. ");
                            j = s.nextInt();
                            if(j<=0||j>list1.get(i).getList2().size()){
                                throw new InputMismatchException();
                            }
                            inputFormatCorrect=true;
                        }
                        catch(InputMismatchException e) {
                            System.out.println("Invalid input.");
                            s.nextLine();
                        }
                    }
                    System.out.println(list1.get(i).getList2().get(--j));
                }
            }
            if (subMenu2Choice == 3) {
                //this choice should add a song to the selected playlist
                //(title, genre,artist, rating, and time (mm:ss)).

                int i=theNumOfSelectedPL-1;
                boolean inputFormatCorrect1 = false;
                String ifStart;
                do{
                    System.out.println("Type in 'start' to start filling in the information for the song: ");
                    ifStart = s.nextLine();
                }
                while(!ifStart.equalsIgnoreCase("start"));

                System.out.println("Please enter the title of the song: ");
                String title = s.nextLine();
                System.out.println("Please enter the genre of the song: ");
                String genre = s.nextLine();
                System.out.println("Please enter the artist of the song: ");
                String artist = s.nextLine();
                int rating =-1;
                while(!inputFormatCorrect1){
                    try {
                        System.out.println("Please enter the rating of the song: ");
                        rating = s.nextInt();
                        if(rating<=0||rating>5){
                            throw new InputMismatchException();
                        }
                        inputFormatCorrect1=true;
                    }
                    catch(InputMismatchException e){
                        System.out.println("Invalid input. ");
                        s.nextLine();
                    }
                }
                s.nextLine();
                boolean inputFormatCorrect2 = false;
                String time ="";
                int minute = 0;
                int second =0;
                while(!inputFormatCorrect2){
                    try{
                        System.out.println("Please enter the time of the song in the format of (mm:ss) (for SECONDS: smaller than 60 and 2 digit places , NO LIMIT for minutes, ':' is mandatory ): ");
                        time = s.nextLine();
                        if(!time.contains(":")){
                            throw new  InputMismatchException();
                        }
                        int min = Integer.parseInt(time.substring(0, time.indexOf(":")));
                        int sec = Integer.parseInt(time.substring(time.indexOf(":")+1));
                        if(sec>=60||min<0||sec<0){
                            throw new InputMismatchException();
                        }
                        minute = min;
                        second = sec;
                        inputFormatCorrect2=true;
                    }
                    catch(InputMismatchException | NumberFormatException e){
                        System.out.println("Invalid input.");
                    }
                }

                //Combine the information gained, turn it into a song object.
                Song song = new Song(title, artist, genre, rating, new Time(minute, second));
                list1.get(i).addASong(song);
                int b = list1.get(i).getNumOfSongs() + 1;
                list1.get(i).setNumOfSongs(b);
                System.out.println("You successfully added a song.");

            }
            if (subMenu2Choice == 4) {
                //This choice remove a song from the playlist
                //The user should have 4 ways of removing a song: # of song, song title, first song, a range of songs
                int i=theNumOfSelectedPL-1;
                if(list1.get(i).getList2().size()==0){
                    System.out.println("No songs exist.");
                }
                else {

                    boolean inputFormatCorrect1 =false;
                    int howToRemove =-1;

                    // Ask for how to remove a song by what the user want to do
                    while(!inputFormatCorrect1){
                        try{
                            System.out.println("What way do you want to use to remove songs: ");
                            System.out.println("1) Remove by number of song.");
                            System.out.println("2) Remove by song title.");
                            System.out.println("3) Remove the first song.");
                            System.out.println("4) Remove a range of songs.");
                            howToRemove=s.nextInt();
                            s.nextLine();
                            if(howToRemove<=0||howToRemove>4){
                                throw new InputMismatchException();
                            }
                            inputFormatCorrect1=true;
                        }
                        catch(InputMismatchException e){
                            System.out.println("Invalid input");
                            s.nextLine();
                        }
                    }

                    if (howToRemove == 1) {
                        //Remove by the # of song
                        list1.get(i).displayAllSongs();
                        int theNumToRemove = -1;
                        boolean inputFormatCorrect2 =false;
                        while(!inputFormatCorrect2){
                            try{
                                System.out.println("Please enter the # of the song that you want to remove: ");
                                theNumToRemove=s.nextInt();
                                if(theNumToRemove<=0||theNumToRemove>list1.get(i).getList2().size()){
                                    throw new InputMismatchException();
                                }
                                inputFormatCorrect2=true;
                            }
                            catch(InputMismatchException e){
                                System.out.println("Invalid input");
                                s.nextLine();
                            }
                        }

                        //Remove and change the data related to the removal of this playlist.
                        list1.get(i).getList2().remove(--theNumToRemove);
                        int b = list1.get(i).getNumOfSongs() - 1;
                        list1.get(i).setNumOfSongs(b);
                        System.out.println("You successfully removed the selected song/songs. ");

                    }
                    else if (howToRemove == 2) {

                        //Remove by song title
                        int numOfSongsRemoved = 0;
                        list1.get(i).displayAllSongs();
                        while(true){
                            System.out.println("Please enter the title of the song that you want to remove: ");

                            String theTitleOfSongToRemove = "";
                            theTitleOfSongToRemove=s.nextLine();

                            int size = list1.get(i).getList2().size();
                            for (int n = 0; n < size; n++) {
                                if (list1.get(i).getList2().get(n).getSongTitle().equalsIgnoreCase(theTitleOfSongToRemove)) {
                                    list1.get(i).getList2().remove(n);
                                    n--;
                                    size--;
                                    numOfSongsRemoved++;
                                }
                            }
                            if(numOfSongsRemoved==0){
                                System.out.println("No song with that title exist.");
                            }
                            else {
                                int b = list1.get(i).getNumOfSongs() - numOfSongsRemoved;
                                list1.get(i).setNumOfSongs(b);
                                System.out.println("You successfully removed the selected song/songs. ");
                                break;
                            }
                        }

                    }
                    else if (howToRemove == 3) {
                        //Remove the first song
                        //Ask if they want to remove the song
                        System.out.println("The first song is '" + list1.get(i).getList2().getFirst().getSongTitle() +"' are you sure you want to remove it? (enter 'Yes' to remove)");
                        if(s.nextLine().equalsIgnoreCase("Yes")){
                            list1.get(i).getList2().removeFirst();
                            int b = list1.get(i).getNumOfSongs() - 1;
                            list1.get(i).setNumOfSongs(b);
                            System.out.println("You successfully removed the selected song/songs. ");
                        }
                    }
                    else if (howToRemove == 4) {
                        //Checked for input format
                        list1.get(i).displayAllSongs();
                        while (true) {
                            try {
                                //Remove a group of songs
                                System.out.println("Please enter the # of the first song : ");
                                int start = s.nextInt();
                                if(start<=0||start>list1.get(i).getList2().size()){
                                    throw new InputMismatchException();
                                }

                                System.out.println("Please enter the # of the second song : ");
                                int end = s.nextInt();
                                if(end<start||end>list1.get(i).getList2().size()){
                                    throw new InputMismatchException();
                                }

                                int n0 = start-1;
                                //n2 is the num of songs that are removed
                                int n2 = end - start + 1;
                                for (int n = 0; n < n2; n++) {
                                    list1.get(i).getList2().remove(n0);
                                }
                                int b = list1.get(i).getNumOfSongs() - n2;
                                list1.get(i).setNumOfSongs(b);
                                break;
                            }
                            catch (InputMismatchException e) {
                                System.out.println("Input Invalid.");
                                s.nextLine();
                            }
                        }
                        System.out.println("You successfully removed the selected song/songs. ");

                    }
                }

            }
            if (subMenu2Choice == 5) {
                // Sort the songs in the playlist by three ways:
                // Title, artist , and time
                int i=theNumOfSelectedPL-1;
                boolean inputFormatCorrect = false;
                int theWayToSort = 0;
                if(list1.get(i).getList2().size()==0){
                    System.out.println("No song exists");
                }
                else {

                    // Ask the user about how to sort
                    System.out.println("How do you want to sort the playlist: ");
                    System.out.println("1) Sort by title.");
                    System.out.println("2) Sort by artist.");
                    System.out.println("3) Sort by time.");
                    while (!inputFormatCorrect) {
                        try {
                            theWayToSort = s.nextInt();
                            if (theWayToSort <= 0 || theWayToSort > 3) {
                                throw new InputMismatchException();
                            }
                            inputFormatCorrect = true;
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Invalid input.");
                            s.nextLine();
                        }
                    }


                    if (theWayToSort == 1) {
                        //sort by title
                        Collections.sort(list1.get(i).getList2());
                        list1.get(i).displayAllSongs();
                    }
                    else if (theWayToSort == 2) {
                        //sort by artist
                        Collections.sort(list1.get(i).getList2(), new SortByArtist());
                        list1.get(i).displayAllSongs();
                    }
                    else if (theWayToSort == 3) {
                        //sort by time
                        Collections.sort(list1.get(i).getList2(), new SortByTime());
                        list1.get(i).displayAllSongs();
                    }
                }
            }
            if (subMenu2Choice == 6) {
                //return to the main menu
                mainMenuChoice=0;
            }

        }
    }
    //Submenu 1
    //Option 1
    // Description: This method print out all the playlist.
    // Parameters: NO
    // Return: Void
    public static void displayAllList(){
        if(list1.size()==0){
            System.out.println("No playlist exist.");
        }
        else {
            System.out.println();
            for(int i=0;i<list1.size();i++) {
                System.out.println("#"+(++i));
                i--;
                System.out.println(list1.get(i).getListTitle());
            }
        }
    }
    //Option 2
    // Description: This method ask the user for a playlist that they want to know and print it out.
    // Parameters: NO
    // Return: Void
    public static void displayAList(){
        Scanner s = new Scanner(System.in);
        boolean inputFormatCorrect = false;
        if(list1.size()==0){
            System.out.println("No playlist exist.");
        }
        else {
            while(!inputFormatCorrect){
                try{
                    System.out.println();
                    displayAllList();
                    System.out.println("Please enter the number of the playlist that you want to display: ");
                    int listNumber = s.nextInt();
                    if(listNumber<=0||listNumber>list1.size()){
                        throw new InputMismatchException();
                    }
                    inputFormatCorrect=true;
                    System.out.println(list1.get(--listNumber));
                }
                catch(InputMismatchException e){
                    System.out.println("Invalid input. Please enter a # between 1 and " + list1.size());
                    displayAList();
                    return;
                }

            }

        }
    }
    //Option 3
    // Description: This method ask the user for a name of file. Read the information from it and turn it into a playlist object;
    // Parameters: No
    // Return: Void
    public static void addAList(){
        Scanner s= new Scanner(System.in);
        String fileName;
        ArrayList<Song> arrayListOfSongs=new ArrayList<Song>() ;
        boolean inputFormatCorrect = false;
        while(!inputFormatCorrect){
            try{
                System.out.println("Please enter the name of the file that you want to input from : ");
                fileName=s.nextLine();
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                String listTitle= br.readLine();
                int numberOfSongs = Integer.parseInt(br.readLine().trim());
                String line ;
                while ((line= br.readLine())!=null){
                    String title = line.trim();
                    String artist= br.readLine().trim();
                    String genre= br.readLine().trim();
                    int rating = Integer.parseInt(br.readLine().trim());
                    String length= br.readLine().trim();
                    Time t= new Time(Integer.parseInt(length.substring(0,length.indexOf(":"))),Integer.parseInt(length.substring(length.indexOf(":")+1)));
                    arrayListOfSongs.add(new Song(title,artist,genre,rating,t));
                }
                list1.add(new Playlist(listTitle,numberOfSongs,arrayListOfSongs));
                inputFormatCorrect=true;
                System.out.println("You successfully added a playlist.");

            }
            catch (FileNotFoundException e){
                System.out.println("File does not exist.");
            }
            catch (IOException ignored){}
        }

    }
    // Description: This is a method that ask the user for a # of playlist that they want to remove.
    // Parameters: NO
    // Return: void
    //Option 4
    public static void removeAList(){
        Scanner s=new Scanner(System.in);
        int in;
        if(list1.size()==0){
            System.out.println("No playlist exist. You can not remove any playlist.");
        }
        else {
            boolean inputFormatCorrect =false;
            while(!inputFormatCorrect){
                try{
                    displayAllList();
                    System.out.println("Please enter the # of the playlist that you want to remove: ");
                    in =s.nextInt();
                    if(in<=0||in>list1.size()){
                        throw new InputMismatchException();
                    }
                    list1.remove(--in);
                    System.out.println("You successfully removed a playlist.");
                    inputFormatCorrect=true;
                }
                catch(InputMismatchException e){
                    System.out.println("Invalid input. Please enter a number that is between 1 and "+list1.size());
                    removeAList();
                    return;
                }
            }
        }
    }
    // Description: This is a method that copy a playlist of what the user selected and paste it to the end of the Playlists arraylist
    // Parameters: NO
    // Return: Void
    //Option 5
    public static void copyAList(){
        Scanner s= new Scanner(System.in);


        if(list1.size()==0){
            System.out.println("No playlist exist. You can not copy any playlist.");
        }
        else {

            //Ask user for a song to copy
            displayAllList();
            System.out.println("Enter the # of playlist that you want to copy: ");
            int i=-1;
            boolean inputFormatCorrect =false;
            while(!inputFormatCorrect){
                try{
                    i=s.nextInt();
                    if(i>list1.size()||i<=0){
                        throw new InputMismatchException();
                    }
                    inputFormatCorrect=true;
                }
                catch(InputMismatchException e){
                    System.out.println("Input invalid. Please enter another # of playlist: ");
                    copyAList();
                    return;
                }
            }
            Playlist PL =  list1.get(--i);
            ArrayList<Song> ALS = new ArrayList<Song>(PL.getList2());
            Playlist copiedPlaylist= new Playlist("Copy of "+PL.getListTitle(),PL.getNumOfSongs(),ALS);
            list1.add(copiedPlaylist);
            System.out.println("Playlist successfully copied");
        }


    }
    // Description: This is a method that ask the user for a # of playlist and then ask them for the number of songs to create a sublist.
    // Parameters: NO
    // Return: Void
    //Option 6
    public static void createSubList (){
        Scanner s = new Scanner(System.in);

        if (list1.size() == 0) {
            System.out.println("No playlist exists. You cannot create a sublist.");
        }
        else {
            displayAllList();

            //Ask for the # of playlist
            System.out.println("Enter the # of playlist you want to create a sublist from: ");
            int indexOfTheSelectedPlaylist = -1;
            boolean inputFormatCorrect1 = false;
            while (!inputFormatCorrect1) {
                try {
                    indexOfTheSelectedPlaylist = s.nextInt();
                    if (indexOfTheSelectedPlaylist > list1.size() || indexOfTheSelectedPlaylist <= 0) {
                        throw new InputMismatchException();
                    }
                    int i=indexOfTheSelectedPlaylist-1;
                    if(list1.get(i).getList2().isEmpty()){
                        System.out.println("The playlist you selected does not have a song in it.");
                        return;
                    }
                    inputFormatCorrect1 = true;
                }
                catch (InputMismatchException e) {
                    System.out.println("Input invalid.");
                    createSubList();
                    return;
                }

            }
            Playlist theOriginalPlaylist = list1.get(--indexOfTheSelectedPlaylist);

            theOriginalPlaylist.displayAllSongs();
            int startIndex=-1;
            int endIndex = -1;
            boolean inputFormatCorrect2 =false;
            System.out.println("Enter the start index : ");
            //Ask for the starting index
            while(!inputFormatCorrect2){
                try {
                    startIndex = s.nextInt();
                    if (startIndex < 1 || startIndex > theOriginalPlaylist.getNumOfSongs()) {
                        System.out.println("Input invalid. Please enter a valid number between 1 and "+theOriginalPlaylist.getNumOfSongs());
                        createSubList();
                        return;
                    }
                    inputFormatCorrect2 = true;
                }
                catch (InputMismatchException e) {
                    System.out.println("Input invalid.");
                    createSubList();
                    return;
                }
            }

            boolean inputFormatCorrect3 = false;
            //Ask for the end index
            while(!inputFormatCorrect3){
                try {
                    System.out.println("Enter the end index : ");
                    endIndex = s.nextInt();
                    if (endIndex < 1 || endIndex > theOriginalPlaylist.getNumOfSongs()) {
                        throw new InputMismatchException();
                    }
                    if(endIndex < startIndex ){
                        throw new InputMismatchException();
                    }
                    inputFormatCorrect3 = true;
                }
                catch (InputMismatchException e) {
                    System.out.println("Input invalid. Please enter a valid number between " + startIndex + " and " +theOriginalPlaylist.getNumOfSongs());
                    createSubList();
                    return;
                }
            }

            //Create the sublist
            ArrayList<Song>sublist = new ArrayList<>();
            for (int i = startIndex - 1; i < endIndex; i++) {
                Song song = theOriginalPlaylist.getList2().get(i);
                sublist.add(new Song(song));
            }
            if (sublist.isEmpty()) {
                System.out.println("The sublist is empty.");
                return;
            }
            Playlist sublistPlaylist = new Playlist("Sublist of " + theOriginalPlaylist.getListTitle(),sublist.size(),sublist);
            list1.add(sublistPlaylist);

            System.out.println("Your sublist is successfully created and added to the list!");
        }

    }
    //Option 7
    // Description: This is a method that ask the user for two numbers and find out the songs that are common.
    // Parameters: NO
    // Return: Void
    public static void listCommonSongs(){
        Scanner s=new Scanner(System.in);
        if (list1.size() < 2) {
            System.out.println("There are less than two playlists. You cannot find common songs.");
            return;
        }
        displayAllList();

        //Ask for the first playlist
        System.out.println("Enter the first playlist number: ");
        int playlistIndex1 = -1;
        boolean inputFormatCorrect1 = false;

        while (!inputFormatCorrect1) {
            try {
                playlistIndex1 = s.nextInt();
                if (playlistIndex1 > list1.size() || playlistIndex1 <= 0) {
                    throw new InputMismatchException();
                }
                inputFormatCorrect1 = true;
            } catch (InputMismatchException e) {
                System.out.println("Input invalid. Please enter a valid playlist number: ");
                listCommonSongs();
                return;
            }
        }
        int i=playlistIndex1-1;
        Playlist playlist1 = list1.get(i);

        //Ask for the second playlist
        System.out.println("Enter the second playlist number: ");
        int playlistIndex2 = -1;
        boolean inputFormatCorrect2 = false;
        while (!inputFormatCorrect2) {
            try {
                playlistIndex2 = s.nextInt();
                if (playlistIndex2 > list1.size() || playlistIndex2 <= 0 || playlistIndex2 == playlistIndex1) {
                    throw new InputMismatchException();
                }
                i=playlistIndex2-1;
                inputFormatCorrect2=true;
            }
            catch (InputMismatchException e) {
                System.out.println("Input invalid. Please enter a valid playlist number (it also need to be different from the first one): ");
                listCommonSongs();
                return;
            }
        }
        Playlist playlist2=list1.get(i);;

        //find the songs that are common and store them into a Playlist
        ArrayList<Song> commonSongsList=new ArrayList<>();
        for(Song song1:playlist1.getList2()){
            for(Song song2: playlist2.getList2()){
                if(song1.getSongTitle().equalsIgnoreCase(song2.getSongTitle())){
                    //avoid duplicates
                    boolean isAlreadyInList =false;
                    for(Song commonSong:commonSongsList){
                        if(commonSong.getSongTitle().equalsIgnoreCase(song1.getSongTitle())){
                            isAlreadyInList = true;
                            break;
                        }
                    }
                    if(!isAlreadyInList){
                        commonSongsList.add(song1);
                    }
                    break;
                }
            }
        }

        //Check if there are commons songs
        if(commonSongsList.isEmpty()){
            System.out.println("There are no common songs between the selected two playlists.");
        }
        else {
            // Print out the common songs
            System.out.println("Common songs between the two playlists:");
            for (int t=0;t<commonSongsList.size();t++) {
                int c=t+1;
                System.out.println("#"+c);
                System.out.println(commonSongsList.get(t));
                System.out.println();
            }
            System.out.println("Successfully find the common songs. ");

        }
    }
}