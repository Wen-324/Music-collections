package Music;

public class Song implements Comparable<Song>{

    //variables
    private int songNumber;
    private String songTitle;
    private String genre;
    private int rating;
    private String artist;
    private Time t;


    // Description: This is the constructor of Song class
    // Parameters: title of the song, artist of the song , genre of the song, rating of the song, a Time object
    // Return: No return type
    public Song(String songTitle , String artist,String genre, int rating, Time o){
        this.songTitle=songTitle;
        this.rating=rating;
        this.genre=genre;
        this.artist=artist;
        t=o;
    }

    // Description: This is another constructor of Song class that are specialized for copying(To actually store the information in the copy)
    // Parameters: the Song to be copied
    // Return: No return type
    public Song(Song other) {
        this.songTitle = other.songTitle;
        this.rating = other.rating;
        this.genre = other.genre;
        this.artist = other.artist;
        this.t = new Time(other.t.getMinutes(), other.t.getSeconds());
    }
    // Description: This is a toString method that help to print Song object out
    // Parameters: NO
    // Return: a String
    public String toString(){
        return String.format("%s%n%s%n%s%n%d%n",songTitle,artist,genre,rating)+t;
    }
    @Override

    // Description: This is a compareTo method that help sorting by ranking the Songs by the alphabetical order of their song titles
    // Parameters: A Song object
    // Return: a integer that decides the rank
    public int compareTo(Song o) {
        return this.songTitle.compareToIgnoreCase(o.songTitle);
    }
    //Getters & setters
    public int getRating() {
        return rating;
    }

    public String getArtist() {
        return artist;
    }

    public Time getT() {
        return t;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setT(Time t) {
        this.t = t;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
