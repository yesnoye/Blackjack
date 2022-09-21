import acm.program.GraphicsProgram;
import acm.graphics.GLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Optional;

import svu.csc213.*;
import svu.csc213.Dialog;

public class Blackjack extends GraphicsProgram{

    private int wager = 0;
    private int balance = 10000;
    private int bank = 10000;



    private GLabel bankLabel, wagerLabel, balanceLabel;

    private JButton wagerButton;
    private JButton playButton;
    private JButton hitButton;
    private JButton stayButton;
    private JButton quitButton;



    private GHand dealer;
    private GHand playerHand;
    private Deck deck;


    @Override
    public void init(){
        this.setBackground(Color.GRAY);

        deck = new Deck();

        wagerButton = new JButton("Wager");
        playButton = new JButton("Play");
        hitButton = new JButton("Hit");
        stayButton = new JButton("Stay");
        quitButton = new JButton("Quit");

        add(playButton, SOUTH);
        add(wagerButton, SOUTH);
        add(hitButton, SOUTH);
        add(stayButton, SOUTH);
        add(quitButton, SOUTH);

        hitButton.setVisible(false);
        stayButton.setVisible(false);
        wagerButton.setVisible(true);

        addActionListeners();

        bankLabel = new GLabel("Bank: " + bank);
        balanceLabel = new GLabel("Balance: " + balance);
        wagerLabel = new GLabel("Wager: " + wager);

        add(bankLabel, 10,20);
        add(balanceLabel, 10, 40);
        add(wagerLabel, 10, 60);


    }

    public void actionPerformed(ActionEvent ae){
        switch (ae.getActionCommand()){
            case"Wager":
                wager();
                break;
            case "Play":
                play();
                break;
            case "Hit":
                hit();
                break;
            case "Stay":
                stay();
                break;
            case "Quit":
                System.exit(0);
                break;
            default: System.out.println("I do not recognize this Commad, chek button");

        }
    }

    public void wager(){
        wager = Dialog.getInteger("How much bruaw");
        if(wager > balance){
            wager();
        }
        hitButton.setVisible(true);
        stayButton.setVisible(true);

    }

    public void play(){

        playerHand = new GHand(new Hand(deck, false));
        add(playerHand, 100,250);



        wagerButton.setVisible(true);
        playButton.setVisible(false);
        deck.shuffle();
    }

    public void hit(){

        playerHand.hit();
        System.out.println(playerHand.getTotal());
    }

    public void stay(){


        dealer.hit();
        System.out.println(dealer.getTotal());
    }

    private void checkWin(){
        if(playerHand.getTotal()> dealer.getTotal()){
            System.out.println("winna");
        }
    }





    @Override
    public void run(){
        GHand hand = new GHand(new Hand(deck, true));
        add(hand, 100,100);
        hand.hit();

    }




    public static void main(String[] args) {
        new Blackjack().start();


    }


}
