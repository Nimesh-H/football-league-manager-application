package models;


import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PremierLeagueManager implements LeagueManager {

    private static PremierLeagueManager premierLeague;

    public static final int MAX_CLUBS = 20;

    private List<FootballClub> clubsList = new ArrayList<>();   //List for Clubs
    private List<FootballMatch> matchList = new ArrayList<>();  //List for Matches

    private PremierLeagueManager(){}

    public static PremierLeagueManager getInstance(){
        if (premierLeague == null){
            synchronized (PremierLeagueManager.class){
                if (premierLeague == null){
                    premierLeague = new PremierLeagueManager();
                }
            }
        }
        return premierLeague;
    }


    @Override
    public List<FootballClub> getClubList() {
        return this.clubsList;
    }

    @Override
    public List<FootballMatch> getMatchList() {
        return this.matchList;
    }

    @Override
    public void creatNewFootballClub(SportsClub footballClub) {

        boolean checkClubPresence = false;

        //Validating the maximum number of clubs in the league
        if (clubsList.size() == MAX_CLUBS){
            System.out.println("Maximum Number Of Clubs Have Been Added");
        }

        //validating if the Club is already in the table
        for (FootballClub club : clubsList){
            if (footballClub.equals(club)){
                System.out.println("Club is Already In The Premier League");
                checkClubPresence = true;
                break;
            }
        }

        if (!checkClubPresence) {
            clubsList.add((FootballClub) footballClub); //Adding the added club to the list
        }

    }

    @Override
    public void deleteFootballClub(SportsClub footballClub) {

        boolean checkClubPresence = false;

        for (FootballClub clubToDelete : clubsList){

            //validating the entered details
            if (clubToDelete.getClubCode().equals(footballClub.getClubCode()) && clubToDelete.getClubName().equals(footballClub.getClubName()) && clubToDelete.getLocation().equals(footballClub.getLocation())){
                clubsList.remove(clubToDelete);

                System.out.println("Club '" + footballClub.getClubName() + "' Is Deleted");
                checkClubPresence = true;
                break;
            }
        }

        if (!checkClubPresence) {
            System.out.println("Please Try Again. Given Details Are Incorrect.");

        }

    }

    @Override
    public void displayStatistics(SportsClub footballClub) {

        boolean checkClubPresence = false;

                for (FootballClub clubStatistics : clubsList){
                    if (clubStatistics.getClubCode().equals(footballClub.getClubCode()) && clubStatistics.getClubName().equals(footballClub.getClubName()) && clubStatistics.getLocation().equals(footballClub.getLocation())){
                        System.out.println();
                        System.out.println("Football Club Name              : "+clubStatistics.getClubName());
                        System.out.println("------------------Club Statistics----------------");
                        System.out.println("Number Of Matches Played        : "+clubStatistics.getNumberOfMatchesPlayed());
                        System.out.println("Total Number of Points          : "+clubStatistics.getPoints());
                        System.out.println("Number Of Goals Scored          : "+clubStatistics.getGoalsScored());
                        System.out.println("Number Of Goals Received        : "+clubStatistics.getGoalsReceived());
                        System.out.println("Number Of Wins By The Club      : "+clubStatistics.getWins());
                        System.out.println("Number Of Defeats By The Club   : "+clubStatistics.getDefeats());
                        System.out.println("Number Of Draws By The Club     : "+clubStatistics.getDraws());
                        System.out.println("-------------------------------------------------");
                        checkClubPresence = true;
                        break;

                    }
                }
        if (!checkClubPresence) {
            System.out.println();
            System.out.println("Please Try Again. Given Details Are Incorrect.");

        }
    }


    @Override
    public void displayPremierLeagueTable() {

        //Checking whether the table is empty
        if (clubsList.isEmpty()){
            System.out.println("No Clubs In The Premier League");
        }else {

            Collections.sort(clubsList); //Sorting according to the Points as in the specification
            System.out.println();
            System.out.printf("| %20s | %15s | %15s | %15s | %15s | %15s |%n", "Club Name    ","Club Code","Scored Goals","Received Goals","Goal Difference","Total Points");
            System.out.println();
            for (FootballClub displayClubs : clubsList){

                System.out.format("| %20s | %15s | %15s | %15s | %15s | %15s |%n",displayClubs.getClubName(),displayClubs.getClubCode(),displayClubs.getGoalsScored(),displayClubs.getGoalsReceived(),(displayClubs.getGoalsScored() - displayClubs.getGoalsReceived()),displayClubs.getPoints());
            }

            System.out.println();

        }


    }

    @Override
    public void addPlayedMatch(FootballClub team01, FootballClub team02) {

        LocalDateTime dateTime = LocalDateTime.now();  //Adding the date for adding matches by automatically getting the entering date

        FootballClub club01 = null;
        FootballClub club02 = null;

        while (true){

            //Updating the Statistics
            if (team01.getGoalsScored() > team02.getGoalsScored()){

                for (FootballClub footballClub : clubsList){

                    if (team01.getClubCode().equals(footballClub.getClubCode()) && team01.getClubName().equals(footballClub.getClubName()) && team01.getLocation().equals(footballClub.getLocation())){
                        club01 = footballClub;
                        footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed()  + 1);
                        footballClub.setGoalsScored(footballClub.getGoalsScored() + team01.getGoalsScored());
                        footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team02.getGoalsScored());
                        footballClub.setWins(footballClub.getWins() +1);
                        footballClub.setPoints(footballClub.getPoints() + team01.getPoints());

                    }

                    if (team02.getClubCode().equals(footballClub.getClubCode()) && team02.getClubName().equals(footballClub.getClubName()) && team02.getLocation().equals(footballClub.getLocation())){
                        club02 = footballClub;
                        footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed()  + 1);
                        footballClub.setGoalsScored(footballClub.getGoalsScored() + team02.getGoalsScored());
                        footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team01.getGoalsScored());
                        footballClub.setDefeats(footballClub.getDefeats() + 1);
                        footballClub.setPoints(footballClub.getPoints() + team02.getPoints());

                    }

                }

            }else if (team01.getGoalsScored() < team02.getGoalsScored()){

                for (FootballClub footballClub : clubsList) {
                    if (team02.getClubCode().equals(footballClub.getClubCode()) && team02.getClubName().equals(footballClub.getClubName()) && team02.getLocation().equals(footballClub.getLocation())){
                        club02 = footballClub;
                        footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() + 1);
                        footballClub.setWins(footballClub.getWins() + 1);
                        footballClub.setGoalsScored(footballClub.getGoalsScored() + team02.getGoalsScored());
                        footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team01.getGoalsScored());
                        footballClub.setPoints(footballClub.getPoints() + team02.getPoints());

                    }

                    if (team01.getClubCode().equals(footballClub.getClubCode()) && team01.getClubName().equals(footballClub.getClubName()) && team01.getLocation().equals(footballClub.getLocation())){

                        club01 = footballClub;
                        footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() + 1);
                        footballClub.setDefeats(footballClub.getDefeats() + 1);
                        footballClub.setGoalsScored(footballClub.getGoalsScored() + team01.getGoalsScored());
                        footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team02.getGoalsScored());
                        footballClub.setPoints(footballClub.getPoints() + team01.getPoints());


                    }
                }
            }else {
                for (FootballClub footballClub : clubsList){

                    if (team01.getClubCode().equals(footballClub.getClubCode()) && team01.getClubName().equals(footballClub.getClubName()) && team01.getLocation().equals(footballClub.getLocation())){
                        club01 = footballClub;
                        footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() + 1);
                        footballClub.setDraws(footballClub.getDraws() + 1);
                        footballClub.setGoalsScored(footballClub.getGoalsScored() + team01.getGoalsScored());
                        footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team02.getGoalsScored());
                        footballClub.setPoints(footballClub.getPoints() + team01.getPoints());

                    }
                    if (team02.getClubCode().equals(footballClub.getClubCode()) && team02.getClubName().equals(footballClub.getClubName()) && team02.getLocation().equals(footballClub.getLocation())){
                        club02 = footballClub;
                        footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() + 1);
                        footballClub.setDraws(footballClub.getDraws() + 1);
                        footballClub.setGoalsScored(footballClub.getGoalsScored() + team02.getGoalsScored());
                        footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team01.getGoalsScored());
                        footballClub.setPoints(footballClub.getPoints() + team02.getPoints());

                    }
                }
            }

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");



            //Validating the input details with the details of the clubs

                if (clubsList.contains(club01)&& clubsList.contains(club02)){
                    System.out.println();
                    System.out.println("The Entered Match Has Been Added To The Data Records!");
                    FootballMatch match = new FootballMatch(dateTime.format(dtf),club01.getClubName(),club02.getClubName(), team01.getGoalsScored(), team02.getGoalsScored(), team01.getPoints(), team02.getPoints());
                    matchList.add(match);
                    break;
                }else if (!clubsList.contains(club01)  || !clubsList.contains(club02)){
                    System.out.println();
                    System.out.println("Entered Club Details are Invalid. Please Check Again!");
                    System.out.println();
                    break;
                }


            break;

        }

    }

    @Override
    public void saveData(File footballClubs, File playedMatches) throws IOException {

        FileOutputStream fileOutPut01 = new FileOutputStream(footballClubs);
        FileOutputStream fileOutPut02 = new FileOutputStream(playedMatches);

        ObjectOutputStream objectOutPut01 =  new ObjectOutputStream(fileOutPut01);
        ObjectOutputStream objectOutPut02 =  new ObjectOutputStream(fileOutPut02);

        for (FootballClub footballClub :clubsList ){
            objectOutPut01.writeObject(footballClub);
        }

        for (FootballMatch match : matchList){
            objectOutPut02.writeObject(match);
        }

        objectOutPut01.flush();
        objectOutPut02.flush();
        fileOutPut01.close();
        objectOutPut01.close();
        fileOutPut02.close();
        objectOutPut02.close();

        System.out.println();
        System.out.println("All Data Have Been saved To The File From The Application");
        System.out.println();

        clubsList.clear();
        matchList.clear();
    }

    @Override
    public void loadData(File footballClubs, File playedMatches) throws IOException, ClassNotFoundException {

        if ((footballClubs.exists() && footballClubs.length() != 0) || (playedMatches.exists() && playedMatches.length() != 0)) {

            FileInputStream fileInput01 = new FileInputStream(footballClubs);
            ObjectInputStream objectInput01 = new ObjectInputStream(fileInput01);

            FileInputStream fileInput02 = new FileInputStream(playedMatches);
            ObjectInputStream objectInput02 = new ObjectInputStream(fileInput02);


            for (; ; ) {
                try {
                    FootballClub footballClub = (FootballClub) objectInput01.readObject();
                    clubsList.add(footballClub);
                } catch (EOFException e) {
                    break;
                }

            }
            for (; ; ) {
                try {
                    FootballMatch match = (FootballMatch) objectInput02.readObject();
                    matchList.add(match);
                } catch (EOFException e) {
                    break;
                }
            }
            fileInput01.close();
            objectInput01.close();
            fileInput02.close();
            objectInput02.close();

            System.out.println();
            System.out.println("All Data Have Been Load From The File To The Application");
            System.out.println();

        }
    }


}
