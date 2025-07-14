public class Team {
    
    private int id;
    private String name;
    private String abbreviation;
    private int matchesPlayed;
    private int points;
    private int wins;
    private int ties;
    private int losses;
    private int goalsScored;
    private int goalsAgainst;
    private int goalDifference;

    public Team(String teamName, String teamAbbreviation, int teamID) {
        this.id = teamID;
        this.name = teamName;
        this.abbreviation = teamAbbreviation;
        this.matchesPlayed = 0;
        this.points = 0;
        this.wins = 0;
        this.ties = 0;
        this.losses = 0;
        this.goalsScored = 0;
        this.goalsAgainst = 0;
        this.goalDifference = 0;
    }

    public void updateTeamStats(int teamGoals, int opponentGoals) {

        this.matchesPlayed++;
        
        if (teamGoals > opponentGoals) {
            // Team has won
            this.points += 3;
            this.wins++;
        }
        else if (teamGoals < opponentGoals) {
            // Team has lost
            this.losses++;
        }
        else {
            // Team has drawn
            this.points++;
            this.ties++;
        }

        // Update goals stats
        this.goalsScored += teamGoals;
        this.goalsAgainst += opponentGoals;
        this.goalDifference = this.goalsScored - this.goalsAgainst;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

    public int getMatchesPlayed() {
        return this.matchesPlayed;
    }

    public int getPoints() {
        return this.points;
    }

    public int getWins() {
        return this.wins;
    }

    public int getTies() {
        return this.ties;
    }

    public int getLosses() {
        return this.losses;
    }

    public int getGoalsScored() {
        return this.goalsScored;
    }

    public int getGoalsAgainst() {
        return this.goalsAgainst;
    }

    public int getGoalDifference() {
        return this.goalDifference;
    }
}
