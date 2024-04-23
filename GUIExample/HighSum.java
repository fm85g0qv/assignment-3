package GUIExample;
import Model.*;
import Controller.*;
import View.*;

public class HighSum {

	private Dealer dealer;
	private Player player;
	private ViewController view;
    private GameController gc;
    private GameTableFrame gameTableframe;
    
    public HighSum() {
    	//create all the required objects
    	this.dealer = new Dealer();
        this.player = new Player("IcePeak","password",50);
        this.view = new ViewController(dealer, player, gameTableframe);
        //bring them together
    	this.gc = new GameController(this.dealer,this.player,this.view);
    }
	
    public void run() {
    	//starts the game!
    	gc.run();
    }
    
	public static void main(String[] args) {
	    new HighSum().run();
	}

}
