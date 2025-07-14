import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class FootballApp {
    public static void main(String [] args) {
        
        DatabaseTest test = new DatabaseTest();

        System.out.println("\nFootball League Simulator");

        Scanner scan = new Scanner(System.in);

        int option = 0;
        while (option != 3) {
            // Print menu
            System.out.println("\nSELECT AN OPTION");
            System.out.println("1. New Game");
            System.out.println("2. Load Game");
            System.out.println("3. Exit\n");
            System.out.print("Enter your choice: ");

            // Scan user input
            option = scan.nextInt();

            if (option == 1) {
                new_game();
                option = 3;
            }
            else if (option == 2) {
                System.out.println("This function is not already available");
            }
        }

        System.out.println("Thanks for playing!");
    }

    public static void new_game() {

        System.out.println("\nStarting a new game...\n");
        Scanner scan = new Scanner(System.in);
        
        League league = new League();
        league.printTeams();
        int totalMatchweeks = league.getTotalMatchweeks();
        
        int option = 0;
        // Iterate until final matchweek or EXIT
        while ((league.getMatchweek() <= totalMatchweeks) && (option != 3)) {
            System.out.println("SELECT AN OPTION");  
            System.out.println("1. Simulate Matchweek " + league.getMatchweek());  
            System.out.println("2. Print Table");  
            System.out.println("3. Exit\n");  
            System.out.print("Enter your choice: ");  

            option = scan.nextInt();
            System.out.println();

            if (option == 1) {
                league.simulateMatchweek();
            }
            else if (option == 2) {
                league.printSQLTable();
            }
            else if (option == 3) {

            }
            else {
                System.out.println("Enter a valid option");
            }
            System.out.println();
        }

        // Give user the option to print the table at the end of the season
        while (option != 3) {
            System.out.println("SELECT AN OPTION"); 
            System.out.println("2. Print Table");  
            System.out.println("3. Exit\n");  
            System.out.print("Enter your choice: "); 

            option = scan.nextInt();
            System.out.println();

            if (option == 2) {
                league.printTable();
            }
            else if (option != 2 && option != 3) {
                System.out.println("Enter a valid option");
            }
        }
    }
}
