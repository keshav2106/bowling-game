package bowling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	//function to read and check value from user
	public static int readValue() throws IOException {
		int input = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean readFlag = true;
		//loop until user has entered the right integer value
		while(readFlag) {
			try 
			{
				readFlag = false;
				input = Integer.parseInt(br.readLine()); 
				//if negative value is entered then ask to enter a positive integer.
				if(input < 0) {
					readFlag = true;
					System.out.println("Please enter only a valid positive integer between 0 and 10");		
				}
				if(input > 10) {
					readFlag = true;
					System.out.println("Please enter only a valid positive integer between 0 and 10");		
				}
			}  
			catch (NumberFormatException e)  
			{ 
				//if string or float value is entered then ask to enter a positive integer.
				readFlag = true;
				System.out.println("Please enter only a valid positive integer between 0 and 10"); 
				input = -1;
			} 
		}
		return input;
	}

	public static void main(String[] args) throws IOException  {
		System.out.println("Welcome to Bowling-Game CLI.");
		System.out.println("To calculate final score for single player, please");
		//declaring variables
		int score = 0;
		int frame = 10;
		int roll = 2;
		int roll_score = 0;
		int frame_score = 0;
		boolean strike = false;
		boolean spare = false;
		//Loop for 10 frames
		for (int i = 0; i < frame ; i ++) {
			//initialize frame score to 0
			frame_score = 0;
			// Each frame carries 2 rolls
			for (int j = 0 ; j < roll; j++) {	
				// Taking score for each roll from user
				 System.out.println("Enter frame " + String.valueOf(i+1) + ": roll " + String.valueOf(j+1) + " score");
				 roll_score = readValue();
				 //if spare flag is true add 1st roll score as bonus
				 if(spare) {
					 score = score + roll_score;
					 spare = false;
				 }
				//if strike flag is true add both roll scores as bonus
				 if (strike) {
					 score = score + roll_score;
					 if(j == 1) {
						 strike = false;
					 }
				 }
				 // set strike flag and break
				 if (roll_score == 10 && j == 0) {
					 System.out.println("Strike!");
					 strike = true;
					 frame_score = frame_score + roll_score;
					 break;
				 }
				 // add roll score to compute frame score
				 frame_score = frame_score + roll_score;
				 // set spare flag if frame score is 10
				 if (frame_score == 10 && j == 1) {
					 System.out.println("Spare!");
					 spare = true;
				 }
			}
			// add frame score to final score
			score = score + frame_score;
			//if spare in final frame then 1 additional roll
			if(i == 9 && spare) {
				System.out.println("Enter additional roll score");
				roll_score = readValue();
				score = score + roll_score;
			}
			//if strike in final frame then 2 additional roll
			if(i == 9 && strike) {
				for (int j = 0 ; j < roll; j++) {
					System.out.println("Enter "+ String.valueOf(j+1) +" additional roll score");
					roll_score = readValue();
					score = score + roll_score;
				}
			}
			// print score after each frame
			System.out.println("Score after frame " + String.valueOf(i+1) + " : " + String.valueOf(score));	
		}
		//print final score
		System.out.println("Final Score : " + String.valueOf(score));		
	}
}