# FootballLeagueSimulator

### A football league simulator, using Java, that allows to simulate matches, track team statistics, and manage league standings with continous MySQL database storage.

## Features
- **`Interactive Menu`**: Navigate through different options via an intuitive command-line interface.
- **`Match Simulation`**:: Simulate individual match weeks with score generation.
- **`Team Statistics`**: Track detailed team performance metrics.
- **`Database Integration`**: Continous data storage using MySQL database.

## Project Structure
```plaintext
FootballLeagueSimulator/
├── src/
│   ├── FootballApp.java      # Main application with interactive menu
│   ├── Match.java            # Match simulation logic
│   ├── Team.java             # Team management
│   ├── League.java           # League management
│   ├── Fixtures.java         # Match fixtures generation
│   ├── Database.java         # Database connection and operations
│   └── DatabaseTest.java     # Database connection testing
├── lib/
│   └── mysql-connector-j-9.3.0.jar # MySQL JDBC driver
└── README.md
```

## Setup and Installation
### 0. Prerequisites
- Java
- MySQL Server

### 1. Clone the repository
```plaintext
git clone https://github.com/victorbrull/FootballLeagueSimulator.git
```
```plaintext
cd FootballLeagueSimulator
```

### 2. Set up MySQL Database
```plaintext
# Create Database
CREATE DATABASE football_league;

# Create teams table
CREATE TABLE `football_league`.`teams` (
  `id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `abbreviation` VARCHAR(3) NOT NULL,
  `matches_played` INT NULL DEFAULT 0,
  `points` INT NULL DEFAULT 0,
  `wins` INT NULL DEFAULT 0,
  `ties` INT NULL DEFAULT 0,
  `losses` INT NULL DEFAULT 0,
  `goals_scored` INT NULL DEFAULT 0,
  `goals_against` INT NULL DEFAULT 0,
  `goal_difference` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

# Create matches table
CREATE TABLE `football_league`.`matches` (
  `id` INT NOT NULL,
  `home_id` INT NULL,
  `away_id` INT NULL,
  `home_goals` INT NULL,
  `away_goals` INT NULL,
  `match_week` INT NULL,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  PRIMARY KEY (`id`));
```

### 3. Download MySQL Connector/J
- Download from MySQL Official Site
```plaintext
https://dev.mysql.com/downloads/connector/j
```
- Place the JAR file in the lib/ folder and remove the pre existence one

### 4. Configure Database Connection
- Update the Database.java and DatabaseTest.java files with your MySQL credentials
```plaintext
private String URL = "URL";  // URL of your local MySQL server
private String USERNAME = "USERNAME";  // Replace with your MySQL username
private String PASSWORD = "PASSWORD";  // Replace with your MySQL password
```

## Future Enhancements
- Allow users to save and load game sessions
- Java UI implementation to enhance the user experience
- Player statistics tracking
