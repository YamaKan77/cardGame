package cardGame;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class HigherCard {
	
	public enum Rotation{
		CLOCKWISE, COUNTER;
	}
	
	public static int remaining(boolean w, boolean x, boolean y, boolean z)
	{
		int numLeft = 0;
		if(!w && !x && !y && !z)
		{
			numLeft = 4;
		}
		if((w && !x && !y && !z) || (!w && x && !y && !z) || 
				(!w && !x && y && !z) || (!w && !x && !y && z))
		{
			numLeft = 3;
		}
		if((!w && !x && y && z) || (!w && x && !y && z) || 
				(!w && x && y && !z) || (w && !x && !y && z) || (w && !x && y && !z) || 
				(!w && x && !y && !z))
		{
			numLeft = 2;
		}
		if((w && x && y && !z) || (w && x && !y && z) || 
				(w && !x && y && z) || (!w && x && y && z))
		{
			numLeft = 1;
		}
		
		return numLeft;
	}
	
	public static boolean isDone(Card[] hand)
	{
		if(hand.length > 0)
			return false;
		else
			return true;
	}
	
	public static void printHand(Card[] x)
	{
		System.out.println("\n");
		Arrays.sort(x, Card.CardComparator);
		for(int i = 0; i < x.length; i++)
		{
			System.out.println(i + ". " + x[i].getValueAsString()+ " " + x[i].getSuitAsString());
		}
	}

	public static int numberPassed(boolean a, boolean b, boolean c, boolean d)
	{

		int count = 0;
		if(a == true)
		{
			count++;
		}
		if(b == true)
		{
			count++;
		}
		if(c == true)
		{
			count++;
		}
		if(d == true)
		{
			count++;
		}
		return count;
	}
	
	public static int[] getInput(int start, Rotation r, int remaining, Card[][] hands)
	{
		Scanner scan = new Scanner(System.in);
		int s1;
		int[] inputs = new int[4];
		if(r == Rotation.CLOCKWISE)
		{
			int numberOfInputs = 0;
			while(start < 5)
			{
				printHand(hands[start-1]);
				System.out.println("Player " + start + ", select card to play");
				s1 = scan.nextInt();
				inputs[start-1] = s1;
				numberOfInputs++;
				start++;
			}
			if(start == 5 && numberOfInputs < 4)
			{
				start = 1;
			}
			while(numberOfInputs < remaining)
			{
				printHand(hands[start-1]);
				System.out.println("Player " + start + ", select card to play");
				s1 = scan.nextInt();
				inputs[start-1] = s1;
				numberOfInputs++;
				start++;
			}
		}
		else if(r == Rotation.COUNTER)
		{
			int numberOfInputs = 0;
			while(start > 0)
			{
				printHand(hands[start-1]);
				System.out.println("Player " + start + ", select card to play");
				s1 = scan.nextInt();
				inputs[start-1] = s1;
				numberOfInputs++;
				start--;
			}
			if(start == 0 && numberOfInputs < remaining)
			{
				start = 4;
			}
			while(numberOfInputs < remaining)
			{
				printHand(hands[start-1]);
				System.out.println("Player " + start + ", select card to play");
				s1 = scan.nextInt();
				inputs[start-1] = s1;
				numberOfInputs++;
				start--;
			}
		}
			
		
		return inputs;
	}
	
	
	public static void main(String[] args) {
	      

	      boolean playAgain;       // Record user's response when user is 
	                               //   asked whether he wants to play 
	                               //   another game.
	      

	         int scoreThisGame;        // Score for one game.
	         scoreThisGame = play();   // Play the game and get the score.

	   
	   }  // end main()
	   

	   /**
	    * Lets the user play one game of HighLow, and returns the
	    * user's score in that game.  The score is the number of
	    * correct guesses that the user makes.
	    */
	   private static int play() {
		  Scanner scan = new Scanner(System.in);
	   
	      Deck deck = new Deck();  // Get a new deck of cards, and 
	                               //   store a reference to it in 
	                               //   the variable, deck.
	      
	      
	  	  boolean done1 = false, done2 = false, done3 = false, done4 = false;
	  	  ArrayList<Card> hand = new ArrayList<Card>(13);
	      Card[] hand1 = new Card[13];
	      Card[] hand2 = new Card[13];
	      Card[] hand3 = new Card[13];
	      Card[] hand4 = new Card[13];
	      Card[][] hands = {hand1, hand2, hand3, hand4};
	      
	      int hand1num = 0, hand2num = 0, hand3num = 0, hand4num = 0;
	      int one = 0, two = 0, three = 0, four = 0;
	      
	      deck.shuffle(); 
	      deck.shuffle(); 
	      deck.shuffle(); 
	      deck.shuffle(); 
	      // Shuffle the deck into a random order before
	                       //    starting the game.
	      
	   

	      int dealTo = 0;
	      while(deck.cardsLeft() != 0)
	      {
	    	  if(dealTo == 0)
	    	  {
	    		  hand1[hand1num] = deck.dealCard();
	    		  hand1num++;
	    		  dealTo++;
	    	  }
	    	  if(dealTo == 1)
	    	  {
	    		  hand2[hand2num] = deck.dealCard();
	    		  hand2num++;
	    		  dealTo++;
	    	  }
	    	  if(dealTo == 2)
	    	  {
	    		  hand3[hand3num] = deck.dealCard();
	    		  hand3num++;
	    		  dealTo++;
	    	  }
	    	  if(dealTo == 3)
	    	  {
	    		  hand4[hand4num] = deck.dealCard();
	    		  hand4num++;
	    		  dealTo = 0;
	    	  }
	      }
	      
	      
	      done1 = isDone(hand1);
	      done2 = isDone(hand2);
	      done3 = isDone(hand3);
	      done4 = isDone(hand4);
	      
	      System.out.println(remaining(done1, done2, done3, done4));
	      
	      while(remaining(done1, done2, done3, done4) > 1)
	      {
	    	  int winner = 1;
	    	  int[] inputs = getInput(winner, Rotation.CLOCKWISE, remaining(done1, done2, done3, done4), hands);

	    	  Card c1, c2, c3, c4;
		      c1 = hand1[inputs[0]];
		      c2 = hand2[inputs[1]];
		      c3 = hand3[inputs[2]];
		      c4 = hand4[inputs[3]];
		      
		      Card[] cardInputs = {c1,c2,c3,c4};
		      Arrays.sort(cardInputs, Card.CardComparator);
		      for(Card c : cardInputs)
		      {
		    	  System.out.println(c.getValueAsString() + " " + c.getSuitAsString());
		      }
		      if(cardInputs[0] == c1)
		      {
		    	  winner = 1;
		      }
		      else if(cardInputs[0] == c2)
		      {
		    	  winner = 2;
		      }
		      else if(cardInputs[0] == c3)
		      {
		    	  winner = 3;
		      }
		      else
		    	  winner = 4; 
		        
		      System.out.println(winner);
		        
		      done1 = isDone(hand1);
			  done2 = isDone(hand2);
			  done3 = isDone(hand3);
			  done4 = isDone(hand4);

		      } // end of while loop
	      
	      	      
	      
	      System.out.println();
	      System.out.println("The game is over.");

	      System.out.println();
	      
	      return 1;
	      
	   }  // end play()
	   

	} // end class HighLow
