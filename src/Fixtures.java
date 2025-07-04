import java.util.ArrayList;
import java.util.List;

public class Fixtures {

    private List<Team> teams;
    private List<List<Match>> fixtures;

    public Fixtures(List<Team> teams) {
        this.teams = teams;
        generateFixtures();
    }

    public void simulateMatchweek(int matchweek) {

        // Get the list of matches from the specific matchweek
        List<Match> matches = new ArrayList<>(fixtures.get(matchweek-1));
        for (Match match : matches) {
            // Simulate match
            simulateMatch(match);
        }
    }

    private void simulateMatch(Match match) {

        // Print previous match info
        System.out.println("Simulating match");
        System.out.println(match.printMatch());
        // Simulate and print results
        match.simulateMatch();
        System.out.println(match.printResult());
        System.out.println();
    }

    public void printMatchweekFixtures(int matchweek) {

        // Get the list of matches from the specific matchweek
        List<Match> matches = new ArrayList<>(fixtures.get(matchweek-1));

        System.out.println("Matchweek " + matchweek + ":");
        for (Match match : matches) {
            // Print match information
            System.out.println("  " + match.printMatch());
        }
        System.out.println();
    }

    public void printFixtures() {

        System.out.println(fixtures.size());

        int matchweek = 1;
        // For every matchweek print every game
        for (List<Match> matches : fixtures) {
            System.out.println("Matchweek " + matchweek + ":");
            for (Match match : matches) {
                System.out.println("  " + match.printMatch());
            }
            matchweek++;
        }
    }

    // Method to generate the fixtures of the league
    private void generateFixtures() {

        int numTeams = teams.size();

        // Array to be returned filled with matches
        this.fixtures = new ArrayList<>();

        // Create the matchweeks
        for (int leg = 0; leg < 2; leg++) {
            for (int matchweek = 0; matchweek < numTeams - 1; matchweek++) {
                List<Match> matches = new ArrayList<>();

                // Pair teams
                for (int i = 0; i < numTeams / 2; i++) {
                    if ((matchweek % 2 == 0 && leg == 0) || (matchweek % 2 == 1 && leg == 1)) {
                        Team home = teams.get(i);
                        Team away = teams.get(numTeams - 1 - i);
                        matches.add(new Match(home, away));
                    }
                    else if ((matchweek % 2 == 0 && leg == 1) || (matchweek % 2 == 1 && leg == 0)) {
                        Team away = teams.get(i);
                        Team home = teams.get(numTeams - 1 - i);
                        matches.add(new Match(home, away));
                    }
                }

                // Add matches of the current matchweek
                fixtures.add(matches);

                // Rotate teams
                reorganizeArray(numTeams);
            }
        }
    }

    // Method to reorganize the team array to aviod repeated matches
    private void reorganizeArray(int numTeams) {

        List<Team> temp = new ArrayList<>(teams);
        Team last = temp.get(numTeams - 1);

        for (int i = 2; i < numTeams; i++) {
            teams.set(i, temp.get(i-1));
        }
        teams.set(1, last);
    }
}
