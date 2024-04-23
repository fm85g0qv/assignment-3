package GUIExample;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Model.*;
import View.ViewController;

public class GameTableFrame extends JFrame{
	 
    private GameTablePanel gameTablePanel;
    private Dealer dealer;
    private Player player;
	private ViewController view;
    private int count=0;
    
    public GameTableFrame(Dealer dealer, Player player)
    {
        this.dealer = dealer;
        this.player = player;
        this.view = new ViewController(dealer, player, this);
        gameTablePanel = new GameTablePanel(dealer,player);
        this.count=0;
        
        setTitle("HIGHSUM");
        add(gameTablePanel);
        pack();
        setVisible(true);
    }
    
    public void setViewController(ViewController viewController) {
        this.view = viewController;
    }
    
    public void updateGameTable()
    {
        gameTablePanel.repaint();
    }
   

    public void run() {
        //Round 1
    	player.resetChips();
    	player.clearCardsOnHand();
    	dealer.clearCardsOnHand();
    	dealer.shuffleCards();
    	gameTablePanel.reset();
        gameTablePanel.displayGifForThreeSeconds();
        gameTablePanel.displayDeckImage("Deck");
        gameTablePanel.displayCurrentRound("ROUND 1");
    	gameTablePanel.displayChips();
    	gameTablePanel.displayNames();
    	int tableBet = 0;
    	gameTablePanel.setTableChipsText("Table bet: " + tableBet);
    	gameTablePanel.displayPlayerChips("Balance Chips: " + player.getChips());
    	for(int i=0;i<2;i++) {    
    		dealer.dealCardTo(dealer);
        	dealer.dealCardTo(player);
        	pause();
        	gameTablePanel.playerHandValue("Value: " + player.getTotalCardsValue());
        	updateGameTable();
    	}
    	pause();
    	if (dealer.getLastCard().getRank() > player.getLastCard().getRank()) {
    		tableBet = view.displayDealerCallPrompt(player, tableBet);
    		gameTablePanel.setTableChipsText("Table bet: " + tableBet);
    		gameTablePanel.displayPlayerChips("Balance Chips: " + player.getChips());
    		updateGameTable();
    	} else {
    		tableBet = view.displayPlayerCallBetPrompt(player, tableBet);
    		gameTablePanel.setTableChipsText("Table bet: " + tableBet);
    		gameTablePanel.displayPlayerChips("Balance Chips: " + player.getChips());
    		updateGameTable();
    	}
    	updateGameTable();
    	//Round 2
    	gameTablePanel.displayCurrentRound("ROUND 2");
    	dealer.dealCardTo(dealer);
    	dealer.dealCardTo(player);
    	pause();
    	gameTablePanel.playerHandValue("Value: " + player.getTotalCardsValue());
    	updateGameTable();
    	if (dealer.getLastCard().getRank() > player.getLastCard().getRank()) {
    		tableBet = view.displayDealerCallPrompt(player, tableBet);
    		gameTablePanel.setTableChipsText("Table bet: " + tableBet);
    		gameTablePanel.displayPlayerChips("Balance Chips: " + player.getChips());
    		updateGameTable();
    	} else {
    		tableBet = view.displayPlayerCallBetPrompt(player, tableBet);
    		gameTablePanel.setTableChipsText("Table bet: " + tableBet);
    		gameTablePanel.displayPlayerChips("Balance Chips: " + player.getChips());
    		updateGameTable();
    	}
    	//Round 3
    	gameTablePanel.displayCurrentRound("ROUND 3");
    	dealer.dealCardTo(dealer);
    	dealer.dealCardTo(player);
    	pause();
    	gameTablePanel.playerHandValue("Value: " + player.getTotalCardsValue());
    	updateGameTable();
    	if (dealer.getLastCard().getRank() > player.getLastCard().getRank()) {
    		tableBet = view.displayDealerCallPrompt(player, tableBet);
    		gameTablePanel.setTableChipsText("Table bet: " + tableBet);
    		gameTablePanel.displayPlayerChips("Balance Chips: " + player.getChips());
    		updateGameTable();
    	} else {
    		tableBet = view.displayPlayerCallBetPrompt(player, tableBet);
    		gameTablePanel.setTableChipsText("Table bet: " + tableBet);
    		gameTablePanel.displayPlayerChips("Balance Chips: " + player.getChips());
    		updateGameTable();
    	}
    	//Round 4
    	gameTablePanel.displayCurrentRound("ROUND 4");
    	dealer.dealCardTo(dealer);
    	dealer.dealCardTo(player);
    	pause();
    	gameTablePanel.playerHandValue("Value: " + player.getTotalCardsValue());
    	updateGameTable();
    	if (dealer.getLastCard().getRank() > player.getLastCard().getRank()) {
    		tableBet = view.displayDealerCallPrompt(player, tableBet);
    		gameTablePanel.setTableChipsText("Table bet: " + tableBet);
    		gameTablePanel.displayPlayerChips("Balance Chips: " + player.getChips());
    		updateGameTable();
    	} else {
    		tableBet = view.displayPlayerCallBetPrompt(player, tableBet);
    		gameTablePanel.setTableChipsText("Table bet: " + tableBet);
    		gameTablePanel.displayPlayerChips("Balance Chips: " + player.getChips());
    		updateGameTable();
    	}
    	//Round 5 - Show all cards
    	gameTablePanel.displayCurrentRound("SHOW CARDS");
    	gameTablePanel.showAllCards();
    	if (dealer.getTotalCardsValue() > player.getTotalCardsValue()) {
    		//logic YOU LOSE
    		view.displayConfirmationPopup("YOU LOSE, Play Again?");
    		dealer.addCardsBackToDeck(player.getCardsOnHand());
        	dealer.addCardsBackToDeck(dealer.getCardsOnHand());
    	} else if (player.getTotalCardsValue() > dealer.getTotalCardsValue()) {
    		//logic YOU WIN
    		int total = player.getChips() + tableBet;
    		gameTablePanel.displayPlayerChips("NEW Balance Chips: " + total);
    		tableBet = 0;
    		gameTablePanel.setTableChipsText("Table bet: " + tableBet);
    		pause();
    		view.displayConfirmationPopup("YOU WIN, Play Again?");
    		dealer.addCardsBackToDeck(player.getCardsOnHand());
        	dealer.addCardsBackToDeck(dealer.getCardsOnHand());
    	}
    	updateGameTable();
    }
    
    //pause for 500msec
    private void pause() {
    	 try{
             Thread.sleep(500);
             
          }catch(Exception e){}
    }
    
}
