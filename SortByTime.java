package Music;

import java.util.*;

// Description: This is a comparator that sort by the time of the Song
// Parameters: Tow Song objects
// Return: a integer that helps to rank the songs in the playlist
public class SortByTime implements Comparator <Song>{
    @Override
    public int compare(Song o1, Song o2) {
        int o1TotalTime = o1.getT().getMinutes()*60+o1.getT().getSeconds();
        int o2TotalTime = o2.getT().getMinutes()*60+o2.getT().getSeconds();
        if(o1TotalTime>o2TotalTime){
            return 1;
        }
        else if(o1TotalTime<o2TotalTime){
            return -1;
        }
        else return 0;
    }
}
