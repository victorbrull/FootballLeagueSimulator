import java.util.Random;

public class Match {
    
    private Team home;
    private Team away;
    private int homeGoals;
    private int awayGoals;
    private int[] possibleGoals;

    public Match(Team homeTeam, Team awayTeam) {
        this.home = homeTeam;
        this.away = awayTeam;
        possibleGoals = new int[]{0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5};
    }

    public String printMatch() {
        return this.home.getName() + " vs " + this.away.getName();
    }

    public void simulateMatch() {
        this.homeGoals = getRandomGoals();
        this.awayGoals = getRandomGoals();

        // Update teams stats after match
        this.home.updateAfterMatch(homeGoals, awayGoals);
        this.away.updateAfterMatch(awayGoals, homeGoals);
    }

    public String printResult() {
        return home.getName() + " " + homeGoals + " - " + awayGoals + " " + away.getName();
    }

    private int getRandomGoals() {
        Random random = new Random();
        int goalsIndex = random.nextInt(possibleGoals.length);
        return possibleGoals[goalsIndex];
    }
}
