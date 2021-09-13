import com.team007.model.Player;
import com.team007.model.PlayerAttributes;
import com.team007.model.Team;
import com.team07.SQL.SQLFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class represent the client
 *
 * @see Client
 */
public class Client extends JFrame implements Runnable {
    /**
     * Input from the server
     */
    private Scanner input;
    /**
     * Output to the Server
     */
    private Formatter output;
    /**
     * Stores IP address
     */
    private final String address;
    /**
     * Socket connection
     */
    private Socket connection;
    /**
     * Client GUI display
     */
    private final JTextArea displayArea;
    /**
     * Client GUI input field
     */
    private JTextField inputArea;
    /**
     * Client GUI chat window
     */
    private final JTextArea chatArea;
    /**
     * Client GUI chat input field
     */
    private JTextField chatInput;
    /**
     * Helper to identify event
     */
    private int helper;
    /**
     * Team button to look up team info in database
     */
    private final JButton teamButton;
    /**
     * Player button to look up player info in database
     */
    private final JButton playerButton;
    /**
     * Holds connection status
     */
    private boolean connected;

    /**
     * Constructor Client to initialize GUI and client
     * @param address IP
     */
    public Client(String address) {
        super("Client");
        Fields field = new Fields();
        setResizable(false);
        displayArea = new JTextArea();
        chatArea = new JTextArea();
        displayArea.setEditable(false);
        chatArea.setEditable(false);
        chatArea.setText("CHAT ROOM\n");
        displayArea.setBackground(Color.CYAN);
        chatArea.setBackground(Color.GREEN);
        displayArea.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
        chatArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        displayArea.setPreferredSize(new Dimension(350, 9999));
        chatArea.setPreferredSize(new Dimension(350, 9999));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        teamButton = new JButton("TEAM");
        playerButton = new JButton("PLAYER");
        teamButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        String team = JOptionPane.showInputDialog(null, "Give me a TEAM name:");
                        chatMessage(teamInfo(team));
                    }
                });
        playerButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        String player = JOptionPane.showInputDialog(null, "Give me a PLAYER name:");
                        chatMessage(playerInfo(player));
                    }
                }
        );
        buttonPanel.add(teamButton);
        buttonPanel.add(playerButton);
        add(buttonPanel, BorderLayout.LINE_END);
        add(new JScrollPane(displayArea), BorderLayout.LINE_START);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);
        add(field, BorderLayout.SOUTH);
        setSize(800, 600);
        setVisible(true);
        this.address = address;
    }

    /**
     * Displays the message in the GUI display area
     *
     * @param messageToDisplay string message
     */
    private void displayMessage(final String messageToDisplay) {
        SwingUtilities.invokeLater(
                () -> displayArea.append(messageToDisplay + "\n")
        );
    }

    /**
     * Displays the message in the GUI chat area
     *
     * @param message string message
     */
    private void chatMessage(final String message) {
        SwingUtilities.invokeLater(
                () -> chatArea.append(message + "\n")
        );
    }

    /**
     * Sets up the client input, output and service
     */
    public void startClient() {
        try {
            connection = new Socket(InetAddress.getByName(address), 23511);
            output = new Formatter(connection.getOutputStream());
            input = new Scanner(connection.getInputStream());
            connected = true;
            displayMessage("Connected to Server!!\nWait for data to load....");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExecutorService worker = Executors.newFixedThreadPool(2);
        worker.execute(this);
    }

    /**
     * Closes connection to server
     */
    private void closeConnection() {
        displayMessage("Connection Closed");
        try {
            connected = false;
            output.close();
            input.close();
            connection.close();
            inputArea.setText("THE END");
            inputArea.setEditable(false);
            chatInput.setText("THE END");
            chatInput.setEditable(false);
            teamButton.setEnabled(false);
            playerButton.setEnabled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runnable to listen to input from server
     */
    @Override
    public void run() {
        if (connected) {
            while (input.hasNextLine() && connected) {
                processMessage(input.nextLine());
            }
        }
    }

    /**
     * Processes the message received from server
     *
     * @param message string message
     */
    private void processMessage(String message) {
        // Server feedback into chat
        if (message.contains("SERVER>>>")) {
            chatMessage(message);
        }
        // Team chatting
        else if (message.contains("Team 1 sent: ") || message.contains("Team 2 sent: ")) {
            message = message.replace(" sent: ", ": ");
            chatMessage(message);
        }
        // initial start
        else if (message.contains("Starting: ")) {
            message = message.replace("Starting: ", "");
            displayMessage(message);
        }
        // Stage 1 starting
        else if (message.equals("Stage1:")) {
            sendToServer("Start:");
        }
        // Random teams selection
        else if (message.contains("Random Teams: ")) {
            message = message.replace("Random Teams: ", "");
            displayMessage(message);
        }
        // Selection of team
        else if (message.equals("Choose Team:")) {
            displayMessage("Choose between 0-4:");
            inputArea.setText("");
            inputArea.setEditable(true);
            helper = 0;
        }
        // what selected by other client
        else if (message.contains("Other selected ")) {
            message = message.replace("Other selected ", "");
            displayMessage(message);
        }
        // Event 1
        else if (message.contains("Event1:")) {
            message = message.replace("Event1: ", "");
            displayMessage(message);
        }
        // Starting of stage 2
        else if (message.equals("Stage2")) {
            sendToServer("Select players");
        }
        // Received random players
        else if (message.contains("Random player: ")) {
            message = message.replace("Random player: ", "");
            displayMessage(message);
        }
        // Selecting player for a team
        else if (message.equals("Choose players")) {
            displayMessage("Select 11 player for your team\nChoose between 0-24");
            inputArea.setText("");
            inputArea.setEditable(true);
            helper = 1;
        }
        // Client team roster
        else if (message.contains("Your-Roster: ")) {
            message = message.replace("Your-Roster: ", "");
            displayMessage(message);
        }
        // Starts the stage 3
        else if (message.equals("Stage3")) {
            displayMessage("\nSERVER >>> Do you want to trade players with TEAM 2??");
            displayMessage("0: YES\n1: NO");
            helper = 2;
            inputArea.setText("");
            inputArea.setEditable(true);
        }
        // Trade offer is being made by one of the client
        else if (message.equals("Trade-Offer: Team 1 would like to trade players. Do you want to trade??")) {
            message = message.replace("Trade-Offer: ", "");
            displayMessage("\nSERVER >>> " + message);
            displayMessage("0: YES\n1: NO");
            helper = 2;
            inputArea.setText("");
            inputArea.setEditable(true);
        }
        // Any other trade offer missed
        else if (message.contains("Trade-Offer:")) {
            message = message.replace("Trade-Offer: ", "");
            displayMessage(message);
        }
        // Trade offer accepted
        else if (message.contains("Accepted:")) {
            message = message.replace("Accepted: ", "");
            displayMessage(message);
        }
        // Trade offer rejected
        else if (message.contains("Rejected: ")) {
            message = message.replace("Rejected: ", "");
            displayMessage(message);
        }
        // Team 2 roster
        else if (message.contains("Team2Roster: ")) {
            message = message.replace("Team2Roster: ", "");
            displayMessage(message);
        }
        // Team 1 roster
        else if (message.contains("Team1Roster: ")) {
            message = message.replace("Team1Roster: ", "");
            displayMessage(message);
        }
        // Trading occurs if both agrees
        else if (message.contains("Trading: ")) {
            message = message.replace("Trading: ", "");
            displayMessage("\nSERVER>>>" + message);
            inputArea.setText("");
            inputArea.setEditable(true);
            helper = 3;
        } else if (message.contains("Game:")) {
            message = message.replace("Game: ", "");
            displayMessage(message);
        } else if (message.equals("CloseConnection")) {
            closeConnection();
        }
    }

    /**
     * Sends message to server
     *
     * @param msg string message
     */
    private void sendToServer(String msg) {
        output.format(msg + "\n");
        output.flush();
    }

    /**
     * Returns team information
     * @param teamName String
     * @return Returns team information
     */
    public String teamInfo(String teamName) {
        String result = "";
        SQLFunctions function = new SQLFunctions();
        ArrayList<Team> team  = function.getTeams().getTeamList();
        for (int i =0; i< team.size(); i++){
            if(teamName.equals(team.get(i).getTeam_long_name())){
                result = team.get(i).toString();
                break;
            }
            else{
                result = "Team Not Found!!";
            }
        }
        return result;
    }


    /**
     * Return player information
     * @param playerName String
     * @return Returns player information
     */
    public String playerInfo(String playerName ){
        String result = "";
        int id = 0;
        SQLFunctions function = new SQLFunctions();
        ArrayList<Player> player = function.getPlayers().getPlayerList();
        ArrayList<PlayerAttributes> playerAttributes = function.getPlayerAttributes().getPlayerAttributesArrayList();
        for(int i = 0; i < player.size(); i++){
            if(playerName.equals(player.get(i).getPlayer_name())){
                id = player.get(i).getPlayer_api_id();
                break;
            }else{
                result = "Player Not Found";
            }
        }

        for(int i =0; i <playerAttributes.size(); i++){
            if(id == playerAttributes.get(i).getPlayer_api_id()){
                result = playerName + "\n" + playerAttributes.get(i).toString();
                break;
            }
        }
        return result;
    }

    /**
     * Class to add input fields to GUI
     *
     * @see Fields
     */
    private class Fields extends JPanel implements ActionListener {
        /**
         * Constructor to create two input fields
         */
        public Fields() {
            inputArea = new JTextField(32);
            inputArea.setBackground(Color.cyan);
            inputArea.setEditable(false);
            inputArea.addActionListener(this);
            JPanel inputsDesign = new JPanel();
            inputsDesign.setLayout(new FlowLayout(FlowLayout.LEFT));
            chatInput = new JTextField(32);
            chatInput.setBackground(Color.green);
            chatInput.addActionListener(this);
            add(inputsDesign, BorderLayout.SOUTH);
            inputsDesign.add(new JLabel("UI Input:"));
            inputsDesign.add(inputArea);
            inputsDesign.add(new JLabel("Chat Input:"));
            inputsDesign.add(chatInput);
            setVisible(true);
        }

        /**
         * Keep track of player already selected
         */
        private final int[] tracker = {25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25};
        /**
         * Keep track of how many player already selected
         */
        private int i = 0;

        private boolean checker(int in) {
            for (int value : tracker) {
                if (value == in) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Register the input given from JTextFields
         *
         * @param actionEvent event
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == inputArea) {
                int choice = Integer.parseInt(actionEvent.getActionCommand());
                // Selecting team
                if (helper == 0) {
                    if (choice >= 0 && choice < 5) {
                        sendToServer("Event1: " + choice);
                        inputArea.setEditable(false);
                        inputArea.setText("Wait for next input");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid input");
                        inputArea.setText("");
                    }
                }
                // selecting players
                else if (helper == 1) {
                    if (!checker(choice)) {
                        if ((choice >= 0) && (choice <= 24)) {
                            sendToServer("Event2: " + choice);
                            tracker[i] = choice;
                            i++;
                            inputArea.setText("");
                            if (i == 11) {
                                inputArea.setEditable(false);
                                inputArea.setText("Wait for next input");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid input");
                            inputArea.setText("");
                        }
                    } else if (checker(choice)) {
                        JOptionPane.showMessageDialog(null, "You already selected this player");
                        inputArea.setText("");
                    }

                }
                // Trading inputs
                else if (helper == 2) {
                    if (choice == 0) {
                        sendToServer("YES-Trade");
                        inputArea.setEditable(false);
                    } else if (choice == 1) {
                        sendToServer("NO-Trade");
                        inputArea.setEditable(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid input");
                    }
                    inputArea.setText("Wait for next input");
                }
                // Trading player selection inputs
                else if (helper == 3) {
                    if (choice >= 0 && choice <= 11) {
                        sendToServer("T-Selects: " + choice);
                        inputArea.setEditable(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid input");
                        inputArea.setText("");
                    }
                    inputArea.setText("Wait for next input");
                }
            }
            // Chat field inputs
            else if (actionEvent.getSource() == chatInput) {
                String msg = actionEvent.getActionCommand();
                sendToServer("Message: " + msg);
                chatInput.setText("");
            }
        }
    }
}
