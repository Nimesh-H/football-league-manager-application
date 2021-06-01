package models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface LeagueManager {

    List<FootballClub> getClubList();

    List<FootballMatch> getMatchList();

    void creatNewFootballClub(SportsClub footballClub);

    void deleteFootballClub(SportsClub footballClub);

    void displayStatistics(SportsClub footballClub);

    void displayPremierLeagueTable();

    void addPlayedMatch(FootballClub team01, FootballClub team02);

    void saveData(File footballClubs, File playedMatches) throws IOException;

    void loadData(File footballClubs, File playedMatches) throws IOException, ClassNotFoundException;


}
