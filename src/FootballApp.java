import java.util.List;
import java.util.ArrayList;

public class FootballApp {
    public static void main(String [] args) {
        // To Compile:
        // javac -d bin src/*.java

        // List<String> teams = new ArrayList<>(List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T"));

        List<Team> teams = createTeams();
                                                    
        League league = new League(teams);
        league.simulateMatchweek();
    }

    public static List<Team> createTeams() {
        List<Team> teams = new ArrayList<>();

        Team barcelona = new Team("FC Barcelona", "FCB");
        teams.add(barcelona);
        Team realMadrid = new Team("Real Madrid CF", "RM");
        teams.add(realMadrid);
        Team tottenham = new Team("Tottenham Hotspur FC", "TOT");
        teams.add(tottenham);
        Team liverpool = new Team("Liverpool FC", "LIV");
        teams.add(liverpool);
        Team bayern = new Team("FC Bayern Munchen", "BAY");
        teams.add(bayern);
        Team atleti = new Team("Club Atletico de Madrid", "ATL");
        teams.add(atleti);

        return teams;
    }
}
