package models;

import java.util.Comparator;

//Sorting according to Goals Scored by a club
public class SortScoredGoals implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub club01, FootballClub club02) {
        if (club01.getGoalsScored() < club02.getGoalsScored()){
            return 1;
        }else if (club01.getGoalsScored() > club02.getGoalsScored()){
            return -1;
        }else
            return 0;
    }
}
