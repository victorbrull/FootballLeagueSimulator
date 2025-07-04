import java.util.List;

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
        
    }

    public void printFixtures() {
        // Print the full season calendar
        this.fixtures.printFixtures();
    }
}
