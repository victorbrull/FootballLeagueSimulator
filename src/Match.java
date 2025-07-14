import java.util.Random;

public class Match {
    
    private int id;
    private int matchweek;
    private Team home;
    private int home_id;
    private Team away;
    private int away_id;
    private int homeGoals;
    private int awayGoals;
    private int[] possibleGoals;
    private Database database;

    public Match(Team homeTeam, Team awayTeam, int matchID, int week) {
        this.id = matchID;
        this.matchweek = week;
        this.home = homeTeam;
        this.home_id = homeTeam.getID();
        this.away = awayTeam;
        this.away_id = awayTeam.getID();
        possibleGoals = new int[]{0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5};
        this.database = new Database(); // Saves results and updates teams stats
    }

    public String printMatch() {
        return this.home.getName() + " vs " + this.away.getName();
    }

    public void simulateMatch() {
        this.homeGoals = getRandomGoals();
        this.awayGoals = getRandomGoals();

        // Update teams stats after match
        this.home.updateTeamStats(homeGoals, awayGoals);
        this.away.updateTeamStats(awayGoals, homeGoals);

        // Save match result
        this.database.saveMatchweekResults(this);
    }

    public String printResult() {
        return home.getName() + " " + homeGoals + " - " + awayGoals + " " + away.getName();
    }

    private int getRandomGoals() {
        Random random = new Random();
        int goalsIndex = random.nextInt(possibleGoals.length);
        return possibleGoals[goalsIndex];
    }

    public int getID() {
        return this.id;
    }

    public int getMatchweek() {
        return this.matchweek;
    }

    public int getHomeID() {
        return this.home_id;
    }

    public int getAwayID() {
        return this.away_id;
    }

    public int getHomeGoals() {
        return this.homeGoals;
    }

    public int getAwayGoals() {
        return this.awayGoals;
    }
}
