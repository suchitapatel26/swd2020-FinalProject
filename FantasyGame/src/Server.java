import com.team007.model.Match;
import com.team007.model.Player;
import com.team007.model.Team;
import com.team07.SQL.SQLFunctions;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Server class connects two clients and implements Fantasy Season
 *
 * @see Server
 */
public class Server extends JFrame {
    /**
     * Server socket holds server
     */
    private ServerSocket server;

    /**
     * Server execution service
     */
    private final ExecutorService service;

    /**
     * Stores client
     */
    private static Soccer[] Soccers;

    /**
     * Server GUI display area
     */
    private static JTextArea displayArea;

    /**
     * Helper for the connecting clients
     */
    private final static int team1 = 0;

    /**
     * to wait for other team
     */
    private static Condition otherTeamConnected;

    /**
     * to lock the game for synchronization
     */
    private static Lock gameLock;

    /**
     * Constructor to set up GUI and Server
     */
    public Server() {
        super("Server");
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setBackground(Color.CYAN);
        displayArea.setText("Welcome to the Server!!\nWaiting for Teams!!\n");
        displayArea.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        setSize(400, 400);
        setVisible(true);
        service = Executors.newFixedThreadPool(2);
        Soccers = new Soccer[2];
        gameLock = new ReentrantLock();
        otherTeamConnected = gameLock.newCondition();
        Condition otherTeamTurn = gameLock.newCondition();
        try {
            server = new ServerSocket(23511, 2);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Displays the message in to GUI display area = cyan
     *
     * @param messageToDisplay string message
     */
    private static void displayMessage(final String messageToDisplay) {
        SwingUtilities.invokeLater(
                () -> displayArea.append(messageToDisplay + "\n")
        );
    }

    /**
     * Wait for two connection so session can be started
     */
    public void execute() {
        for (int i = 0; i < 2; i++) {
            try {
                Soccers[i] = new Soccer(server.accept(), i);
                displayMessage("Team " + (i + 1) + " Connected!!");
                service.execute(Soccers[i]);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                System.exit(1);
            }
        }
        gameLock.lock();
        try {
            Soccers[team1].setSuspended(false);
            otherTeamConnected.signal();
        } finally {
            gameLock.unlock();
        }
    }

    /**
     * Private inner class to manage clients and their input and outputs with Server
     *
     * @see Soccer
     */
    private static class Soccer implements Runnable {
        /**
         * Connection to client
         */
        private final Socket connection;

        /**
         * Output to client
         */
        private Formatter output;

        /**
         * Input from client
         */
        private Scanner input;

        /**
         * Track which team it is
         */
        private final int teamNumber;

        /**
         * Check if thread is suspended
         */
        private boolean suspended = true;

        /**
         * Helper to track team 1 inputs
         */
        private int ith = 0;

        /**
         * Helper to track team 2 inputs
         */
        private int jth = 0;

        /**
         * Helper to swap player fot team
         */
        private static int temp;

        /**
         * Holds team 1 players
         */
        private static Player[] team1Roster = new Player[11];

        /**
         * Holds team 2 players
         */
        private static Player[] team2Roster = new Player[11];

        /**
         * Holds home team
         */
        private static Team home;

        /**
         * Holds away team
         */
        private static Team away;

        /**
         * Holds connection status
         */
        private boolean connected;

        /**
         * To access data from database
         */
        private final SQLFunctions functions = new SQLFunctions();

        /**
         * Saves randomly selected team from database
         */
        private static com.team007.model.Team[] randomTeams = new com.team007.model.Team[10];

        /**
         * Saves randomly selected players from database
         */
        private static Player[] randomPlayers = new Player[50];

        /**
         * Constructor initialize client connection
         *
         * @param socket client connection
         * @param number client number
         */
        public Soccer(Socket socket, int number) {
            teamNumber = number;
            connection = socket;
            try {
                output = new Formatter(connection.getOutputStream());
                input = new Scanner(connection.getInputStream());
                connected = true;

            } catch (IOException ioException) {
                ioException.printStackTrace();
                System.exit(1);
            }

        }

        /**
         * Closes connection to client
         */
        public void closeConnection() {
            displayMessage("Connection closed");
            try {
                connected = false;
                Soccers[0].output.close();
                Soccers[0].input.close();
                Soccers[0].connection.close();
                Soccers[1].output.close();
                Soccers[1].input.close();
                Soccers[1].connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * Takes care of session input and send outputs accordingly to client
         */
        public void run() {
            for (int i = 0; i < 50; i++) {
                Random random = new Random();
                int ran = random.nextInt(functions.getPlayers().getPlayerList().size());
                randomPlayers[i] = functions.getPlayers().getPlayerList().get(ran);
            }
            try {
                if (teamNumber == 0) {
                    output.format("Starting: Team 1 connected \nWaiting for another team\n"); // Team 1 connects and wait for another one
                    output.flush();
                    gameLock.lock();
                    try {
                        while (suspended) {
                            otherTeamConnected.await();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        gameLock.unlock();
                    }
                    sendMessageToClient1("Starting: Other team connected"); // Other team connected

                } else {
                    sendMessageToClient2("Starting: Team 2 connected"); // Team 2 connected
                    sendMessageToClient1("Stage1:"); // Start asking inputs
                }
                output.flush();
                while (input.hasNextLine() && connected) {
                    String msg = input.nextLine();
                    // Taking care of client 1 inputs
                    if (Soccers[0].input == input) {
                        // Takes care of chatting
                        if (msg.contains("Message:")) {
                            msg = msg.replace("Message: ", "");
                            sendMessageToBoth("Team 1 sent: " + msg);
                        }
                        // Starts the first stage of selecting a team
                        else if (msg.contains("Start:")) {
                            for (int i = 0; i < 5; i++) {
                                Random random = new Random();
                                int ran = random.nextInt(functions.getTeams().getTeamList().size());
                                randomTeams[i] = functions.getTeams().getTeamList().get(ran);
                                sendMessageToClient1("Random Teams: " + i + " " + functions.getTeams().getTeamList().get(ran).getTeam_long_name());
                            }
                            sendMessageToClient1("Choose Team:");
                        }
                        // Check the input received from client about team
                        else if (msg.contains("Event1:")) {
                            msg = msg.replace("Event1: ", "");
                            displayMessage("Team 1 selected " + randomTeams[Integer.parseInt(msg)].getTeam_long_name());
                            sendMessageToBoth("SERVER>>> Team 1 selected " + randomTeams[Integer.parseInt(msg)].getTeam_long_name());
                            home = randomTeams[Integer.parseInt(msg)];
                            sendMessageToClient1("Event1: You selected " + randomTeams[Integer.parseInt(msg)].getTeam_long_name());
                            sendMessageToClient2("Other selected Team 1 selected " + randomTeams[Integer.parseInt(msg)].getTeam_long_name());
                            sendMessageToClient2("Stage1:");
                        }
                        // Start stage 2 of selecting players
                        else if (msg.equals("Select players")) {
                            for (int i = 0; i < 25; i++) {
                                sendMessageToClient1("Random player: " + (i) + " " + randomPlayers[i].getPlayer_name());
                            }
                            sendMessageToClient1("Choose players");
                        }
                        // Take care of what player is selected
                        else if (msg.contains("Event2: ")) {
                            msg = msg.replace("Event2: ", "");
                            displayMessage("Team 1 selected " + randomPlayers[Integer.parseInt(msg)].getPlayer_name());
                            sendMessageToBoth("SERVER>>> Team 1 selected " + randomPlayers[Integer.parseInt(msg)].getPlayer_name());
                            team1Roster[ith] = randomPlayers[Integer.parseInt(msg)];
                            ith++;
                            if (ith == 11) {
                                sendMessageToClient1("Your-Roster: >>>> YOUR TEAM <<<<");
                                printRoster(team1Roster, 0);
                                sendMessageToClient2("Stage2");
                            }
                        }
                        // Take care of input if yes for trade
                        else if (msg.equals("YES-Trade")) {
                            sendMessageToClient2("Trade-Offer: Team 1 would like to trade players. Do you want to trade??");
                        }
                        // Take care of input if no for trade
                        else if (msg.equals("NO-Trade")) {
                            sendMessageToClient2("Trade-Offer: Team 1 doesn't want to trade players");
                            startGame(home, away, team1Roster, team2Roster);
                        }
                        // What player is selected by client1 from client2 to trade
                        else if (msg.contains("T-Selects: ")) {
                            msg = msg.replace("T-Selects: ", "");
                            temp = Integer.parseInt(msg);
                            displayMessage("Team 1 selected player " + team2Roster[temp] + " to trade with Team 2");
                            sendMessageToBoth("SERVER>>> Team 1 selected player " + team2Roster[temp] + " to trade with Team 2");
                            sendMessageToClient2("Team1Roster: <<<< OPPOSITION TEAM >>>>");
                            printRoster(team1Roster, 1);
                            sendMessageToClient2("Trading: Choose a player you want from Team 1");
                        }
                    }
                    // Take care of client2 inputs
                    if (Soccers[1].input == input) {
                        // Take cares of chatting
                        if (msg.contains("Message:")) {
                            msg = msg.replace("Message: ", "");
                            sendMessageToBoth("Team 2 sent: " + msg);
                        }
                        // Starts the stage 1 of selecting a team
                        else if (msg.contains("Start:")) {
                            for (int i = 0; i < 5; i++) {
                                Random random = new Random();
                                int ran = random.nextInt(functions.getTeams().getTeamList().size());
                                randomTeams[i + 5] = functions.getTeams().getTeamList().get(ran);
                                sendMessageToClient2("Random Teams: " + i + " " + functions.getTeams().getTeamList().get(ran).getTeam_long_name());
                            }
                            sendMessageToClient2("Choose Team:");
                        }
                        // checks the input for event 1 selecting a team
                        else if (msg.contains("Event1:")) {
                            msg = msg.replace("Event1: ", "");
                            displayMessage("Team 2 selected " + randomTeams[Integer.parseInt(msg) + 5].getTeam_long_name());
                            sendMessageToBoth("SERVER>>> Team 2 selected " + randomTeams[Integer.parseInt(msg) + 5].getTeam_long_name());
                            away = randomTeams[Integer.parseInt(msg) + 5];
                            sendMessageToClient2("Event1: You selected " + randomTeams[Integer.parseInt(msg) + 5].getTeam_long_name());
                            sendMessageToClient1("Other selected Team 2 selected " + randomTeams[Integer.parseInt(msg) + 5].getTeam_long_name());
                            sendMessageToClient1("Stage2");
                        }
                        // Starts stage 2 of selecting players
                        else if (msg.equals("Select players")) {
                            for (int i = 25; i < 50; i++) {
                                sendMessageToClient2("Random player: " + (i - 25) + " " + randomPlayers[i].getPlayer_name());
                            }
                            sendMessageToClient2("Choose players");
                        }
                        // Take care of selecting players input
                        else if (msg.contains("Event2: ")) {
                            msg = msg.replace("Event2: ", "");
                            displayMessage("Team 2 selected " + randomPlayers[Integer.parseInt(msg) + 25].getPlayer_name());
                            sendMessageToBoth("SERVER>>> Team 2 selected " + randomPlayers[Integer.parseInt(msg) + 25].getPlayer_name());
                            team2Roster[jth] = randomPlayers[Integer.parseInt(msg) + 25];
                            jth++;
                            if (jth == 11) {
                                sendMessageToClient2("Your-Roster: >>>> YOUR TEAM <<<<");
                                printRoster(team2Roster, 1);
                                sendMessageToClient1("Stage3");
                            }
                        }
                        // Take care of trade if input is yes
                        else if (msg.equals("YES-Trade")) {
                            sendMessageToClient1("Accepted: Team 2 would also like to trade a player");
                            sendMessageToClient1("Team2Roster: <<<< OPPOSITION TEAM >>>> ");
                            printRoster(team2Roster, 0);
                            sendMessageToClient1("Trading: Choose a player you want from team 2");
                        }
                        // Take care of trade if input is no
                        else if (msg.equals("NO-Trade")) {
                            sendMessageToClient1("Rejected: Team 2 doesn't want to trade");
                            startGame(home, away, team1Roster, team2Roster);
                        }
                        // Take care of trade event occurs swap players with team 1
                        else if (msg.contains("T-Selects: ")) {
                            msg = msg.replace("T-Selects: ", "");
                            Player temp1 = team1Roster[Integer.parseInt(msg)];
                            displayMessage("Team 2 selected " + temp1.getPlayer_name() + " to trade with Team 1");
                            sendMessageToBoth("SERVER>>> Team 2 selected " + temp1.getPlayer_name() + " to trade with Team 1");
                            team1Roster[Integer.parseInt(msg)] = team2Roster[temp];
                            team2Roster[temp] = temp1;
                            sendMessageToClient1("Your-Roster: >>>> YOUR TEAM <<<<");
                            printRoster(team1Roster, 0);
                            sendMessageToClient2("Your-Roster: >>>> YOUR TEAM <<<<");
                            printRoster(team2Roster, 1);
                            sendMessageToClient1("Stage3");
                        }
                    }
                }
            } finally {
                connected = false;
                closeConnection();
            }
        }


        /**
         * Sends message to both clients
         *
         * @param message string message
         */
        public void sendMessageToBoth(final String message) {
            for (Soccer soccer : Soccers) {
                soccer.output.format(message + "\n");
                soccer.output.flush();
            }
        }

        /**
         * Sends message to the client 1
         *
         * @param message string message
         */
        public void sendMessageToClient1(final String message) {
            Soccers[0].output.format(message + "\n");
            Soccers[0].output.flush();
        }

        /**
         * Sends message to client 2
         *
         * @param message string message
         */
        public void sendMessageToClient2(final String message) {
            Soccers[1].output.format(message + "\n");
            Soccers[1].output.flush();
        }

        /**
         * set weather or not thread suspended
         *
         * @param status
         */
        public void setSuspended(boolean status) {
            suspended = status;
        }

        /**
         * Print players that client selected
         *
         * @param roster  array of players
         * @param teamNum teamNumber to send to
         */
        private void printRoster(Player[] roster, int teamNum) {
            if (teamNum == 0) {
                for (int i = 0; i < roster.length; i++) {
                    sendMessageToClient1("Your-Roster: " + i + " " + roster[i].getPlayer_name());
                }
            } else if (teamNum == 1) {
                for (int i = 0; i < roster.length; i++) {
                    sendMessageToClient2("Your-Roster: " + i + " " + roster[i].getPlayer_name());
                }
            }
        }

        /**
         * Simulates the soccer game
         *
         * @param home  Team-home
         * @param away  Team-away
         * @param team1 Home team players
         * @param team2 Away team players
         */
        private void startGame(Team home, Team away, Player[] team1, Player[] team2) {
            Match match = new Match();
            Random random = new Random();
            match.setCountry_id(functions.getCountries().getCountryList().get(random.nextInt(11)).getId());
            match.setHome_team_name(home.getTeam_long_name());
            match.setAway_team_name(away.getTeam_long_name());
            int homeScore = 0;
            int awayScore = 0;
            for (int i = 0; i < 5; i++) {
                if (i == 0) {
                    sendMessageToBoth("Game: \n<<<<< Simulation started >>>>>");
                    sendMessageToBoth("Game: Quarter 1 started");
                    sendMessageToBoth("Game: Score: " + home.getTeam_long_name() + ": " + homeScore + " " + away.getTeam_long_name() + ": " + awayScore);
                    boolean goal = random.nextBoolean();
                    if (goal) {
                        int team = random.nextInt(2);
                        int numGoal = 1 + random.nextInt(3);
                        for (int j = 0; j < numGoal; j++) {
                            int numPlayer = random.nextInt(11);
                            if (team == 0) {
                                scoringMethod(home, away, team1, team2, team, numPlayer);
                                homeScore++;
                            } else {
                                scoringMethod(home, away, team1, team2, team, numPlayer);
                                awayScore++;
                            }
                        }
                    }
                    sendMessageToBoth("Game: Quarter 1 is over");
                    sendMessageToBoth("Game: Score: " + home.getTeam_long_name() + ": " + homeScore + " " + away.getTeam_long_name() + ": " + awayScore);
                } else if (i == 1) {
                    sendMessageToBoth("Game: Quarter 2 started");
                    sendMessageToBoth("Game: Score: " + home.getTeam_long_name() + ": " + homeScore + " " + away.getTeam_long_name() + ": " + awayScore);
                    boolean goal = random.nextBoolean();
                    if (goal) {
                        int team = random.nextInt(2);
                        int numGoal = 1 + random.nextInt(3);
                        for (int j = 0; j < numGoal; j++) {
                            int numPlayer = random.nextInt(11);
                            if (team == 0) {
                                scoringMethod(home, away, team1, team2, team, numPlayer);
                                homeScore++;
                            } else {
                                scoringMethod(home, away, team1, team2, team, numPlayer);
                                awayScore++;
                            }
                        }
                    }
                    sendMessageToBoth("Game: Quarter 2 is over");
                    sendMessageToBoth("Game: Score: " + home.getTeam_long_name() + ": " + homeScore + " " + away.getTeam_long_name() + ": " + awayScore);
                } else if (i == 2) {
                    sendMessageToBoth("Game: Quarter 3 started");
                    sendMessageToBoth("Game: Score: " + home.getTeam_long_name() + ": " + homeScore + " " + away.getTeam_long_name() + ": " + awayScore);
                    boolean goal = random.nextBoolean();
                    if (goal) {
                        int team = random.nextInt(2);
                        int numGoal = 1 + random.nextInt(3);
                        for (int j = 0; j < numGoal; j++) {
                            int numPlayer = random.nextInt(11);
                            if (team == 0) {
                                scoringMethod(home, away, team1, team2, team, numPlayer);
                                homeScore++;
                            } else {
                                scoringMethod(home, away, team1, team2, team, numPlayer);
                                awayScore++;
                            }
                        }
                    }
                    sendMessageToBoth("Game: Quarter 3 is over");
                    sendMessageToBoth("Game: Score: " + home.getTeam_long_name() + ": " + homeScore + " " + away.getTeam_long_name() + ": " + awayScore);
                } else if (i == 3) {
                    sendMessageToBoth("Game: Final quarter started");
                    sendMessageToBoth("Game: Score: " + home.getTeam_long_name() + ": " + homeScore + " " + away.getTeam_long_name() + ": " + awayScore);
                    boolean goal = random.nextBoolean();
                    if (goal) {
                        int team = random.nextInt(2);
                        int numGoal = 1 + random.nextInt(3);
                        for (int j = 0; j < numGoal; j++) {
                            int numPlayer = random.nextInt(11);
                            if (team == 0) {
                                scoringMethod(home, away, team1, team2, team, numPlayer);
                                homeScore++;
                            } else {
                                scoringMethod(home, away, team1, team2, team, numPlayer);
                                awayScore++;
                            }
                        }
                    }
                    sendMessageToBoth("Game: Final Quarter is over");
                    sendMessageToBoth("Game: Score: " + home.getTeam_long_name() + ": " + homeScore + " " + away.getTeam_long_name() + ": " + awayScore);
                } else {
                    if (homeScore == awayScore) {
                        sendMessageToBoth("Game: Match Tied!! EXTRA TIME");
                        sendMessageToBoth("Game: Score: " + home.getTeam_long_name() + ": " + homeScore + " " + away.getTeam_long_name() + ": " + awayScore);
                        boolean goal = random.nextBoolean();
                        if (goal) {
                            int team = random.nextInt(2);
                            int numPlayer = random.nextInt(11);
                            if (team == 0) {
                                scoringMethod(home, away, team1, team2, team, numPlayer);
                                homeScore++;
                            } else {
                                scoringMethod(home, away, team1, team2, team, numPlayer);
                                awayScore++;
                            }
                        }
                        sendMessageToBoth("Game: Game is over");
                        sendMessageToBoth("Game: Score: " + home.getTeam_long_name() + ": " + homeScore + " " + away.getTeam_long_name() + ": " + awayScore);
                        if (homeScore > awayScore) {
                            sendMessageToBoth("Game: " + home.getTeam_long_name() + " WON!!");
                        } else if (homeScore < awayScore) {
                            sendMessageToBoth("Game: " + away.getTeam_long_name() + " WON!!");
                        } else {
                            sendMessageToBoth("Game: Game resulted in NO RESULT!!");
                        }
                    } else if (homeScore > awayScore) {
                        sendMessageToBoth("Game: " + home.getTeam_long_name() + " WON!!");
                    } else {
                        sendMessageToBoth("Game: " + away.getTeam_long_name() + " WON!!");
                    }
                    match.setHome_team_goal(homeScore);
                    match.setAway_team_goal(awayScore);
                    functions.insertMatch(match);
                }
            }
            sendMessageToBoth("CloseConnection");
            closeConnection();
        }

        /**
         * Add scores to the team and displays message to each clients
         *
         * @param home      Home team
         * @param away      Away team
         * @param team1     Home team players
         * @param team2     Away team players
         * @param team      Team number - who goaled
         * @param numPlayer Team player number - who goaled
         */
        private void scoringMethod(Team home, Team away, Player[] team1, Player[] team2, int team, int numPlayer) {
            if (team == 0) {
                sendMessageToBoth("Game: ***Team " + home.getTeam_long_name() + " scored a goal!!***");
                sendMessageToBoth("Game: ***" + team1[numPlayer].getPlayer_name() + " scored a goal!!***");
                team1[numPlayer].setFantasy_soccer_goal(team1[numPlayer].getFantasy_soccer_goal() + 1);
                functions.updatePlayer(team1[numPlayer]);
            } else {
                sendMessageToBoth("Game: ***Team " + away.getTeam_long_name() + " scored a goal!!***");
                sendMessageToBoth("Game: ***" + team2[numPlayer].getPlayer_name() + " scored a goal!!***");
                team2[numPlayer].setFantasy_soccer_goal(team2[numPlayer].getFantasy_soccer_goal() + 1);
                functions.updatePlayer(team2[numPlayer]);
            }
        }
    }
}






