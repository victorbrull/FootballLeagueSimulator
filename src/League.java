import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class League {
    
    private List<Team> teams;
    private Fixtures fixtures;
    private int matchweek;
    private Database database;

    public League() {
        this.database = new Database(); // Initialises the teams
        this.teams = new ArrayList<>();
        createTeams();
        this.fixtures = new Fixtures(this.teams);
        this.matchweek = 1;
    }

    private void createTeams() {
        System.out.println("Creating league and teams...");
        Team barcelona = new Team("FC Barcelona", "FCB", 1);
        this.teams.add(barcelona);
        Team realMadrid = new Team("Real Madrid CF", "RM", 2);
        this.teams.add(realMadrid);
        Team tottenham = new Team("Tottenham Hotspur FC", "TOT", 3);
        this.teams.add(tottenham);
        Team liverpool = new Team("Liverpool FC", "LIV", 4);
        this.teams.add(liverpool);
        Team bayern = new Team("FC Bayern Munchen", "BAY", 5);
        this.teams.add(bayern);
        Team atleti = new Team("Club Atletico de Madrid", "ATL", 6);
        this.teams.add(atleti);

        // Create teams also in the database
        this.database.initializeTeams(this.teams);
    }

    public void simulateMatchweek() {

        // Print matches to be played
        fixtures.printMatchweekFixtures(this.matchweek);

        // 2000 ms (2s) delay before simulating the matches
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        // Simulate every match
        fixtures.simulateMatchweek(this.matchweek);
        this.matchweek++;
    }

    public void printTable() {

        System.out.println("LEAGUE TABLE");
        System.out.printf("%-25s %4s %4s %4s %4s %4s %4s %4s %4s%n", "Team", "Pts", "GP", "W", "D", "L" ,"GS", "GA", "GD");

        Collections.sort(teams, new Comparator<Team>() {
            @Override
            public int compare(Team t1, Team t2) {
                if (t1.getPoints() != t2.getPoints()) {
                    return t2.getPoints() - t1.getPoints(); // Descending
                } 
                else if (t1.getGoalDifference() != t2.getGoalDifference()) {
                    return t2.getGoalDifference() - t1.getGoalDifference();
                }
                else {
                    return t2.getGoalsScored() - t1.getGoalsScored();
                }
            }
        });

        for (Team t : teams) {
            System.out.printf("%-25s %4d %4d %4d %4d %4d %4d %4d %4d%n", t.getName(), t.getPoints(), 
                                t.getMatchesPlayed(), t.getWins(), t.getTies(), t.getLosses(), 
                                t.getGoalsScored(), t.getGoalsAgainst(), t.getGoalDifference());
        }
        System.out.println();
    }

    public void printSQLTable() {
        this.database.displayLeagueTable();
    }

    public void printFixtures() {
        // Print the full season calendar
        this.fixtures.printFixtures();
    }

    public int getMatchweek() {
        return this.matchweek;
    }

    public int getTotalMatchweeks() {
        return (this.teams.size() - 1) * 2;
    }

    public void printTeams() {
        // Print team names
        for (Team t : this.teams) {
            System.out.println(t.getName() + " - " + t.getAbbreviation());
        }
        System.out.println();
    }
}
