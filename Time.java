package Music;

public class Time {
    //Variables
    private int minutes;
    private int seconds;

    // Description: This is the constructor of Time class
    // Parameters: minutes of the song, seconds of the song
    // Return: No return type
    public Time(int minutes, int seconds){
        this.minutes =minutes;
        this.seconds=seconds;
    }
    // Description: This is a toString method that help to print Time  object out
    // Parameters: NO
    // Return: a String that you will print out
    public String toString(){
        if(seconds>=10){
            return minutes+":"+seconds;
        }
        else {
            return minutes+":"+0+seconds;
        }
    }

    //Getters & setters
    public int getMinutes(){
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
    public void setMinutes(int minutes){
        this.minutes=minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
