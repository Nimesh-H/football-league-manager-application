package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import play.libs.Json;
import play.mvc.*;
import utils.Util;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HomeController extends Controller {


    static File footballClubs = new File("footballClubsData.txt");
    static File playedMatches = new File("playedMatchesData.txt");

    static Scanner input = new Scanner(System.in);

    public static LeagueManager premierLeague = PremierLeagueManager.getInstance();

    private static List<FootballClub> listClub = new ArrayList<>();
    private static List<FootballMatch> matchList = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);

        main_console:
        while (true){
            //Menu For Users To select Their Option
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("----------------------------- Welcome To The Premier League Manager --------------------------------");
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println();
            System.out.println("You Can Easily Manage The Premier League From This Application.");
            System.out.println("Check The Numbers Of The Processes And Enter The Suitable Number Of The Process That You Required ");
            System.out.println();
            System.out.println(" 1. Add A New Club To Premier League");
            System.out.println(" 2. Delete A Club From Premier League");
            System.out.println(" 3. Display Statistics Of A Club In Premier League");
            System.out.println(" 4. Display Premier League Table");
            System.out.println(" 5. Add A New Played Match");
            System.out.println(" 6. Open GUI");
            System.out.println(" 7. Quit The Application");
            System.out.println();

            System.out.print("Enter The Number Of Process That You wish to Proceed: "); //Getting the Option of the user
            String command1 = input.next();

            //Switch Case according to the user option
            switch (command1){

                case "1":
                    premierLeague.loadData(footballClubs,playedMatches);
                    creatNewFootballClub();
                    premierLeague.saveData(footballClubs,playedMatches);
                    continue main_console;

                case "2":
                    premierLeague.loadData(footballClubs,playedMatches);
                    deleteFootballClub();
                    premierLeague.saveData(footballClubs,playedMatches);
                    continue main_console;

                case "3":
                    premierLeague.loadData(footballClubs,playedMatches);
                    displayStatistics();
                    premierLeague.saveData(footballClubs,playedMatches);
                    continue main_console;

                case "4":
                    premierLeague.loadData(footballClubs,playedMatches);
                    displayPremierLeagueTable();

                    premierLeague.saveData(footballClubs,playedMatches);
                    continue main_console;

                case "5":
                    premierLeague.loadData(footballClubs,playedMatches);
                    addPlayedMatch();

                    premierLeague.saveData(footballClubs,playedMatches);
                    continue main_console;

                case "6":
                    revokeGUI();
                    continue main_console;

                case "7":
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Thank you For Using premier League Manager | Have A Nice Day");
                    System.out.println();
                    System.out.println("Successfully Exiting From The Application...");
                    System.out.println("------------------------------------------------------------");
                    break main_console;

                default:
                    System.out.println("Invalid Input.Please Try Again");
            }

        }


    }

    //Method To Call the Angular GUI from the CLI Menu
    private static void revokeGUI() throws Exception {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "sbt run");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        System.out.println("GUI Is Opening.... Please Wait.......");
    }

    //Create New Football Club
    public static void creatNewFootballClub(){

        create:
        while (true){


            System.out.println("Creat a New Football Club from here!");
            System.out.println("To Exit To Menu Type 'exit'"); //Option to Exit
            System.out.println();

            System.out.println("1.  Football Club Code  : ");   //Input Club Code
            String clubCode = input.next();

            if (clubCode.equals("exit")){
                break;
            }

            System.out.println("2.  Football Club Name  : ");   //Input Club Name
            String clubName = input.next();

            System.out.println("3.  Football Club Location  : ");   //input Club Location
            String location = input.next();


            //Creating the Object
            SportsClub footballClub = new FootballClub(clubCode,clubName,location,0,0,0,0,0,0,0);

            premierLeague.creatNewFootballClub(footballClub);

            System.out.println("The New Football Club Has Been Added To The Premier League");
            System.out.println("----------------------------------------------------------");

            break;
        }
    }

    //Delete Football Club
    public static void deleteFootballClub(){
        while (true){


            System.out.println();
            System.out.println("Delete a Football Club from here!");
            System.out.println("To Exit To Menu Type 'exit'");      //Option to Exit


            System.out.println("1.  Football Club's Code That Needed To Delete:   "); //Getting Code
            String clubCode = input.next();

            if (clubCode.equals("exit")){
                break;
            }

            System.out.println("2.  Football Club's Name That Needed To Delete:    ");  //Getting Name
            String clubName = input.next();

            System.out.println("3.  Football Club's Location That Needed To Delete:    ");  //Getting Location
            String location = input.next();

            SportsClub footballClub = new SportsClub(clubCode,clubName,location);

            premierLeague.deleteFootballClub(footballClub);

            break;



        }
    }

    //Display Statistics Of A Club
    public static void displayStatistics(){
        while (true){


            System.out.println();
            System.out.println("Display Statistics Of A Football Club from here!");
            System.out.println("To Exit To Menu Type 'exit'");


            System.out.println("1.  Football Club's Code That Needed To Display Statistics:   "); //Input Code Of The Club
            String clubCode = input.next();

            if (clubCode.equals("exit")){
                break;
            }

            System.out.println("2.  Football Club Name That Needed To Display Statistics:    ");    //Input Name Of The Club
            String clubName = input.next();

            System.out.println("3.  Football Club Location That Needed To Display Statistics:    ");    //Input Location Of The Club
            String location = input.next();

            SportsClub footballClub = new SportsClub(clubCode,clubName,location);

            premierLeague.displayStatistics(footballClub);

            break;
        }
    }

    //Displaying League Table
    public static void displayPremierLeagueTable(){

        premierLeague.displayPremierLeagueTable();
    }

    //Adding A New Played Match
    public static void addPlayedMatch(){

        match:
        while(true){
            System.out.println();
            System.out.println("Add a Football Match from here!");
            System.out.println("To Exit To Menu Type 'exit'");


            //Team 01 Details--------------------------------
            System.out.print("1.1 Football Club 01 Code:   ");
            String team01Code = input.next();

            if (team01Code.equals("exit")){
                break;
            }

            System.out.print("1.2 Football Club 01 Name: ");
            String team01Name = input.next();

            System.out.print("1.3 Football Club 01 Location: ");
            String  team01Location = input.next();
            System.out.println();

            //Team 02 Details--------------------------------

            System.out.print("2.1 Football Club 02 Code: ");
            String team02Code = input.next();

            if (team02Code.equals("exit")){
                break;
            }

            System.out.print("2.2 Football Club 02 Name: ");
            String team02Name = input.next();

            System.out.print("2.3 Football Club 02 Location: ");
            String  team02Location = input.next();
            System.out.println();

            //Match Score-------------------------------------

            System.out.print("3.1 Team "+  team01Name +"'s Score: ");

            if (!input.hasNextInt()){

                System.out.println("invalid Score. Try Again:   ");
                input.next();
            }
            int team01Score = input.nextInt();
            input.nextLine();

            //------------------------------------------------

            System.out.print("3.2 Team "+  team02Name +"'s Score: ");

            if (!input.hasNextInt()){

                System.out.println("invalid Score. Try Again: ");
                input.next();
            }
            int team02Score = input.nextInt();
            input.nextLine();
            System.out.println();

            //Match Points--------------------------------

            System.out.print("4.1 Team "+  team01Name +"'s Points: ");

            if (!input.hasNextInt()){

                System.out.println("invalid points. Try Again:   ");
                input.next();
            }

            int team01Points = input.nextInt();
            input.nextLine();

            //------------------------------------------------

            System.out.print("4.2 Team "+  team02Name +"'s Points: ");

            if (!input.hasNextInt()){

                System.out.println("invalid Points. Try Again: ");
                input.next();
            }
            int team02Points = input.nextInt();
            input.nextLine();

            //Creating the Objects----------------------------

            FootballClub team01 = new FootballClub(team01Code,team01Name,team01Location,team01Points,0,team01Score,team02Score,0,0,0);
            FootballClub team02 = new FootballClub(team02Code,team02Name,team02Location,team02Points,0,team02Score,team01Score,0,0,0);

            premierLeague.addPlayedMatch(team01,team02);
            break;
        }
    }

    public Result clubList() throws IOException, ClassNotFoundException {

        loadData(footballClubs,playedMatches);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonData = mapper.convertValue(listClub, JsonNode.class);
        saveData(footballClubs,playedMatches);
        return ok(Util.createResponse(jsonData, true));
    }

    public Result clubListSortGoals() throws IOException, ClassNotFoundException {

        loadData(footballClubs,playedMatches);
        ObjectMapper mapper = new ObjectMapper();
        Collections.sort(listClub, new SortScoredGoals());
        JsonNode jsonData = mapper.convertValue(listClub, JsonNode.class);
        return ok(Util.createResponse(jsonData, true));
    }

    public Result clubListSortWins() throws IOException, ClassNotFoundException {

        loadData(footballClubs,playedMatches);
        ObjectMapper mapper = new ObjectMapper();
        Collections.sort(listClub, new SortMatchWins());
        JsonNode jsonData = mapper.convertValue(listClub, JsonNode.class);
        return ok(Util.createResponse(jsonData, true));
    }

    public Result generateRandomMatch() throws IOException, ClassNotFoundException {

        List<FootballMatch> randomMatch = new ArrayList<>();

        loadData(footballClubs,playedMatches);
        ObjectMapper mapper = new ObjectMapper();

        LocalDateTime dateTime = LocalDateTime.now();

        FootballClub randomTeam01;
        FootballClub randomTeam02;

        Random randomGenerator = new Random();

        randomTeam01 = listClub.get(randomGenerator.nextInt(listClub.size()));

        while (true){
            randomTeam02 = listClub.get(randomGenerator.nextInt(listClub.size()));
            if (randomTeam02.equals(randomTeam01)){
                continue;
            }
            break;
        }

        int team01Points = 0;
        int team02Points = 0;

        int team01Score = randomGenerator.nextInt(15);
        int team02Score = randomGenerator.nextInt(15);

        if (team01Score > team02Score){          //According To The PremierLeague Teams receive three points for a win and one point for a draw
            team01Points = 3;
        }else if (team02Score > team01Score){
            team02Points = 3;
        }else {
            team01Points = 1;
            team02Points = 1;
        }

        if (team01Score > team02Score){
            for (FootballClub footballClub : listClub){
                if (footballClub.getClubName().equals(randomTeam01.getClubName())){
                    footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() +1);
                    footballClub.setPoints(footballClub.getPoints() + team01Points);
                    footballClub.setGoalsScored(footballClub.getGoalsScored() + team01Score);
                    footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team02Score);
                    footballClub.setWins(footballClub.getWins() + 1);
                }
                if (footballClub.getClubName().equals(randomTeam02.getClubName())){
                    footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() +1);
                    footballClub.setPoints(footballClub.getPoints() + team02Points);
                    footballClub.setGoalsScored(footballClub.getGoalsScored() + team02Score);
                    footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team01Score);
                    footballClub.setDefeats(footballClub.getDraws() +1);

                }
            }

        }else if (team02Score > team01Score){
            for (FootballClub footballClub : listClub){
                if (footballClub.getClubName().equals(randomTeam02.getClubName())){
                    footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() +1);
                    footballClub.setPoints(footballClub.getPoints() + team02Points);
                    footballClub.setGoalsScored(footballClub.getGoalsScored() + team02Score);
                    footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team01Score);
                    footballClub.setWins(footballClub.getWins() + 1);
                }
                if (footballClub.getClubName().equals(randomTeam01.getClubName())){
                    footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() +1);
                    footballClub.setPoints(footballClub.getPoints() + team01Points);
                    footballClub.setGoalsScored(footballClub.getGoalsScored() + team01Score);
                    footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team02Score);
                    footballClub.setDefeats(footballClub.getDraws() +1);
                }
            }

        }else {
            for (FootballClub footballClub : listClub){
                if (footballClub.getClubName().equals(randomTeam02.getClubName())){
                    footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() +1);
                    footballClub.setPoints(footballClub.getPoints() + team01Points);
                    footballClub.setGoalsScored(footballClub.getGoalsScored() + team01Score);
                    footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team02Score);
                    footballClub.setDraws(footballClub.getDraws() +1);
                }
                if (footballClub.getClubName().equals(randomTeam01.getClubName())){
                    footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() +1);
                    footballClub.setPoints(footballClub.getPoints() + team02Points);
                    footballClub.setGoalsScored(footballClub.getGoalsScored() + team02Score);
                    footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team01Score);
                    footballClub.setDraws(footballClub.getDraws() +1);
                }
            }
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        FootballMatch match = new FootballMatch(dateTime.format(dtf),randomTeam01.getClubName(),randomTeam02.getClubName(),team01Score,team02Score,team01Points,team02Points);
        randomMatch.add(match);
        matchList.add(match);
        saveData(footballClubs,playedMatches);
        premierLeague.loadData(footballClubs,playedMatches);
        JsonNode jsonData = mapper.convertValue(randomMatch, JsonNode.class);
        return ok(Util.createResponse(jsonData, true));
    }

    public Result matchesList() throws IOException, ClassNotFoundException {
        loadData(footballClubs,playedMatches);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonData = mapper.convertValue(matchList, JsonNode.class);
        saveData(footballClubs,playedMatches);
        return ok(Util.createResponse(jsonData, true));
    }

    public Result findMatch(String date) throws IOException, ClassNotFoundException {

        List<FootballMatch> foundMatches = new ArrayList<>();

        loadData(footballClubs,playedMatches);

        for (FootballMatch match : matchList){
           if (match.getPlayingDate().equals(date)){
               foundMatches.add(match);
           }
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonData = mapper.convertValue(foundMatches, JsonNode.class);
        saveData(footballClubs,playedMatches);
        return ok(Util.createResponse(jsonData, true));
    }

    public static void saveData(File footballClubs, File playedMatches) throws IOException {

        FileOutputStream fileOutPut01 = new FileOutputStream(footballClubs);
        FileOutputStream fileOutPut02 = new FileOutputStream(playedMatches);

        ObjectOutputStream objectOutPut01 = new ObjectOutputStream(fileOutPut01);
        ObjectOutputStream objectOutPut02 = new ObjectOutputStream(fileOutPut02);

        for (FootballClub footballClub : listClub) {
            objectOutPut01.writeObject(footballClub);
        }

        for (FootballMatch match : matchList) {

            objectOutPut02.writeObject(match);
        }

        objectOutPut01.flush();
        objectOutPut02.flush();
        fileOutPut01.close();
        objectOutPut01.close();
        fileOutPut02.close();
        objectOutPut02.close();

        listClub.clear();
        matchList.clear();

    }

    public static void loadData(File footballClubs, File playedMatches) throws IOException, ClassNotFoundException {

        if ((footballClubs.exists() && footballClubs.length() != 0) || (playedMatches.exists() && playedMatches.length() != 0)) {

            FileInputStream fileInput01 = new FileInputStream(footballClubs);
            ObjectInputStream objectInput01 = new ObjectInputStream(fileInput01);

            FileInputStream fileInput02 = new FileInputStream(playedMatches);
            ObjectInputStream objectInput02 = new ObjectInputStream(fileInput02);

            for (; ; ) {
                try {
                    FootballClub footballClub = (FootballClub) objectInput01.readObject();
                    if (!listClub.contains(footballClub)) {
                        listClub.add(footballClub);
                    }
                } catch (EOFException e) {
                    break;
                }

            }

            for (; ; ) {
                try {
                    FootballMatch match = (FootballMatch) objectInput02.readObject();
                    if (!matchList.contains(match)) {
                        matchList.add(match);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
            fileInput01.close();
            objectInput01.close();
            fileInput02.close();
            objectInput02.close();

        }
    }

}