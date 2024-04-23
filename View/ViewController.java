package View;


import GUIExample.*;
import Helper.Keyboard;
import Model.Card;
import Model.Dealer;
import Model.Player;

import java.awt.*;
import javax.swing.*;
//all input and output should be done view ViewController
//so that it is easier to implement GUI later
public class ViewController {
	private Player player;
	private Dealer dealer;
	private GameTableFrame app;
	private GUIExample guiExample;
	private GameTablePanel gameTablePanel;
	
	public ViewController(Dealer dealer,Player player, GameTableFrame app) {
		this.dealer = dealer;
		this.player = player;
		this.app = app;
	}
	
	
	//YOU WIN/LOSE
	public void displayConfirmationPopup(String message) {
        Object[] options = {"Yes", "Quit"};
        int choice = JOptionPane.showOptionDialog(
            null,
            message,
            "Confirmation",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
        if (choice == 0) {
            // Yes option selected
        	//Start the game again.
        	GameTablePanel gameTablePanel = new GameTablePanel(dealer, player);
        	gameTablePanel.reset();
        	app.run();
        } else if (choice == 1) {
            // Quit option selected
            displayExitGameGUI();
            System.exit(0);
        }
    }

	//FOLLOW/QUIT OPTION
	// Display the dealer call prompt with options to follow or quit
	public int displayDealerCallPrompt(Player player, int followBet) {
	    Object[] options = { "Follow", "Quit" };
	    int choice = JOptionPane.showOptionDialog(
	        null,
	        "Dealer call, place 10 chips.",
	        "Player Action",
	        JOptionPane.DEFAULT_OPTION,
	        JOptionPane.INFORMATION_MESSAGE,
	        null,
	        options,
	        options[0]
	    );
	    if (choice == 0) {
	        // Follow option selected
	        // Deduct 10 chips from the player and update the follow bet
	        player.deductChips(10);
	        followBet += 10 * 2;
	    } else if (choice == 1) {
	        // Quit option selected
	        // Display the exit game GUI and exit the application
	        displayExitGameGUI();
	        System.exit(0);
	    }
		return followBet;
	}
	
	public int displayPlayerCallBetPrompt(Player player, int playerBet) {
	    int inputBet = 0;
	    boolean validBetAmount = false;
	    
	    String[] options = { "OK", "CANCEL" }; // Add "CANCEL" option
	    
	    while (!validBetAmount) {
	        String input = (String) JOptionPane.showInputDialog(
	            null, "Player call, state bet:", "Bet", JOptionPane.PLAIN_MESSAGE,
	            null, null, "");
	        
	        if (input == null || input.equals("CANCEL")) { // Check if "CANCEL" was selected
	            displayExitGameGUI();
	            System.exit(0);
	        }
	        
	        try {
	            inputBet = Integer.parseInt(input);
	            
	            if (inputBet < 0) {
	                JOptionPane.showMessageDialog(null, "Chips cannot be negative");
	            } else if (inputBet > player.getChips()){
	            	JOptionPane.showMessageDialog(null, "Chips cannot exceed your current chips");
	            }else {
	                // Deduct the entered bet amount from the player's chips and update the player bet
	                player.deductChips(inputBet);
	                playerBet += inputBet * 2;
	                validBetAmount = true;
	            }
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(null, "Invalid input. Please enter an integer.");
	        }
	    }
	    return playerBet;
	}
	
	

	public void displayExitGame() {
		System.out.println("Thank you for playing HighSum game");
	}
	//GUI implementation
	public void displayExitGameGUI() {
        JOptionPane.showMessageDialog(null, "Thank you for playing HighSum game");
    }
	
	
	public void displayBetOntable(int bet) {
		System.out.println("Bet on table : "+bet);
	}
	//GUI implementation
	public void displayBetOntableGUI(int bet) {
        JOptionPane.showMessageDialog(null, "Bet on table: " + bet);
    }
	

	
	public void displayPlayerWin(Player player) {
		System.out.println(player.getLoginName()+" Wins!");
	}
	
	public void displayDealerWin() {
		System.out.println("Dealer Wins!");
	}
	
	public void displayTie() {
		System.out.println("It is a tie!.");
	}
	
	public void displayPlayerQuit() {
		System.out.println("You have quit the current game.");
	}
	
	public void displayPlayerCardsOnHand(Player player) {
		
		System.out.println(player.getLoginName());

		if(player instanceof Dealer) {
			
			for(int i=0;i<player.getCardsOnHand().size();i++) {
				if(i==0) {
					System.out.print("<HIDDEN CARD> ");
				}else {
					System.out.print(player.getCardsOnHand().get(i).toString()+" ");
				}
			}
		}else {
			for(Card card:player.getCardsOnHand()) {
				System.out.print(card+" ");
			}
		}
		System.out.println();
	}
	
	public void displayBlankLine() {
		System.out.println();
	}
	
	public void displayPlayerTotalCardValue(Player player) {
		System.out.println("Value:"+player.getTotalCardsValue());
	}
	
	public void displayDealerDealCardsAndGameRound(int round) {
		System.out.println("Dealer dealing cards - ROUND "+round);
	}
	
	public void displayGameStart() {
		System.out.println("Game starts - Dealer shuffle deck");
	}
	
	public void displayPlayerNameAndChips(Player player) {
		System.out.println(player.getLoginName()+", You have "+player.getChips()+" chips");
	}
	
	public void displayPlayerNameAndLeftOverChips(Player player) {
		System.out.println(player.getLoginName()+", You are left with "+player.getChips()+" chips");
	}
	
	public void displayGameTitle() {
		System.out.println("HighSum GAME");
	}
	
	public void displaySingleLine() {
		for(int i=0;i<30;i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	public void displayDoubleLine() {
		for(int i=0;i<30;i++) {
			System.out.print("=");
		}
		System.out.println();
	}
	
	
	public char getPlayerCallOrQuit() {
		char[] choices = {'c','q'};
		char r = Keyboard.readChar("Do you want to [c]all or [q]uit?:",choices);
		return r;
	}
	
	public char getPlayerFollowOrNot(Player player, int dealerBet) {
		
		boolean validChoice = false;
		char[] choices = {'y','n'};
		
		char r = 'n';
		while(!validChoice) {
			r = Keyboard.readChar("Do you want to follow?",choices);
			//check if player has enff chips to follow
			if(r=='y' && player.getChips()<dealerBet) {
				System.out.println("You do not have enough chips to follow");
				displayPlayerNameAndChips(player);
			}else {
				validChoice = true;
			}
		}
		return r;
	}
	
	public char getPlayerNextGame() {
		char[] choices = {'y','n'};
		char r = Keyboard.readChar("Next game?",choices);
		return r;
	}
	
	public int getPlayerCallBetChip(Player player) {
		boolean validBetAmount = false;
		int chipsToBet = 0;
		while(!validBetAmount) {
			chipsToBet = Keyboard.readInt("Player call, state bet:");
			if(chipsToBet<0) {
				System.out.println("Chips cannot be negative");
			}else if(chipsToBet>player.getChips()) {
				System.out.println("You do not have enough chips");
			}else {
				validBetAmount = true;
			}
		}
		return chipsToBet;
	}
	
	public int getDealerCallBetChips() {
		System.out.println("Dealer call, state bet: 10");
		return 10;
	}
	
	
}
