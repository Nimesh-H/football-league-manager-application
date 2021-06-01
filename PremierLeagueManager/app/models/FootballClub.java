package models;

import java.io.Serializable;

public class FootballClub extends SportsClub implements Comparable<FootballClub>, Serializable {
    private int points;                 //points of the club
    private int numberOfMatchesPlayed;  //number of matches played by the club
    private int goalsScored;            //Goals scored by the club
    private int goalsReceived;          //Goals received by the club
    private int wins;                   //wins by the club
    private int defeats;                //Defeats by the club
    private int draws;                  //Draws by the club

    public FootballClub(String clubCode, String clubName, String location, int points, int numberOfMatchesPlayed, int goalsScored, int goalsReceived, int wins, int defeats, int draws) {
        super(clubCode, clubName, location);
        this.points = points;
        this.numberOfMatchesPlayed = numberOfMatchesPlayed;
        this.goalsScored = goalsScored;
        this.goalsReceived = goalsReceived;
        this.wins = wins;
        this.defeats = defeats;
        this.draws = draws;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getNumberOfMatchesPlayed() {
        return numberOfMatchesPlayed;
    }

    public void setNumberOfMatchesPlayed(int numberOfMatchesPlayed) {
        this.numberOfMatchesPlayed = numberOfMatchesPlayed;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsReceived() {
        return goalsReceived;
    }

    public void setGoalsReceived(int goalsReceived) {
        this.goalsReceived = goalsReceived;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }




    @Override
    public int compareTo(FootballClub o) {

       if (this.points == o.points){
           if (Math.abs(this.goalsScored - this.goalsReceived) < Math.abs(o.goalsScored - o.goalsReceived))
               return  1;
           else if (Math.abs(this.goalsScored - this.goalsReceived)>Math.abs(o.goalsScored - o.goalsReceived))
               return -1;
           else
               return 0;
       }
       else if (this.points < o.points)
           return 1;
       else
           return -1;
    }
}
