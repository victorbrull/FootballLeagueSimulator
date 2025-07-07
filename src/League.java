import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class League {
    
    private List<Team> teams;
    private Fixtures fixtures;
    private int matchweek;

    public League(List<Team> teamList) {
        this.teams = teamList;
        this.fixtures = new Fixtures(teams);
        this.matchweek = 1;
    }

    public void simulateMatchweek() {

        // Print matches to be played
        fixtures.printMatchweekFixtures(this.matchweek);
        // Simulate every match
        fixtures.simulateMatchweek(this.matchweek);
        this.matchweek++;
    }

    public void printTable() {

        System.out.println("League Table:");
        System.out.printf("%-25s %4s %4s %4s %4s %4s %4s %4s %4s%n", "Team", "Pts", "GP", "W", "D", "L" ,"GS", "GA", "GD");

        Collections.sort(teams, new Comparator<Team>() {
            @Override
            public int compare(Team t1, Team t2) {
                if (t1.getPoints() != t2.getPoints()) {
                    return t2.getPoints() - t1.getPoints(); // Descending
                } else {
                    return t2.getGoalDifference() - t1.getGoalDifference();
                }
            }
        });

        for (Team t : teams) {
            System.out.printf("%-25s %4d %4d %4d %4d %4d %4d %4d %4d%n", t.getName(), t.getPoints(), 
                                t.getMatchesPlayed(), t.getWins(), t.getTies(), t.getLosses(), 
                                t.getGoalsScored(), t.getGoalsAgainst(), t.getGoalDifference());
        }
    }

    public void printFixtures() {
        // Print the full season calendar
        this.fixtures.printFixtures();
    }
}
