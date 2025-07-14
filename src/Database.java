import java.sql.*;
import java.util.List;

public class Database {
    
    // Database connection parameters
    private String URL = "URL";  // URL of your local MySQL server
    private String USERNAME = "USERNAME";  // Replace with your MySQL username
    private String PASSWORD = "PASSWORD";  // Replace with your MySQL password

    public Database() {}
    
    // Get a connection to the database
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    // Initialize database with the given teams
    public void initializeTeams(List<Team> teams) {
        
        try (Connection conn = getConnection()) {
            // Clear existing data
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM matches");
            stmt.executeUpdate("DELETE FROM teams");
            
            // Insert teams
            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO teams (id, name, abbreviation, matches_played, points, wins, ties, " +
                                    "losses, goals_scored, goals_against, goal_difference) " +
                                    "VALUES (?, ?, ?, 0, 0, 0, 0, 0, 0, 0, 0)"
            );
            
            // Get and insert essential team information
            for (Team t : teams) {
                pstmt.setInt(1, t.getID());
                pstmt.setString(2, t.getName());
                pstmt.setString(3, t.getAbbreviation());
                pstmt.executeUpdate();
            }
            
            System.out.println("Database initialized with " + teams.size() + " teams.\n");
            
        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage() + "\n");
        }
    }
    
    // Save match week results to database
    public void saveMatchweekResults(Match match) {

        try (Connection conn = getConnection()) {
            // Insert match result
            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO matches (id, home_id, away_id, home_goals, away_goals, match_week) " + 
                                        "VALUES (?, ?, ?, ?, ?, ?)"
            );

            pstmt.setInt(1, match.getID());
            pstmt.setInt(2, match.getHomeID());
            pstmt.setInt(3, match.getAwayID());
            pstmt.setInt(4, match.getHomeGoals());
            pstmt.setInt(5, match.getAwayGoals());
            pstmt.setInt(6, match.getMatchweek());
            pstmt.executeUpdate();
            
            // Update team stats
            updateTeamStats(match.getHomeID(), match.getAwayID(), match.getHomeGoals(), match.getAwayGoals());
            
        } catch (SQLException e) {
            System.out.println("Error saving match result: " + e.getMessage());
        }
    }
    
    // Update team statistics after a match
    private void updateTeamStats(int homeID, int awayID, int homeGoals, int awayGoals) {

        try (Connection conn = getConnection()) {
            // Update home team stats
            updateSingleTeamStats(homeID, homeGoals, awayGoals);
            // Update away team stats
            updateSingleTeamStats(awayID, awayGoals, homeGoals);

        } catch (SQLException e) {
            System.out.println("Error updating team stats: " + e.getMessage());
        }
    }
    
    private void updateSingleTeamStats(int teamId, int teamGoals, int opponentGoals) {

        try (Connection conn = getConnection()) {
            // Update team stats
            PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE teams SET goals_scored = goals_scored + ?, goals_against = goals_against + ?, " +
                "goal_difference = goal_difference + ?, matches_played = matches_played + 1, " +
                "wins = wins + ?, ties = ties + ?, losses = losses + ?, " +
                "points = points + ? WHERE id = ?"
            );
            
            // Update goals scored and against
            pstmt.setInt(1, teamGoals);
            pstmt.setInt(2, opponentGoals);
            pstmt.setInt(3, teamGoals - opponentGoals);
            
            if (teamGoals > opponentGoals) {
                // Win
                pstmt.setInt(4, 1); // Update wins
                pstmt.setInt(5, 0); 
                pstmt.setInt(6, 0); 
                pstmt.setInt(7, 3); // +3 points
            } 
            else if (teamGoals < opponentGoals) {
                // Loss
                pstmt.setInt(4, 0);
                pstmt.setInt(5, 0);
                pstmt.setInt(6, 1); // Update losses
                pstmt.setInt(7, 0); // +0 points
            } 
            else {
                // Tie
                pstmt.setInt(4, 0); 
                pstmt.setInt(5, 1); // Update ties
                pstmt.setInt(6, 0); 
                pstmt.setInt(7, 1); // +1 points
            }

            pstmt.setInt(8, teamId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating team stats: " + e.getMessage());
        }
    }
    
    // Get league table from database
    public void displayLeagueTable() {
        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();

            // SQL statement
            ResultSet rs = stmt.executeQuery(
                "SELECT name, points, matches_played, wins, ties, losses, goals_scored, " + 
                "goals_against, goal_difference " +
                "FROM teams ORDER BY points DESC, goal_difference DESC, goals_scored DESC"
            );
            
            // Print table information
            System.out.println("LEAGUE TABLE");
            System.out.println("=".repeat(80));
            System.out.printf("   %-24s %4s %4s %4s %4s %4s %4s %4s %4s%n", "Team", "Pts", "GP", "W", "D", "L" ,"GS", "GA", "GD");
            System.out.println("-".repeat(80));
            
            // Print team stats
            int position = 1;
            while (rs.next()) {
                System.out.printf("%-2d %-23s %4d %5d %4d %4d %4d %4d %4d %4d\n",
                    position++,
                    rs.getString("name"),
                    rs.getInt("points"),
                    rs.getInt("matches_played"),
                    rs.getInt("wins"),
                    rs.getInt("ties"),
                    rs.getInt("losses"),
                    rs.getInt("goals_scored"),
                    rs.getInt("goals_against"),
                    rs.getInt("goal_difference")
                );
            }
            
        } catch (SQLException e) {
            System.out.println("Error displaying league table: " + e.getMessage());
        }
    }
}