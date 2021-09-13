package com.team07.SQL;

import com.team007.model.*;

import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Class SQLFunction has functions to access data from database, insert data to database and update data to database
 */
public class SQLFunctions {

    /**
     * Connection connect
     */
    private Connection connect;

    /**
     * SqlConnectionImplement objects
     */
    private SQLConnectionImplement SQLCall = new SQLConnectionImplement();

    /**
     * Connect to SQL Database
     */
    public SQLFunctions() {
        this.connect = SQLCall.connect();
    }

    /**
     * Get collection of counties from database as a list
     * @return Return collection fo countries from database as a list
     */
    public CountryCollection getCountries() {
        ArrayList<Country> countryArrayList = new ArrayList<Country>();

        try {
            Statement query = connect.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM Country");

            while (result.next()) {
                countryArrayList.add(extractCounties(result));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return new CountryCollection(countryArrayList);
    }

    /**
     * Extract counties from Table Country in database
     * @param result ResultSet
     * @return Returns countries from Table Country in database
     * @throws SQLException SQL Exception
     */
    private Country extractCounties(ResultSet result) throws SQLException {
        Country country = new Country(result.getInt("id"), result.getString("country"));
        return country;
    }

    /**
     * Get collection of teams from database as a list
     * @return Returns collection of teams from database as a list
     */
    public TeamCollection getTeams() {
        ArrayList<Team> teamArrayList = new ArrayList<Team>();

        try {
            Statement query = connect.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM Team");

            while (result.next()) {
                teamArrayList.add(extractTeams(result));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return new TeamCollection(teamArrayList);
    }


    /**
     * Extract teams from Table Team in database
     * @param result ResultSet
     * @return Returns from Table Team in database
     * @throws SQLException SQL Exception
     */
    private Team extractTeams(ResultSet result) throws SQLException {
        Team team = new Team(result.getInt("id"), result.getInt("team_api_id"), result.getInt("team_fifa_api_id"),
                result.getString("team_long_name"), result.getString("team_short_name"));
        return team;
    }

    /**
     * Get collection of players from database as a list
     * @return Returns collection of player from database as a list
     */
    public PlayerCollection getPlayers() {
        ArrayList<Player> playerArrayList = new ArrayList<Player>();

        try {
            Statement query = connect.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM Player");

            while (result.next()) {
                playerArrayList.add(extractPlayers(result));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return new PlayerCollection(playerArrayList);
    }

    /**
     * Extract players from Table Player in database
     * @param result ResultSet
     * @return Returns from Table Player in database
     * @throws SQLException SQL Exception
     */
    private Player extractPlayers(ResultSet result) throws SQLException {
        Player player = new Player(result.getInt("id"), result.getInt("player_api_id"),
                result.getString("player_name"), result.getInt("player_fifa_api_id"),
                result.getString("birthday"), result.getInt("height"),
                result.getInt("weight"), result.getInt("fantasy_soccer_goal"));
        return player;
    }

    /**
     * Get player attributes information from database as list
     * @return Returns player attributes information from database as list
     */
    public PlayerAttributesCollection getPlayerAttributes() {
        ArrayList<PlayerAttributes> playerAttributesArrayList = new ArrayList<PlayerAttributes>();

        try {
            Statement query = connect.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM Player_Attributes");

            while (result.next()) {
                playerAttributesArrayList.add(extractPlayerAttributes(result));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return new PlayerAttributesCollection(playerAttributesArrayList);
    }

    /**
     * Extract player attributes from Table PlayerAttributes in database
     * @param result ResultSet
     * @return Returns extracted player attributes from Table PlayerAttributes in database
     * @throws SQLException SQL Exception
     */
    private PlayerAttributes extractPlayerAttributes(ResultSet result) throws SQLException {
        PlayerAttributes playerAttributes = new PlayerAttributes(result.getInt("id"), result.getInt("player_fifa_api_id"), result.getInt("player_api_id"),
                result.getInt("overall_rating"), result.getInt("potential"), result.getString("preferred_foot"),
                result.getString("attacking_work_rate"), result.getString("defensive_work_rate"),
                result.getInt("free_kick_accuracy"), result.getInt("ball_control"), result.getInt("shot_power"),
                result.getInt("stamina"), result.getInt("strength"), result.getInt("penalties"));
        return playerAttributes;
    }


    /**
     * Takes Player object from client side with the changes in it.
     * And update database by matching player_fifa_api_id to it
     * @param player Player
     */
    public void updatePlayer(Player player) {
        try {
            Statement query = connect.createStatement();
            String sql = playerUpdateQuery(player);
            query.executeUpdate(sql);

            if (query.getUpdateCount() > 0) {
                //next System print statement will be utilized in logger later
                System.out.println("Row Updated for Player " + player.toString());
            }
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }


    /**
     * Returns query to update database by matching player_fifa_api_id
     * @param player Player
     * @return Returns query to update database by matching player_fifa_api_id
     */
    public String playerUpdateQuery(Player player) {
        return "UPDATE Player SET " +
                "fantasy_soccer_goal= '" + player.getFantasy_soccer_goal() +
                "'WHERE player_fifa_api_id=" + player.getPlayer_fifa_api_id();
    }


    /**
     * Get Match information
     * @return Return Match information
     */
    public MatchCollection getMatchInfo() {
        ArrayList<Match> matchArrayList = new ArrayList<Match>();

        try {
            Statement query = connect.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM Match");

            while (result.next()) {
                matchArrayList.add(extractMatchInfo(result));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return new MatchCollection(matchArrayList);
    }

    /**
     * Extract match information from Table Match
     * @param result Match
     * @return Returns match information form Table Match
     * @throws SQLException SQL Exception
     */
    private Match extractMatchInfo(ResultSet result) throws SQLException {
        Match match = new Match(result.getInt("id"), result.getInt("country_id"),
                result.getInt("leauge_id"), result.getString("season"), result.getInt("stage"),
                result.getString("date"), result.getString("home_team_name"), result.getString("away_team_name"),
                result.getInt("away_team_goal"), result.getInt("home_team_goal"), result.getString("goal"), result.getString("shoton"),
                result.getString("shotoff"), result.getString("foulcommit"), result.getString("card"),
                result.getString("cross"), result.getString("corner"), result.getString("possession"));
        return match;
    }

    /**
     * Take Match object from client side with changes in it.
     * And update database by matching country_id to it
     * @param match Match
     */
    public void updateMatch(Match match) {
        try {
            Statement query = connect.createStatement();
            String sql = matchUpdateQuery(match);
            query.executeUpdate(sql);

            if (query.getUpdateCount() > 0) {
                System.out.println("Updated Match");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Returns query to update database by matching country_id
     * @param match Match
     * @return Returns query to update database by matching country_id
     */
    public String matchUpdateQuery(Match match) {
        return "UPDATE MatchGame SET " +
                "season = '" + match.getSeason() +
                "', home_team_name= '" + match.getHome_team_name() +
                "', away_team_name= '" + match.getAway_team_name() +
                "', home_team_goal= '" + match.getHome_team_goal() +
                "', away_team_goal= '" + match.getAway_team_goal() +
                "', goal= '" + match.getGoal() +
                "', shoton= '" + match.getShoton() +
                "', shotoff= '" + match.getShotoff() +
                "', foulcommit= '" + match.getFoulcommit() +
                "', card= '" + match.getCard() +
                "' WHERE country_id= " + match.getCountry_id();
    }

    /**
     * Insert Match object to database to update database when game is being played
     * @param match Match
     */
    public void insertMatch(Match match) {
        try {
            Statement query = connect.createStatement();
            String sql = matchInsertQuery(match);
            System.out.println(sql);
            query.executeUpdate(sql);

            if (query.getUpdateCount() > 0) {
                System.out.println("New Data Added!");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Returns query to insert Match objects to database
     * @param match Match
     * @return Returns query to insert Match objects to database
     */
    public String matchInsertQuery(Match match) {
        return "INSERT INTO MatchGame(country_id, home_team_name, away_team_name, home_team_goal, away_team_goal," +
                "goal, shoton, shotoff, foulcommit, card)" +
                " VALUES (" + "'" + match.getCountry_id() + "',"
                + "'" + match.getHome_team_name() + "',"
                + "'" + match.getAway_team_name() + "',"
                + "'" + match.getHome_team_goal() + "',"
                + "'" + match.getAway_team_goal() + "',"
                + "'" + match.getGoal() + "',"
                + "'" + match.getShoton() + "',"
                + "'" + match.getShotoff() + "',"
                + "'" + match.getFoulcommit() + "',"
                + "'" + match.getCard() + "')";
    }

}
