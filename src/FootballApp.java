import java.util.List;
import java.util.ArrayList;

public class FootballApp {
    public static void main(String [] args) {
        // To Compile:
        // javac -d bin src/*.java

        // List<String> teams = new ArrayList<>(List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T"));
        
        List<String> teams = new ArrayList<>(List.of("FC Barcelona", "Real Madrid CF", "Tottenham Hotspur FC", 
                                                    "Liverpool FC", "FC Bayern Munchen", "Atletico de Madrid CF"));
                                                    
        League league = new League(teams);
    }
}
