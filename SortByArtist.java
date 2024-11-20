package Music;
import java.util.*;
public class SortByArtist implements Comparator<Song>{

    // Description: This is a comparator that sort by the alphabetical order of the artist's name
    // Parameters: Tow Song objects
    // Return: a integer that helps to rank the songs in the playlist
    @Override
    public int compare(Song o1, Song o2) {
        String artist1 = o1.getArtist();
        String artist2 = o2.getArtist();
        return artist1.compareToIgnoreCase(artist2);
    }
}
