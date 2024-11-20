package Music;

import java.util.ArrayList;

public class Playlist implements Comparable<Playlist> {

    //variables
    private String listTitle;
    private int numOfSongs;
    private ArrayList<Song> list2 ;


    // Description: This is the constructor of playlist class
    // Parameters: title of the playlist, # of songs in the playlist, an arraylist of songs
    // Return: No return type
    Playlist(String listTitle, int numOfSongs, ArrayList<Song> inputList){
        this.listTitle=listTitle;
        this.numOfSongs=numOfSongs;
        list2=inputList;
    }

    // Description: This is another constructor of Playlist class that are specialized for copying(To actually store the information in the copy)
    // Parameters: the playlist to be copied
    // Return: No return type
    public Playlist(Playlist other) {
        this.listTitle = other.listTitle;
        this.numOfSongs = other.numOfSongs;
        this.list2 = new ArrayList<Song>();
        for (Song song : other.list2) {
            this.list2.add(new Song(song));
        }
    }

    // Description: This is a toString method that help to print Playlist object out
    // Parameters: NO
    // Return: a String that you will print out
    public String toString(){
        int totalNumOfMinutes=0;
        int totalNumOfSeconds=0;
        int aveNumOfMinutes=0;
        int aveNumOfSeconds=0;
        for(int i=0;i<list2.size();i++){
            totalNumOfMinutes+=list2.get(i).getT().getMinutes();
            totalNumOfSeconds+=list2.get(i).getT().getSeconds();
        }
        while(totalNumOfSeconds>=60){
            totalNumOfSeconds-=60;
            totalNumOfMinutes++;
        }
        int totalTimeInSeconds;
        long aveInS;
        totalTimeInSeconds=totalNumOfSeconds+totalNumOfMinutes*60;
        aveInS=Math.round(totalTimeInSeconds/(double)numOfSongs);
        while(aveInS>=60){
            aveNumOfMinutes++;
            aveInS-=60;
        }
        aveNumOfSeconds=(int)aveInS;
        // Cases for conversion between seconds and minutes
        if(totalNumOfSeconds<10&&aveNumOfSeconds<10) return String.format("Title : %s%nNumber of songs : %d%nTotal time of all songs : %d:0%d%nAve. time of all songs : %d:0%d",listTitle,numOfSongs,totalNumOfMinutes,totalNumOfSeconds,aveNumOfMinutes,aveNumOfSeconds);
        else if(aveNumOfSeconds<10) return String.format("Title : %s%nNumber of songs : %d%nTotal time of all songs : %d:%d%nAve. time of all songs : %d:0%d",listTitle,numOfSongs,totalNumOfMinutes,totalNumOfSeconds,aveNumOfMinutes,aveNumOfSeconds);
        else if(totalNumOfSeconds<10) return String.format("Title : %s%nNumber of songs : %d%nTotal time of all songs : %d:0%d%nAve. time of all songs : %d:%d",listTitle,numOfSongs,totalNumOfMinutes,totalNumOfSeconds,aveNumOfMinutes,aveNumOfSeconds);
        else return String.format("Title : %s%nNumber of songs : %d%nTotal time of all songs : %d:%d%nAve. time of all songs : %d:%d",listTitle,numOfSongs,totalNumOfMinutes,totalNumOfSeconds,aveNumOfMinutes,aveNumOfSeconds);

    }
    // Description: This is a comparator that display all the title of songs in the playlist
    // Parameters: NO
    // Return: Void
    public void displayAllSongs(){
        for(int i=0;i<list2.size();i++){
            System.out.println("#"+(++i));
            i--;
            System.out.println(list2.get(i).getSongTitle());
        }

    }

    // Description: This is a method that add a song to the playlist
    // Parameters: A song object
    // Return: Void
    public void addASong(Song song ){
        list2.add(song);
    }

    //Getters & setters
    public ArrayList<Song> getList2() {
        return list2;
    }

    public int getNumOfSongs() {
        return numOfSongs;
    }

    public String getListTitle() {
        return listTitle;
    }

    public void setList2(ArrayList<Song> list2) {
        this.list2 = list2;
    }
    public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
    }


    // Description: This is a compareTo method that help sorting by ranking the Playlists by the alphabetical order of their song titles
    // Parameters: A Playlist object
    // Return: a integer that decides the rank
    @Override
    public int compareTo(Playlist o) {
        return this.listTitle.compareTo(o.listTitle);
    }

}
