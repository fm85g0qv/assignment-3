package GUIExample;
import javax.swing.JOptionPane;

import View.ViewController;
import javax.swing.JOptionPane;

import Model.*;

public class GUIExample {

	private Dealer dealer;
	private Player player;
	public GameTableFrame app;
	private LoginScreen login;
	private ViewController viewController;
	
	 //testing of game table UI
	public GUIExample() {
       player = new Player("user","password",100);
       dealer = new Dealer();
	}
	
	public void run() {
        login = new LoginScreen(player);
        if (login.loginSuccessful()) {
            dealer.shuffleCards();
            app = new GameTableFrame(dealer, player);
            app.run();
        } else {
            // Handle login failure
            JOptionPane.showMessageDialog(null, "Login failed. Try again.");
            login.loginSuccessful();
        }
    }
	
	public static void main(String[] args) {
	    GUIExample guiExample = new GUIExample();
	    guiExample.run();
	    ViewController viewController = new ViewController(guiExample.dealer, guiExample.player, guiExample.app);
	    guiExample.app.setViewController(viewController); // Set the ViewController in GameTableFrame
	}

}
