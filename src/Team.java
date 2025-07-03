public class Team {
    
    private String name;
    private int matchesPlayed;
    private int points;
    private int wins;
    private int ties;
    private int losses;
    private int goalsScored;
    private int goalsAgainst;
    private int goalDifference;

    public Team(String teamName) {
        this.name = teamName;
        this.matchesPlayed = 0;
        this.points = 0;
        this.wins = 0;
        this.ties = 0;
        this.losses = 0;
        this.goalsScored = 0;
        this.goalsAgainst = 0;
        this.goalDifference = 0;
    }
}
