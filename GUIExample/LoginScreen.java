package GUIExample;
import javax.swing.JOptionPane;
import Model.*;
import javax.swing.*;

public class LoginScreen {
	private Player player;
	
	public LoginScreen(Player player) {
        this.player = player;
    }

    public boolean loginSuccessful() {
        JPanel panel = new JPanel();
        JLabel userLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField usernameField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);

        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        int option = JOptionPane.showOptionDialog(null, panel, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Add logic to validate the credentials
            if (username.equals(player.getLoginName()) && password.equals("password")) {
                return true;
            } else {
            	JOptionPane.showMessageDialog(null, "Login failed. Try again.");
            	GUIExample guiExample = new GUIExample();
        	    guiExample.run();
            }
        }  else if (option == JOptionPane.CANCEL_OPTION) {
        	System.exit(0);
        }

        return false;
    }
}
