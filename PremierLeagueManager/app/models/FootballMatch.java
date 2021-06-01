package models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class FootballMatch implements Comparable<FootballMatch>,Serializable {

    private String playingDate;     //Playing Date Of The Match
    private String playingClub01;   //Playing Team 01
    private String playingClub02;   //Playing Team 02
    private int score01;            //Team 01 Score
    private int score02;            //Team 02 Score
    private int receivedPoints01;   //Team 01 Points
    private int receivedPoints02;   //Team 02 Points


    public FootballMatch(String playingDate,String playingClub01,String playingClub02, int score01, int score02, int receivedPoints01, int receivedPoints02) {
        this.playingDate = playingDate;
        this.playingClub01 = playingClub01;
        this.playingClub02 = playingClub02;
        this.score01 = score01;
        this.score02 = score02;
        this.receivedPoints01 = receivedPoints01;
        this.receivedPoints02 = receivedPoints02;
    }

    public String getPlayingDate() {
        return playingDate;
    }

    public void setPlayingDate(String playingDate) {
        this.playingDate = playingDate;
    }

    public String getPlayingClub01() {
        return playingClub01;
    }

    public void setPlayingClub01(String playingClub01) {
        this.playingClub01 = playingClub01;
    }

    public String getPlayingClub02() {
        return playingClub02;
    }

    public void setPlayingClub02(String playingClub02) {
        this.playingClub02 = playingClub02;
    }

    public int getScore01() {
        return score01;
    }

    public void setScore01(int score01) {
        this.score01 = score01;
    }

    public int getScore02() {
        return score02;
    }

    public void setScore02(int score02) {
        this.score02 = score02;
    }

    public int getReceivedPoints01() {
        return receivedPoints01;
    }

    public void setReceivedPoints01(int receivedPoints01) {
        this.receivedPoints01 = receivedPoints01;
    }

    public int getReceivedPoints02() {
        return receivedPoints02;
    }

    public void setReceivedPoints02(int receivedPoints02) {
        this.receivedPoints02 = receivedPoints02;
    }


    @Override
    public int compareTo(FootballMatch o) {
        if (this.getPlayingDate().compareTo(o.getPlayingDate()) > 0){
            return 1;
        }else if (this.getPlayingDate().compareTo(o.getPlayingDate()) < 0){
            return -1;
        }else
            return 0;
    }
}
