package models;

import org.junit.jupiter.api.Test;

class PremierLeagueManagerTest {

    @Test
    void creatNewFootballClub() {
        PremierLeagueManager manager = PremierLeagueManager.getInstance();
        SportsClub club =  new FootballClub("001","Chelsea","London",0,0,0,0,0,0,0);
        manager.creatNewFootballClub(club);
    }

    @Test
    void deleteFootballClub() {
        PremierLeagueManager manager = PremierLeagueManager.getInstance();
        SportsClub club =  new FootballClub("001","Chelsea","London",0,0,0,0,0,0,0);
        manager.deleteFootballClub(club);
    }

    @Test
    void displayStatistics() {
        PremierLeagueManager manager = PremierLeagueManager.getInstance();
        SportsClub club =  new FootballClub("001","Chelsea","London",0,0,0,0,0,0,0);
        manager.creatNewFootballClub(club);
        manager.displayStatistics(club);
    }

    @Test
    void displayPremierLeagueTable() {
        PremierLeagueManager manager = PremierLeagueManager.getInstance();
        manager.displayPremierLeagueTable();
    }

    @Test
    void addPlayedMatch() {
        PremierLeagueManager manager = PremierLeagueManager.getInstance();
        FootballClub club1 =  new FootballClub("001","Chelsea","London",0,0,0,0,0,0,0);
        FootballClub club2 =  new FootballClub("002","Liverpool","London",0,0,0,0,0,0,0);
        manager.creatNewFootballClub(club1);
        manager.creatNewFootballClub(club2);
        manager.addPlayedMatch(club1,club2);
    }
}