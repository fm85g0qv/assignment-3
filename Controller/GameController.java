package Controller;
import Model.Dealer;
import Model.Player;
import View.ViewController;

public class GameController {

	private Dealer dealer;
	private Player player;
	private ViewController view;
	private int chipsOnTable;
	
	public GameController(Dealer dealer,Player player,ViewController view) {
		this.dealer = dealer;
		this.player = player;
		this.view = view;
		this.chipsOnTable = 0;
	}
	
	public void run() {
		
		boolean carryOn= true;
		
		while(carryOn) {
			runOneRound();
			char r = this.view.getPlayerNextGame();
			if(r=='n') {
				carryOn = false;
			}
		}
		this.view.displayPlayerNameAndLeftOverChips(this.player);
		this.view.displayExitGame();
	}
	
	public void runOneRound() {
		
		this.view.displayGameTitle();
		this.view.displayDoubleLine();
		this.view.displayPlayerNameAndChips(this.player);
		this.view.displaySingleLine();
		this.view.displayGameStart();
		this.view.displaySingleLine();
		this.dealer.shuffleCards();
		this.chipsOnTable = 0;
		boolean playerQuit = false;
				
		for(int round = 1;round<=4;round++) {
			this.view.displaySingleLine();
			this.view.displayDealerDealCardsAndGameRound(round);
			this.view.displaySingleLine();

			if(round==1) {//round 1 deal extra card
				this.dealer.dealCardTo(this.player);
				this.dealer.dealCardTo(this.dealer);
			}
			this.dealer.dealCardTo(this.player);
			this.dealer.dealCardTo(this.dealer);
			
			this.view.displayPlayerCardsOnHand(this.dealer);
			this.view.displayBlankLine();
			this.view.displayPlayerCardsOnHand(player);
			this.view.displayPlayerTotalCardValue(player);

			int whoCanCall = this.dealer.determineWhichCardRankHigher(dealer.getLastCard(), player.getLastCard());
			
			if(whoCanCall==1) {//dealer call
				int chipsToBet = this.view. getDealerCallBetChips();
				//ask player want to follow?
				char r = this.view.getPlayerFollowOrNot(this.player,chipsToBet);
				if(r=='y') {
					this.player.deductChips(chipsToBet);
					this.chipsOnTable+=2*chipsToBet;	
					this.view.displayPlayerNameAndLeftOverChips(this.player);
					this.view.displayBetOntable(this.chipsOnTable);
				}else {
					playerQuit = true;
					break;
				}
			}else {//player call
				if(round==1) {//round 1 player cannot quit
					int chipsToBet = view.getPlayerCallBetChip(this.player);
					this.player.deductChips(chipsToBet);
					this.chipsOnTable+=2*chipsToBet;
					this.view.displayBetOntable(this.chipsOnTable);
				}else {
					char r = this.view.getPlayerCallOrQuit();
					if(r=='c') {
						int chipsToBet = view.getPlayerCallBetChip(this.player);
						this.player.deductChips(chipsToBet);
						this.chipsOnTable+=2*chipsToBet;
						this.view.displayPlayerNameAndLeftOverChips(this.player);
						this.view.displayBetOntable(this.chipsOnTable);
					}else {
						playerQuit = true;
						break;
					}
				}
			}
		}
		
		//check who win
		if(playerQuit) {
			this.view.displayPlayerNameAndLeftOverChips(this.player);
			this.view.displayPlayerQuit();
		}
		else if(this.player.getTotalCardsValue()>this.dealer.getTotalCardsValue()) {
			this.view.displayPlayerWin(this.player);
			this.player.addChips(chipsOnTable);
			this.chipsOnTable=0;
			this.view.displayPlayerNameAndLeftOverChips(this.player);
		}else if(this.player.getTotalCardsValue()<this.dealer.getTotalCardsValue()) {
			this.view.displayDealerWin();
			this.view.displayPlayerNameAndLeftOverChips(this.player);
		}else {
			this.view.displayTie();
			this.player.addChips(chipsOnTable/2);
			this.view.displayPlayerNameAndLeftOverChips(this.player);
		}
		
		//put all the cards back to the deck
		dealer.addCardsBackToDeck(dealer.getCardsOnHand());
		dealer.addCardsBackToDeck(player.getCardsOnHand());
		dealer.clearCardsOnHand();
		player.clearCardsOnHand();	
	}
	
	
}
