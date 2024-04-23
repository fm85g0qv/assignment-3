package GUIExample;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.*;
import View.ViewController;

public class GameTablePanel extends JPanel {
	
	private ViewController view;
	private Player player;
	private Dealer dealer;
	private ImageIcon cardBackImage;
	private ImageIcon chipsImage;
	private boolean chipsLoaded;
	private String tableChipsText;
	private boolean namesLoaded;
	private String dealerName;
	private String playerName;
	private boolean pHandLoaded;
	private String pHandValue;
	private boolean pChips;
	private String playerChips;
	private boolean finalRound;
	private boolean dealerTotalHandLoaded;
	private String dealerTotalValue;
	private boolean showAllCards;
	private boolean displayCurrentRound;
	private String currentRound;
	private boolean deckImage;
	private ImageIcon deck;
	private String deckLabel;

	
	//loading screen
	private ImageIcon gifImage;
    private boolean displayGif;
    private String gifText;
    

	public GameTablePanel(Dealer dealer, Player player) {

		setBackground(new Color(0, 128, 0));
		setPreferredSize(new Dimension(1024, 768));
		deck = new ImageIcon("images/deck.png");
		cardBackImage = new ImageIcon("images/back.png");
        gifImage = new ImageIcon("images/loading.gif");
        chipsImage = new ImageIcon("images/chips.jpg");
		this.dealer = dealer;
		this.player = player;
        gifText = "Shuffling...";
        tableChipsText = "";
        dealerName = "Dealer";
        playerName = player.getLoginName();
        pHandValue = "";
        playerChips = "";
        deckLabel = "";
//        dealerTotalValue = "";
        showAllCards = false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//display a deck image
		if (deckImage) {
			int deckX = getWidth() - (getWidth() / 6);
            int deckY = getHeight() / 2 - 50;
            deck.paintIcon(this, g, deckX, deckY);
            Font font = new Font("Arial", Font.BOLD, 20);
		    g.setFont(font);
		    FontMetrics fontMetrics = g.getFontMetrics();
            g.drawString(deckLabel, deckX + 50, deckY + 137);
		}
		
		//display CURRENT ROUND
		if (displayCurrentRound) {
			int x = getWidth() / 2;
			int y = getHeight() / 2;
			Font font = new Font("Arial", Font.BOLD, 40);
		    g.setFont(font);
		    FontMetrics fontMetrics = g.getFontMetrics();
		    g.drawString(currentRound, x, y);
		}
		
		//load player's remaining chips
		if (pChips) {
			int x = 500;
			int y = 530;
			Font font = new Font("Arial", Font.BOLD, 20);
		    g.setFont(font);
		    FontMetrics fontMetrics = g.getFontMetrics();
		    g.drawString(playerChips, x, y);
		}
		
		
		//load player's hand value
		if (pHandLoaded) {
			int x = 400;
			int y = 530;
			Font font = new Font("Arial", Font.BOLD, 20);
		    g.setFont(font);
		    FontMetrics fontMetrics = g.getFontMetrics();
		    g.drawString(pHandValue, x, y);
		}
		
		//load Dealer and Player names
		if (namesLoaded) {
		    int namesx = 250;
		    int namesy = 300;
		    Font font = new Font("Arial", Font.BOLD, 20);
		    g.setFont(font);
		    FontMetrics fontMetrics = g.getFontMetrics();
		    g.drawString(dealerName, namesx, namesy);
		    g.drawString(playerName, namesx, namesy + 230);
		}

		
		
		//load chips image
		if (chipsLoaded) {
		    int chipsx = 50;
		    int chipsy = (getHeight() / 2) - 60;
		    chipsImage.paintIcon(this, g, chipsx, chipsy);

		    // Draw chips text
		    Font font = new Font("Arial", Font.BOLD, 20);
		    g.setFont(font);
		    FontMetrics fontMetrics = g.getFontMetrics();
		    int textWidth = fontMetrics.stringWidth(tableChipsText);
		    int textX = (textWidth + chipsx)-textWidth;
		    int textY = chipsy + chipsImage.getIconHeight() + 30; // Adjust the vertical position of the text
		    g.drawString(tableChipsText, textX, textY);
		}

		
		//loading dealer card images
		int x = 50;
		int y = 70;

		int i = 0;
		for (Card c : dealer.getCardsOnHand()) {
			// display dealer cards
//			finalRound = true;
//			dealerTotalHandLoaded = false;
			if (i == 0d) {
				cardBackImage.paintIcon(this, g, x, y);
				i++;

			}  
//				else if (!finalRound) {
//				c.paintIcon(this, g, x, y);
//				if (dealerTotalHandLoaded) {
//					int dealerValueX = 350;
//					int dealerValueY = 300;
//					Font font = new Font("Arial", Font.BOLD, 20);
//				    g.setFont(font);
//				    FontMetrics fontMetrics = g.getFontMetrics();		    g.drawString(dealerTotalValue, dealerValueX, dealerValueY);
//				}
//			} 
				else {
				c.paintIcon(this, g, x, y);
			}

			x += 200;
		}

		// display player cards
		x = 50;
		y = 550;

		for (Card c : player.getCardsOnHand()) {
			// display player cards
			c.paintIcon(this, g, x, y);
			x += 200;
		}
		
		if (showAllCards) {
			// Show all dealer cards and their total value
	        x = 51;
	        y = 70;

	        for (Card c : dealer.getCardsOnHand()) {
	            c.paintIcon(this, g, x, y);
	            x += 200;
		}
	        int dealerValueX = 350;
	        int dealerValueY = 300;
	        Font font = new Font("Arial", Font.BOLD, 20);
	        g.setFont(font);
	        FontMetrics fontMetrics = g.getFontMetrics();
	        g.drawString("Value: " + dealer.getTotalCardsValue(), dealerValueX, dealerValueY);
	        }
		
		
		if (displayGif) {
            int gifX = (getWidth() - gifImage.getIconWidth()) / 2;
            int gifY = (getHeight() - gifImage.getIconHeight()) / 2;
            gifImage.paintIcon(this, g, gifX, gifY);

            // Add text under the GIF
            Font font = new Font("Arial", Font.BOLD, 20);
            g.setFont(font);
            FontMetrics fontMetrics = g.getFontMetrics();
            int textWidth = fontMetrics.stringWidth(gifText);
            int textX = (getWidth() - textWidth) / 2;
            int textY = gifY + gifImage.getIconHeight() + 30; // Adjust the vertical position of the text
            g.drawString(gifText, textX, textY);
        }
	}
	
	public void displayGifForThreeSeconds() {
        displayGif = true;
        repaint();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        displayGif = false;
        repaint();
    }
	
	public void displayChips() {
		//chips
		chipsLoaded = true;
		repaint();
	}
	
	public void setTableChipsText(String text) {
	    tableChipsText = text;
	    repaint();
	}
	
	public void displayNames() {
		namesLoaded = true;
		repaint();
	}
	
	public void playerHandValue(String text) {
		pHandLoaded = true;
		pHandValue = text;
		repaint();
	}
	
	public void displayPlayerChips(String text) {
		pChips = true;
		playerChips = text;
		repaint();
	}
	
	public void finalRoundShowAllDealerCards(String text) {
		finalRound = false;
		dealerTotalHandLoaded = true;
		dealerTotalValue = text;
        repaint();
	}
	
	public void showAllCards() {
        showAllCards = true;
        repaint();
    }
	
	public void displayCurrentRound(String text) {
		displayCurrentRound = true;
		currentRound = text;
		repaint();
	}
	
	public void reset() {
        displayCurrentRound = false;
        displayGif = false;
        chipsLoaded = false;
        namesLoaded = false;
        pHandLoaded = false;
        pChips = false;
        finalRound = false;
        dealerTotalHandLoaded = false;
        showAllCards = false;
        deckImage = false;
        
        gifText = "Shuffling...";
        tableChipsText = "";
        dealerName = "Dealer";
        playerName = player.getLoginName();
        pHandValue = "";
        playerChips = "";
        currentRound = "";

        repaint();
    }
	
	public void displayDeckImage(String text) {
		deckImage = true;
		deckLabel = text;
		repaint();
	}
}
