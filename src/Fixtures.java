import java.util.ArrayList;
import java.util.List;

public class Fixtures {

    private List<String> teams;
    private List<List<String>> fixtures;

    public Fixtures(List<String> teams) {
        this.teams = teams;
        generateFixtures();
        printFixtures();
    }

    public void printFixtures() {
        System.out.println(fixtures.size());

        int matchweek = 1;
        // For every matchweek print every game
        for (List<String> matches : fixtures) {
            if ((matchweek == 1) || (matchweek == 2) || (matchweek == 6) || (matchweek == 7)) {
                System.out.println("Matchweek " + matchweek + ":");
                for (String match : matches) {
                    System.out.println("  " + match);
                }
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
                List<String> matches = new ArrayList<>();

                // Pair teams
                for (int i = 0; i < numTeams / 2; i++) {
                    if ((matchweek % 2 == 0 && leg == 0) || (matchweek % 2 == 1 && leg == 1)) {
                        String home = teams.get(i);
                        String away = teams.get(numTeams - 1 - i);
                        matches.add(home + " vs " + away);
                    }
                    else if ((matchweek % 2 == 0 && leg == 1) || (matchweek % 2 == 1 && leg == 0)) {
                        String away = teams.get(i);
                        String home = teams.get(numTeams - 1 - i);
                        matches.add(home + " vs " + away);
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

        List<String> temp = new ArrayList<>(teams);
        String last = temp.get(numTeams - 1);

        for (int i = 2; i < numTeams; i++) {
            teams.set(i, temp.get(i-1));
        }
        teams.set(1, last);
    }
}
