import java.sql.*;

public class DatabaseTest {
    
    // Database connection parameters
    private String URL = "URL";  // URL of your local MySQL server
    private String USERNAME = "USERNAME";  // Replace with your MySQL username
    private String PASSWORD = "PASSWORD";  // Replace with your MySQL password

    public DatabaseTest() {
        testConnection();
    }
    
    public void testConnection() {
        System.out.println("Testing MySQL Database Connection...");
        
        try {
            // Try to establish connection
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Successfully connected to the database!");
            
            // Test if our tables exist
            testTables(connection);
            
            // Close the connection
            connection.close();
            System.out.println("Connection closed successfully.");
            
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            System.out.println("Error: " + e.getMessage());
            
            // Common error solutions
            if (e.getMessage().contains("Access denied")) {
                System.out.println("Check your username and password");
            } else if (e.getMessage().contains("Unknown database")) {
                System.out.println("Make sure the database 'football_league' exists");
            } else if (e.getMessage().contains("Connection refused")) {
                System.out.println("Make sure MySQL server is running");
            }
        }
    }
    
    private static void testTables(Connection connection) {
        System.out.println("\nChecking tables...");
        
        try {
            // Check teams table
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM teams");
            if (rs.next()) {
                System.out.println("Teams table exists");
            }
            
            // Check matches table
            rs = stmt.executeQuery("SELECT COUNT(*) FROM matches");
            if (rs.next()) {
                System.out.println("Matches table exists");
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            System.out.println("Error checking tables: " + e.getMessage());
            System.out.println("Make sure you've created the teams and matches tables");
        }
    }
}